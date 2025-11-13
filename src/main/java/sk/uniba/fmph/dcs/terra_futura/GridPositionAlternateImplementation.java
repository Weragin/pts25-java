package sk.uniba.fmph.dcs.terra_futura;

public class GridPositionAlternateImplementation {
    private Coordinate x;
    private Coordinate y;

    public GridPositionAlternateImplementation(final Coordinate x, final Coordinate y) {
        this.x = x;
        this.y = y;
    }

    public Coordinate getX() {
        return this.x;
    }

    public void setX(final Coordinate x) {
        this.x = x;
    }

    public Coordinate getY() {
        return this.y;
    }

    public void setY(final Coordinate y) {
        this.y = y;
    }

    public enum Coordinate {
        NEG_TWO(-2),
        NEG_ONE(-1),
        ZERO(0),
        ONE(1),
        TWO(2);

        private final int value;

        Coordinate(final int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
}
