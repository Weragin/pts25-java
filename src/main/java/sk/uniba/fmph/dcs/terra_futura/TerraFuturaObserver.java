package sk.uniba.fmph.dcs.terra_futura;

import java.util.Map;

public interface TerraFuturaObserver {
    void notify(Map<Integer, String> gameState);
}
