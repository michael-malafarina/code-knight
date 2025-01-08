package unit.hero.mage;

import animation.Animation;
import core.Color;
import ui.Images;
import unit.hero.mage.actions.*;
import unit.hero.mage.actions.storm.LightningBolt;
import unit.hero.mage.actions.storm.Tailwind;
import unit.hero.mage.actions.storm.Zap;
import unit.hero.mage.perks.core.Spellstorm;

public class StormMage extends Mage
{
    public void setup()
    {
        super.setup();

        name = "Storm Mage";
        description = "An offensive caster focused on [LIGHTNING]Lightning damage[] and shock effects.";
        icon = Images.iconStormMage;
        animation = new Animation(Images.stormmage);
        animation.setHero();
        classColor = new Color(255, 255, 0);
    }

    public void setStartingAttributes()
    {
        super.setStartingAttributes();
        addSpeedPerTurn(10);
    }

    public void setStartingAbilities()
    {
       addAction(new Zap());
       addAction(new ChaosBolt());
    //   addPerk(new Spellstorm());
    }

    public void setUpgradePool()
    {
        super.setUpgradePool();
    }

    public void setActionPool()
    {
        super.setActionPool();

        // Attack
        actionPool.add(Zap.class);
        actionPool.add(LightningBolt.class);
        actionPool.add(Tailwind.class);
    }

    public void setCodePool()
    {
        super.setCodePool();
    }

    public void setPerkPool()
    {
        super.setPerkPool();

    }

}
