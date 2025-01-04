package unit.hero.cleric;

import unit.ability.action.Upgrade;
import unit.ability.action.code.conditional.*;
import core.Color;
import unit.hero.Hero;
import unit.hero.cleric.actions.*;
import unit.hero.cleric.actions.runepriest.Bless;
import unit.hero.cleric.perks.*;

public abstract class Cleric extends Hero
{
    public void setup()
    {
        superClassName = "Cleric";
        description = "A support caster focused on [HEAL]Healing[], [BUFF]Buffs[], and [RADIANT]radiant damage[].";

        classColor = new Color(238, 195, 25);
    }

    public void setStartingAttributes()
    {
        addMaxHealth(50);
        addMaxMana(16);
      //  addManaPerTurn(2);
    }

    public void setUpgradePool()
    {
        upgradePool.add(Upgrade.MAX_MANA);
        upgradePool.add(Upgrade.SPEED);
        upgradePool.add(Upgrade.SPIRIT);
    }

    public void setActionPool()
    {
        // Heal
        actionPool.add(Cure.class);
        actionPool.add(MassHeal.class);

        // Buff
        actionPool.add(Sanctuary.class);
        actionPool.add(Prayer.class);
        actionPool.add(Bless.class);

    }

    public void setCodePool()
    {
        codePool.add(IfAllyHighHealth.class);
        codePool.add(IfAllyLowHealth.class);
        codePool.add(IfLowMana.class);
        codePool.add(IfHighMana.class);
    }

    public void setPerkPool()
    {
        perkPool.add(Grace.class);
        perkPool.add(Wrath.class);
        perkPool.add(Purity.class);
    }

}
