package unit.ability.action.code.conditional;

public class IfHasBlock extends Conditional
{
    public void setup()
    {
        super.setup();
        name = "If Blocking";
    }

    public boolean conditionIsTrue()
    {
        return self.getBlock() > 0;
    }

    public String getDescription()
    {
        return "If you have any [BLOCK]Block[], activate the next ability.";
    }
}
