package unit.enemy.skeleton;

import unit.enemy.skeleton.actions.skeleton.Repel;
import unit.enemy.skeleton.actions.skeleton.Skewer;
import animation.Animation;
import ui.Images;
import unit.enemy.Enemy;
import unit.hero.mage.actions.fire.Burn;
import unit.hero.mage.actions.fire.Emberstorm;

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


    }

    public void setAbilities()
    {
        addAction(new Skewer());
        addAction(new Repel());
    }
}
