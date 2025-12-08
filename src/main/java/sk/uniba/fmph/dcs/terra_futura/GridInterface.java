package sk.uniba.fmph.dcs.terra_futura;

public interface GridInterface {
    CardInterface getCard(GridPosition coordinate);
    boolean canBeActivated(GridPosition coordinate);
    void putCard(GridPosition coordinate, CardInterface card);
    boolean canPutCard(GridPosition coordinate);
    void setActivated(GridPosition coordinates);
}
