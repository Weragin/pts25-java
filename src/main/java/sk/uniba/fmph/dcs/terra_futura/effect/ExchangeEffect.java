package sk.uniba.fmph.dcs.terra_futura.effect;

import org.json.JSONObject;
import sk.uniba.fmph.dcs.terra_futura.Resource;

import java.util.*;

/**
 * Represents a simple exchange effect, i.e. an effect that takes a given list of input resources and puts a list of
 * output resources on the card.
 */
public class ExchangeEffect implements Effect {
    private final List<Resource> input;
    private final List<Resource> output;
    private final int pollution;

    public ExchangeEffect(List<Resource> input, List<Resource> output) {
        this(input, output, 0);
    }

    public ExchangeEffect(List<Resource> input, List<Resource> output, int pollution) {
        this.input = new ArrayList<>(input);
        this.output = new ArrayList<>(output);
        this.pollution = pollution;
    }

    @Override
    public boolean check(List<Resource> input, List<Resource> output, int pollution) {
        // check added pollution
        if (pollution != this.pollution) {
            return false;
        }
        for (Resource resource : Resource.values()) {
            // check if this effect's inputs are not present in the given input
            if (Collections.frequency(input, resource) != Collections.frequency(this.input, resource)){
                return false;
            }
            // check if output is incorrect after using this effect given inputs
            if (Collections.frequency(output, resource) != Collections.frequency(this.output, resource)
            ) {
                return false;
            }
        }

        return true;
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
        json.put("pollution", pollution);
        return json.toString();
    }
}
