package unit.enemy.goblin.perks;

import unit.ability.perks.Perk;
import ui.Images;
import ui.sound.Sounds;

public class Shifty extends Perk
{

    public void setup()
    {
        name = "Shifty";
        icon = Images.perkShifty;
    }

    public void onBloodied()
    {
        retreat();
        Sounds.goblinHurt.play();
    }

    public String getDescription()
    {
        return "The first time you are reduced below 50% health, [KEY]Retreat[] one.";
    }
}
