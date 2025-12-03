package sk.uniba.fmph.dcs.terra_futura;

import org.apache.commons.lang3.tuple.Pair;
import java.util.List;

public class ProcessActionAssistance {
    public boolean activateCard(final Card card, final Grid grid, int assistingPlayer, Card assistingCard, List<Pair<Resource,GridPosition>> inputs, List<Pair<Resource,GridPosition>> outputs, List<GridPosition> pollution){
        // check neccesary things based on the rules
        if(card == null ||  assistingCard == null){
            return false;
        }

        if(!card.hasAssistance()){
            return false;
        }

        return ProcessActionBase.activateCard(assistingCard, grid, inputs, outputs, pollution, true);
    }
}
