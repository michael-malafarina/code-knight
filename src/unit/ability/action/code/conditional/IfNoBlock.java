package unit.ability.action.code.conditional;

public class IfNoBlock extends Conditional
{
    public void setup()
    {
        super.setup();
        name = "If No Block";
    }

    public boolean conditionIsTrue()
    {
        return self.getBlock() == 0;
    }

    public String getDescription()
    {
        return "If you have no [BLOCK]Block[], activate the next ability.";
    }
}
