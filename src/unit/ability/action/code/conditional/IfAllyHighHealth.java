package unit.ability.action.code.conditional;

import unit.Unit;

public class IfAllyHighHealth extends Conditional
{
    public void setup()
    {
        super.setup();
        name = "If Ally High Health";
    }

    public boolean conditionIsTrue()
    {
        for(Unit u : getAllies())
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
        return "If you and all allies have at least 50% health, activate the next ability.";
    }
}
