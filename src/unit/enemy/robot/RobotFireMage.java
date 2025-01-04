package unit.enemy.robot;

import unit.hero.mage.actions.AttackMage;
import unit.hero.mage.actions.fire.Burn;
import unit.hero.mage.actions.fire.Emberstorm;
import animation.Animation;
import ui.Images;
import unit.enemy.Enemy;

public class RobotFireMage extends Enemy
{
    public RobotFireMage()
    {
       super();
        name = "Robot Fire Mage";
        value = 6;

        animation = new Animation(Images.robotFireMage);

        setEnergyPerTurn(2);
        addMaxHealth(50);
        addMaxMana(10);

        algorithm.add(new AttackMage());
        algorithm.add(new Burn());
        algorithm.add(new AttackMage());
        algorithm.add(new Emberstorm());
    }
}
