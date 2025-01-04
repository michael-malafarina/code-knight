package unit.hero.warrior;

import animation.Animation;
import core.Color;
import ui.Images;
import unit.ability.action.Upgrade;
import unit.hero.warrior.actions.AttackWarrior;
import unit.hero.warrior.actions.warden.Lacerate;
import unit.hero.warrior.actions.warden.Regrowth;
import unit.hero.warrior.actions.warden.Slash;
import unit.hero.warrior.actions.warden.Thornskin;
import unit.hero.warrior.perks.Oakheart;

public class Warden extends Warrior
{
    public void setup()
    {
        super.setup();

        name = "Warden";
        description = "A defensive [KEY]Warrior[] who uses the power of nature to protect his allies with blade, thorn, and regeneration.";
        icon = Images.iconWarden;
        classColor = new Color(40, 200, 80);
        animation = new Animation(Images.warden);
        animation.setHero();
    }

    public void setStartingAttributes()
    {
        super.setStartingAttributes();
        addMaxMana(4);
        addMaxHealth(10);
    }

    public void setStartingAbilities()
    {
        algorithm.add(new Slash());
        algorithm.add(new Slash());
        algorithm.add(new Regrowth());
        algorithm.add(new Regrowth());
        algorithm.add(new Thornskin());
        addPerk(new Oakheart());
    }

    public void setUpgradePool()
    {
        super.setUpgradePool();
        upgradePool.add(Upgrade.SPIRIT);

    }

    public void setActionPool()
    {
        super.setActionPool();

        actionPool.add(Slash.class);
        actionPool.add(Lacerate.class);
        actionPool.add(Thornskin.class);
        actionPool.add(Regrowth.class);

    }

    public void setCodePool()
    {
        super.setCodePool();
    }

    public void setPerkPool()
    {
        super.setPerkPool();

        perkPool.add(Oakheart.class);
    }

}
