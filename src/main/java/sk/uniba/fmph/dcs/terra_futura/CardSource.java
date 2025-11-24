package sk.uniba.fmph.dcs.terra_futura;

public final class CardSource {
    private Deck deck;
    private int index;

    public CardSource(final Deck deck, final int index) {
        this.deck = deck;
        this.index = index;
    }

    public Deck getDeck() {
        return deck;
    }

    public void setDeck(final Deck deck) {
        this.deck = deck;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(final int index) {
        this.index = index;
    }


}
