package sk.uniba.fmph.dcs.terra_futura;

public class MoveCard {
    public static boolean moveCard(final int index, final Pile pile, final GridPosition gridCoordinate, final Grid grid) {

        if (!grid.canPutCard(gridCoordinate)) {
           return false;
        }
        // we get index and pile from CardSource which have built in restrictions
        // for the index range I don't know if it is needed here to have a try catch block
        // todo: might be good to put it inside a try/catch block
        // the card is free to place
        Card card = pile.getCard(index);
        if (card == null) {
            return false; // no card left there
        }
        grid.putCard(gridCoordinate, card);
        pile.takeCard(index);
        return true;
    }
}
