package unit.hero.cleric.actions;

import unit.ability.action.Action;
import unit.ability.action.MovementType;
import unit.ability.action.Tag;
import unit.ability.action.Upgrade;
import unit.ability.conditions.buff.Barrier;
import ui.Images;
import ui.sound.Sounds;

public class Sanctuary extends Action
{
    public void setup()
    {
        name = "Sanctuary";
        addTag(Tag.BUFF);
        setAnimation(MovementType.CAST);
    }

    public void rarity()
    {
        mana = 4;
        addCondition(Barrier.class, 2+ getRarity().getValue());
    }

    public void setTarget()
    {
        targets.add(getMostDamagedAlly());
    }

    public void use()
    {
        applyConditions(getTarget());
    }

    public void sound()
    {
        Sounds.buffHoly.play();
    }

    public void animation()
    {
        animate(Images.animCastingGray, self(), Tag.BLOCK.getColor());
        animate(Images.animCastingGray, getTarget(), Tag.BLOCK.getColor());
    }

    public String getDescription()
    {
        return "The most damaged ally gains [BUFF]Barrier[] " + getStacksText() + ".";
    }
}
