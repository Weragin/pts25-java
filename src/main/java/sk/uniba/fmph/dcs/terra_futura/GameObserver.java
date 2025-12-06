package sk.uniba.fmph.dcs.terra_futura;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GameObserver implements TerraFuturaObserverManager<Player> {
    private final List<Player> observers;

    public GameObserver() {
        this.observers = new ArrayList<>();
    }

    public GameObserver(List<Player> observers) {
        if (observers == null)
            observers = new ArrayList<>();
        this.observers = observers;
    }

    @Override
    public void addObserver(Player player) {
        this.observers.add(player);
    }

    @Override
    public void removeObserver(Player player) {
        this.observers.remove(player);
    }

    @Override
    public void notifyAll(Map<Integer, String> gameState) {
        for (Player player : observers) {
            player.notify(gameState);
        }
    }
}
