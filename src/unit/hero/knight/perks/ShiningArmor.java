package unit.hero.knight.perks;

import unit.ability.perks.Perk;
import ui.Images;
import ui.sound.Sounds;

public class ShiningArmor extends Perk
{
    public ShiningArmor()
    {
        name = "Shining Armor";
        icon = Images.perkShiningArmor;

    }

    public void setup()
    {
        name = "Shining Armor";
        icon = Images.perkShiningArmor;
    }


    public void sound()
    {
        Sounds.heal.play();
    }

    public void animation()
    {
        animate(Images.animHeal, self(), .4f);
    }

    public void onEndTurn()
    {
        if(getOwner().getBlock() > 0)
        {
            getOwner().regainHealth(2);
        }
    }

    public String getDescription()
    {
        return "When you end your turn with any [BLOCK]Block[], you regain 2 HP.";
    }
}
