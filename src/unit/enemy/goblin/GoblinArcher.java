package unit.enemy.goblin;

import animation.Animation;
import ui.Images;
import unit.enemy.goblin.actions.archer.GoblinAim;
import unit.enemy.goblin.actions.archer.GoblinShoot;
import unit.enemy.goblin.actions.archer.GoblinSnipe;

public class GoblinArcher extends Goblin
{
    public GoblinArcher()
    {
       super();

        name = "Goblin Archer";
        value = 3;

        animation = new Animation(Images.goblinArcher);

        addMaxHealth(55);
        addSpeedPerTurn(15);

        algorithm.add(new GoblinShoot());
        algorithm.add(new GoblinShoot());
        algorithm.add(new GoblinAim());
        algorithm.add(new GoblinSnipe());


    }
}
