package sk.uniba.fmph.dcs.terra_futura;

// this is really bad, but since it is not in a stl, we have to stick to these or create our own
import org.apache.commons.lang3.tuple.Pair;


import java.util.List;

public interface TerraFuturaInterface {
    boolean takeCard(int playerId, CardSource source, GridPosition gridCoordinate);
    boolean discardLastCardFromDeck(int playerId, Deck deck);
    void activateCard(int playerId, Card card, List<Pair<Resource, GridPosition>> inputs, List<Pair<Resource, GridPosition>> outputs, List<GridPosition> pollution, int otherPlayerId, GridPosition otherCard);
    void selectReward(int playerId, Resource resource);
    boolean turnFinished(int playerId);
    boolean selectActivationPattern(int playerId, Card card);
    boolean selectScoring(int playerId, Card card);
}
