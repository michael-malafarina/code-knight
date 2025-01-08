package unit.hero.mage;

import unit.ability.action.Upgrade;
import unit.ability.action.code.conditional.*;
import core.Color;
import unit.hero.mage.perks.core.*;
import unit.hero.mage.perks.fire.Conflagration;
import unit.hero.Hero;
import unit.hero.mage.actions.*;

public abstract class Mage extends Hero
{
    public void setup()
    {
        superClassName = "Mage";
        description = "An offensive caster focused on [FIRE]Fire damage[], [COLD]Cold damage[], and [LIGHTNING]Lightning damage[].";

        classColor = new Color(190, 0, 110);

    }

    public void setStartingAttributes()
    {
        addMaxHealth(40);
        addMaxMana(24);
    }

    @Override
    public void setUpgradePool()
    {
        upgradePool.add(Upgrade.MAX_HP);
        upgradePool.add(Upgrade.MAX_MANA);
        upgradePool.add(Upgrade.SPEED);
        upgradePool.add(Upgrade.FOCUS);
    }


    @Override
    public void setActionPool()
    {
        actionPool.add(ChaosBolt.class);
        actionPool.add(AetherBlast.class);
    }

    @Override
    public void setCodePool()
    {
        codePool.add(IfFirstUse.class);
        codePool.add(IfMultipleEnemies.class);
        codePool.add(IfEnemyLowHealth.class);
        codePool.add(IfHighMana.class);
    }

    @Override
    public void setPerkPool()
    {
        perkPool.add(Conflagration.class);
        perkPool.add(WildSurge.class);
        perkPool.add(Rimeshock.class);
        perkPool.add(Flarefrost.class);
        perkPool.add(Sparkfire.class);
        perkPool.add(Spellstorm.class);

    }


}
