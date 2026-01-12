package sk.uniba.fmph.dcs.terra_futura.effect;

import org.json.JSONObject;
import sk.uniba.fmph.dcs.terra_futura.Resource;

import java.util.Collections;
import java.util.List;

public class GainEffect implements Effect {
    /**
     * Represents a gain effect without choices, i.e. putting a single output resource on the card
     */
    private final Resource output;

    public GainEffect(Resource output) {
        this.output = output;
    }

    @Override
    public boolean check(List<Resource> input, List<Resource> output, int pollution) {
        return pollution == 0 &&
                input.isEmpty() &&
                output.size() == 1 &&
                output.getFirst() == this.output;
    }

    @Override
    public boolean hasAssistance() {
        return false;
    }

    @Override
    public String state() {
        JSONObject json = new JSONObject();
        json.put("input", new int[] {});
        json.put("output", new Resource[] {output});
        json.put("pollution", 0);
        return json.toString();
    }
}
