package unit.ability.action.code.conditional;

import unit.ability.action.Action;
import unit.ability.action.MovementType;
import unit.ability.action.Tag;
import unit.ability.action.code.LinkedCode;

public abstract class Conditional extends LinkedCode
{
    public void setup()
    {
        name = "If Health < 50%";
        energy = 0;
        time = 20;

        addTag(Tag.CODE);
        setAnimation(MovementType.NONE);
    }

    abstract public boolean conditionIsTrue();

    public boolean canUse()
    {
      //  Action next = self().getAlgorithm().getActionAfter(this);


        //System.out.println(this + " --> " + self().getAlgorithm().getActionAfter(this) + " " + canUseNext());
        return super.canUse() && canUseNext();
    }

    public boolean canUseNext()
    {
        Action next = self().getAlgorithm().getActionAfter(this);
        if(next == null)
        {
            return false;
        }
        return next.canUse();
    }

    public void use()
    {
        Action linkedAction = self().getAlgorithm().getActionAfter(this);

        if (!conditionIsTrue())
        {
            linkedAction.disable();
            self().getAlgorithm().advanceAlgorithm();
        }

        disable();
    }

//    public void disable()
//    {
//        if(linkedAction.getEnergyCost() > self().getCurEnergy())
//        {
//            disabled = false;
//        }
//        else
//        {
//            disabled = true;
//        }
//    }
//
//    public void enable()
//    {
//        activatedCondition = false;
//    }


    public void sound()
    {

    }

    public void animation()
    {

    }

}
