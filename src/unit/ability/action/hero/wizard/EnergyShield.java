package unit.ability.action.hero.wizard;

import unit.ability.action.Action;
import unit.ability.action.MovementType;
import unit.ability.action.Tag;
import unit.ability.action.Upgrade;
import unit.ability.conditions.buff.Power;
import ui.Images;
import ui.sound.Sounds;

public class EnergyShield extends Action
{
    public void setup()
    {
        name = "Energy Shield";

        addTag(Tag.BLOCK);
        setAnimation(MovementType.BLOCK);
        setUpgrade(Upgrade.DEFENSE);
    }

    public void rarity()
    {
        mana = 2;
        block = scale(8) - 3;
        addCondition(Power.class, 2);

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
        Sounds.armorUp.play();
    }

    public void animation()
    {
        animate(Images.animShield, self());
    }

    public String getDescription()
    {
        return "The most damaged ally gains " + getBlockText() + " [BLOCK]Block[] and [BUFF]Might[] " + getStacksText() + ".";
    }
}
