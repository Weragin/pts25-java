package sk.uniba.fmph.dcs.terra_futura;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import java.util.List;

@SuppressWarnings("checkstyle:RegexpSingleline")
public class Grid {

    public Grid() {
        throw new RuntimeException("Not implemented");

    }

    public Card getCard(GridPosition coordinate) {
        throw new RuntimeException("Not implemented");
    }

    public boolean canPutCard(GridPosition coordinate) {
        throw new RuntimeException("Not implemented");
    }

    public void putCard(GridPosition coordinate, Card card) {
        throw new RuntimeException("Not implemented");

    }


    public boolean canBeActivated(GridPosition coordinate) {
        throw new RuntimeException("Not implemented");
    }

    public void setActivated(GridPosition coordinate) {
        throw new RuntimeException("Not implemented");
    }

    public void setActivationPattern(List<GridPosition> pattern) {
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
