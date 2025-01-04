package unit.hero.cleric.actions;

import unit.ability.action.Action;
import unit.ability.action.MovementType;
import unit.ability.action.Tag;
import unit.ability.action.Upgrade;
import ui.Images;
import ui.sound.Sounds;

public class Prayer extends Action
{
    public void setup()
    {
        name = "Prayer";

        addTag(Tag.BUFF);
        setAnimation(MovementType.CAST);
        setUpgrade(Upgrade.MAX_MANA);

    }

    public void rarity()
    {
        manaGain = 8 + getRarity().getValue() * 2;
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
        return "Gain " + manaGain + " [MANA]Mana[].";
    }
}
