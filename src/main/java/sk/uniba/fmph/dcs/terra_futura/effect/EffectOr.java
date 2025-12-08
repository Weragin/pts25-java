package sk.uniba.fmph.dcs.terra_futura.effect;

import org.json.JSONArray;
import sk.uniba.fmph.dcs.terra_futura.Resource;

import java.util.ArrayList;
import java.util.List;

public class EffectOr implements Effect {
    /**
     * Represents a choice exchange effect, e.g. the player chooses one effect from a list of effects
     */
    private final List<Effect> effectList;

    public EffectOr(){
        this.effectList = new ArrayList<>();
    }

    public EffectOr(List<Effect> effectList) {
        this.effectList = new ArrayList<>(effectList);
    }

    public void addEffect(Effect effect) {
        effectList.add(effect);
    }

    @Override
    public boolean check(List<Resource> input, List<Resource> output, int pollution) {
        for (Effect effect : effectList) {
            if (effect.check(input, output, pollution)) return true;
        }
        return false;
    }

    @Override
    public boolean hasAssistance() {
        for (Effect effect : effectList) {
            if (effect.hasAssistance()) return true;
        }
        return false;
    }

    @Override
    public String state() {
        JSONArray json = new JSONArray();
        for (Effect effect : effectList) {
            json.put(effect.state());
        }
        return json.toString();
    }
}
