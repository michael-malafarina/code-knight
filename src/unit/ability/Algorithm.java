package unit.ability;

import core.Utility;
import unit.ability.action.Action;
import unit.ability.action.Rarity;
import unit.ability.action.Tag;
import unit.ability.action.code.Code;
import unit.ability.action.code.LinkedCode;
import ui.algorithm.AlgorithmDisplay;
import unit.Unit;

import java.util.ArrayList;

public class Algorithm
{
    private Unit unit;
    private int index;
    private ArrayList<Action> abilities;
    //    private boolean reachedEnd = false;
    private AlgorithmDisplay display;

    public Algorithm(Unit unit)
    {
        this.unit = unit;
        abilities = new ArrayList<>();
    }

    public boolean reachedEnd()
    {
        return index == abilities.size();
    }

    public void add(Action action)
    {
        add(action, action.getRarity());
    }

    public void addRandomPosition(Action action)
    {
        action.setRarity(action.getRarity());
        action.linkUnit(unit);

        int r = Utility.random(abilities.size());
        abilities.add(r, action);


    }

    public void add(Action action, Rarity rarity)
    {
        action.setRarity(rarity);
        action.linkUnit(unit);
        if (action instanceof Code)
        {
            abilities.addFirst(action);
        }
        else
        {
            abilities.add(action);
        }
    }

    public void replace(Action action)
    {
        for (int i = 0; i < abilities.size(); i++)
        {
//            System.out.println(abilities.get(i).isDelete());

            if (abilities.get(i).isDelete())
            {
                abilities.set(i, action);
            }
        }
    }

    public Unit getUnit()
    {
        return unit;
    }


    public Action getNextAction()
    {
        return abilities.get(index);
    }

    public Action getFirstAction()
    {
        return abilities.getFirst();
    }

    public Action getLastAction()
    {
        return abilities.getLast();
    }

    public Action getFutureAction()
    {
        if (index < abilities.size() - 1)
        {
            return abilities.get(index + 1);
        }
        return null;

    }

    public ArrayList<Action> getActions()
    {
        return abilities;
    }

    public void advanceAlgorithm()
    {
        index++;

        if (index == abilities.size())
        {
            reset();
//           reachedEnd = true;
        }
    }

    public void reset()
    {
        index = 0;
        unit.endCycle();

        for (Action a : abilities)
        {
            a.enable();
        }

        for (Action a : abilities)
        {
            a.resetBattle();
        }

//        reachedEnd = false;

    }

    public int getIndex(Action action)
    {
        for (int i = 0; i < abilities.size(); i++)
        {
            if (abilities.get(i) == action)
            {
                return i;
            }
        }
        return -1;
    }

    public void swap(int a, int b)
    {
        Action first = abilities.get(a);
        Action second = abilities.get(b);

        abilities.set(a, second);
        abilities.set(b, first);
    }


    public Action getActionBefore(Action action)
    {
        int i = getIndex(action);

        if (i < 1)
        {
            return null;
        }

        return abilities.get(i - 1);
    }

    public Action getActionAfter(Action action)
    {
        int i = getIndex(action);

        if (i < abilities.size() - 1)
        {
            return abilities.get(i + 1);
        }

        return null;
    }

    public int countLinkedCodeActions()
    {
        int c = 0;
        for (Action a : abilities)
        {
            if (a instanceof LinkedCode)
            {
                c++;
            }
        }
        return c;
    }

    public int countActions(Tag actionType)
    {
        int count = 0;

        for (Action a : abilities)
        {
            if (a.getType() == actionType)
            {
                count++;
            }
        }

        return count;
    }

    public int countActions(Tag actionType, Tag secondaryTag)
    {
        int count = 0;

        for (Action a : abilities)
        {
            if (a.getType() == actionType && a.getTags().contains(secondaryTag))
            {
                count++;
            }
        }

        return count;
    }


    public void setDisplay(AlgorithmDisplay display)
    {
        this.display = display;
    }

    public AlgorithmDisplay getDisplay()
    {
        return display;
    }


}
