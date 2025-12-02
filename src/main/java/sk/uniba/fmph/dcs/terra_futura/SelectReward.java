package sk.uniba.fmph.dcs.terra_futura;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SelectReward {
    private Set<Integer> player;
    private List<Resource> selection;

    public SelectReward() {
        this.player = new HashSet<>();
        this.selection = new ArrayList<>();
    }
    /**
     * @param playerId the id of the player
     * @param card the card granting the reward
     * @param reward the reward you want to choose
     */
    public void setReward(final int playerId, final Card card, final Resource reward) {
        throw new RuntimeException("Not implemented");
    }
    /**
     * @param reward the reward you want to check if available
     * @return boolean if you can select it
     */
    public boolean canSelectReward(final int playerId, final Resource reward) {
        throw new RuntimeException("Not implemented");
    }

    /**
     * @param reward the reward to be chosen
     * @return
     */
    public boolean selectReward(final int playerId, final Resource reward) {
        throw new RuntimeException("Not implemented");
    }

    /**
     * @return returns the state of the class
     */
    public String state() {
        throw new RuntimeException("Not implemented");
    }
}
