package unit.hero.cleric.actions.runepriest;

import unit.ability.action.*;
import unit.ability.conditions.buff.Strength;
import ui.Images;
import ui.sound.Sounds;

public class DivineStrength extends Action
{
    public void setup()
    {
        name = "Divine Strength";

        addTag(Tag.BUFF);
        setAnimation(MovementType.CAST);
        setUpgrade(Upgrade.MAX_MANA);
    }

    public void rarity()
    {
        mana = 3 + getRarity().getValue();
        addCondition(Strength.class, 2 + getRarity().getValue());
    }

    public void setTarget()
    {
        targets.add(getAllyWithMostActions(Tag.ATTACK, Tag.PHYSICAL));
    }

    public void use()
    {
        applyConditions(getTarget());
        downgrade();

    }

    public void sound()
    {
        Sounds.buffHoly.play();
    }

    public void animation()
    {
        animate(Images.animCastingGray, self(), Tag.HEAL.getColor());
        animate(Images.animHeal, getTarget());
    }

    public String getDescription()
    {
        String desc = "The ally with the most physical attacks gains [BUFF]Strength[] " + getStacksText() + ".";

//        if(getCurrentRarity().getValue() > 0)
//        {
//            desc += " [KEY]Downgrade.";
//        }
        return desc;
    }
}
