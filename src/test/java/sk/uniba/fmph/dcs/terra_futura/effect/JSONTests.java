package sk.uniba.fmph.dcs.terra_futura.effect;

import org.junit.Test;
import sk.uniba.fmph.dcs.terra_futura.Resource;

import java.util.ArrayList;

public class JSONTests {
    @Test
    public void stringRepresentation() {
        Effect a = new AssistanceEffect();

        Effect g1 = new GainEffect(Resource.Green);
        Effect g2 = new GainEffect(Resource.Gear);

        ArrayList<Resource> resourceList1 = new ArrayList<>();
        resourceList1.add(Resource.Green);
        resourceList1.add(Resource.Green);
        resourceList1.add(Resource.Red);

        ArrayList<Resource> resourceList2 = new ArrayList<>();
        resourceList2.add(Resource.Bulb);
        resourceList2.add(Resource.Car);
        resourceList2.add(Resource.Pollution);

        ArrayList<Resource> resourceList3 = new ArrayList<>();
        resourceList3.add(Resource.Money);

        Effect e1 = new ExchangeEffect(resourceList1, resourceList2);
        Effect e2 = new ExchangeEffect(resourceList3, resourceList1);

        EffectOr o1 = new EffectOr();
        EffectOr o2 = new EffectOr();
        o2.addEffect(e1);

        EffectOr o3 = new EffectOr();
        o3.addEffect(e1);
        o3.addEffect(e2);

        for (Effect effect : new Effect[] {a, g1, g2, e1, e2, o1, o2, o3}) {
            System.out.println(effect.state());
        }
    }
}
