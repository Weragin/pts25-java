package sk.uniba.fmph.dcs.terra_futura;

import java.util.List;

public class SelectReward {
    private int player;
    private List<Resource> selection;

    public SelectReward(final int player, final List<Resource> selection) {
        this.player = player;
        this.selection = selection;
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
    public boolean canSelectReward(final Resource reward) {
        throw new RuntimeException("Not implemented");
    }

    /**
     * @param reward the reward to be chosen
     */
    public void selectReward(final Resource reward) {
        throw new RuntimeException("Not implemented");
    }

    /**
     * @return returns the state of the class
     */
    public String state() {
        throw new RuntimeException("Not implemented");
    }
}
