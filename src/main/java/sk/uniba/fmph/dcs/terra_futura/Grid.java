package sk.uniba.fmph.dcs.terra_futura;

public class Grid {

    public Card getCard(GridPosition coordinate) {
        throw new UnsupportedOperationException("Kazdy sam implementuje.");
    }

    public boolean canBeActivated(GridPosition coordinate) {
        throw new UnsupportedOperationException("Kazdy sam implementuje.");
    }

    public boolean canPutCard(GridPosition coordinate) {
        throw new UnsupportedOperationException("Kazdy sam implementuje.");
    }

    public void putCard(GridPosition coordinate, CardInterface card) {
        throw new UnsupportedOperationException("Kazdy sam implementuje.");
    }

    public void setActivated(GridPosition coordinates) {
        throw new UnsupportedOperationException("Kazdy sam implementuje.");
    }
}
