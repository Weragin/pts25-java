package sk.uniba.fmph.dcs.terra_futura.effect;

import org.json.JSONObject;
import sk.uniba.fmph.dcs.terra_futura.Resource;

import java.util.ArrayList;
import java.util.List;

public class ExchangeEffect implements Effect {
    /**
     * Represents a simple exchange effect, e.g. an effect that takes a given list of input resources and puts a list of
     * output resources on the card.
     */
    private final List<Resource> input;
    private final List<Resource> output;

    public ExchangeEffect(List<Resource> input, List<Resource> output) {
        this.input = new ArrayList<>(input);
        this.output = new ArrayList<>(output);
    }

    @Override
    public boolean check(List<Resource> input, List<Resource> output, int pollution) {
        return input.equals(this.input) && output.equals(this.output);
    }

    @Override
    public boolean hasAssistance() {
        return false;
    }

    @Override
    public String state() {
        JSONObject json = new JSONObject();
        json.put("input", input);
        json.put("output", output);
        return json.toString();
    }
}
