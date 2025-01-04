package campaign;

import core.Utility;
import unit.enemy.Enemy;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class Group
{
    private ArrayList<Enemy> enemyPool;

    public abstract void buildPool();
    public abstract int getMinDifficulty();
    public abstract int getMaxDifficulty();

    public Group()
    {
        enemyPool = new ArrayList<>();
        buildPool();
    }

    protected void add(Class<? extends Enemy> type)
    {
        enemyPool.add(enemyFactory(type));
    }

    private int getLowestValue()
    {
        int lowestValue = Integer.MAX_VALUE;

        for(Enemy e : enemyPool)
        {
            if(e.getValue() < lowestValue)
            {
                lowestValue = e.getValue();
            }
        }

        return lowestValue;
    }

    public ArrayList<Enemy> buildEncounter(int difficulty)
    {
        ArrayList<Enemy> enemies = new ArrayList<>();

        while(difficulty >= getLowestValue() && enemies.size() < 4)
        {
            Enemy newEnemy = getEnemy(difficulty);

            enemies.add(newEnemy);
            difficulty -= newEnemy.getValue();
        }

        return enemies;
    }

    private Enemy getEnemy(int difficulty)
    {
        Enemy e = getRandomEnemy();

        // If it's too hard, try again
        if(e.getValue() > difficulty)
        {
            return getEnemy(difficulty);
        }

//        // Avoid picking anything below 50% of the points left
//        if(e.getValue() < pointsLeft * .5f)
//        {
//            return getEnemy(difficulty, pointsLeft);
//        }

        return enemyFactory(e.getClass());
    }

    private Enemy getRandomEnemy()
    {
        return enemyPool.get(Utility.random(enemyPool.size()));
    }

    private Enemy enemyFactory(Object o)
    {
        Class<? extends Enemy> clazz = (Class<? extends Enemy>) o;

        Enemy enemy = null;

        try
        {
            // When I create a new condition, set its duration to the actual duration after modifiers
            enemy = clazz.newInstance();
        }
        catch (InstantiationException | IllegalAccessException e)
        {
            e.printStackTrace();
        }

        return enemy;
    }

    public ArrayList<Enemy> list(Enemy... types)
    {
        return new ArrayList<>(Arrays.asList(types));
    }

}
