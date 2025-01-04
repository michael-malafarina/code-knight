package unit.hero.knight.actions.shadowKnight;

import core.Color;
import ui.Images;
import ui.sound.Sounds;
import unit.ability.action.*;
import unit.ability.conditions.buff.Evade;

public class Shroud extends Action
{
    int counter = 0;
    int usesNeeded;

    public void setup()
    {
        name = "Shroud";
        addTag(Tag.BLOCK);
        setAnimation(MovementType.BLOCK);
        setUpgrade(Upgrade.DEFENSE);
    }

    public void rarity()
    {
        block = 5;
        usesNeeded = 5-getRarity().getValue();
        addCondition(Evade.class, 1);
    }

    public void setTarget()
    {
        targets.add(self());
    }

    public void use()
    {
        block();
        counter++;

        if(counter == usesNeeded)
        {
            applyConditions();
            counter = 0;
        }
    }

    public void sound()
    {
        Sounds.armorUp.play();
    }

    public void animation()
    {
        animate(Images.animShield, self());
        animate(Images.animCastingGray, self(), new Color(255, 255, 0));
    }

    public String getDescription()
    {
        return "Gain " + getBlockText() + " [BLOCK]Block[].  Every " + usesNeeded + " uses, gain [KEY]Evade[] 1.";
    }
}
