package campaign;

import battlefield.Cell;
import core.Utility;
import states.combat.Combat;
import unit.Team;
import unit.Unit;
import unit.ability.Algorithm;
import unit.ability.action.Action;
import unit.hero.*;

import java.util.ArrayList;

public class HeroManager
{
	private static int position = 0;
	private static Algorithm algorithm;

	protected static ArrayList<Hero> units;

	public static void init()
	{
		units = new ArrayList<>();
		algorithm = new Algorithm(Team.PLAYER);
	}



	public static ArrayList<Unit> getUnits()
	{
		return new ArrayList<>(units);
	}

	public static Algorithm getAlgorithm()
	{
		return algorithm;
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
		Cell c = u.getRow().getEmptyCell();
		u.setCell(c, true);
		position++;
		units.add(u);

		u.setStartingAbilities();


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

		algorithm = new Algorithm(Team.PLAYER);
	//	HeroManager.addUnit(new Knight());
//		HeroManager.addUnit(new Warrior());
//		HeroManager.addUnit(new Cleric());
//		HeroManager.addUnit(new Mage());
	}


	public void updateActions()
	{
//		if (Combat.inActionMode() && Combat.getActingUnit() == this)
//		{
//			getAlgorithm().getNextAction().update();
//			getAlgorithm().getNextAction().setMovement();      // sets the movement method
//			movement();
//
//		}
//
//		if (algorithm.getNextAction().isDisabled())
//		{
//			algorithm.advanceAlgorithm();
//		}
	}

//
//
//	public void replaceAction(Action action)
//	{
//		algorithm.replace(action);
//	}

	public static void updateAlgorithm()
	{
		algorithm.update();
	}
}
