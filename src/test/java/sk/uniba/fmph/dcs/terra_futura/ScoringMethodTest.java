package sk.uniba.fmph.dcs.terra_futura;

import org.junit.jupiter.api.Test;
import java.util.*;

class ScoringMethodTest {

    private static class TestCard implements CardInterface {
        private final List<Resource> resources = new ArrayList<>();

        @Override
        public void getResources(List<Resource> output) {
            output.addAll(resources);
        }

        @Override
        public void putResources(List<Resource> resources) {
            this.resources.addAll(resources);
        }
    }

    private static class TestGrid implements GridInterface {
        private final Map<GridPosition, CardInterface> cards = new HashMap<>();
        private final Set<GridPosition> active = new HashSet<>();

        @Override
        public Card getCard(GridPosition coordinate) {
            return cards.get(coordinate);
        }

        @Override
        public boolean canBeActivated(GridPosition coordinate) {
            return active.contains(coordinate);
        }

        @Override
        public void putCard(GridPosition coordinate, CardInterface card) {
            cards.put(coordinate, card);
        }

        @Override
        public void setActivated(GridPosition pos) {
            active.add(pos);
        }

    }

    @Test
    void testSimpleScoring() {

        GridInterface grid = new TestGrid();

        CardInterface card1 = new TestCard();
        CardInterface card2 = new TestCard();
        CardInterface card3 = new TestCard();
        CardInterface card4 = new TestCard();
        CardInterface card5 = new TestCard();
        CardInterface card6 = new TestCard();
        CardInterface card7 = new TestCard();
        CardInterface card8 = new TestCard();
        CardInterface card9 = new TestCard();

        card1.putResources(List.of(Resource.Pollution, Resource.Pollution));
        card2.putResources(List.of(Resource.Car, Resource.Car));
        card3.putResources(List.of(Resource.Yellow));
        card4.putResources(List.of(Resource.Money));
        card6.putResources(List.of(Resource.Red));
        card7.putResources(List.of(Resource.Gear, Resource.Gear));
        card8.putResources(List.of(Resource.Gear, Resource.Gear));
        card9.putResources(List.of(Resource.Red));

        grid.putCard(new GridPosition(-1,-1), card1);
        grid.putCard(new GridPosition(0,-1), card2);
        grid.putCard(new GridPosition(1,-1), card3);
        grid.putCard(new GridPosition(-1,0), card4);
        grid.putCard(new GridPosition(0,0), card5);
        grid.putCard(new GridPosition(1,0), card6);
        grid.putCard(new GridPosition(-1,1), card7);
        grid.putCard(new GridPosition(0,1), card8);
        grid.putCard(new GridPosition(1,1), card9);

        grid.setActivated(new GridPosition(0,-1));
        grid.setActivated(new GridPosition(1,-1));
        grid.setActivated(new GridPosition(-1,0));
        grid.setActivated(new GridPosition(0,0));
        grid.setActivated(new GridPosition(1,0));
        grid.setActivated(new GridPosition(-1,1));
        grid.setActivated(new GridPosition(0,1));
        grid.setActivated(new GridPosition(1,1));

        List<Resource> resources = new ArrayList<>(List.of(Resource.Gear,Resource.Car));
        List<Integer> requiredNumbersOfResources = new ArrayList<>(List.of(2,1));
        Points pointsPerCombination = new Points(5);

        ScoringMethod scoringMethod = new ScoringMethod(grid, resources, requiredNumbersOfResources, pointsPerCombination);

        System.out.println(scoringMethod.state());
        scoringMethod.selectThisMethodAndCalculate();
        System.out.println(scoringMethod.state());
    }
}
