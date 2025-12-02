package sk.uniba.fmph.dcs.terra_futura;

import java.util.*;
import java.util.stream.Stream;

public class Card {
    private final int maxPollutionHealth;

    private final List<Resource> resourceStore;
    private final int maxResourceCount;

    private final Effect[] effectStore;
    private Effect assistanceEffect;

    public Card(int maxResourceCount, int maxPollutionHealth) {
        this.effectStore = new Effect[2];
        this.resourceStore = new ArrayList<>();
        this.maxResourceCount = maxResourceCount;
        this.assistanceEffect = null;
        this.maxPollutionHealth = maxPollutionHealth;
    }

    public Card(int maxResourceCount, int maxPollutionHealth, Effect upperEffect) {
        this(maxPollutionHealth, maxResourceCount);
        this.effectStore[0] = upperEffect;
    }

    public Card(int maxResourceCount, int maxPollutionHealth, Effect upperEffect, Effect lowerEffect) {
        this(maxPollutionHealth, maxResourceCount);
        this.effectStore[0] = upperEffect;
        this.effectStore[1] = lowerEffect;
    }

    public int getPollutionHealth() {
        return Math.max(maxPollutionHealth - Collections.frequency(resourceStore, Resource.Pollution), 0);
    }

    public boolean canTakeResources(List<Resource> resources) {
        for (Resource resource : new HashSet<>(resources))
            if (Collections.frequency(resources, resource) > Collections.frequency(resourceStore, resource))
                return false;
        return true;
    }

    public List<Resource> takeResources(List<Resource> resources) {
        List<Resource> takenResources = new ArrayList<>();
        for (Resource resource : resources) {
            if (!resourceStore.contains(resource))
                continue;
            takenResources.add(resource);
            resourceStore.remove(resource);
        }
        return takenResources;
    }

    public List<Resource> getResources() {
        return Collections.unmodifiableList(this.resourceStore);
    }

    public boolean canPutResources(List<Resource> resources) {
        List<Resource> theoreticalJoinedResources = Stream.concat(resourceStore.stream(), resources.stream()).toList();
        List<Resource> withoutPollution = theoreticalJoinedResources.stream().filter(r -> r != Resource.Pollution).toList();
        return withoutPollution.size() <= maxResourceCount && theoreticalJoinedResources.size() - withoutPollution.size() <= maxPollutionHealth;
    }

    public void putResources(List<Resource> resources) {
        for (Resource resource : resources) {
            if (!canPutResources(List.of(resource)))
                continue;
            resourceStore.add(resource);
        }
    }

    private boolean check(Effect effect, List<Resource> input, List<Resource> output, int pollution) {
        return effect.check(input, output, pollution);
    }

    public boolean checkUpper(List<Resource> input, List<Resource> output, int pollution) {
        return check(effectStore[0], input, output, pollution);
    }

    public boolean checkLower(List<Resource> input, List<Resource> output, int pollution) {
        return check(effectStore[1], input, output, pollution);
    }

    public boolean hasAssistance() {
        return assistanceEffect != null;
    }

    public void giveAssistance(Effect effect) {
        assistanceEffect = effect;
    }

    public String state() {
        StringBuilder resourcesState = new StringBuilder("Resources: ");
        for (Resource resource : resourceStore)
            resourcesState.append(resource.toString()).append(" ");

        // Prepare the effects state
        StringBuilder effectsState = new StringBuilder("Effects: ");
        if (effectStore[0] != null)
            effectsState.append("Upper Effect: ").append(effectStore[0].toString()).append(" ");
        if (effectStore[1] != null)
            effectsState.append("Lower Effect: ").append(effectStore[1].toString()).append(" ");
        if (assistanceEffect != null)
            effectsState.append("Assistance Effect: ").append(assistanceEffect.toString()).append(" ");

        // Return the combined state string
        return String.format("Pollution Health: %d, Max Resource Count: %d, %s, %s",
                getPollutionHealth(), maxResourceCount, resourcesState.toString(), effectsState.toString());
    }
}
