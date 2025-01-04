package unit.hero.cleric.perks;

import unit.ability.perks.Perk;
import ui.Images;

public class Purity extends Perk
{

    public void setup()
    {
        name = "Purity";
        icon = Images.perkPurity;
    }

    public void onEndTurnSecond()
    {
        getOwner().getModifiers().removeRandomDebuffStacks(2);
    }

    public String getDescription()
    {
        return "At the end of your turn, remove two stacks of a random [DEBUFF]Debuff[].";
    }

}
