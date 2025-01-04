package unit.hero.warrior.actions;

import unit.ability.action.*;
import ui.Images;
import ui.sound.Sounds;

public class DefensiveShift extends Action
{
    public void setup()
    {
        name = "Defensive Shift";
        addTag(Tag.BLOCK);
        setUpgrade(Upgrade.DEFENSE);
    }

    public void rarity()
    {
        block = scale(6);
        speed = Speed.FAST;
    }

    public void setMovement()
    {
        if (hasBackwardCell())
        {
            movement = MovementType.NONE;
        }
        else
        {
            movement = MovementType.BLOCK;
        }
    }

    public void setTarget()
    {
        if (hasBackwardCell() && getBackwardCell().hasUnit())
        {
            targets.add(getBackwardCell().getUnit());
        }
        else
        {
            targets.add(self());
        }
    }

    public void use()
    {
        retreat();
        block(getTarget());
    }

    public void sound()
    {
        Sounds.armorUp.play();
    }

    public void animation()
    {
        animate(Images.animShield, getTarget());
    }

    public String getDescription()
    {
        return "[KEY]Retreat[] one space and gain " + getBlockText() + " [BLOCK]Block[].  If you swapped positions with an ally, they gain the block instead.";
    }
}
