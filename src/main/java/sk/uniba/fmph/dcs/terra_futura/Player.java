package sk.uniba.fmph.dcs.terra_futura;


public final class Player {
    private final ScoringMethod scoringMethod1;
    private final ScoringMethod scoringMethod2;
    private final ActivationPattern activationPattern1;
    private final ActivationPattern activationPattern2;
    private final Grid grid;

    public Player(ScoringMethod scoringMethod1, ScoringMethod scoringMethod2,  ActivationPattern activationPattern1, ActivationPattern activationPattern2) {
        this.scoringMethod1 = scoringMethod1;
        this.scoringMethod2 = scoringMethod2;
        this.activationPattern1 = activationPattern1;
        this.activationPattern2 = activationPattern2;
        this.grid = new Grid();
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
}
