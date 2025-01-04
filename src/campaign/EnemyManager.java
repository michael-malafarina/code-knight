package campaign;

import battlefield.Cell;
import campaign.monsters.Monsters;
import core.Utility;
import states.map.Map;
import ui.panel.Panel;
import unit.Team;
import unit.Unit;
import unit.enemy.Enemy;
import unit.enemy.skeleton.Skeleton;
import unit.enemy.skeleton.SkeletonArcher;
import unit.enemy.skeleton.SkeletonKnight;
import unit.hero.Hero;

import java.util.ArrayList;

public class EnemyManager
{
	protected static ArrayList<Enemy> enemies;
	private static int position = 0;

	private static Faction faction;
	private static int skips;
	public static void init()
	{

		enemies = new ArrayList<>();
		faction = new Monsters();

	}

	public static int getCombatDifficulty()
	{
		return (Map.getStage() - 2) + HeroManager.getUnits().size() * 2;
	}

	public static ArrayList<Enemy> getUnits()
	{
		return enemies;
	}

	public static Enemy getRandomUnit()
	{
		Enemy u = enemies.get(Utility.random(enemies.size()));
		if(!u.isAlive())
		{
			u = getRandomUnit();
		}

		return u;
	}

	public static void buildEncounter()
	{
		ArrayList<Enemy> enemies = faction.buildEncounter(getCombatDifficulty());

		skips = 2;

		for(Enemy e : enemies)
		{
			addUnit(e);
		}



//		addUnit(new SkeletonKnight());
	}
	public static void addUnit(Enemy u)
	{
		if(enemies.size() == 4)
		{
			return;
		}

		// 25% chance to skip any given cell
		if(skips > 0 && Math.random() > .75f)
		{
			position++;
			skips--;
		}

		u.setTeam(Team.ENEMY);
		u.setCell(position);
		position++;
		enemies.add(u);
	}

	public static void clear()
	{

		for(Enemy u : enemies)
		{
			if(u != null)
			{
				Cell c = u.getCell();

				if(c != null)
				{
					c.clear();
				}
			}
		}
		enemies.clear();
		position = 0;
	}
}
