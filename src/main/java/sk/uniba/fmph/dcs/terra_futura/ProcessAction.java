package sk.uniba.fmph.dcs.terra_futura;

import org.apache.commons.lang3.tuple.Pair;
import java.util.List;

public class ProcessAction {
    public static boolean activateCard(final Card card, final Grid grid, List<Pair<Resource,GridPosition>> inputs,List<Pair<Resource,GridPosition>> outputs, List<GridPosition> pollution){

        // CHECK IF ALL CARDS ARE VALID AND CAN PUT/GET THE DESIRED RESOURCES

        if(card == null){
            return false;
        }

        // checks if all the earned resources are placed on the calling card
        GridPosition gp = null;
        for(int x = -2; x < 3; x++){
            for(int y = -2; y < 3; y++){
                if(grid.getCard(new GridPosition(x, y)) == card) {
                    gp = new GridPosition(x, y);
                }
            }
        }
        for (Pair<Resource,GridPosition> out : outputs) {
            if(!out.getRight().equals(gp)) {
                return false;
            }
        }


        return ProcessActionBase.activateCard(card,grid,inputs,outputs,pollution,false);

    }
}
