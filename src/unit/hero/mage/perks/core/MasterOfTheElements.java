package unit.hero.mage.perks.core;

import unit.ability.action.Action;
import unit.ability.perks.Perk;
import ui.Images;

public class MasterOfTheElements extends Perk
{
    public void setup()
    {
        name = "Master Of The Elements";
        icon = Images.perkMasterOfTheElements;
    }

    public String getDescription()
    {
        return "TODO";
    }

    public void onActionUsed(Action a)
    {

    }
    /*

    10% chance on action (or every 10 times) or per energy spent

    gain damage, defense, or speed based on element cast



     */
}
