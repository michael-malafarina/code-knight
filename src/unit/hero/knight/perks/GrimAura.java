package unit.hero.knight.perks;

import campaign.EnemyManager;
import ui.Images;
import unit.Unit;
import unit.ability.conditions.buff.Armor;
import unit.ability.conditions.debuff.Fear;
import unit.ability.perks.Perk;

public class GrimAura extends Perk
{

    public void setup()
    {
        name = "Grim Aura";
        icon = Images.perkDeathward;
        addCondition(Fear.class, 2);
    }

    public void onStartCombat()
    {
        for(Unit u : EnemyManager.getUnits())
        {
            applyConditionsLater(u);
        }
    }

    public String getDescription()
    {
        return "The first time you are reduced below 50% HP each battle, all enemies gain [DEBUFF]Fear[] " + getStacksText() + ".";
    }
}
