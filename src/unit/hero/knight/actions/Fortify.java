package unit.hero.knight.actions;

import unit.ability.action.*;
import unit.ability.conditions.buff.Defense;
import ui.Images;
import ui.sound.Sounds;

public class Fortify extends Action
{
    public void setup()
    {
        name = "Fortify";

        addTag(Tag.BUFF);
        setUpgrade(Upgrade.DEFENSE);
        setRarity(Rarity.STARTER);
    }

    public void rarity()
    {
        mana = 4;
        addCondition(Defense.class, 2 + getRarity().getValue());
    }

    public void setTarget()
    {
        targets.add(self());
    }

    public void use()
    {
        block();
        applyConditions(getTarget());
//        downgrade();
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
//        if(getRarity() == Rarity.STARTER || getRarity() == Rarity.COMMON)
//        {
            return "Gain [BUFF]Defense[] " + getStacksText();
//        }
//        else
//        {
//            return "Gain [BUFF]Defense[] " + getStacksText() + ". Downgrade.";
//        }

    }
}
