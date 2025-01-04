package unit.hero.cleric.perks;

import unit.ability.action.Action;
import unit.ability.action.Tag;
import unit.ability.conditions.buff.Spirit;
import unit.ability.perks.Perk;
import ui.Images;

public class Grace extends Perk
{
    float chance = .50f;

    public void setup()
    {
        name = "Grace";
        icon = Images.perkGrace;
        addCondition(Spirit.class, 1);
    }

    public void onActionUsed(Action action)
    {
//        System.out.println("GRACE CALLED");

        if(action.getType() == Tag.HEAL && Math.random() < chance)
        {
//            System.out.println("GRACE TRIGGERING");
            applyConditionsLater();
        }
    }

    public String getDescription()
    {
        return "When you [HEAL]Heal[], 50% chance to gain [BUFF]Spirit[] 1.";
    }

}
