package sk.uniba.fmph.dcs.terra_futura;

import java.util.Map;

public final class Player implements TerraFuturaObserver {
    private ScoringMethod scoringMethod1;
    private ScoringMethod scoringMethod2;
    private ActivationPattern activationPattern1;
    private ActivationPattern activationPattern2;
    private Grid grid;

    public Player(Grid grid, ActivationPattern activationPattern1, ActivationPattern activationPattern2,
            ScoringMethod scoringMethod1, ScoringMethod scoringMethod2) {
        this.grid = grid;
        this.activationPattern1 = activationPattern1;
        this.activationPattern2 = activationPattern2;
        this.scoringMethod1 = scoringMethod1;
        this.scoringMethod2 = scoringMethod2;
    }

    public ScoringMethod getScoringMethod1() {
        return this.scoringMethod1;
    }

    public ScoringMethod getScoringMethod2() {
        return this.scoringMethod2;
    }

    public ActivationPattern getActivationPattern1() {
        return this.activationPattern1;
    }

    public ActivationPattern getActivationPattern2() {
        return this.activationPattern2;
    }

    public Grid getGrid() {
        return this.grid;
    }

    @Override
    public void notify(Map<Integer, String> gameState) {

    }
}
