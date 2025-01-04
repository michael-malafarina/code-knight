package unit.ability.action.code.conditional;

import unit.Unit;

public class IfEnemyLowHealth extends Conditional
{
    public void setup()
    {
        super.setup();
        name = "If Enemy Low Health";
    }

    public boolean conditionIsTrue()
    {
        for(Unit u : getEnemies())
        {
            if(u.isLowHealth())
            {
                return true;
            }
        }
        return false;
    }

    public String getDescription()
    {
        return "If at least one enemy is below 50% health, activate the next ability.";
    }
}
