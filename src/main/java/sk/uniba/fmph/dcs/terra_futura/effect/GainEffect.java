package sk.uniba.fmph.dcs.terra_futura.effect;

import sk.uniba.fmph.dcs.terra_futura.Resource;

import java.util.List;

public class GainEffect implements Effect {
    /**
     * Represents a gain effect without choices, e.g. putting a single output resource on the card
     */
    private final Resource output;

    public GainEffect(Resource output) {
        this.output = output;
    }

    @Override
    public boolean check(List<Resource> input, List<Resource> output, int pollution) {
        // Since gain effects have no requirements on available resources, we do not check the contents of input
        return output.size() == 1 && output.getFirst().equals(this.output);
    }

    @Override
    public boolean hasAssistance() {
        return false;
    }

    @Override
    public String state() {
        org.json.JSONObject json = new org.json.JSONObject();
        json.put("input", new int[] {});

        return json.toString();
    }
}
