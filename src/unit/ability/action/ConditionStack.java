package unit.ability.action;

import unit.ability.conditions.Condition;

public class ConditionStack
{
    private Class<? extends Condition> condition;
    private int stacks;

    public ConditionStack(Class<? extends Condition> condition, int stacks)
    {
        this.condition = condition;
        this.stacks = stacks;
    }

    public Class<? extends Condition> getCondition()
    {
        return condition;
    }

    public int getStacks()
    {
        return stacks;
    }

    public Condition conditionFactory()
    {
        Class<? extends Condition> clazz = condition;

        Condition c = null;

        try
        {
            // When I create a new condition, set its duration to the actual duration after modifiers
            c = clazz.newInstance();
            c.setStacks(stacks);
        }
        catch (InstantiationException | IllegalAccessException e)
        {
            e.printStackTrace();
        }

        return c;
    }

}
