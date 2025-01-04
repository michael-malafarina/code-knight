package campaign;

import battlefield.Cell;
import core.Utility;
import unit.Team;
import unit.Unit;
import unit.hero.*;

import java.util.ArrayList;

public class HeroManager
{
	private static int position = 0;

	protected static ArrayList<Hero> units;

	public static void init()
	{
		units = new ArrayList<>();
	}

	public static ArrayList<Unit> getUnits()
	{
		return new ArrayList<>(units);
	}

	public static Hero getLowestLevelUnit()
	{
		int lowestLevel = 9999;
		Hero lowestUnit = null;

		for(Hero u : units)
		{
			if(u.getLevel() < lowestLevel)
			{
				lowestLevel = u.getLevel();
				lowestUnit = u;
			}
		}

		return lowestUnit;
	}

	public static Hero getRandomUnit()
	{
		Hero u = units.get(Utility.random(units.size()));
		if(!u.isAlive())
		{
			u = getRandomUnit();
		}

		return u;
	}

	public static void addUnit(Hero u)
	{
		u.setTeam(Team.PLAYER);
		Cell c = u.getRow().getEmptyCell();
		u.setCell(c, true);
		position++;
		units.add(u);

	}

	public static void recover()
	{
		for(Unit u : units)
		{
			u.reset();
		}
	}

	public static void newParty()
	{
		units = new ArrayList<>();

	//	HeroManager.addUnit(new Knight());
//		HeroManager.addUnit(new Warrior());
//		HeroManager.addUnit(new Cleric());
//		HeroManager.addUnit(new Mage());
	}
}
