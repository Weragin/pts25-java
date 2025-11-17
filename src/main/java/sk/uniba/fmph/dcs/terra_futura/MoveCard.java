package sk.uniba.fmph.dcs.terra_futura;

public class MoveCard {
    public static boolean moveCard(final int index, final Pile pile, final GridPosition gridCoordinate, final Grid grid) {

        if (!grid.canPutCard(gridCoordinate)) {
           return false;
        }

        // todo: might be good to put it inside a try/catch block
        // the card is free to place
        Card card = pile.getCard(index);
        grid.putCard(gridCoordinate, card);
        pile.takeCard(index);
        return true;
    }
}
