package sk.uniba.fmph.dcs.terra_futura;

import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

public class ProcessActionAssistance {
    public boolean activateCard(final Card card, final Grid grid, int assisingPlayer, Card assisingCard, List<Pair<Resource,GridPosition>> inputs, List<Pair<Resource,GridPosition>> outputs, List<GridPosition> pollution){
        // check neccesary things based on the rules
        if(card == null ||  assisingCard == null){
            return false;
        }

        if(!card.hasAssistance()){
            return false;
        }

        return ProcessActionBase.activateCard(assisingCard,grid,inputs,outputs,pollution,true);
    }
}
