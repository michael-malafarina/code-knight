package unit.hero.knight.actions;

import unit.ability.action.*;
import ui.Images;
import ui.sound.Sounds;

public class Protect extends Action
{
    public void setup()
    {
        name = "Protect";

        addTag(Tag.BLOCK);
        setUpgrade(Upgrade.DEFENSE);
    }


    public void rarity()
    {
        speed = Speed.SLOW;
        block = scale(8);
    }

    public void setTarget()
    {
        targets.add(self());
    }

    public void setMovement()
    {
        if(hasForwardCell())
        {
            movement = MovementType.NONE;
        }
        else
        {
            movement = MovementType.BLOCK;
        }
    }

    public void use()
    {
        advance();
        block();

        if(getBackwardCell().hasUnit())
        {
            block(getBackwardCell().getUnit(), .5f);
        }
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
        return "Gain " + getBlockText() + " [BLOCK]Block[] and [KEY]Advance[] one space." ;

    }
}
