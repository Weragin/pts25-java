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
    private int turnNumber = 0;

    private final Pile pileI;
    private final Pile pileII;
    private final Map<Integer, Player> playerReferences;

    public Game(final List<Integer> players, final int startingPlayer) {
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
            playerReferences.put(player, new Player()); // todo: add activation pattern grid and scoring method
        }
    }

    @Override
    public boolean takeCard(int playerId, CardSource source, GridPosition gridCoordinate) {
        if (state != GameState.TakeCardNoCardDiscarded && state != GameState.TakeCardCardDiscarded) {
            return false;
        }

        // todo: if could take card return 0 and stay in the same state
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
        if (state != GameState.TakeCardNoCardDiscarded) {
            return false;
        }

        if (playerId != onTurn) {
            return false;
        }

        // REVIEW: does this breaks the single responsibility prinicple?
        Pile pileToDiscardFrom = (deck == Deck.I) ?  pileI : pileII;
        pileToDiscardFrom.removeLastCard();
        state = GameState.TakeCardCardDiscarded;
        return true;
    }

    @Override
    public void activateCard(int playerId, Card card, List<Pair<Resource, GridPosition>> inputs, List<Pair<Resource, GridPosition>> outputs, List<GridPosition> pollution, int otherPlayerId, GridPosition otherCard) {
        if (playerId != onTurn) {
            return false;
        }
    }

    @Override
    public void selectReward(int playerId, Resource resource) {
        if (state != GameState.ActivateCard) {
           return;
        }

        if (playerId != onTurn) {
            return;
        }

        state = GameState.SelectReward;
        // Todo: Conenct to select reward

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
}
