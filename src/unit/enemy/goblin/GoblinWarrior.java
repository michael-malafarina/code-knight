package unit.enemy.goblin;

import animation.Animation;
import ui.Images;
import unit.enemy.goblin.actions.warrior.GoblinShield;
import unit.enemy.goblin.actions.warrior.GoblinSlash;
import unit.enemy.goblin.actions.warrior.GoblinWarcry;

public class GoblinWarrior extends Goblin
{
    public GoblinWarrior()
    {
       super();

        name = "Goblin Warrior";
        value = 3;

        animation = new Animation(Images.goblinWarrior);

        addMaxHealth(95);
        addSpeedPerTurn(5);

        algorithm.add(new GoblinSlash());
        algorithm.add(new GoblinSlash());
        algorithm.add(new GoblinShield());
        algorithm.add(new GoblinWarcry());

    }
}
