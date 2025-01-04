package unit.enemy.goblin;

import animation.Animation;
import ui.Images;
import unit.enemy.goblin.actions.general.GoblinBlock;
import unit.enemy.goblin.actions.general.GoblinPunch;

public class GoblinBasic extends Goblin
{
    public GoblinBasic()
    {
       super();

        name = "Goblin";
        value = 2;

        animation = new Animation(Images.goblin);

        addMaxHealth(65);
        addSpeedPerTurn(10);

        algorithm.add(new GoblinPunch());
        algorithm.add(new GoblinBlock());
        algorithm.add(new GoblinPunch());

    }
}
