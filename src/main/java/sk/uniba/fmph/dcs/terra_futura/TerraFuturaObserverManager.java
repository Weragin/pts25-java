package sk.uniba.fmph.dcs.terra_futura;

import java.util.*;

public interface TerraFuturaObserverManager<T> {
    void addObserver(T observer);

    void removeObserver(T observer);

    void notifyAll(Map<Integer, String> gameState);
}
