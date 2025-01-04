package unit.hero.warrior.actions;

import unit.ability.action.*;
import ui.Images;
import ui.sound.Sounds;

public class AttackWarrior extends Action
{
    public void setup()
    {
        name = "Attack";
        setRarity(Rarity.STARTER);
        addTag(Tag.ATTACK);
        addTag(Tag.PHYSICAL);
        setAnimation(MovementType.RUSH);
    }

    public void rarity()
    {
        damage = scale(9);
        speed = Speed.FAST;
    }

    public void setTarget()
    {
        targets.add(getFrontEnemy());
    }

    public void use()
    {
        damage(getTarget(), DamageType.PHYSICAL);
        applyConditions(getTarget());
    }

    public void sound()
    {
        Sounds.slashMetal.play();
    }

    public void animation()
    {
        animate(Images.animSlash, getTarget());
        animate(Images.animSlashing, self());
    }

    public String getDescription()
    {
        return "Deal " + getDamageText() + " [PHYSICAL]Physical Damage[] to the front enemy.";
    }
}
