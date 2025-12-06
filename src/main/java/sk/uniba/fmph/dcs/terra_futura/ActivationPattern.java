package sk.uniba.fmph.dcs.terra_futura;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Collection;



public final class ActivationPattern {
    private final ArrayList<SimpleEntry<Integer, Integer>> pattern;
    private boolean selected;
    private ActivateGrid grid;

    public ActivationPattern(final ActivateGrid grid, final Collection<SimpleEntry<Integer, Integer>> pattern) {
        this.grid = grid;
        this.pattern = new ArrayList<>(pattern);  // copy the pattern
        this.selected = false;
    }

    public void select() {
        if (this.selected) {
            throw new IllegalStateException("Pattern already selected");
        }
        this.grid.setActivationPattern(this.pattern);
        this.selected = true;
    }


    public boolean isSelected() {
        return this.selected;
    }


    public String state() {
        JSONArray patternList = new JSONArray();
        for (SimpleEntry<Integer, Integer> entry: pattern) {
            JSONObject pair = new JSONObject();
            pair.put("x", entry.getKey());
            pair.put("y", entry.getValue());
            patternList.put(pair);
        }
        JSONObject result = new JSONObject();
        result.put("selected", this.selected);
        result.put("activations", patternList);
        return result.toString();
    }
}
