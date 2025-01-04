package unit.ability.conditions;

public abstract class Debuff extends Condition
{
    public Debuff(int stacks)
    {
        super(stacks);

    }

    public Debuff()
    {
        this(1);
    }

//    public String getName()
//    {
//        return "[DEBUFF]"+name + " (Debuff)[]";
//    }
}
