package unit.hero.mage.perks.core;

import unit.ability.action.DamageType;
import unit.ability.conditions.debuff.Burning;
import unit.ability.perks.Perk;
import ui.Images;
import unit.Unit;

public class Sparkfire extends Perk
{
    public void setup()
    {
        name = "Sparkfire";
        icon = Images.perkSparkfire;
        addCondition(Burning.class, 1);

    }

    public String getDescription()
    {
        return "When you deal lightning damage, apply [BURNING]Burning[] 1 to each target.";
    }

    public void onDamageDealt(int amount, Unit target, DamageType type)
    {
        if(type == DamageType.LIGHTNING)
        {
            applyConditionsLater();
        }
    }

}
