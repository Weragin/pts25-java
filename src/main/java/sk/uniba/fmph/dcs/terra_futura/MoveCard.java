package sk.uniba.fmph.dcs.terra_futura;

public final class MoveCard {
    private MoveCard() {
        throw new AssertionError("Cannot instantiate utility class");
    }

    static boolean moveCard(final int index, final Pile pile, final GridPosition gridCoordinate, final Grid grid) {
        if (!grid.canPutCard(gridCoordinate)) {
            return false;
        }

        Card card = pile.getCard(index);
        if (card == null) {
            throw new RuntimeException("Atleast one card missing in the visible field - shouldnt get here");
        }

        grid.putCard(gridCoordinate, card);
        pile.takeCard(index);
        return true;
    }
}
