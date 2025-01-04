package unit.hero.warrior.perks;

import campaign.HeroManager;
import ui.Images;
import unit.Unit;
import unit.ability.Ability;
import unit.ability.action.DamageType;
import unit.ability.conditions.buff.Vigor;
import unit.ability.perks.Perk;

public class BattefrontLeader extends Perk
{
    public void setup()
    {
        name = "Battlefront Leader";
        icon = Images.perkBattlefrontLeader;
    }

    public void onDamageDealt(int amount, Unit target, DamageType type)
    {
        for(Unit u : HeroManager.getUnits())
        {
            u.addSpeed(1);
        }
    }

    public void onDamageTaken(DamageType type, Ability source)
    {
        for(Unit u : HeroManager.getUnits())
        {
            u.addSpeed(1);
        }
    }

    public String getDescription()
    {
        return "When the Vanguard deals damage or takes damage, each ally gains 1 speed.";
    }


}
