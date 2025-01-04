package unit.hero.cleric.actions.runepriest;

import ui.Images;
import ui.sound.Sounds;
import unit.ability.action.*;

public class HealingStrike extends Action
{
    public void setup()
    {
        name = "Healing Strike";

        addTag(Tag.ATTACK);
        addTag(Tag.HEAL);
        setAnimation(MovementType.RUSH);
    }

    public void rarity()
    {
        mana = 1;
        damage = scale(7);
        healing = scale(4);
    }

    public void setTarget()
    {
        targets.add(getFrontEnemy());
    }

    public void use()
    {
        damage(DamageType.PHYSICAL);
        heal(getMostDamagedAlly());
    }

    public void sound()
    {
        Sounds.bashHeavy.play();
        Sounds.heal.play();
    }

    public void animation()
    {
        animate(Images.animCastingGray, self(), Tag.HEAL.getColor());
        animate(Images.animHeal, getMostDamagedAlly());
        animate(Images.animBlunt, getTarget());

    }

    public String getDescription()
    {
        return "Deal " + getDamageText() + " [PHYSICAL]Physical Damage[] to the front enemy and [HEAL]Heal[]" + " the most damaged ally for " + getHealingText() + " HP.";
    }
}
