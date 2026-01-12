package sk.uniba.fmph.dcs.terra_futura.effect;

import org.json.JSONObject;
import sk.uniba.fmph.dcs.terra_futura.Resource;

import java.util.List;

public class AssistanceEffect implements Effect {
    @Override
    public boolean check(List<Resource> input, List<Resource> output, int pollution) {
        return false;
    }

    @Override
    public boolean hasAssistance() {
        return true;
    }

    @Override
    public String state() {
        JSONObject json = new JSONObject();
        json.put("input", new int[] {});
        json.put("output", new String[] {"Assistance"});
        json.put("pollution", 0);
        return json.toString();
    }
}
