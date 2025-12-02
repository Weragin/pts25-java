package sk.uniba.fmph.dcs.terra_futura;

import org.apache.commons.lang3.tuple.Pair;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Game implements TerraFuturaInterface {
    private GameState state;
    private final List<Integer> players;
    private int onTurn;
    private int startingPlayer;
    private int turnNumber = FIRST_TURN;

    private static final int FIRST_TURN = 1;
    private static final int LAST_TURN = 9;

    private final Pile pileI;
    private final Pile pileII;
    private final Map<Integer, Player> playerReferences;

    private final SelectReward selectReward;

    private int assistingPlayer = -1;

    public Game(final List<Integer> players,
                final Map<Integer, Pair<ActivationPattern,ActivationPattern>> playerActivationPatterns,
                final Map<Integer, Pair<ScoringMethod,ScoringMethod>> playerScoringMethods,
                final int startingPlayer) {
        state = GameState.TakeCardNoCardDiscarded;
        this.players = players;
        this.startingPlayer = startingPlayer;
        this.pileI = new Pile();
        this.pileII = new Pile();
        if (!players.contains(startingPlayer)) {
            throw new IllegalArgumentException("Player " + startingPlayer + " is not in the list");
        }

        playerReferences = new HashMap<>();
        for (int player : players) {
            if(!playerActivationPatterns.containsKey(player) || !playerScoringMethods.containsKey(player)) {
                throw new IllegalArgumentException("Player " + player + " has no scoringmethods or activationpattern");
            }

            playerReferences.put(player, new Player(
                    playerScoringMethods.get(player).getLeft(),
                    playerScoringMethods.get(player).getRight(),
                    playerActivationPatterns.get(player).getLeft(),
                    playerActivationPatterns.get(player).getRight())
            );
        }
        // TODO SOMETHING WITH OBSERVERS?
        selectReward = new SelectReward();
    }

    @Override
    public boolean takeCard(int playerId, CardSource source, GridPosition gridCoordinate) {
        if (state != GameState.TakeCardNoCardDiscarded && state != GameState.TakeCardCardDiscarded) {
            return false;
        }

        if (playerId != onTurn) {
            return false;
        }

        Pile activePile = (source.getDeck() == Deck.I) ?  pileI : pileII;

        Grid grid = playerReferences.get(playerId).getGrid();
        if (MoveCard.moveCard(source.getIndex(), activePile, gridCoordinate, grid)) {
            state = GameState.ActivateCard;
            return true;
        }
        return false;

    }

    @Override
    public boolean discardLastCardFromDeck(int playerId, Deck deck) {
        // wrong state
        if (state != GameState.TakeCardNoCardDiscarded) {
            return false;
        }
        // wrong player
        if (playerId != onTurn) {
            return false;
        }

        Pile pileToDiscardFrom = (deck == Deck.I) ?  pileI : pileII;
        pileToDiscardFrom.removeLastCard();
        state = GameState.TakeCardCardDiscarded;
        return true;
    }



    @Override
    public void activateCard(int playerId, Card card, List<Pair<Resource, GridPosition>> inputs, List<Pair<Resource, GridPosition>> outputs, List<GridPosition> pollution, int otherPlayerId, Card otherCard) {
        // activate card with assistance
        // wrong state
        if (state != GameState.ActivateCard && state != GameState.TakeCardCardDiscarded && state != GameState.TakeCardNoCardDiscarded) {
            return;
        }

        // wrong player
        if (playerId != onTurn) {
            return;
        }
        Player nowPlaying = playerReferences.get(playerId);
        // card has no assistance, then we cant assist
        if (!card.hasAssistance()) {
            return;
        }
        // wrong turn
        if (turnNumber < FIRST_TURN || turnNumber > LAST_TURN) {
            return;
        }

        if (ProcessActionAssistance.activateCard(card, nowPlaying.getGrid(), otherPlayerId, otherCard, inputs, outputs, pollution)) {
                state = GameState.SelectReward;
                assistingPlayer = otherPlayerId;
        }
    }

    @Override
    public void activateCard(int playerId, Card card, List<Pair<Resource, GridPosition>> inputs, List<Pair<Resource, GridPosition>> outputs, List<GridPosition> pollution) {
        // activate card without assistance
        // wrong state
        if (state != GameState.ActivateCard && state != GameState.TakeCardCardDiscarded && state != GameState.TakeCardNoCardDiscarded) {
            return;
        }

        // not player on turn
        if (playerId != onTurn) {
            return;
        }
        // wrong turn
        if (turnNumber < FIRST_TURN || turnNumber > LAST_TURN) {
            return;
        }

        Player nowPlaying = playerReferences.get(playerId);
        if (ProcessAction.activateCard(card, nowPlaying.getGrid(), inputs, outputs, pollution)) {
                state = GameState.ActivateCard;
        }
    }

    @Override
    public void selectReward(int playerId, Resource resource) {

        if (state != GameState.SelectReward) {
           return;
        }
        // the assisting player should get the reward
        if (playerId != assistingPlayer) {
            return;
        }

        if(!selectReward.canSelectReward(playerId, resource)) {
            return;
        }

        if(selectReward.selectReward(playerId, resource)){
            state = GameState.ActivateCard;
            assistingPlayer = -1;
        }


    }

    @Override
    public boolean turnFinished(int playerId) {
        if (state != GameState.SelectScoringMethod) {
            return false;
        }

        if (playerId != onTurn) {
            return false;
        }

        state = GameState.Finish;
        // get next player,
        getNextPlayer();

        if(turnNumber > LAST_TURN){
            state = GameState.SelectActivationPattern;
        }
        else{
            state = GameState.TakeCardNoCardDiscarded;
        }

        return true;

    }


    @Override
    public boolean selectActivationPattern(int playerId, Card card) {
        if (state != GameState.ActivateCard &&  state != GameState.SelectActivationPattern) {
            return false;
        }

        if (playerId != onTurn) {
            return false;
        }
        state = GameState.SelectActivationPattern;
        return true;
    }

    @Override
    public boolean selectScoring(int playerId, Card card) {
        if (state != GameState.ActivateCard && state != GameState.SelectScoringMethod) {
            return false;
        }

        if (playerId != onTurn) {
            return false;
        }
        state = GameState.SelectScoringMethod;
        return true;
    }

    private boolean getNextPlayer(){
        int playerIndex = players.indexOf(onTurn);

        onTurn = players.get((playerIndex+1) %  players.size());

        if(onTurn == startingPlayer){
            turnNumber++;
        }

        return true;

    }
}
