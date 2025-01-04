package unit.hero.warrior.perks;

import unit.ability.conditions.buff.Regen;
import unit.ability.conditions.buff.Vigor;
import unit.ability.perks.Perk;
import ui.Images;

public class Oakheart extends Perk
{
    public void setup()
    {
        name = "Oakheart";
        icon = Images.perkOakheart;
        addCondition(Vigor.class, 2);
    }

    public String getDescription()
    {
        return "The first time you are reduced below 50% health each battle, gain [BUFF]Vigor[] 2";
    }

    public void onBloodied()
    {
        applyConditionsLater();
    }

}
