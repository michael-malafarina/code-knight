package unit.ability.action.code.conditional;

public class IfHighHealth extends Conditional
{
    public void setup()
    {
        super.setup();
        name = "If High Health";
    }

    public boolean conditionIsTrue()
    {
        return !self().isLowHealth();
    }

    public String getDescription()
    {
        return "If you have at least 50% health, activate the next ability.";
    }
}
