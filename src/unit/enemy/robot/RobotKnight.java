package unit.enemy.robot;

import unit.enemy.goblin.actions.warrior.GoblinShield;
import unit.enemy.goblin.actions.warrior.GoblinSlash;
import unit.enemy.goblin.actions.warrior.GoblinWarcry;
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



    }

    public void setAbilities()
    {
        addAction(new AttackWarrior());
        addAction(new Protect());
    }
}
