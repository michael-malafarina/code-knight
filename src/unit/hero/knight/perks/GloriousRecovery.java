package unit.hero.knight.perks;

import unit.ability.conditions.buff.Glory;
import unit.ability.perks.Perk;
import ui.Images;

public class GloriousRecovery extends Perk
{

    public void setup()
    {
        name = "Glorious Recovery";
        icon = Images.perkGloriousRecovery;
        addCondition(Glory.class, 1);
    }

    public void onHealingRecieved()
    {
        applyConditionsLater();
    }

    public String getDescription()
    {
        return "When you are [HEAL]Healed[], gain [BUFF]Glory[] 1";
    }
}
