package unit.hero.warrior;

import unit.ability.action.Upgrade;
import unit.ability.action.code.conditional.*;
import core.Color;
import unit.hero.warrior.actions.warden.Lacerate;
import unit.hero.warrior.actions.warden.Slash;
import unit.hero.warrior.perks.BoundlessEndurance;
import unit.hero.warrior.perks.Disarm;
import unit.hero.warrior.perks.VitalSurge;
import unit.hero.Hero;
import unit.hero.warrior.actions.*;

public class Warrior extends Hero
{
    public void setup()
    {
        superClassName = "Warrior";
        description = "A balanced tank focused on [PHYSICAL]Physical Damage[], [BLOCK]Block[], and self-only [HEAL]Healing[] effects.";
        classColor = new Color(240, 50, 50);
    }

    public void setStartingAttributes()
    {
        addMaxHealth(80);
        addMaxMana(8);
//        addManaPerTurn(1);
    }

    public void setStartingAbilities()
    {
        algorithm.add(new AttackWarrior());
        algorithm.add(new AttackWarrior());
        algorithm.add(new AttackWarrior());
        algorithm.add(new Parry());
        algorithm.add(new Parry());
        algorithm.add(new SecondWind());
        addPerk(new BoundlessEndurance());
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
        actionPool.add(Shattersteel.class);
        actionPool.add(Parry.class);
        actionPool.add(DefensiveShift.class);
        actionPool.add(SecondWind.class);
        actionPool.add(EndurePain.class);
    }

    public void setCodePool()
    {
        codePool.add(IfLowHealth.class);
        codePool.add(IfHighHealth.class);
        codePool.add(IfNoBlock.class);
        codePool.add(IfHasBlock.class);
        codePool.add(IfEnemyHighHealth.class);
        codePool.add(IfEnemyLowHealth.class);
    }

    public void setPerkPool()
    {
        perkPool.add(BoundlessEndurance.class);
        perkPool.add(Disarm.class);
        perkPool.add(VitalSurge.class);
    }



}
