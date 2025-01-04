package unit.ability.action.code.conditional;

import unit.Unit;

public class IfAllyLowHealth extends Conditional
{
    public void setup()
    {
        super.setup();
        name = "If Ally Low Health";
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
        return "If you or at least one ally is below 50% health, activate the next ability.";
    }
}
