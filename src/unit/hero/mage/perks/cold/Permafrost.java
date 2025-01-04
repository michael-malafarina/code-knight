package unit.hero.mage.perks.cold;

import unit.ability.action.Action;
import unit.ability.conditions.Condition;
import unit.ability.conditions.debuff.Chill;
import unit.ability.conditions.debuff.Freeze;
import unit.ability.perks.Perk;
import ui.Images;
import unit.Unit;

public class Permafrost extends Perk
{

    int threshold = 5;

    public void setup()
    {
        name = "Permafrost";
        icon = Images.perkPermafrost;
        addCondition(Freeze.class, 2);

    }

    public String getDescription()
    {
        return "When you use an action that applies [KEY]Chill[], convert 5 [KEY]Chill[] stacks into one [KEY]Freeze[].";
    }

    public void onActionUsed(Action a)
    {
        // Won't trigger when I use a non-chill action
        if (!a.hasCondition(Chill.class))
        {
            return;
        }

        // Convert chill to freeze
        for (Unit t : a.getTargets())
        {
            // If this target has no chill, skip it
            if (t.getModifiers().hasCondition(Chill.class))
            {
                continue;
            }

            Condition chill = t.getModifiers().getCondition(Chill.class);

            if (chill.getStacks() > threshold)
            {
                chill.removeStacks(threshold);
                applyConditionsLater(t);
            }
        }
    }
}

