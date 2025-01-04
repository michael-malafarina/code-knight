package unit.ability.action.code.conditional;

public class IfMultipleEnemies extends Conditional
{
    public void setup()
    {
        super.setup();
        name = "If Multiple Enemies";
    }

    public boolean conditionIsTrue()
    {
        return countEnemies() > 1;
    }

    public String getDescription()
    {
        return "If the number of enemies is greater than one, activate the next ability.";
    }
}
