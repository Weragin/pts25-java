package sk.uniba.fmph.dcs.terra_futura;

import java.util.*;

public interface TerraFuturaObserver {
    void notify(Map<Integer, String> gameState);
}
