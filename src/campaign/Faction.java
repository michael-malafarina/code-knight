package campaign;

import core.Utility;
import unit.ability.perks.Perk;
import unit.Unit;
import unit.enemy.Enemy;

import java.util.ArrayList;

public class Faction
{
    protected ArrayList<Group> groups;

    public Faction()
    {
        groups = new ArrayList<>();
    }

    public ArrayList<Enemy> buildEncounter(int difficulty)
    {
        return selectGroup(difficulty).buildEncounter(difficulty);
    }

    public ArrayList<Group> getValidGroups(int difficulty)
    {
        ArrayList<Group> validGroups = new ArrayList<>();

        for (Group g : groups)
        {
            if (g.getMinDifficulty() <= difficulty && difficulty <= g.getMaxDifficulty())
            {
                validGroups.add(g);
            }
        }

        return validGroups;
    }

    public Group selectGroup(int difficulty)
    {
        ArrayList<Group> validGroups = getValidGroups(difficulty);
        return validGroups.get(Utility.random(validGroups.size()));
    }
}

