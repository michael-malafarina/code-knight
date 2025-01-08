package unit;

import battlefield.Battlefield;
import battlefield.Row;
import campaign.EnemyManager;
import campaign.HeroManager;
import unit.ability.Algorithm;
import unit.ability.action.Action;

public enum Team
{
    PLAYER, ENEMY;


    public Team getOpponent()
    {
        if(this == PLAYER)
        {
            return ENEMY;
        }
        else
        {
            return PLAYER;
        }
    }

    public Algorithm getAlgorithm()
    {
        if(this == PLAYER)
        {
            return HeroManager.getAlgorithm();
        }
        else
        {
            return EnemyManager.getAlgorithm();
        }
    }

    public Row getRow()
    {
        if(this == PLAYER)
        {
            return Battlefield.getHeroRow();
        }
        else
        {
            return Battlefield.getEnemyRow();
        }
    }

    public Row getEnemyRow()
    {
        if(this == PLAYER)
        {
            return Battlefield.getEnemyRow();
        }
        else
        {
            return Battlefield.getHeroRow();
        }
    }

    public Row getAlliedRow()
    {
        if(this == PLAYER)
        {
            return Battlefield.getHeroRow();
        }
        else
        {
            return Battlefield.getEnemyRow();
        }
    }


}
