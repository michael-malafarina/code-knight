package unit.hero.cleric.actions;

import unit.ability.action.Action;
import unit.ability.action.MovementType;
import unit.ability.action.Tag;
import unit.ability.action.Upgrade;
import ui.Images;
import ui.sound.Sounds;

public class Cure extends Action
{
    public void setup()
    {
        name = "Cure";

        addTag(Tag.HEAL);
        setAnimation(MovementType.CAST);
        setUpgrade(Upgrade.SPIRIT);

    }

    public void rarity()
    {
        mana = 4;
        healing = scale(8);
    }




    public void setTarget()
    {
        targets.add(getMostDamagedAlly());
    }

    public void use()
    {
        heal(getTarget());

        getTarget().getModifiers().removeRandomDebuff();
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
        return "[HEAL]Heal[]" + " the most damaged ally for " + getHealingText() + " HP and remove all stacks of 1 random debuff.";
    }
}
