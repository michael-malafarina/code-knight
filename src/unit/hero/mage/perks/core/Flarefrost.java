package unit.hero.mage.perks.core;

import unit.ability.action.DamageType;
import unit.ability.conditions.debuff.Chill;
import unit.ability.perks.Perk;
import ui.Images;
import unit.Unit;

public class Flarefrost extends Perk
{
    public void setup()
    {
        name = "Flarefrost";
        icon = Images.perkFlarefrost;
        addCondition(Chill.class, 1);

    }

    public String getDescription()
    {
        return "When you deal fire damage, apply [CHILL]Chill[] 1 to each target.";
    }

    public void onDamageDealt(int amount, Unit target, DamageType type)
    {
        if(type == DamageType.FIRE)
        {

            applyConditionsLater();
        }
    }

}
