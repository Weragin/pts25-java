package sk.uniba.fmph.dcs.terra_futura;

public class GridPositionAlternateImplementation {
    public Coordinate x;
    public Coordinate y;

    public enum Coordinate {
        NEG_TWO(-2),
        NEG_ONE(-1),
        ZERO(0),
        ONE(1),
        TWO(2);

        private final int value;

        Coordinate(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
}
