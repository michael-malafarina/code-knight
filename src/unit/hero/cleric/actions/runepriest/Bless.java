package unit.hero.cleric.actions.runepriest;

import unit.ability.action.Action;
import unit.ability.action.MovementType;
import unit.ability.action.Tag;
import unit.ability.action.Upgrade;
import unit.ability.conditions.buff.Power;
import ui.Images;
import ui.sound.Sounds;
import unit.ability.conditions.buff.Strength;

public class Bless extends Action
{
    public void setup()
    {
        name = "Bless";
        addTag(Tag.BUFF);
        setAnimation(MovementType.CAST);
    }

    public void rarity()
    {
        mana = 2 + getRarity().getValue() * 2;
        addCondition(Power.class, 1 + getRarity().getValue());
    }

    public void setTarget()
    {
        targets.addAll(getAllies());
    }

    public void use()
    {
		applyConditions(getTargets());
    }

    public void sound()
    {
        Sounds.buffHoly.play();
    }

    public void animation()
    {
        animate(Images.animCastingGray, self(), Tag.RADIANT.getColor());
        animate(Images.animCleanse, getTargets());
    }

    public String getDescription()
    {
        return "Each ally gains [BUFF]Power[] " +  getStacksText() + ".";
    }



}
