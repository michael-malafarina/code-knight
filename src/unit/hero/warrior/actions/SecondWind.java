package unit.hero.warrior.actions;

import unit.ability.action.*;
import ui.Images;
import ui.sound.Sounds;

public class SecondWind extends Action
{
    public void setup()
    {
        name = "Second Wind";

        addTag(Tag.HEAL);
        setAnimation(MovementType.STEP);
        setUpgrade(Upgrade.MAX_HP);
    }

    public void rarity()
    {
        mana = 2;
        speed = Speed.SLOW;
        healing = scale(6);
    }

    public void setTarget()
    {
        targets.add(self());
    }

    public void use()
    {
        if(self().isLowHealth())
        {
            heal(self(), 2f);
        }
        else
        {
            heal();
        }
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
        return "[HEAL]Heal " + getHealingText() + " HP. If you are reduced below 50% Max HP, [HEAL]Heal[] " + getHealingText(2f) + " instead.";
    }
}
