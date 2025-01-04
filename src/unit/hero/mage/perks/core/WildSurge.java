package unit.hero.mage.perks.core;

import unit.ability.action.Action;
import unit.ability.action.DamageType;
import campaign.EnemyManager;
import unit.ability.perks.Perk;
import ui.Images;

public class WildSurge extends Perk
{
    int stacksNeeded = 10;

    public void setup()
    {
        name = "Wild Surge";
        icon = Images.perkWildSurge;
    }

    public String getDescription()
    {
        return "Every " + stacksNeeded + " actions, deal 5 [FIRE]fire[], [COLD]cold[], or [LIGHTNING]lightning[] damage to a random enemy.";
    }

    public void onActionUsed(Action a)
    {
        stacks += a.getEnergyCost();

        if(stacks >= stacksNeeded)
        {
            stacks -= stacksNeeded;
            EnemyManager.getRandomUnit().takeDamage(5, DamageType.getRandomElementalType(), this);
        }
    }
    /*

    10% chance on action (or every 10 times) or per energy spent

    gain damage, defense, or speed based on element cast



     */
}
