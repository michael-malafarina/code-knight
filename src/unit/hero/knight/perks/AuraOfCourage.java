package unit.hero.knight.perks;

import campaign.HeroManager;
import unit.ability.conditions.buff.Glory;
import unit.ability.perks.Perk;
import ui.Images;

public class AuraOfCourage extends Perk
{

    public void setup()
    {
        name = "Aura of Courage";
        icon = Images.perkAuraOfCourage;
        addCondition(Glory.class, 2);
    }

    public void onStartCombat()
    {
        applyConditionsLater(HeroManager.getUnits());
    }

    public String getDescription()
    {
        return "At the start of combat, each ally gains [BUFF]Glory[] 2.";
    }
}
