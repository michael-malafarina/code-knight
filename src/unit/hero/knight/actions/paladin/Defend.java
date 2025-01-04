package unit.hero.knight.actions.paladin;

import core.Color;
import ui.Images;
import ui.sound.Sounds;
import unit.ability.action.*;
import unit.ability.conditions.buff.Barrier;

public class Defend extends Action
{
    public void setup()
    {
        name = "Defend";
        addTag(Tag.BLOCK);
        setAnimation(MovementType.BLOCK);
        setUpgrade(Upgrade.DEFENSE);
    }

    public void rarity()
    {
        mana = 0;
        speed = Speed.SLOW;
        block = scale(11);
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
        return "Gain " + getBlockText() + " [BLOCK]Block[].";
    }
}
