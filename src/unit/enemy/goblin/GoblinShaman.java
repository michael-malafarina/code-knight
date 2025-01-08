package unit.enemy.goblin;

import animation.Animation;
import ui.Images;
import unit.enemy.goblin.actions.rogue.GoblinGrenade;
import unit.enemy.goblin.actions.rogue.GoblinPoisonStab;
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



    }

    public void setAbilities()
    {
        addAction(new GoblinCurse());
        addAction(new GoblinCurse());
//        addAction(new GoblinHeal());
    }
}
