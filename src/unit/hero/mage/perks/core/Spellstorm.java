package unit.hero.mage.perks.core;

import unit.ability.action.Action;
import unit.ability.perks.Perk;
import ui.Images;

public class Spellstorm extends Perk
{

    int stacksNeeded = 8;
    int bonus = 20;

    public void setup()
    {
        name = "Spellstorm";
        icon = Images.perkSpellstorm;
    }

    public String getDescription()
    {
        return "For every "+stacksNeeded+" Mana you spend, gain " + bonus + " speed.";
    }

    public void onActionUsed(Action a)
    {
        stacks += a.getManaCost();

        if(stacks >= stacksNeeded)
        {
            stacks -= stacksNeeded;
            self().addSpeed(bonus);
        }
    }
    /*

    10% chance on action (or every 10 times) or per energy spent

    gain damage, defense, or speed based on element cast



     */
}
