package unit.hero.warrior.actions.warden;

import ui.Images;
import ui.sound.Sounds;
import unit.ability.action.*;
import unit.ability.conditions.buff.Thorns;

public class Thornskin extends Action
{
    public void setup()
    {
        name = "Thornskin";
        addTag(Tag.BLOCK);
        setAnimation(MovementType.BLOCK);
    }

    public void rarity()
    {
        mana = 1;
        block = 6;
        addCondition(Thorns.class, 3 + getRarity().getValue());
    }

    public void setTarget()
    {
        targets.add(self());
    }

    public void use()
    {
        block();
        applyConditions();
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
        return "Gain " + getBlockText() + " [BLOCK]Block[] and [BUFF]Thorns[] " + getStacksText() + ".";
    }
}
