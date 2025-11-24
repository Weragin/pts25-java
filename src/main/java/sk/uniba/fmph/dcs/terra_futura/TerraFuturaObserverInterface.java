package sk.uniba.fmph.dcs.terra_futura;

public interface TerraFuturaObserverInterface {
    // no return value specified, expecting void
    // notify is reserved method for Object, hence the name
    void notifyPlayer(String gameState);
}
