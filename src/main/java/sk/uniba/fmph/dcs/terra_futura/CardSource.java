package sk.uniba.fmph.dcs.terra_futura;

public final class CardSource {
    private final Deck deck;
    private final int index;

    public CardSource(final Deck deck, final int index) {
        this.deck = deck;
        this.index = index;
    }

    public Deck getDeck() {
        return deck;
    }



    public int getIndex() {
        return index;
    }



}
