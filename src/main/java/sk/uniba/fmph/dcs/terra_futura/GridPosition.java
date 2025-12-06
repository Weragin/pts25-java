package sk.uniba.fmph.dcs.terra_futura;

import java.util.Objects;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if ((o == null) || this.getClass() != o.getClass()) {
            return false;
        }
        GridPosition that = (GridPosition) o;
        return this.x == that.x && this.y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x,y);
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
