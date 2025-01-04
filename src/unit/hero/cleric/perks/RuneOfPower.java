package unit.hero.cleric.perks;

import unit.ability.action.Action;
import unit.ability.action.Tag;
import campaign.HeroManager;
import unit.ability.conditions.buff.Power;
import unit.ability.perks.Perk;
import ui.Images;

public class RuneOfPower extends Perk
{
    float chance = .50f;

    public void setup()
    {
        name = "Rune of Power";
        icon = Images.perkRuneOfPower;
        addCondition(Power.class, 1);
    }

    public void onActionUsed(Action action)
    {
        if(action.getType() == Tag.BUFF && Math.random() < chance)
        {
            applyConditionsLater(HeroManager.getRandomUnit());
        }
    }

    public String getDescription()
    {
        return "When you [BUFF]Buff[], 50% chance a random friendly unit gains [BUFF]Might[] 1.";
    }

}
