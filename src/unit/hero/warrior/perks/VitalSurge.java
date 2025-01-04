package unit.hero.warrior.perks;

import unit.ability.perks.Perk;
import ui.Images;

public class VitalSurge extends Perk
{
    public void setup()
    {
        name = "Vital Surge";
        icon = Images.perkVitalSurge;
    }

    public String getDescription()
    {
        return "The first time you are reduced below 50% health each battle, [HEAL]Heal[] 15.";
    }

    public void onBloodied()
    {
        self.heal(getHealing(15), this);
    }
}
