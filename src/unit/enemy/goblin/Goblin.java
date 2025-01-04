package unit.enemy.goblin;

import core.Color;
import unit.enemy.Enemy;
import unit.enemy.goblin.perks.Shifty;

public class Goblin extends Enemy
{
    Goblin()
    {
        super();
        classColor = new Color(0, 160, 0);
        addPerk(new Shifty());
    }
}
