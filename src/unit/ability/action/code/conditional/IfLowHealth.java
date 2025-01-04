package unit.ability.action.code.conditional;

public class IfLowHealth extends Conditional
{
    public void setup()
    {
        super.setup();
        name = "If Low Health";
    }

    public boolean conditionIsTrue()
    {
        return self.isLowHealth();
    }

    public String getDescription()
    {
        return "If you are below 50% health, activate the next ability.";
    }
}
