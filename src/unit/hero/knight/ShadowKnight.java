package unit.hero.knight;

import animation.Animation;
import core.Color;
import ui.Images;
import unit.hero.knight.actions.Defend;
import unit.hero.knight.actions.shadowKnight.Blackwall;
import unit.hero.knight.actions.shadowKnight.Shroud;
import unit.hero.knight.actions.shadowKnight.DarkStrike;
import unit.hero.knight.actions.shadowKnight.Lifetap;
import unit.hero.knight.perks.ReactiveShield;

public class ShadowKnight extends Knight
{
    public void setup()
    {
        super.setup();

        name = "Shadow Knight";
        description = "A defensive tank focused on [BLOCK]Block[], [SHADOW] Shadow Damage[], and Lifesteal.";

        icon = Images.iconShadowKnight;
        animation = new Animation(Images.shadowknight);
        animation.setHero();
        classColor = new Color(100, 100, 160);
    }

    public void setStartingAttributes()
    {
        super.setStartingAttributes();
        addMaxHealth(10);
        addStrength(1);

    }

    public void setStartingAbilities()
    {
        addAction(new DarkStrike());
        addAction(new Defend());

//        addPerk(new GrimAura());
    }

    public void setUpgradePool()
    {
        super.setUpgradePool();
    }

    public void setActionPool()
    {
        super.setActionPool();

        // Attack
        actionPool.add(Lifetap.class);
        actionPool.add(DarkStrike.class);
        actionPool.add(Shroud.class);

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
