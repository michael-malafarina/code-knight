package campaign.monsters.groups;

import campaign.Group;
import unit.enemy.Enemy;
import unit.enemy.robot.RobotKnight;
import unit.enemy.robot.RobotFireMage;

import java.util.ArrayList;

public class RobotLegion extends Group
{
    public int getMinDifficulty()
    {
        return 11;
    }

    public int getMaxDifficulty()
    {
        return 22;
    }

    public void buildPool()
    {
        add(RobotKnight.class);
        add(RobotFireMage.class);
    }

    public ArrayList<Enemy> buildEncounter(int difficulty)
    {
        return switch (difficulty)
        {
            case 10 -> list(new RobotKnight(), new RobotKnight());
            case 11,12,13, 14 -> list(new RobotKnight(), new RobotFireMage());
            case 15 -> list(new RobotKnight(), new RobotKnight(), new RobotKnight());
            case 16 -> list(new RobotKnight(), new RobotKnight(), new RobotFireMage());
            case 17,18,19, 20, 21 -> list(new RobotKnight(), new RobotFireMage(), new RobotFireMage());
            default -> list(new RobotKnight(), new RobotKnight(), new RobotFireMage(), new RobotFireMage());
        };
    }


}
