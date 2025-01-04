package unit.hero.warrior.actions;

import unit.ability.action.*;
import ui.Images;
import ui.sound.Sounds;

public class Parry extends Action
{
    public void setup()
    {
        name = "Parry";
        addTag(Tag.BLOCK);
        setAnimation(MovementType.BLOCK);
        setUpgrade(Upgrade.DEFENSE);
    }

    public void rarity()
    {
        speed = Speed.FAST;
        block = scale(8);
    }

    public void setTarget()
    {
        targets.add(self());
    }


    public void use()
    {
        block();
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
        return "Gain " + getBlockText() + " [BLOCK]Block[]." ;
    }
}
