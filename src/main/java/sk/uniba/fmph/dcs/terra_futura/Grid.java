package sk.uniba.fmph.dcs.terra_futura;

public class Grid implements GridInterface {

    @Override
    public CardInterface getCard(GridPosition coordinate) {
        throw new UnsupportedOperationException("Kazdy sam implementuje.");
    }

    @Override
    public boolean canBeActivated(GridPosition coordinate) {
        throw new UnsupportedOperationException("Kazdy sam implementuje.");
    }

    @Override
    public void putCard(GridPosition coordinate, CardInterface card) {
        throw new UnsupportedOperationException("Kazdy sam implementuje.");
    }

    @Override
    public void setActivated(GridPosition coordinates) {
        throw new UnsupportedOperationException("Kazdy sam implementuje.");
    }
}
