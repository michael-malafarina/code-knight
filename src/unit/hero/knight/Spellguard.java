package unit.hero.knight;

import animation.Animation;
import core.Color;
import ui.Images;
import unit.hero.knight.actions.AttackKnight;
import unit.hero.knight.actions.paladin.HolyShield;
import unit.hero.knight.actions.Fortify;
import unit.hero.knight.perks.ReactiveShield;
import unit.hero.mage.actions.FrostShield;

public class Spellguard extends Knight
{
    public void setup()
    {
        super.setup();

        name = "Spellguard";
        description = "A defensive tank focused on [BLOCK]Block[], [BUFF]Buffs[], and [PHYSICAL] Physical Damage[].";

        icon = Images.iconPaladin;
        animation = new Animation(Images.paladin);
        animation.setHero();
        classColor = new Color(50, 120, 255);
    }

    public void setStartingAttributes()
    {
        super.setStartingAttributes();
        addMaxMana(5);
        addDefense(1);
    }

    public void setStartingAbilities()
    {
        algorithm.add(new AttackKnight());
        algorithm.add(new AttackKnight());
        algorithm.add(new AttackKnight());
        algorithm.add(new HolyShield());
        algorithm.add(new Fortify());
        addPerk(new ReactiveShield());
    }

    public void setUpgradePool()
    {

    }

    public void setActionPool()
    {
        super.setActionPool();

        // Attack
        actionPool.add(FrostShield.class);
    }

    public void setCodePool()
    {
        super.setCodePool();
    }

    public void setPerkPool()
    {
        super.setPerkPool();

        perkPool.add(ReactiveShield.class);
    }

}
