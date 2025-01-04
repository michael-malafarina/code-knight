package unit.hero.knight.perks;

import unit.ability.conditions.buff.Armor;
import unit.ability.conditions.buff.Defense;
import unit.ability.perks.Perk;
import ui.Images;

public class BloodAndIron extends Perk
{

    public void setup()
    {
        name = "Blood and Iron";
        icon = Images.perkBloodAndIron;
        addCondition(Defense.class, 2);
    }

    public void onBloodied()
    {
        applyConditionsLater();
    }

    public String getDescription()
    {
        return "The first time you are reduced below 50% HP each battle, gain 2 defense.";
    }
}
