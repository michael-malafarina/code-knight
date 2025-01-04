package states.combat;

import battlefield.Battlefield;
import campaign.HeroManager;
import unit.Unit;

import java.util.ArrayList;
import java.util.Collections;

public class InitiativeQueue 
{

	public static final int DELAY_PER_TURN = 50;

	private static ArrayList<Unit> units;

	public static void begin(ArrayList<Unit> startingUnits)
	{
		units = startingUnits;

		// Reset all units
		for(Unit u : startingUnits)
		{
			u.reset();
		}

		// Set starting delay
		for(int i = Battlefield.getHeroRow().getCells().size() - 1; i >= 0 ; i--)
		{
			Unit u = Battlefield.getHeroRow().getCells().get(i).getUnit();
			if(u != null)
			{
//				System.out.println(u + ": " + (9-i));
				u.setDelay(9-i);
			}
		}

		for(int i = 0; i < Battlefield.getEnemyRow().getCells().size(); i++)
		{
			Unit u = Battlefield.getEnemyRow().getCells().get(i).getUnit();
			if(u != null)
			{
//				System.out.println(u + ": " + (i+10));
				u.setDelay(i+10);
			}
		}
	}

	public static ArrayList<Unit> getUnits()
	{
		return units;
	}

	public static void tick()
	{
		if(units == null || units.isEmpty())
		{
			return;
		}


		Collections.sort(units);

		Unit next = units.getFirst();
		int minDelay = next.getDelay();

		// Fast forward to current unit
		for(Unit u : units)
		{
			u.reduceDelay(minDelay);
		}


	//	printQueue();

	//	System.out.println("-----");

		// Remove dead units
		while(!next.isAlive())
		{
			units.remove(next);

			if(units.isEmpty())
			{
				return;
			}
			next = units.getFirst();
		}

		// check that this grabs the right one, and not the next one.  (it should work, 80% sure)

		Combat.startTurn(next);



//		int actionTime =
//		next.getAlgorithm().getNextAction().getTime();
//
//
//		if(next.getAlgorithm().getNextFunction().canUse())
//		{
//			Combat.startTurn(next, actionTime);
//		}
//		else
//		{
//			Combat.startTurn(next, 0);
//			Combat.endTurn();
//			next.addDelay(100);
//			tick();
//		}


//		next.act();
	}
	
//	public Unit getNextActor()
//	{
//		return nextActor;
//	}


}
