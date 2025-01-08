package unit.ability.action.code.conditional;

public class IfHighMana extends Conditional
{
    public void setup()
    {
        super.setup();
        name = "If Mana > 50%";
    }

    public boolean conditionIsTrue()
    {
        return getAlgorithm().getNextAction().getUnit().getCurMana() >=
                getAlgorithm().getNextAction().getUnit().getMaxMana() / 2;
    }

    public String getDescription()
    {
        return "If you have at least 50% mana, activate the next ability.";
    }
}
