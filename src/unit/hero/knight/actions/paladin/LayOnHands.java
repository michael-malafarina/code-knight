package unit.hero.knight.actions.paladin;

import ui.Images;
import ui.sound.Sounds;
import unit.ability.action.Action;
import unit.ability.action.MovementType;
import unit.ability.action.Tag;
import unit.ability.action.Upgrade;

public class LayOnHands extends Action
{
    public void setup()
    {
        name = "Lay On Hands";
        addTag(Tag.HEAL);
        setAnimation(MovementType.CAST);
    }

    public void rarity()
    {
        mana = 2;
        healing = scale(9);
    }

    public void setTarget()
    {
        targets.add(getMostDamagedAlly());
    }

    public void use()
    {
        heal(getTarget());
    }

    public void sound()
    {
        Sounds.heal.play();
    }

    public void animation()
    {
        animate(Images.animCastingGray, self(), Tag.HEAL.getColor());
        animate(Images.animHeal, getTarget());
    }

    public String getDescription()
    {
        return "[HEAL]Heal[]" + " the most damaged ally for " + getHealingText() + " HP.";
    }
}
