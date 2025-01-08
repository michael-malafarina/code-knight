package unit.enemy.robot;

import unit.hero.knight.actions.Protect;
import unit.hero.mage.actions.AttackMage;
import unit.hero.mage.actions.fire.Burn;
import unit.hero.mage.actions.fire.Emberstorm;
import animation.Animation;
import ui.Images;
import unit.enemy.Enemy;
import unit.hero.warrior.actions.AttackWarrior;

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


    }

    public void setAbilities()
    {
        addAction(new Burn());
        addAction(new Emberstorm());
    }
}
