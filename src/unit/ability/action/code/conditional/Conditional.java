package unit.ability.action.code.conditional;

import core.Color;
import states.combat.Combat;
import ui.message.FloatText;
import unit.ability.action.Action;
import unit.ability.action.MovementType;
import unit.ability.action.Tag;
import unit.ability.action.code.Code;
import unit.ability.action.code.LinkedCode;

import javax.swing.*;

public abstract class Conditional extends LinkedCode
{
    public void setup()
    {
        name = "If Health < 50%";
        energy = 0;
        time = 60;

        addTag(Tag.CODE);
        setAnimation(MovementType.NONE);
    }

    abstract public boolean conditionIsTrue();

//    public boolean canUse()
//    {
//      //  Action next = self().getAlgorithm().getActionAfter(this);
//
//
//        //System.out.println(this + " --> " + self().getAlgorithm().getActionAfter(this) + " " + canUseNext());
//        return super.canUse() && canUseNext();
//    }

//    public boolean canUseNext()
//    {
//        Action next = getTeam().getAlgorithm().getActionAfter(this);
//        if(next == null)
//        {
//            return false;
//        }
//        return next.canUse();
//    }

    public void use()
    {
        Action linkedAction = getTeam().getAlgorithm().getNextAction();

        // Ignore if link cards are doubled up
        if(linkedAction instanceof LinkedCode)
        {
//            System.out.println("code next");
            return;
        }

        // Ignore if dead
        if(linkedAction.getUnit() == null || !linkedAction.getUnit().isAlive())
        {
            return;
        }

        if (linkedAction != null && !conditionIsTrue())
        {
            linkedAction.disable();
//            getTeam().getAlgorithm().advanceAlgorithm();

//            System.out.println("false");

            Combat.addMessage(new FloatText(linkedAction.getUnit(), "FALSE", new Color(255, 0, 0)));
        }
        else
        {
//            System.out.println("true");

            Combat.addMessage(new FloatText(linkedAction.getUnit(), "TRUE", new Color(0, 255, 0)));

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
