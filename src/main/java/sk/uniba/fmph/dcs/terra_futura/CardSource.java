package sk.uniba.fmph.dcs.terra_futura;

public final class CardSource {
    private final Deck deck;
    private final int index;

    private static final int MIN_INDEX = 1;
    private static final int MAX_INDEX = 4;

    public CardSource(final Deck deck, final int index) {
        if (index < MIN_INDEX || index > MAX_INDEX) {
            throw new IllegalArgumentException("index out of range");
        }
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
