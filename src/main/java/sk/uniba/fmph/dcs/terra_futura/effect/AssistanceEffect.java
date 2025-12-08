package sk.uniba.fmph.dcs.terra_futura.effect;

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
        return "AssistanceEffect";
    }
}
