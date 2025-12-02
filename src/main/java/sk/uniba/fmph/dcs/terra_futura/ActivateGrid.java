package sk.uniba.fmph.dcs.terra_futura;

import java.util.AbstractMap.SimpleEntry;
import java.util.Collection;
public interface ActivateGrid {
    void setActivationPattern(Collection<SimpleEntry<Integer, Integer>> pattern);
}
