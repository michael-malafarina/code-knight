package unit.hero.knight.actions;

import unit.ability.action.*;
import ui.Images;
import ui.sound.Sounds;

public class SteelYourself extends Action
{
    public void setup()
    {
        name = "Steel Yourself";
        addTag(Tag.BLOCK);
        setUpgrade(Upgrade.DEFENSE);
    }

    public void rarity()
    {
        mana = 2;
        speed = Speed.SLOW;
        block = 4 + getRarity().getValue() * 2;
    }

    public void setTarget()
    {
        targets.add(self());
    }

    public void use()
    {
        applyConditions(getTarget());
        getTarget().getModifiers().removeAllDebuffs();
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
        return "Gain [BLOCK]Block[] " + getBlockText() + " and remove all stacks of [Debuff]Debuffs[].";
    }
}
