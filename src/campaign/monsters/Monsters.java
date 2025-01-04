package campaign.monsters;

import campaign.Faction;
import campaign.monsters.groups.*;
import unit.enemy.goblin.groups.Goblins;

public class Monsters extends Faction
{
    public Monsters()
    {
        groups.add(new Goblins());

   //     groups.add(new SkeletonsBasic());
  //      groups.add(new SkeletonCrew());
//        groups.add(new SkeletonLegion());
  //      groups.add(new RobotLegion());

    }
}
