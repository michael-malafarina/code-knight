package unit.enemy;

import campaign.EnemyManager;
import campaign.HeroManager;
import unit.Team;
import unit.Unit;
import unit.ability.action.Action;

public abstract class Enemy extends Unit
{
    public Enemy()
    {
        super();
        setTeam(Team.ENEMY);
    }

    abstract public void setAbilities();


    public int value = 1;

    public int getValue()
    {
        return value;
    }

    public void addAction(Action a)
    {
        EnemyManager.getAlgorithm().add(a, this);
    }
}
