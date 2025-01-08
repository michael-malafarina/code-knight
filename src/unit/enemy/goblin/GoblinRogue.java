package unit.enemy.goblin;

import animation.Animation;
import ui.Images;
import unit.enemy.goblin.actions.reaver.GoblinRage;
import unit.enemy.goblin.actions.reaver.GoblinSwing;
import unit.enemy.goblin.actions.rogue.GoblinGrenade;
import unit.enemy.goblin.actions.rogue.GoblinPoisonStab;

public class GoblinRogue extends Goblin
{
    public GoblinRogue()
    {
       super();

        name = "Goblin Rogue";
        value = 4;

        animation = new Animation(Images.goblinRogue);

        addMaxHealth(75);
        addSpeedPerTurn(25);



    }

    public void setAbilities()
    {
        addAction(new GoblinPoisonStab());
        addAction(new GoblinPoisonStab());
//        addAction(new GoblinGrenade());
    }
}
