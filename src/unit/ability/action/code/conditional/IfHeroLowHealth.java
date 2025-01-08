package unit.ability.action.code.conditional;

import unit.Unit;

import java.util.ArrayList;

public class IfHeroLowHealth extends Conditional
{
    public void setup()
    {
        super.setup();
        name = "If Hero Health < 50%";
    }

    public boolean conditionIsTrue()
    {
        ArrayList<Unit> units = getTeam().getEnemyRow().getUnits();

        for(Unit u : units)
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
        return "If at least one hero is below 50% health, activate the next ability.";
    }
}
