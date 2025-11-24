package sk.uniba.fmph.dcs.terra_futura;

import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

public final class ProcessAction {
    private ProcessAction() {
    }
    public static boolean activateCard(final Card card, final Grid grid, final List<Pair<Resource, GridPosition>> inputs, final List<Pair<Resource, GridPosition>> outputs, final List<GridPosition> pollution) {
        throw new RuntimeException("Not implemented");
    }
}
