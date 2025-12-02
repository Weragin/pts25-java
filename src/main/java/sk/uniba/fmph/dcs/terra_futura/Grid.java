package sk.uniba.fmph.dcs.terra_futura;

public interface Grid {
    Card getCard(GridPosition coordinate);
    boolean canBeActivated(GridPosition coordinate);
    void putCard(GridPosition coordinate, Card card);
    void setActivated(GridPosition coordinates);
}
