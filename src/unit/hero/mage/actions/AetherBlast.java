package unit.hero.mage.actions;

import unit.ability.action.*;
import battlefield.Cell;
import core.Utility;
import ui.Images;
import ui.sound.Sounds;

public class AetherBlast extends Action
{
    DamageType currentType;

    public void setup()
    {
        name = "Aether Blast";

        addTag(Tag.ATTACK);
        addTag(Tag.MAGICAL);
        addTag(Tag.FIRE);
        addTag(Tag.COLD);
        addTag(Tag.LIGHTNING);

        setAnimation(MovementType.CAST);
        setUpgrade(Upgrade.MAX_MANA);
    }

    public void rarity()
    {
        mana = 8;
        damage = scale(16);
    }

    public void setTarget()
    {
        targets.add(getLowestHealthEnemy());
    }

    public void use()
    {
        damage(getTarget(), currentType);

        Cell ahead = getForwardCell(getTarget());
        Cell behind = getBackwardCell(getTarget());

        if(ahead != null && ahead.hasUnit())
        {
            damage(ahead.getUnit(), currentType, .5f);
        }

        if(behind != null && behind.hasUnit())
        {
            damage(behind.getUnit(), currentType, .5f);
        }
    }

    public void sound()
    {
        if(currentType == DamageType.FIRE)
        {
            Sounds.fireballImpact.play();
        }
        else  if(currentType == DamageType.COLD)
        {
            Sounds.fireballImpact.play();
        }
        else
        {
            Sounds.fireballImpact.play();
        }
    }

    public void animation()
    {
        int random = Utility.random(3);

        switch (random)
        {
            case 0:
                currentType = DamageType.FIRE;
                animate(Images.animFlame, getTarget());
                break;
            case 1:
                currentType = DamageType.COLD;
                animate(Images.animCold, getTarget());
                break;
            case 2:
                currentType = DamageType.LIGHTNING;
                animate(Images.animHoly, getTarget());
                break;
        }

        animate(Images.animCastingGray, self(), currentType.getColor());

        Cell ahead = getForwardCell(getTarget());
        Cell behind = getBackwardCell(getTarget());

        if(ahead != null && ahead.hasUnit())
        {
            animate(Images.animCastingGray, ahead.getUnit(), currentType.getColor(), .5f);
        }

        if(behind != null && behind.hasUnit())
        {
            animate(Images.animCastingGray, behind.getUnit(), currentType.getColor(), .5f);
        }


    }

    public String getDescription()
    {
        return "Deal " + getDamageText() + " [FIRE]Fire[], [COLD]Cold[], or [LIGHTNING]lightning[] damage to the lowest health enemy, and half damage to enemies in adjacent spaces.";
    }
}
