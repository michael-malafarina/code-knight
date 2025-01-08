package unit.hero.knight;

import unit.ability.action.Upgrade;
import unit.ability.action.code.conditional.*;
import unit.hero.knight.actions.*;
import core.Color;
import unit.hero.Hero;
import unit.hero.knight.actions.Protect;
import unit.hero.knight.actions.paladin.HolyShield;
import unit.hero.knight.actions.paladin.ValiantStrike;
import unit.hero.knight.perks.*;

public abstract class Knight extends Hero
{

    public void setup()
    {
        superClassName = "Knight";
        description = "A defensive tank focused on [BLOCK]Block[], [BUFF]Armor[], and [PHYSICAL] Physical Damage[].";

        classColor = new Color(50, 120, 255);
    }

    public void setStartingAttributes()
    {
        addMaxHealth(60);
        addMaxMana(8);
        addDefense(1);
    }

    public void setUpgradePool()
    {
        upgradePool.add(Upgrade.MAX_HP);
        upgradePool.add(Upgrade.MAX_MANA);
        upgradePool.add(Upgrade.SPEED);
        upgradePool.add(Upgrade.STRENGTH);
        upgradePool.add(Upgrade.DEFENSE);
    }

    public void setActionPool()
    {
        actionPool.add(BatteringRam.class);
        actionPool.add(ShieldBash.class);

        // Block
        actionPool.add(Defend.class);
        actionPool.add(Protect.class);
        actionPool.add(RaiseShield.class);
        actionPool.add(SteelYourself.class);

        // Buff
        actionPool.add(Fortify.class);
    }

    public void setCodePool()
    {
        codePool.add(IfFirstUse.class);
        codePool.add(IfLowHealth.class);
        codePool.add(IfEnemyLowHealth.class);
    }

    public void setPerkPool()
    {
        perkPool.add(ShiningArmor.class);
        perkPool.add(GloriousRecovery.class);
        perkPool.add(BloodAndIron.class);

    }

}
