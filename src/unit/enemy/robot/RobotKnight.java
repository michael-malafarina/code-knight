package unit.enemy.robot;

import unit.hero.knight.actions.paladin.HolyShield;
import unit.hero.knight.actions.Protect;
import unit.hero.knight.actions.SteelYourself;
import unit.hero.warrior.actions.AttackWarrior;
import animation.Animation;
import ui.Images;
import unit.enemy.Enemy;

public class RobotKnight extends Enemy
{
    public RobotKnight()
    {
       super();
        name = "Robot Knight";
        value = 5;

        animation = new Animation(Images.robotKnight);

        setEnergyPerTurn(2);
        addMaxMana(2);
        addMaxHealth(70);

        algorithm.add(new AttackWarrior());
        algorithm.add(new Protect());
        algorithm.add(new SteelYourself());
        algorithm.add(new HolyShield());

    }
}
