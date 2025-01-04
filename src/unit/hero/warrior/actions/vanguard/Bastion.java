package unit.hero.warrior.actions.vanguard;

import ui.Images;
import ui.sound.Sounds;
import unit.ability.action.*;

public class Bastion extends Action
{
    public void setup()
    {
        name = "Bastion";
        addTag(Tag.BLOCK);
        setAnimation(MovementType.BLOCK);
        setUpgrade(Upgrade.DEFENSE);
    }

    public void rarity()
    {
        block = scale(6);
    }

    public void setTarget()
    {
        targets.add(self());
    }

    public void use()
    {
        if(getFrontAlly() == self())
        {
            block(self(), 1.5f);
        }
        else
        {
            block();
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
        return "Gain " + getBlockText() + " [BLOCK]Block[].  If you are the front hero, add "
                + getBlockText(.5f) + " more [BLOCK]Block[]." ;
    }
}
