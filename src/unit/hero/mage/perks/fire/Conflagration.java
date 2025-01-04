package unit.hero.mage.perks.fire;

import unit.ability.action.Action;
import unit.ability.conditions.debuff.Burning;
import unit.ability.perks.Perk;
import ui.Images;
import unit.Unit;

public class Conflagration extends Perk
{
    public void setup()
    {
        name = "Conflagration";
        icon = Images.perkConflagration;
        addCondition(Burning.class, 1);
    }

    public String getDescription()
    {
        return "When you use an action that applies [BURNING]burning[], add one [BURNING]burning[] stack.";
    }

    public void onActionUsed(Action a)
    {
//        System.out.println("CONFLAG CALLED");

        if(a.hasCondition(Burning.class))
        {
            for(Unit t : a.getTargets())
            {
//                System.out.println("CONFLAG TRIGGERING");

                applyConditionsLater(t);
            }
        }
    }

}
