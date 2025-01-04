package unit.ability.action.code.conditional;

public class IfLowMana extends Conditional
{
    public void setup()
    {
        super.setup();
        name = "If Low Mana";
    }

    public boolean conditionIsTrue()
    {
        return self.getCurMana() < self.getMaxMana() / 2;
    }

    public String getDescription()
    {
        return "If you have less than 50% mana, activate the next ability.";
    }
}
