package unit.hero.warrior.perks;

import unit.ability.perks.Perk;
import ui.Images;

public class BoundlessEndurance extends Perk
{
    public void setup()
    {
        name = "Boundless Endurance";
        icon = Images.perkBoundlessEndurance;
    }

    public void onEndTurn()
    {
        if(getOwner().isLowHealth())
        {
            getOwner().regainHealth(3);
        }
    }

    public String getDescription()
    {
        return "When you end your turn below 50% health, regain 3 HP";
    }
}
