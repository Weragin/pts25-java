package sk.uniba.fmph.dcs.terra_futura;

import java.util.List;

@SuppressWarnings("checkstyle:RegexpSingleline")
public class Grid {
    public Grid() {
        // todo: implement this, it is just aplaceholder so we dont get linter error
        throw new RuntimeException("Not implemented");
    }

    public final Card getCard(final GridPosition coordinate) {
        throw new RuntimeException("Not implemented");
    }
    public final boolean canPutCard(final GridPosition coordinate) {
        throw new RuntimeException("Not implemented");
    }
    public final void putCard(final GridPosition coordinate, final Card card) {
        throw new RuntimeException("Not implemented");
    }
    public final boolean canBeActivated(final GridPosition coordinate) {
        throw new RuntimeException("Not implemented");
    }
    public final void setActivated(final GridPosition coordinate) {
        throw new RuntimeException("Not implemented");
    }
    public final void setActivationPattern(final List<GridPosition> pattern) {
        throw new RuntimeException("Not implemented");
    }
    public final void endTurn() {
        throw new RuntimeException("Not implemented");
    }
    public final GameState state() {
        throw new RuntimeException("Not implemented");
    }
}
