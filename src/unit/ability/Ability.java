package unit.ability;

import unit.ability.action.ConditionStack;
import unit.ability.action.DamageType;
import unit.ability.action.Tag;
import animation.AnimatedSpriteSheet;
import animation.Animation;
import animation.AnimationManager;
import animation.AnimationUnitTarget;
import battlefield.Cell;
import battlefield.Row;
import core.Color;
import unit.ability.conditions.Condition;
import core.Settings;
import unit.Unit;

import java.util.ArrayList;

public class Ability
{
    protected ArrayList<Tag> tags;
    protected Unit self;
    private ArrayList<ConditionStack> conditions;
    public static final int FORCED_MOVEMENT_TIME = 10;

    public Ability()
    {
        tags = new ArrayList<>();
        conditions = new ArrayList<>();
    }

    public Unit getUnit()
    {
        return self;
    }

    public Unit self()
    {
        return self;
    }

    public Unit getOwner()
    {
        return self;
    }



//    public int getApplyStacks()
//    {
//        // todo make stack scalar
////		if(getFunction() == null) return getBaseStacks();
////		else return getFunction().getModifiers().getModifiedStacks(duration, tags);
//        return getBaseStacks();
//    }
//
//    public int getBaseStacks()
//    {
//        return applyStacks;
//    }

    public ArrayList<Tag> getTags()
    {
        return tags;
    }

    public boolean hasCondition(Class<? extends Condition> conditionType)
    {
        for(ConditionStack c : conditions)
        {
            if(c.getCondition().equals(conditionType))
            {
                return true;
            }
        }
        return false;
    }

    public ArrayList<Condition> getConditionInstances()
    {
        ArrayList<Condition> newConditions = new ArrayList<>();

        for(ConditionStack c : conditions)
        {
            newConditions.add(c.conditionFactory());
        }
        return newConditions;
    }

    public void animate(AnimatedSpriteSheet anim, Unit target)
    {
        animate(anim, target, 1);
    }


    public void animate(AnimatedSpriteSheet anim, Unit target, Color color)
    {
        animate(anim, target, color, 1);
    }

    public void animate(AnimatedSpriteSheet anim, Unit target, float scale)
    {
        animate(anim, target, Color.white, scale);
    }

    public void animate(AnimatedSpriteSheet anim, Unit target, Color color, float scale)
    {
        if (anim == null || target == null)
        {
            return;
        }

        Animation a = new AnimationUnitTarget(anim, target);
        a.setImageScaling(scale);
        a.setPosition(target);
        a.setColor(color);

        if (self().isEnemy())
        {
            a.setEnemyEffect();
        }

        AnimationManager.add(a);
    }

    public void animate(AnimatedSpriteSheet anim, ArrayList<Unit> targets)
    {
        animate(anim, targets, 1);
    }

    public void animate(AnimatedSpriteSheet anim, ArrayList<Unit> targets, Color color)
    {
        for (Unit u : targets)
        {
            animate(anim, u, color);
        }
    }

    public void animate(AnimatedSpriteSheet anim, ArrayList<Unit> targets, Color color, float scale)
    {
        for (Unit u : targets)
        {
            animate(anim, u, color, scale);
        }
    }

    public void animate(AnimatedSpriteSheet anim, ArrayList<Unit> targets, float scale)
    {
        for (Unit u : targets)
        {
            animate(anim, u, scale);
        }
    }

    public ArrayList<ConditionStack> getConditions()
    {
        return conditions;
    }

    public String getStacksText()
    {
//        if(getConditions().isEmpty())
//        {
//            return "0";
//        }

        return ""+getConditions().getFirst().getStacks();
    }

    protected void clearConditions()
    {
        conditions.clear();
    }

    protected void addCondition(Class<? extends Condition> clazz, int stacks)
    {
        conditions.add(new ConditionStack(clazz, stacks));
    }




    //
    public void applyConditions(Unit target)
    {
        if (target == null)
        {
            return;
        }
        for (ConditionStack c : conditions)
        {
            target.addCondition(c.conditionFactory(), self());
        }
    }


    public void applyConditions(ArrayList<Unit> units)
    {
        for (Unit u : units)
        {
            applyConditions(u);
        }
    }


    public void applyConditionsLater()
    {
        applyConditionsLater(self());
    }


    public void applyConditionsLater(Unit target)
    {
        if (target == null)
        {
            return;
        }
        for (ConditionStack c : conditions)
        {
            target.addConditionLater(c.conditionFactory(), self());
        }
    }

    public void applyConditionsLater(ArrayList<Unit> units)
    {
        for (Unit u : units)
        {
            applyConditionsLater(u);
        }
    }


    public void setOwner(Unit unit)
    {
        self = unit;
    }

    public Row getAlliedRow()
    {
        return self().getTeam().getAlliedRow();
    }

    public Row getEnemyRow()
    {
        return self().getTeam().getEnemyRow();
    }

    /************* Movement ********************/

    public boolean hasForwardCell()
    {
        return getForwardCell() != null;
    }

//	public boolean hasBackwardAlly()
//	{
//		return getBackwardAlly() != null;
//	}

    public boolean hasForwardCell(Unit u)
    {
        return getBackwardCell(u) != null;
    }

    public boolean hasBackwardCell()
    {
        return getBackwardCell() != null;
    }

    public boolean hasBackwardCell(Unit u)
    {
        return getBackwardCell(u) != null;
    }

    public Cell getForwardCell()
    {
        return getAlliedRow().getForwardCell(self());
    }

    public Cell getForwardCell(Unit unit)
    {
        return unit.getRow().getForwardCell(unit);
    }

    public Cell getBackwardCell()
    {
        return getAlliedRow().getBackwardCell(self());
    }

    public Cell getBackwardCell(Unit unit)
    {
        return unit.getRow().getBackwardCell(unit);
    }

    public void advance()
    {
        if (hasForwardCell())
        {
            self.forcedMovement(getForwardCell(), FORCED_MOVEMENT_TIME / Settings.gameSpeed);
        }
    }

    public void retreat()
    {
        if (hasBackwardCell())
        {
            self.forcedMovement(getBackwardCell(), FORCED_MOVEMENT_TIME / Settings.gameSpeed);
        }
    }

    public void pull(Unit u)
    {
        if (hasForwardCell(u))
        {
            u.forcedMovement(getForwardCell(u), FORCED_MOVEMENT_TIME / Settings.gameSpeed);
        }

    }

    public void push(Unit u)
    {
        if (hasBackwardCell(u))
        {
            u.forcedMovement(getBackwardCell(u), FORCED_MOVEMENT_TIME / Settings.gameSpeed);
        }
    }

    /************* Utility Methods **************/



    public void applyDamageDealtTriggers(int amount, Unit target, DamageType type)
    {
        self().getModifiers().triggerOnDamageDealt(amount, target, type);
    }

}
