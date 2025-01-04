package unit.ability.action.hero.druid;

import unit.ability.action.Action;
import unit.ability.action.DamageType;
import unit.ability.action.MovementType;
import unit.ability.action.Tag;
import ui.Images;
import ui.sound.Sounds;

public class VineWhip extends Action
{
    public void setup()
    {
        name = "Vine Whip";
        addTag(Tag.ATTACK);
        addTag(Tag.PHYSICAL);
        setAnimation(MovementType.CAST);
    }

    public void rarity()
    {
        damage = 7;
    }

    public void setTarget()
    {
        targets.add(getLowestHealthEnemy());
    }

    public void use()
    {
        damage(getTarget(), DamageType.PHYSICAL);

        pull(getTarget());


//            Unit front = getFrontEnemy();
//            getSecondEnemy().swapCellLater(front);



    }

    public void sound()
    {
        Sounds.slashMetal.play();
    }

    public void animation()
    {
pull();

        animate(Images.animSlash, getTarget());
        animate(Images.animSlashing, self());

    }

    public String getDescription()
    {
        return "Deal " + getDamageText() + " [PHYSICAL]physical damage[] to the front enemy and [HEAL]heal[] an amount equal to the number of living enemies";
    }
}
