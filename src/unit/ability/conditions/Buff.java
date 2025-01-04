package unit.ability.conditions;

public abstract class Buff extends Condition
{
    public Buff(int stacks)
    {
        super(stacks);
    }

    public Buff()
    {
        this(1);
    }

//    public String getName()
//    {
//        return "[BUFF]"+name+ " (Buff)[]";
//    }
}
