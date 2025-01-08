package unit.hero.mage.actions;

import ui.Images;
import ui.sound.Sounds;
import unit.ability.action.Action;
import unit.ability.action.MovementType;
import unit.ability.action.Tag;
import unit.ability.action.Upgrade;

public class Energize extends Action
{
    public void setup()
    {
        name = "Energize";

        addTag(Tag.BUFF);
        setAnimation(MovementType.CAST);
        setUpgrade(Upgrade.MAX_MANA);

    }

    public void rarity()
    {
        manaGain = 2 + getRarity().getValue() * 2;
    }

    public void setTarget()
    {
        targets.addAll(getAllies());
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
