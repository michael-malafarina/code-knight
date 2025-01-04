package unit.hero.mage.actions.fire;

import ui.Images;
import ui.sound.Sounds;
import unit.ability.action.*;
import unit.ability.conditions.buff.Focus;

public class InnerFire extends Action
{
    public void setup()
    {
        name = "Inner Fire";

        addTag(Tag.BUFF);
        setAnimation(MovementType.CAST);
        setUpgrade(Upgrade.MAX_MANA);

    }

    public void rarity()
    {
        manaGain = 2 + getRarity().getValue() * 2;
        addCondition(Focus.class,  1);
        speed = Speed.VERY_FAST;
    }

    public void setTarget()
    {
        targets.add(self());
    }

    public void use()
    {
        gainMana(self());
    }

    public void sound()
    {
        Sounds.buffHoly.play();
    }

    public void animation()
    {
        animate(Images.animCastingGray, self(), Tag.MANA.getColor());
    }

    public String getDescription()
    {
        return "Gain [BUFF]Focus[]" + getStacksText() + " and " + manaGain + " [MANA]Mana[].";
    }
}
