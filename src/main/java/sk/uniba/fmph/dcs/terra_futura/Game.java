package sk.uniba.fmph.dcs.terra_futura;

import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Game implements TerraFuturaInterface {
    private GameState state;
    private final List<Integer> players;
    private int onTurn;
    private final int startingPlayer;
    private int turnNumber = FIRST_TURN;

    private static final int FIRST_TURN = 1;
    private static final int LAST_TURN = 9;

    private final Pile pileI;
    private final Pile pileII;
    private final Map<Integer, Player> playerReferences;

    private final SelectReward selectReward;

    private final GameObserver gameObserver;
    private final Map<Integer, String> playerMessage;
    private int assistingPlayer = -1;

    public Game(final List<Integer> players,
            final Map<Integer, Pair<ActivationPattern, ActivationPattern>> playerActivationPatterns,
            final Map<Integer, Pair<ScoringMethod, ScoringMethod>> playerScoringMethods, final int startingPlayer) {

        state = GameState.TakeCardNoCardDiscarded;
        this.players = new ArrayList<>(players); //
        this.startingPlayer = startingPlayer;
        this.pileI = new Pile();
        this.pileII = new Pile();
        if (!players.contains(startingPlayer)) {
            throw new IllegalArgumentException("Starting player (" + startingPlayer + ") not in the list of players");
        }

        playerReferences = new HashMap<>();
        for (int player : players) {
            if (!playerActivationPatterns.containsKey(player) || !playerScoringMethods.containsKey(player)) {
                throw new IllegalArgumentException("Player " + player + " has no scoringmethods or activationpattern");
            }

            playerReferences.put(player,
                    new Player(new Grid(), playerActivationPatterns.get(player).getLeft(),
                            playerActivationPatterns.get(player).getRight(), playerScoringMethods.get(player).getLeft(),
                            playerScoringMethods.get(player).getRight()));
        }

        gameObserver = new GameObserver();
        playerMessage = new HashMap<>();

        selectReward = new SelectReward();

        messageAllPlayers("Game started");
    }

    @Override
    public boolean discardLastCardFromDeck(int playerId, Deck deck) {
        // wrong state
        if (state != GameState.TakeCardNoCardDiscarded) {
            messageSpecificPlayer(playerId, "You are in the wrong state for this");
            return false;
        }
        // wrong player
        if (!playerOnTurnCheck(playerId)) {
            messageSpecificPlayer(playerId, "It's not your turn");
            return false;
        }

        Pile pileToDiscardFrom = getPile(deck);
        pileToDiscardFrom.removeLastCard();
        state = GameState.TakeCardCardDiscarded;

        return true;
    }

    @Override
    public boolean takeCard(int playerId, CardSource source, GridPosition gridCoordinate) {
        if (state != GameState.TakeCardNoCardDiscarded && state != GameState.TakeCardCardDiscarded) {
            messageSpecificPlayer(playerId, "You are in the wrong state for this");
            return false;
        }

        if (!playerOnTurnCheck(playerId)) {
            messageSpecificPlayer(playerId, "It's not your turn");
            return false;
        }

        Pile activePile = getPile(source.getDeck());

        Grid grid = playerReferences.get(playerId).getGrid();
        if (MoveCard.moveCard(source.getIndex(), activePile, gridCoordinate, grid)) {
            state = GameState.ActivateCard;
            messageSpecificPlayer(playerId, "Card moved successfully");
            return true;
        }
        return false;

    }

    @Override
    public void activateCard(int playerId, GridPosition card, List<Pair<Resource, GridPosition>> inputs, List<Pair<Resource, GridPosition>> outputs, List<GridPosition> pollution, int otherPlayerId, Card otherCard) {
        // activate card with assistance
        // wrong state
        if (state != GameState.ActivateCard || state != GameState.SelectScoringMethod) {
            messageSpecificPlayer(playerId, "You are in the wrong state for this");
            return;
        }

        // the assisting player should be someone else
        if (playerId == otherPlayerId) {
            messageSpecificPlayer(playerId, "The assisting player should be someone else");
            return;
        }

        // wrong player
        if (!playerOnTurnCheck(playerId)) {
            messageSpecificPlayer(playerId, "You can't do this in the last round");
            return;
        }
        Player nowPlaying = playerReferences.get(playerId);
        // card has no assistance, then we cant assist
        if (!card.hasAssistance()) {
            messageSpecificPlayer(playerId, "TO use card assistance you need to have a card with assistance");
            return;
        }
        // wrong turn
        Grid grid = nowPlaying.getGrid();
        if(!grid.canGetCard(card) || !grid.canBeActivated(card)){
            return;
        }

        Card cardFromGrid = grid.getCard(card);

        if (ProcessActionAssistance.activateCard(cardFromGrid, nowPlaying.getGrid(), otherPlayerId, otherCard, inputs, outputs, pollution)) {
            state = GameState.SelectReward;
            assistingPlayer = otherPlayerId;
            messageSpecificPlayer(playerId, "Card activated with assistance");
        }
    }

    @Override
    public void activateCard(int playerId, GridPosition card, List<Pair<Resource, GridPosition>> inputs, List<Pair<Resource, GridPosition>> outputs, List<GridPosition> pollution) {
        // activate card without assistance
        // wrong state
        if (state != GameState.ActivateCard && || state != GameState.SelectScoringMethod ) {
            messageSpecificPlayer(playerId, "You are in the wrong state for this");
            return;
        }

        // not player on turn
        if (playerOnTurnCheck(playerId)) {
            messageSpecificPlayer(playerId, "It's not your turn");
            return;
        }
        // wrong turn
        if (!turnCheck()) {
            messageSpecificPlayer(playerId, "You can't do this action after the last roun ");
            return;
        }

        Player nowPlaying = playerReferences.get(playerId);

        Grid grid = nowPlaying.getGrid();
        if (!grid.canGetCard(card) || !grid.canBeActivated(card)){
            return;
        }

        Card cardFromGrid = grid.getCard(card);

        if (ProcessAction.activateCard(cardFromGrid, nowPlaying.getGrid(), inputs, outputs, pollution)) {
            state = GameState.ActivateCard;
            messageSpecificPlayer(playerId, "Card activated successfully without assistance");
        }
    }

    @Override
    public void selectReward(int playerId, Resource resource) {

        if (state != GameState.SelectReward) {
            messageSpecificPlayer(playerId, "You are in the wrong state for this");
            return;
        }
        // the assisting player should get the reward
        if (playerId != assistingPlayer) {
            messageSpecificPlayer(playerId, "The assisting player should get the reward");
            return;
        }

        if (!selectReward.canSelectReward(playerId, resource)) {
            messageSpecificPlayer(playerId, "Can select the desired reward");
            return;
        }

        if (selectReward.selectReward(playerId, resource)) {
            state = GameState.ActivateCard;
            assistingPlayer = -1;
            messageSpecificPlayer(playerId, "Reward selected successfully");
        }

    }

    @Override
    public boolean turnFinished(int playerId) {
        if (state != GameState.ActivateCard  ) {
            messageSpecificPlayer(playerId, "You are in the wrong state for this");
            return false;
        }

        if (!playerOnTurnCheck(playerId)) {
            messageSpecificPlayer(playerId, "It's not your turn");
            return false;
        }


        // get next player,
        getNextPlayer();

        if (turnNumber > LAST_TURN) {
            state = GameState.SelectActivationPattern;
        } else {
            state = GameState.TakeCardNoCardDiscarded;
        }

        return true;

    }

    @Override
    public boolean selectActivationPattern(int playerId, int activationPattern) {
        if (state != GameState.SelectActivationPattern) {
            messageSpecificPlayer(playerId, "You are in the wrong state for this");
            return false;
        }

        if (!playerOnTurnCheck(playerId)) {
            messageSpecificPlayer(playerId, "It's not your turn");
            return false;
        }

        if(activationPattern != 1 && activationPattern != 2){
            messageSpecificPlayer(playerId, "You should choose between the two activation patterns");
            return false;
        }
        Player nowPlaying = playerReferences.get(playerId);
        if(activationPattern == 1){
            nowPlaying.getActivationPattern1().select();
            nowPlaying.getGrid().setActivationPattern(nowPlaying.getActivationPattern1().getPattern());
        }
        else{
            nowPlaying.getActivationPattern2().select();
            nowPlaying.getGrid().setActivationPattern(nowPlaying.getActivationPattern2().getPattern());
        }

        state = GameState.SelectScoringMethod;
        messageSpecificPlayer(playerId, "Activation pattern selected successfully");
        return true;
    }

    @Override
    public boolean selectScoring(int playerId, int scoringCard) {
        if (state != GameState.SelectScoringMethod) {
            messageSpecificPlayer(playerId, "You are in the wrong state for this");
            return false;
        }

        if (!playerOnTurnCheck(playerId)) {
            messageSpecificPlayer(playerId, "It's not your turn");
            return false;
        }


        if(scoringCard != 1 && scoringCard != 2){
            messageSpecificPlayer(playerId, "You should choose between the two scoring methods");
            return false;
        }
        Player nowPlaying = playerReferences.get(playerId);
        if(scoringCard == 1){
            nowPlaying.getScoringMethod1().selectThisMethodAndCalculate();
        }
        else{
            nowPlaying.getScoringMethod2().selectThisMethodAndCalculate();
        }

        getNextPlayer();
        if(playerOnTurnCheck(startingPlayer)){
            state = GameState.Finish
        }
        else{
            state = GameState.SelectActivationPattern;
        }

        return true;
    }

    private boolean getNextPlayer() {
        int playerIndex = players.indexOf(onTurn);

        onTurn = players.get((playerIndex + 1) % players.size());

        if (onTurn == startingPlayer) {
            turnNumber++;
        }

        return true;

    }

    private Pile getPile(Deck deck) {
        return (deck == Deck.I) ? pileI : pileII;
    }

    private boolean turnCheck() {
        return turnNumber >= FIRST_TURN && turnNumber <= LAST_TURN;
    }

    private boolean playerOnTurnCheck(int playerId) {
        return playerId == onTurn;
    }

    private void messageSpecificPlayer(int id, String message) {
        playerMessage.clear();
        playerMessage.put(id, message);
        gameObserver.notifyAll(playerMessage);
    }

    private void messageAllPlayers(String message) {
        playerMessage.clear();
        for (int player : players) {
            playerMessage.put(player, message);
        }
        gameObserver.notifyAll(playerMessage);
    }

}
