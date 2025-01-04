package unit.enemy.goblin.actions.shaman;

import unit.ability.action.*;
import unit.ability.conditions.buff.Regen;
import ui.Images;
import ui.sound.Sounds;

public class GoblinHeal extends EnemyAction
{
    public void setup()
    {
        name = "Heal";
        mana = 2;
        healing = 15;
        speed = Speed.AVERAGE;

        setRarity(Rarity.UNCOMMON);
        addTag(Tag.HEAL);
        setAnimation(MovementType.CAST);
    }

    public void setTarget()
    {
        targets.addAll(getAllies());
    }

    public void use()
    {
        heal();
    }

    public void sound()
    {
        Sounds.heal.play();
    }

    public void animation()
    {
        animate(Images.animCastingGray, self(), Tag.HEAL.getColor());
        animate(Images.animHeal, getTarget());
    }

    public String getDescription()
    {
        return "[HEAL]Heal[]" + " all allies for " + getHealingText() + " HP.";
    }
}
