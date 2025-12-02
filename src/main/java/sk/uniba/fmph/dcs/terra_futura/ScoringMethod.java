package sk.uniba.fmph.dcs.terra_futura;

import org.json.JSONObject;

import javax.swing.JScrollBar;
import java.util.*;

public class ScoringMethod {
    private final Grid grid;
    private final List<Resource> resources;
    private final List<Integer> requiredNumbersOfResources;
    private final Points pointsPerCombination;
    private Points calculatedTotal;

    public ScoringMethod(Grid grid, List<Resource> resources, List<Integer> requiredNumbersOfResources, Points pointsPerCombination) {
        if (resources.size() != requiredNumbersOfResources.size()) {
            throw new IllegalArgumentException("resources and count must be of the same size");
        }
        this.grid = grid;
        this.resources = resources;
        this.requiredNumbersOfResources = requiredNumbersOfResources;
        this.pointsPerCombination = pointsPerCombination;
    }

    public Points getCalculatedTotal() {
        return calculatedTotal;
    }

    private boolean canGetCard(GridPosition pos) {
        return grid.getCard(pos) != null;
    }

    private void countResourcesOnCard(Map<Resource,Integer> playersResources, GridPosition pos) {
        List<Resource> resourcesOnCard = new ArrayList<>();
        Card card = grid.getCard(pos);
        card.getResources(resourcesOnCard);
        for (Resource r : resourcesOnCard) {
            playersResources.replace(r, playersResources.get(r)+1);
        }
    }

    private void countPollutionOnCard(Map<Resource,Integer> playersResources, GridPosition pos) {
        List<Resource> resourcesOnCard = new ArrayList<>();
        Card card = grid.getCard(pos);
        card.getResources(resourcesOnCard);
        for (Resource r : resourcesOnCard) {
            if (r == Resource.Polution) {
                playersResources.replace(r,playersResources.get(Resource.Polution)+1);
            }
        }
    }

    public void selectThisMethodAndCalculate() {

        Map<Resource,Integer> playersResources = new EnumMap<>(Resource.class);
        for (Resource r : Resource.values()) {
            playersResources.put(r,0);
        }

        for (int x = -2; x <= 2; x++) {
            for (int y = -2; y <= 2; y++) {
                GridPosition pos = new GridPosition(x,y);
                if (canGetCard(pos) && grid.canBeActivated(pos)) {
                    countResourcesOnCard(playersResources, pos);
                } else if (canGetCard(pos)) {
                    countPollutionOnCard(playersResources, pos);
                } else {
                    continue;
                }
            }
        }

        List<Integer> scoringResourcesCount = new ArrayList<>();
        for (int i = 0; i < resources.size(); i++) {
            int have = playersResources.get(resources.get(i));
            int need = requiredNumbersOfResources.get(i);
            scoringResourcesCount.add(have / need);
        }

        int total = Collections.min(scoringResourcesCount)*pointsPerCombination.getPoints();

        total += playersResources.get(Resource.Green);
        total += playersResources.get(Resource.Red);
        total += playersResources.get(Resource.Yellow);
        total += playersResources.get(Resource.Bulb)*5;
        total += playersResources.get(Resource.Gear)*5;
        total += playersResources.get(Resource.Car)*6;
        total -= playersResources.get(Resource.Polution);

        calculatedTotal = new Points(total);
    }

    public String state() {
        JSONObject  jsonObject = new JSONObject();

        jsonObject.put("Resources: ", resources.toString());
        jsonObject.put("Required number of resources: ", requiredNumbersOfResources.toString());
        jsonObject.put("Points per combination: ", pointsPerCombination);
        jsonObject.put("Calculated total: ", calculatedTotal == null ? JSONObject.NULL : calculatedTotal.getPoints());

        return jsonObject.toString();
    }
}

