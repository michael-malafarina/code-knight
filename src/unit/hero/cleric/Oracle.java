package unit.hero.cleric;

import animation.Animation;
import core.Color;
import ui.Images;
import unit.ability.action.Upgrade;
import unit.hero.cleric.actions.*;
import unit.hero.cleric.actions.dawnbringer.HealingLight;
import unit.hero.cleric.actions.shadowpriest.Shadowstrike;
import unit.hero.cleric.perks.Shadowsight;

public class Oracle extends Cleric
{
    public void setup()
    {
        super.setup();

        name = "Oracle";
        description = "A support caster focused on [HEAL]Healing[], [SHADOW]Shadow Damage[], and [DEBUFF]Debuffs[].";
        icon = Images.iconRunepriest;
        animation = new Animation(Images.runepriest);
        animation.setHero();
        classColor = new Color(238, 195, 25);
    }

    public void setStartingAttributes()
    {
        super.setStartingAttributes();
        addMaxMana(8);
    }

    public void setStartingAbilities()
    {
        algorithm.add(new AttackCleric());
        algorithm.add(new AttackCleric());
        algorithm.add(new AttackCleric());
        algorithm.add(new Shadowstrike());
        algorithm.add(new HealingLight());

        addPerk(new Shadowsight());
    }



    public void setUpgradePool()
    {
        super.setUpgradePool();
        upgradePool.add(Upgrade.FOCUS);

    }

    public void setActionPool()
    {
        super.setActionPool();

        // Attack
        actionPool.add(Shadowstrike.class);
    }

    public void setCodePool()
    {
        super.setCodePool();

    }

    public void setPerkPool()
    {
        super.setPerkPool();

        perkPool.add(Shadowsight.class);

    }

}
