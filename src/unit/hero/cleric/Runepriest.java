package unit.hero.cleric;

import animation.Animation;
import core.Color;
import ui.Images;
import unit.ability.action.Upgrade;
import unit.hero.cleric.actions.*;
import unit.hero.cleric.actions.runepriest.*;
import unit.hero.cleric.perks.RuneOfPower;

public class Runepriest extends Cleric
{
    public void setup()
    {
        super.setup();

        name = "Runepriest";
        description = "A support caster focused on [PHYSICAL]Physical Damage[], [HEAL]Healing[], and [BUFF]Buffs[].";
        icon = Images.iconRunepriest;
        animation = new Animation(Images.runepriest);
        animation.setHero();
        classColor = new Color(255, 255, 200);
    }

    public void setStartingAttributes()
    {
        super.setStartingAttributes();
        addMaxHealth(10);
        addStrength(1);
    }

    public void setStartingAbilities()
    {
        algorithm.add(new Zeal());
        algorithm.add(new Zeal());
        algorithm.add(new HealingStrike());
        algorithm.add(new HealingStrike());
        algorithm.add(new Bless());
        addPerk(new RuneOfPower());
    }

    public void setUpgradePool()
    {
        super.setUpgradePool();
        upgradePool.add(Upgrade.MAX_HP);
        upgradePool.add(Upgrade.STRENGTH);
    }

    public void setActionPool()
    {
        super.setActionPool();

        actionPool.add(Bless.class);
        actionPool.add(DivineStrength.class);
        actionPool.add(HealingStrike.class);
        actionPool.add(Smite.class);
        actionPool.add(Zeal.class);
    }

    public void setCodePool()
    {
        super.setCodePool();

    }

    public void setPerkPool()
    {
        super.setPerkPool();

//        perkPool.add(RuneOfPower.class);

    }

}
