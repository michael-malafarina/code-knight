package unit.hero.mage.actions;

import unit.ability.action.Action;
import unit.ability.action.MovementType;
import unit.ability.action.Tag;
import unit.ability.action.Upgrade;
import unit.ability.conditions.buff.FrostShieldBuff;
import ui.Images;
import ui.sound.Sounds;

public class FrostShield extends Action
{
    public void setup()
    {
        name = "Frost Shield";
        addTag(Tag.BLOCK);
        addTag(Tag.COLD);
        setAnimation(MovementType.CAST);
        setUpgrade(Upgrade.DEFENSE);
    }

    public void rarity()
    {
        mana = 4;
        block = scale(6);
        addCondition(FrostShieldBuff.class, 2 + getRarity().getValue());
    }

    public void setTarget()
    {
        targets.add(getMostDamagedAlly());
    }

    public void use()
    {
        block(getTarget());
        applyConditions(getTarget());
    }

    public void sound()
    {
        Sounds.teleport.play();
    }

    public void animation()
    {
        animate(Images.animCastingGray, self(), Tag.COLD.getColor());
        animate(Images.animShield, getTarget());
    }

    public String getDescription()
    {
        return "Add " + getBlock() + " [BLOCK]Block[] and Frost Shield "+ getStacksText() + " to the most damaged ally.";
    }
}
