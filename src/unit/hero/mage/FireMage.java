package unit.hero.mage;

import animation.Animation;
import core.Color;
import ui.Images;
import unit.hero.mage.actions.*;
import unit.hero.mage.actions.fire.*;
import unit.hero.mage.perks.fire.FireMastery;

public class FireMage extends Mage
{
    public void setup()
    {
        super.setup();

        name = "Fire Mage";
        description = "An offensive caster focused on [FIRE]Fire damage[] and burn effects.";
        icon = Images.iconFireMage;
        animation = new Animation(Images.firemage);
        animation.setHero();
        classColor = new Color(255, 120, 0);
    }

    public void setStartingAttributes()
    {
        super.setStartingAttributes();
        addFocus(1);
        addSpeedPerTurn(5);
    }

    public void setStartingAbilities()
    {
        algorithm.add(new Firebolt());
        algorithm.add(new Firebolt());
        algorithm.add(new Burn());
        algorithm.add(new Burn());
        algorithm.add(new Emberstorm());
        addPerk(new FireMastery());
    }

    public void setUpgradePool()
    {
        super.setUpgradePool();
    }

    public void setActionPool()
    {
        super.setActionPool();

        actionPool.add(Burn.class);
        actionPool.add(Emberstorm.class);
        actionPool.add(Kindle.class);
        actionPool.add(Immolate.class);
        actionPool.add(InnerFire.class);

    }

    public void setCodePool()
    {
        super.setCodePool();
    }

    public void setPerkPool()
    {
        super.setPerkPool();

        perkPool.add(FireMastery.class);
    }

}
