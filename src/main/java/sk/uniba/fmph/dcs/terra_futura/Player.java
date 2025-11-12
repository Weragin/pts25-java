package sk.uniba.fmph.dcs.terra_futura;


public final class Player {
    public ScoringMethod scoringMethod1;
    public ScoringMethod scoringMethod2;
    public ActivationPattern activationPattern1;
    public ActivationPattern activationPattern2;
    public Grid grid;

    public Player() {
        this.scoringMethod1 = new ScoringMethod();
        this.scoringMethod2 = new ScoringMethod();
        /* TODO: add correct arguments for the activation patterns that this should contain
        this.activationPattern1 = new ActivationPattern();
        this.activationPattern2 = new ActivationPattern();
        */
        this.grid = new Grid();
    }
}
