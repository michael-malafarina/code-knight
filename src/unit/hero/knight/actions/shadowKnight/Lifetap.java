package unit.hero.knight.actions.shadowKnight;

import ui.Images;
import ui.sound.Sounds;
import unit.ability.action.*;

public class Lifetap extends Action
{
    public void setup()
    {
        name = "Lifetap";
        setRarity(Rarity.COMMON);
        addTag(Tag.ATTACK);
        addTag(Tag.SHADOW);
        addTag(Tag.HEAL);
        setAnimation(MovementType.CAST);
    }

    public void rarity()
    {
        mana = 2;
        damage = scale(7);
        healing = Math.round(damage * .5f);
    }

    public void setTarget()
    {
        targets.add(getLowestHealthEnemy());
    }

    public void use()
    {
        damage(DamageType.SHADOW);
        heal();
    }

    public void sound()
    {
        Sounds.fireballFlames.play();
    }

    public void animation()
    {
        animate(Images.animCastingGray, self());
        animate(Images.animDark);
    }

    public String getDescription()
    {
        return "Deal " + getDamageText() + " [SHADOW]Shadow Damage[] to the lowest health enemy, and [HEAL]Heal[] " + getDamageText(.5f) + " HP.";
    }
}
