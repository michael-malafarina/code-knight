package unit;

import battlefield.Battlefield;
import battlefield.Row;

public enum Team
{
    PLAYER, ENEMY;

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
