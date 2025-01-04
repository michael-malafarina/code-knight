package unit.hero.warrior.actions;

import unit.ability.action.*;
import ui.Images;
import ui.sound.Sounds;

public class EndurePain extends Action
{
    int removal;

    public void setup()
    {
        name = "Endure Pain";
        addTag(Tag.HEAL);
        setAnimation(MovementType.STEP);
        setUpgrade(Upgrade.SPIRIT);
    }

    public void rarity()
    {
        mana = 2;

        switch (getRarity())
        {
            case Rarity.COMMON:
                healing = 9;
                removal = 2;
                break;
            case Rarity.UNCOMMON:
                healing = 11;
                removal = 3;
                break;
            case Rarity.RARE:
                healing = 13;
                removal = 4;
                break;
            case Rarity.EPIC:
                healing = 15;
                removal = 5;
                break;
            case Rarity.LEGENDARY:
                healing = 18;
                removal = 6;
                break;

        }
    }

    public void setTarget()
    {
        targets.add(self());
    }

    public void use()
    {
        heal();
        getUnit().getModifiers().removeRandomDebuffStacks(3);
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
        return "[HEAL]Heal " + getHealingText() + " HP and remove 3 [DBEUFF]Debuff[] stacks at random.";
    }
}
