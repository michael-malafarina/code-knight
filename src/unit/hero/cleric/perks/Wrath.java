package unit.hero.cleric.perks;

import unit.ability.action.Action;
import unit.ability.action.Tag;
import unit.ability.conditions.buff.Power;
import unit.ability.perks.Perk;
import ui.Images;

public class Wrath extends Perk
{
    float chance = .50f;

    public void setup()
    {
        name = "Wrath";
        icon = Images.perkWrath;
        addCondition(Power.class, 1);
    }

    public void onActionUsed(Action action)
    {
        if(action.getType() == Tag.ATTACK && Math.random() < chance)
        {
            applyConditionsLater();
        }
    }

    public String getDescription()
    {
        return "When you [ATTACK]Attack[], 50% chance to gain [BUFF]Might[] 1.";
    }

}
