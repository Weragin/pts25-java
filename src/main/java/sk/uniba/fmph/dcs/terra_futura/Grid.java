package sk.uniba.fmph.dcs.terra_futura;

import java.util.Optional;

public class Grid {
    public Grid() {
        // todo: implement this, it is just aplaceholder so we dont get linter error
        throw new RuntimeException("Not implemented");
    }
    public Optional<Card> getCard(GridPosition gridPosition) {
        return Optional.empty();
    }

    //public canPutCard(coordinate: GridPosition): bool

    //public putCard(coordinate: GridPosition, card: Card)

    public boolean canBeActivated(GridPosition gridPosition) {
        return true;
    }

    //public setActivated(coordinate: GridPosition)

    //public setActivationPattern(pattern: List[GridPosition])

    //public endTurn()

    public String state() {
        return "";
    }
}
