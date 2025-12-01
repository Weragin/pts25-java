package sk.uniba.fmph.dcs.terra_futura;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public void endTurn() {
        //
        throw new RuntimeException("Not implemented");
    }

    public String state() {
        throw new RuntimeException("Not implemented");
    }


}
