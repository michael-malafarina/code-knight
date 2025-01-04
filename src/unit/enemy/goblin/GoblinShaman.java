package unit.enemy.goblin;

import animation.Animation;
import ui.Images;
import unit.enemy.goblin.actions.shaman.GoblinCurse;
import unit.enemy.goblin.actions.shaman.GoblinHeal;

public class GoblinShaman extends Goblin
{
    public GoblinShaman()
    {
       super();

        name = "Goblin Shaman";
        value = 5;

        animation = new Animation(Images.goblinShaman);

        addMaxHealth(75);
        addSpeedPerTurn(5);
        addMaxMana(16);

        algorithm.add(new GoblinCurse());
        algorithm.add(new GoblinCurse());
        algorithm.add(new GoblinCurse());
        algorithm.add(new GoblinHeal());

    }
}
