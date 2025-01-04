package unit.hero.knight.actions.paladin;

import core.Color;
import unit.ability.action.*;
import ui.Images;
import ui.sound.Sounds;
import unit.ability.conditions.buff.Barrier;

public class HolyShield extends Action
{
    public void setup()
    {
        name = "Holy Shield";
        addTag(Tag.BLOCK);
        setAnimation(MovementType.BLOCK);
        setUpgrade(Upgrade.DEFENSE);
    }

    public void rarity()
    {
        mana = 1;
        speed = Speed.SLOW;
        addCondition(Barrier.class, 1);
        block = scale(11)-5;
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
        animate(Images.animCastingGray, self(), new Color(255, 255, 0));
    }

    public String getDescription()
    {
        return "Gain " + getBlockText() + " [BLOCK]Block[] and add [BUFF]Barrier[] " + getStacksText();
    }
}
