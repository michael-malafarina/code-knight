package unit.hero.druid;

import ui.Images;
import ui.sound.Sounds;
import unit.ability.action.Action;
import unit.ability.action.MovementType;
import unit.ability.action.Tag;

public class Regen extends Action
{
    public void setup()
    {
        name = "Regrowth";
        addTag(Tag.HEAL);
        setAnimation(MovementType.STEP);
    }

    public void rarity()
    {
        mana = 1;
        healing = 2;
        addCondition(unit.ability.conditions.buff.Regen.class, 3 + getRarity().getValue());
    }

    public void setTarget()
    {
        targets.add(self());
    }

    public void use()
    {
        applyConditions();
    }

    public void sound()
    {
        Sounds.heal.play();
    }

    public void animation()
    {
        animate(Images.animHeal, self());
    }

    public String getDescription()
    {
        return "[HEAL]Heal[]" + " the most damaged ally for " + getHealingText() +
                " HP and they gain [BUFF]Regen[] " + getStacksText() + ".";
    }
}
