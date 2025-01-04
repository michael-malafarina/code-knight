package unit.hero.warrior.actions.warden;

import unit.ability.action.*;
import unit.ability.conditions.buff.Regen;
import ui.Images;
import ui.sound.Sounds;

public class Regrowth extends Action
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
        healing = 3;
        addCondition(Regen.class, 2 + getRarity().getValue());
    }

    public void setTarget()
    {
        targets.add(getMostDamagedAlly());
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
