package unit.enemy.skeleton;

import unit.enemy.skeleton.actions.skeleton.Repel;
import unit.enemy.skeleton.actions.skeleton.Skewer;
import animation.Animation;
import ui.Images;
import unit.enemy.Enemy;

public class Skeleton extends Enemy
{
    public Skeleton()
    {
       super();

        name = "Skeleton";
        value = 2;

        animation = new Animation(Images.skeleton);

        energyPerTurn = 1;
        addMaxHealth(40);

     //   algorithm.add(new Advance());
//        algorithm.add(new AetherBlast());
        algorithm.add(new Skewer());
        algorithm.add(new Skewer());
        algorithm.add(new Repel());

    }
}
