package unit.hero.knight;

import animation.Animation;
import core.Color;
import ui.Images;
import unit.ability.action.Upgrade;
import unit.hero.knight.actions.AttackKnight;
import unit.hero.knight.actions.paladin.Defend;
import unit.hero.knight.actions.paladin.HolyShield;
import unit.hero.knight.actions.paladin.LayOnHands;
import unit.hero.knight.actions.paladin.ValiantStrike;
import unit.hero.knight.perks.AuraOfCourage;

public class Paladin extends Knight
{
    public void setup()
    {
        super.setup();

        name = "Paladin";
        description = "A defensive tank focused on [BLOCK]Block[], [HEAL]Healing[], and [PHYSICAL] Physical Damage[].";

        icon = Images.iconPaladin;
        animation = new Animation(Images.paladin);
        animation.setHero();
        classColor = new Color(50, 120, 255);
    }

    public void setStartingAttributes()
    {
        super.setStartingAttributes();
        addSpirit(1);
        addDefense(1);

    }

    public void setStartingAbilities()
    {
        algorithm.add(new ValiantStrike());
        algorithm.add(new ValiantStrike());
        algorithm.add(new Defend());
        algorithm.add(new Defend());
        algorithm.add(new LayOnHands());
        addPerk(new AuraOfCourage());
    }

    public void setUpgradePool()
    {
        super.setUpgradePool();
        upgradePool.add(Upgrade.SPIRIT);
    }

    public void setActionPool()
    {
        super.setActionPool();

        // Attack
        actionPool.add(HolyShield.class);
        actionPool.add(LayOnHands.class);
        actionPool.add(ValiantStrike.class);
        actionPool.add(Defend.class);

    }

    public void setCodePool()
    {
        super.setCodePool();

    }

    public void setPerkPool()
    {
        super.setPerkPool();

        perkPool.add(AuraOfCourage.class);

    }

}
