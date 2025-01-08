package unit.ability.action.code.conditional;

import unit.Unit;

import java.util.ArrayList;

public class IfPartyLowHealth extends Conditional
{
    public void setup()
    {
        super.setup();
        name = "If Party Health < 50%";
    }

    public boolean conditionIsTrue()
    {
        float averageCur = 0;
        float averageMax = 0;

        ArrayList<Unit> units = getTeam().getAlliedRow().getUnits();

        for(Unit u : units)
        {
            averageCur += u.getCurHealth();
            averageMax += u.getMaxHealth();
        }

        return averageCur / averageMax < .5f;
    }

    public String getDescription()
    {
        return "If total party health is below 50%, activate the next ability.";
    }
}
