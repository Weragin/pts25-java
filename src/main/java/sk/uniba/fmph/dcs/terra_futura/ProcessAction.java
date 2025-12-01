package sk.uniba.fmph.dcs.terra_futura;

import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProcessAction {
    public boolean activateCard(final Card card, final Grid grid, List<Pair<Resource,GridPosition>> inputs,List<Pair<Resource,GridPosition>> outputs, List<GridPosition> pollution){
        // check if all cards have enough resources
        Map<GridPosition, List<Resource>> gridResourcesIn = new HashMap<GridPosition, List<Resource>>();
        Map<GridPosition, List<Resource>> gridResourcesOut = new HashMap<GridPosition, List<Resource>>();
        for(Pair<Resource,GridPosition> input: inputs){
            if(!gridResourcesIn.containsKey(input.getRight())){
                gridResourcesIn.put(input.getRight(),new ArrayList<>());
            }
            gridResourcesIn.get(input.getRight()).add(input.getLeft());
        }
        // count resources on the cards
        for(Pair<Resource,GridPosition> output: outputs){
            if(!gridResourcesOut.containsKey(output.getRight())){
                gridResourcesOut.put(output.getRight(),new ArrayList<>());
            }
            gridResourcesOut.get(output.getRight()).add(output.getLeft());
        }

        // check if can get all resources
        for(GridPosition gridPosition: gridResourcesIn.keySet()){
            Card gridCard = grid.getCard(gridPosition);
            if(gridCard == null){
                return false;
            }
            if(!gridCard.canGetResources(gridResourcesIn.get(gridPosition))){
                return false;
            }
        }
        // can put all resources
        for(GridPosition gridPosition: gridResourcesOut.keySet()){
            Card gridCard = grid.getCard(gridPosition);
            if(gridCard == null){
                return false;
            }
            if(!gridCard.canPutResources(gridResourcesOut.get(gridPosition))){
                return false;
            }
        }

        for(GridPosition gridPosition: pollution){
            Card gridCard = grid.getCard(gridPosition);
            if(gridCard == null){
                return false;
            }
        }

        return true;

    }
}
