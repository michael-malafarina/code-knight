package unit.enemy.goblin;

import animation.Animation;
import ui.Images;
import unit.enemy.goblin.actions.general.GoblinPunch;
import unit.enemy.goblin.actions.reaver.GoblinRage;
import unit.enemy.goblin.actions.reaver.GoblinSwing;

public class GoblinReaver extends Goblin
{
    public GoblinReaver()
    {
       super();

        name = "Goblin Reaver";
        value = 6;

        animation = new Animation(Images.goblinReaver);

        addMaxHealth(145);
        addSpeedPerTurn(10);

     //   algorithm.add(new Advance());
//        algorithm.add(new AetherBlast());
        algorithm.add(new GoblinSwing());
        algorithm.add(new GoblinSwing());
        algorithm.add(new GoblinRage());




    }
}
