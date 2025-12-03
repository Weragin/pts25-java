package sk.uniba.fmph.dcs.terra_futura;


public final class Player {
    private ScoringMethod scoringMethod1;
    private ScoringMethod scoringMethod2;
    private ActivationPattern activationPattern1;
    private ActivationPattern activationPattern2;
    private Grid grid;

    public Player() {
        this.scoringMethod1 = new ScoringMethod();
        this.scoringMethod2 = new ScoringMethod();
        /* can use to do because it hurts the linter: add correct arguments for the activation patterns that this should contain
        this.activationPattern1 = new ActivationPattern();
        this.activationPattern2 = new ActivationPattern();
        */
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
