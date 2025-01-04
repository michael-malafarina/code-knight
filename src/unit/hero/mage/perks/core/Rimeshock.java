package unit.hero.mage.perks.core;

import unit.ability.action.DamageType;
import unit.ability.conditions.debuff.Shock;
import unit.ability.perks.Perk;
import ui.Images;
import unit.Unit;

public class Rimeshock extends Perk
{
    public void setup()
    {
        name = "Rimeshock";
        icon = Images.perkRimeshock;
        addCondition(Shock.class, 1);

    }

    public String getDescription()
    {
        return "When you deal [COLD]Cold Damage[], apply [SHOCK]Shock[] 1 to each target.";
    }

    public void onDamageDealt(int amount, Unit target, DamageType type)
    {
        if(type == DamageType.COLD)
        {
            applyConditionsLater();
        }
    }

}
