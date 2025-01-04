package unit.hero.knight.actions;

import unit.ability.action.Action;
import unit.ability.action.Tag;
import unit.ability.action.Upgrade;
import unit.ability.conditions.buff.Barrier;
import ui.Images;
import ui.sound.Sounds;

public class RaiseShield extends Action
{
    public void setup()
    {
        name = "Raise Shield";

        addTag(Tag.BLOCK);
        setUpgrade(Upgrade.DEFENSE);
    }

    public void rarity()
    {
        block = scale(9);
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
        return "Gain " + getBlockText() + " [BLOCK]Block[] and add [BUFF]Barrier[] 1." ;

    }
}
