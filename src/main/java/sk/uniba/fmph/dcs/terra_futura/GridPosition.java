package sk.uniba.fmph.dcs.terra_futura;

public class GridPosition {
    private final int x;
    private final int y;

    public GridPosition(int x, int y) {
        if (notInRange(x) || notInRange(y)) {
            throw new IllegalArgumentException("x and y must be within -2 and 2");
        }
        this.x = x;
        this.y = y;
    }

    private boolean notInRange(int value) {
        return value < -2 || value > 2;
    }

    public int getX() { return x; }

    public int getY() { return y; }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
