package sk.uniba.fmph.dcs.terra_futura;

import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProcessAction {
    public boolean activateCard(final Card card, final Grid grid, List<Pair<Resource,GridPosition>> inputs,List<Pair<Resource,GridPosition>> outputs, List<GridPosition> pollution){

        // CHECK IF ALL CARDS ARE VALID AND CAN PUT/GET THE DESIRED RESOURCES

        if(card == null){
            return false;
        }

        // verify neccesary things based on the rules


        return ProcessActionBase.activateCard(card,grid,inputs,outputs,pollution,false);

    }
}
