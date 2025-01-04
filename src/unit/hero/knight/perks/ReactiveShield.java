package unit.hero.knight.perks;

import unit.ability.perks.Perk;
import ui.Images;
import ui.sound.Sounds;

public class ReactiveShield extends Perk
{

    public void setup()
    {
        name = "Reactive Shield";
        icon = Images.perkReactiveShield;
    }

    public void onBloodied()
    {
        self.addBlock(getBlock(15));
    }

    public void sound()
    {
        Sounds.armorUp.play();
    }

    public void animation()
    {
        animate(Images.animShield, self());
    }


    public String getDescription()
    {
        return "The first time your HP is reduced to 50% each battle, gain 15 [BLOCK]Block[].";
    }
}
