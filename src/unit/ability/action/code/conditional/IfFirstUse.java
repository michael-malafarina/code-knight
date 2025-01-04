package unit.ability.action.code.conditional;

public class IfFirstUse extends Conditional
{
    public void setup()
    {
        super.setup();
        name = "If First Use";
    }

    public boolean conditionIsTrue()
    {
        return self().getAlgorithm().getActionAfter(this).isUsed();
    }

    public String getDescription()
    {
        return "If the next ability has not been used yet this battle, activate it.";
    }
}
