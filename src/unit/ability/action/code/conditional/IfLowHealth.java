package unit.ability.action.code.conditional;

public class IfLowHealth extends Conditional
{
    public void setup()
    {
        super.setup();
        name = "If Health < 50%";
    }

    public boolean conditionIsTrue()
    {
        return getAlgorithm().getNextAction().getUnit().isLowHealth();
    }

    public String getDescription()
    {
        return "If the hero using the next ability is below 50% health, activate the next ability.";
    }
}
