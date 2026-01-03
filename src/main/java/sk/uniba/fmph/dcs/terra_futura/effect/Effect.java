package sk.uniba.fmph.dcs.terra_futura.effect;

import sk.uniba.fmph.dcs.terra_futura.Resource;

import java.util.List;

public interface Effect {
    /**
     * Returns whether an effect may be applied given a list of input resources and a list of expected output resources
     * @param input The list of available resources
     * @param output The list of resources expected after the effect takes place
     * @param pollution The number of pollution ? idk, pollution is still a resource ::shrug::
     * @return whether applying this effect on the given input may result in the given output
     */
    boolean check(List<Resource> input, List<Resource> output, int pollution);

    boolean hasAssistance();

    /**
     * Gives the state of an effect, which is always either a json  object with a (possibly empty) input list
     * and a (non-empty) output list, or a json list of such objects.
     * @return string representing the state's json object
     */
    String state();
}
