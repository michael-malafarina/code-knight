package battlefield;

import unit.ability.conditions.Condition;
import unit.Unit;

import java.util.ArrayList;

public class Row
{
    private ArrayList<Cell> cells;

    public Row()
    {
        cells = new ArrayList<>();

        cells.add(new Cell(this, 0));
        cells.add(new Cell(this, 1));
        cells.add(new Cell(this, 2));
        cells.add(new Cell(this,3));
        cells.add(new Cell(this,4));
        cells.add(new Cell(this,5));


    }

    public ArrayList<Cell> getCells()
    {
        return cells;
    }

    public Cell getCell(int position)
    {
        return cells.get(position);
    }

    public Cell getEmptyCell()
    {
        for(Cell c : cells)
        {
            if(!c.hasUnit())
            {
                return c;
            }
        }
        return null;
    }

    public Cell getFront()
    {
        return cells.getFirst();
    }

    public ArrayList<Unit> getUnits()
    {
        ArrayList<Unit> units = new ArrayList<>();

        for(Cell c : cells)
        {
            if(c.hasUnit() && c.getUnit().isAlive())
            {
                units.add(c.getUnit());
            }
        }

        return units;
    }

    // Includes units that are not alive, potentially
    public ArrayList<Unit> getAllUnits()
    {
        ArrayList<Unit> units = new ArrayList<>();

        for(Cell c : cells)
        {
            if(c.hasUnit())
            {
                units.add(c.getUnit());
            }
        }

        return units;
    }

    public Unit getLowestHealthUnit()
    {
        int lowestHealth = Integer.MAX_VALUE;
        Unit lowestHealthUnit = null;

        for(int i = 0; i < cells.size(); i++)
        {
            Unit u = getCell(i).getUnit();
            if(u != null && u.getCurHealth() < lowestHealth && u.isAlive())
            {
                lowestHealthUnit = u;
                lowestHealth = u.getCurHealth();
            }
        }
        return lowestHealthUnit;
    }


    public Unit getHighestBlockUnit()
    {
        int highestBlock = Integer.MIN_VALUE;
        Unit highestBlockUnit = null;

        for(int i = 0; i < cells.size(); i++)
        {
            Unit u = getCell(i).getUnit();
            if(u != null && u.getBlock() > highestBlock && u.isAlive())
            {
                highestBlockUnit = u;
                highestBlock = u.getBlock();
            }
        }
        return highestBlockUnit;
    }

    public Unit getHighestHealthUnit()
    {
        int highestHealth = Integer.MIN_VALUE;
        Unit highestHealthUnit = null;

        for(int i = 0; i < cells.size(); i++)
        {
            Unit u = getCell(i).getUnit();
            if(u != null && u.getCurHealth() > highestHealth && u.isAlive())
            {
                highestHealthUnit = u;
                highestHealth = u.getCurHealth();
            }
        }
        return highestHealthUnit;
    }

    public Unit getMostDamagedUnit()
    {
        int mostDamage = -Integer.MAX_VALUE;
        Unit mostDamagedAlly = null;

        for(int i = 0; i < cells.size(); i++)
        {
            Unit u = getCell(i).getUnit();
            if(u != null && u.getMaxHealth() - u.getCurHealth() > mostDamage && u.isAlive())
            {
                mostDamagedAlly = u;
                mostDamage = u.getMaxHealth() - u.getCurHealth();

            }
        }
        return mostDamagedAlly;
    }

//    public Unit getForwardUnit(Unit currentUnit)
//    {
//        int startingIndex = getIndex(currentUnit);
//
//        // Count backwards to move toward front
//        for(int i = startingIndex - 1; i >= 0; i++)
//        {
//            if(cells.get(i).getUnit() != null && cells.get(i).getUnit().isAlive())
//            {
//                return cells.get(i).getUnit();
//            }
//        }
//
//        return null;
//
//    }

    public Cell getForwardCell(Unit currentUnit)
    {
//        System.out.println("FORWARD CELL, Currently at " + getIndex((currentUnit)));

        if(getIndex(currentUnit) - 1 >= 0)
        {
            return cells.get(getIndex(currentUnit) - 1);
        }
        return null;
    }

//    public Unit getBackwardUnit(Unit currentUnit)
//    {
//        int startingIndex = getIndex(currentUnit);
//
//        // Count forwards to move toward rear
//        for(int i = startingIndex + 1; i < getUnits().size(); i++)
//        {
//            if(cells.get(i).getUnit() != null && cells.get(i).getUnit().isAlive())
//            {
//                //System.out.println(cells.get(i).getUnit());
//                return cells.get(i).getUnit();
//            }
//        }
//
//        return null;
//
//    }

    public Cell getBackwardCell(Unit currentUnit)
    {
        if(getIndex(currentUnit) + 1 < cells.size())
        {
            return cells.get(getIndex(currentUnit) + 1);
        }
        return null;
    }


    public int getIndex(Unit currentUnit)
    {
        return currentUnit.getCell().getPosition();
//        for(int i = 0; i < cells.size(); i++)
//        {
//            Unit u = getCell(i).getUnit();
//
//            if(u == currentUnit)
//            {
//                return i;
//            }
//        }
//        return -1;
    }


    public Unit getFrontUnit()
    {
        for(int i = 0; i < cells.size(); i++)
        {
            Unit u = getCell(i).getUnit();

            if(u != null && u.isAlive())
            {
                return getCell(i).getUnit();
            }
        }
        return null;
    }

    public Unit getSecondUnit()
    {
        int count = 0;

        for(int i = 0; i < cells.size(); i++)
        {
            Unit u = getCell(i).getUnit();

            if(u != null && u.isAlive())
            {
                count++;

                if(count == 2)
                {
                    return getCell(i).getUnit();
                }
            }
        }
        return null;
    }

    public Unit getRandomUnit()
    {
        int r = (int) (Math.random() * getUnits().size());
        return getUnits().get(r);
    }

    public Unit hasMostStacksOfCondition(Class<? extends Condition> clazz)
    {
        int highestCount = 0;
        Unit topUnit = null;

        for(Unit u : getUnits())
        {
            if(u.getModifiers().hasCondition(clazz))
            {
                int count = u.getModifiers().getCondition(clazz).getStacks();

                if(count > highestCount)
                {
                    highestCount = count;
                    topUnit = u;
                }
            }
        }

        return topUnit;
    }

    public Cell getRear()
    {
        return cells.getLast();
    }


}
