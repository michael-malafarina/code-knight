package unit.hero.cleric;

import animation.Animation;
import core.Color;
import ui.Images;
import unit.ability.action.Upgrade;
import unit.hero.cleric.actions.*;
import unit.hero.cleric.actions.dawnbringer.Flamestrike;
import unit.hero.cleric.actions.dawnbringer.HealingLight;
import unit.hero.cleric.actions.dawnbringer.Dawnbolt;
import unit.hero.cleric.perks.*;

public class Dawnbringer extends Cleric
{
    public void setup()
    {
        super.setup();

        name = "Dawnbringer";
        description = "A support caster focused on [HEAL]Healing[] and [RADIANT]Radiant Damage[].";
        icon = Images.iconDawnbringer;
        animation = new Animation(Images.dawnbringer);
        animation.setHero();
        classColor = new Color(255, 255, 100);
    }




    public void setStartingAttributes()
    {
        super.setStartingAttributes();
        addMaxMana(8);
    }

    public void setStartingAbilities()
    {
        algorithm.add(new Dawnbolt());
        algorithm.add(new Dawnbolt());
        algorithm.add(new Flamestrike());
        algorithm.add(new HealingLight());
        algorithm.add(new HealingLight());
        addPerk(new Morninglord());
    }

    public void setUpgradePool()
    {
        super.setUpgradePool();
        upgradePool.add(Upgrade.FOCUS);
    }

    public void setActionPool()
    {
        super.setActionPool();

        actionPool.add(Dawnbolt.class);
        actionPool.add(Flamestrike.class);
        actionPool.add(HealingLight.class);

    }

    public void setCodePool()
    {
        super.setCodePool();

    }

    public void setPerkPool()
    {
        super.setPerkPool();

        perkPool.add(Morninglord.class);

    }

}
