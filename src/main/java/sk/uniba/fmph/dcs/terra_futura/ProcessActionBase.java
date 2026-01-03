package sk.uniba.fmph.dcs.terra_futura;

import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProcessActionBase {

    private static void putAllResources(Grid grid, Map<GridPosition, List<Resource>> gridResourcesIn,
            Map<GridPosition, List<Resource>> gridResourcesOut,
            Map<GridPosition, List<Resource>> gridResourcesPollution) {
        // PUT RESOURCES, they are after the checks
        for (GridPosition gridPosition : gridResourcesIn.keySet()) {
            grid.getCard(gridPosition).getResources(gridResourcesIn.get(gridPosition));
        }

        for (GridPosition gridPosition : gridResourcesOut.keySet()) {
            grid.getCard(gridPosition).putResources(gridResourcesOut.get(gridPosition));
        }

        for (GridPosition gridPosition : gridResourcesPollution.keySet()) {
            grid.getCard(gridPosition).putResources(gridResourcesPollution.get(gridPosition));
        }
    }

    private static boolean canPutResources(Grid grid, Map<GridPosition, List<Resource>> gridResourcesOut,
            Map<GridPosition, List<Resource>> gridResourcesPollution) {

        for (GridPosition gridPosition : gridResourcesOut.keySet()) {
            Card gridCard = grid.getCard(gridPosition);
            if (gridCard == null) {
                return false;
            }
            if (!gridCard.canPutResources(gridResourcesOut.get(gridPosition))) {
                return false;
            }
        }

        for (GridPosition gridPosition : gridResourcesPollution.keySet()) {
            Card gridCard = grid.getCard(gridPosition);
            if (gridCard == null || !gridCard.canPutResources(gridResourcesPollution.get(gridPosition))) {
                return false;
            }
        }

        return true;
    }

    private static boolean canGetResources(Grid grid, Map<GridPosition, List<Resource>> gridResourcesIn) {
        for (GridPosition gridPosition : gridResourcesIn.keySet()) {
            Card gridCard = grid.getCard(gridPosition);
            if (gridCard == null) {
                return false;
            }
            if (!gridCard.canTakeResources(gridResourcesIn.get(gridPosition))) {
                return false;
            }
        }
        return true;
    }

    private static List<Resource> convertToResources(List<Pair<Resource, GridPosition>> listOfPairs) {
        List<Resource> resources = new ArrayList<>();
        for (Pair<Resource, GridPosition> pair : listOfPairs) {
            resources.add(pair.getLeft());
        }
        return resources;
    }

    public static boolean activateCard(final Card card, final Grid grid, List<Pair<Resource, GridPosition>> inputs,
            List<Pair<Resource, GridPosition>> outputs, List<GridPosition> pollution, boolean assistanceCall) {

        // CHECK IF ALL CARDS ARE VALID AND CAN PUT/GET THE DESIRED RESOURCES

        if (card == null) {
            return false;
        }

        // check if all cards have enough resources
        Map<GridPosition, List<Resource>> gridResourcesIn = new HashMap<GridPosition, List<Resource>>();
        Map<GridPosition, List<Resource>> gridResourcesOut = new HashMap<GridPosition, List<Resource>>();
        Map<GridPosition, List<Resource>> gridResourcesPollution = new HashMap<GridPosition, List<Resource>>();
        // count resources on the grid positions
        for (Pair<Resource, GridPosition> input : inputs) {
            if (!gridResourcesIn.containsKey(input.getRight())) {
                gridResourcesIn.put(input.getRight(), new ArrayList<>());
            }
            gridResourcesIn.get(input.getRight()).add(input.getLeft());
        }

        for (Pair<Resource, GridPosition> output : outputs) {
            if (!gridResourcesOut.containsKey(output.getRight())) {
                gridResourcesOut.put(output.getRight(), new ArrayList<>());
            }
            gridResourcesOut.get(output.getRight()).add(output.getLeft());
        }

        for (GridPosition gridPosition : pollution) {
            if (!gridResourcesPollution.containsKey(gridPosition)) {
                gridResourcesPollution.put(gridPosition, new ArrayList<>());
            }
            gridResourcesPollution.get(gridPosition).add(Resource.Pollution);
        }

        if (assistanceCall && !card.hasAssistance()) {
            return false;
        }

        // check if all resources and places all available
        if (!canGetResources(grid, gridResourcesIn)
                || !canPutResources(grid, gridResourcesOut, gridResourcesPollution)) {
            return false;
        }

        if (card.checkUpper(convertToResources(inputs), convertToResources(outputs), pollution.size())
                || card.checkLower(convertToResources(inputs), convertToResources(outputs), pollution.size())) {
            putAllResources(grid, gridResourcesIn, gridResourcesOut, gridResourcesPollution);
        }

        return true;
    }
}
