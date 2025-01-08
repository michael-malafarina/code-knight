package unit.ability;

import core.Utility;
import unit.Team;
import unit.ability.action.Action;
import unit.ability.action.Rarity;
import unit.ability.action.Tag;
import unit.ability.action.code.Code;
import unit.ability.action.code.LinkedCode;
import unit.Unit;
import unit.ability.action.code.conditional.IfEnemyLowHealth;
import unit.ability.action.code.conditional.IfFirstUse;
import unit.ability.action.code.conditional.IfHeroLowHealth;

import java.util.ArrayList;

public class Algorithm
{
    private int index;
    private ArrayList<Action> abilities;
    private Team team;

    public Algorithm(Team team)
    {

        abilities = new ArrayList<>();
        this.team = team;

        if(team == Team.PLAYER) {
//            add(new IfEnemyLowHealth(), null);
//            add(new IfFirstUse(), null);
//            add(new IfHeroLowHealth(), null);
//            add(new IfHeroLowHealth(), null);

        }
    }

    public void update()
    {
        for(Action a : abilities)
        {
            if(a.getUnit() != null && !a.getUnit().canAct())
            {
                a.disable();
            }
        }
    }

    public boolean isDone()
    {
        return index == abilities.size();
    }

    public boolean atStart()
    {
        return index == 0;
    }

    public void add(Action action, Unit unit)
    {
        add(action, action.getRarity(), unit);
    }

    public void addRandomPosition(Action action, Unit unit)
    {
        action.setRarity(action.getRarity());
        action.linkUnit(unit);

        int r = Utility.random(abilities.size());
        abilities.add(r, action);


    }

    public void add(Action action, Rarity rarity, Unit unit)
    {
        action.setRarity(rarity);
        action.setTeam(team);

        if (action instanceof Code)
        {
            abilities.addFirst(action);
        }
        else
        {
            abilities.add(action);
            action.linkUnit(unit);
        }
    }

    public void replace(Action action)
    {
        for (int i = 0; i < abilities.size(); i++)
        {

            if (abilities.get(i).isDelete())
            {
                abilities.set(i, action);
            }
        }
    }

    public Action getCurrentAction()
    {
        if(index < abilities.size())
        {
            return abilities.get(index);
        }
        return null;
    }

    public Action getPreviousAction()
    {
        if(index - 1 > 0)
        {
            return abilities.get(index - 1);
        }
        return null;
    }

    public Action getNextAction()
    {
        if(index + 1 < abilities.size())
        {
            return abilities.get(index + 1);
        }
        return null;
    }

    public Unit getNextAlly()
    {
        for(int i = index; i < abilities.size(); i++)
        {
            if(abilities.get(i).getUnit() != null)
            {
                return abilities.get(i).getUnit();
            }
        }

        return null;
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

        while(index < abilities.size() && getCurrentAction().isDisabled())
        {
            index++;
        }
    }

    public void reset()
    {
        index = 0;

        for (Action a : abilities)
        {
            a.enable();
        }

        for (Action a : abilities)
        {
            a.resetBattle();
        }

        update();


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


}
