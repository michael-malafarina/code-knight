package unit.hero.cleric.actions;

import unit.ability.action.Action;
import unit.ability.action.MovementType;
import unit.ability.action.Tag;
import unit.ability.action.Upgrade;
import ui.Images;
import ui.sound.Sounds;

public class MassHeal extends Action
{
    public void setup()
    {
        name = "Mass Heal";

        addTag(Tag.HEAL);
        setAnimation(MovementType.CAST);
        setUpgrade(Upgrade.SPIRIT);

    }

    public void rarity()
    {
        mana = 4;
        healing = scale(8);
    }

    public void setTarget()
    {
        targets.addAll(getAllies());
    }

    public void use()
    {
        heal(getTargets());
    }

    public void sound()
    {
        Sounds.heal.play();
    }

    public void animation()
    {
        animate(Images.animCastingGray, self(), Tag.HEAL.getColor());
        animate(Images.animHeal, getTargets());
    }

    public String getDescription()
    {
        return "[HEAL]Heal[]" + " all allies for " + getHealingText() + " HP.";
    }
}
