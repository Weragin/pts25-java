package sk.uniba.fmph.dcs.terra_futura;

public final class GridPosition {
    private final int x;
    private final int y;

    private final int bound = 2;

    public GridPosition(final int x, final int y) {
        if (notInRange(x) || notInRange(y)) {
            throw new IllegalArgumentException("x and y must be within -2 and 2");
        }
        this.x = x;
        this.y = y;
    }

    private boolean notInRange(final int value) {
        return value < -bound || value > bound;
    }

    public int getX() {
        return x;
    }


    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
