package unit.ability.action.code.conditional;

public class IfHighMana extends Conditional
{
    public void setup()
    {
        super.setup();
        name = "If High Mana";
    }

    public boolean conditionIsTrue()
    {
        return self.getCurMana() >= self.getMaxMana() / 2;
    }

    public String getDescription()
    {
        return "If you have at least 50% mana, activate the next ability.";
    }
}
