package unit.ability.action.code.conditional;

import unit.Unit;

public class IfEnemyHighHealth extends Conditional
{
    public void setup()
    {
        super.setup();
        name = "If Enemy High Health";
    }

    public boolean conditionIsTrue()
    {
        for(Unit u : getEnemies())
        {
            if(u.isLowHealth())
            {
                return false;
            }
        }
        return true;
    }

    public String getDescription()
    {
        return "If all enemies have at least 50% health, activate the next ability.";
    }
}
