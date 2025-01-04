package unit.hero.mage.actions;

import unit.ability.action.*;
import core.Utility;
import ui.Images;
import ui.sound.Sounds;
import unit.Unit;

public class ChaosBolt extends Action
{
    DamageType currentType;

    public void setup()
    {
        name = "Chaos Bolt";


        addTag(Tag.ATTACK);
        addTag(Tag.MAGICAL);
        addTag(Tag.FIRE);
        addTag(Tag.COLD);
        addTag(Tag.LIGHTNING);
        setAnimation(MovementType.CAST);
        setUpgrade(Upgrade.FOCUS);
    }

    public void rarity()
    {
        mana = 2;
        damage = scale(12);
    }

    public void setTarget()
    {
        Unit one = getRandomEnemy();
//        Unit two = getRandomEnemy();
//
//        if(getEnemies().size() > 1 && one == two)
//        {
//            setTarget();
//        }

        targets.add(one);
//        targets.add(two);

    }

    public void use()
    {
        damage(getTarget(), currentType);
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

    }

    public String getDescription()
    {
        return "Deal " + getDamageText() + " [FIRE]Fire[], [COLD]Cold[], or [LIGHTNING]Lightning[] damage to a random enemy.";
    }
}
