package unit.hero.warrior.perks;

import unit.ability.action.DamageType;
import unit.ability.conditions.debuff.Weak;
import unit.ability.perks.Perk;
import ui.Images;
import unit.Unit;

public class Disarm extends Perk
{
    public void setup()
    {
        name = "Disarm";
        icon = Images.perkDisarm;
        addCondition(Weak.class, 1);
    }

    public String getDescription()
    {
        return "When you use an attack that deals 10 or more damage, apply [DEBUFF]Weak[] 1.";
    }

    public void onDamageDealt(int amount, Unit target, DamageType type)
    {
        if(amount > 10)
        {
            applyConditionsLater(target);
        }
    }
    /*

    10% chance on action (or every 10 times) or per energy spent

    gain damage, defense, or speed based on element cast



     */
}
