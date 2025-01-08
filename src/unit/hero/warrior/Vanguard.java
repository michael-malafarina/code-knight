package unit.hero.warrior;

import animation.Animation;
import core.Color;
import ui.Images;
import unit.hero.warrior.actions.*;
import unit.hero.warrior.actions.vanguard.Bastion;
import unit.hero.warrior.actions.vanguard.Charge;
import unit.hero.warrior.actions.vanguard.Warcry;
import unit.hero.warrior.actions.warden.Slash;
import unit.hero.warrior.actions.warden.Thornskin;
import unit.hero.warrior.perks.BattefrontLeader;

public class Vanguard extends Warrior
{
    public void setup()
    {
        super.setup();

        name = "Vanguard";
        description = "A balanced [KEY]Warrior[] with high mobility, dealing damage and blocking blows while inspiring allies.";
        icon = Images.iconVanguard;
        classColor = new Color(240, 50, 50);
        animation = new Animation(Images.vanguard);
        animation.setHero();
    }

    public void setStartingAttributes()
    {
        super.setStartingAttributes();
        addSpeedPerTurn(5);
        addDefense(1);

    }

    public void setStartingAbilities()
    {
        addAction(new Slash());
        addAction(new Bastion());            // make it a defensive shout instead

//        algorithm.add(new Charge());
//        algorithm.add(new Charge());
//        algorithm.add(new Bastion());
//        algorithm.add(new Bastion());
//        algorithm.add(new Warcry());
      //  addPerk(new BattefrontLeader());
    }

    public void setUpgradePool()
    {
        super.setUpgradePool();
    }

    public void setActionPool()
    {
        super.setActionPool();
        actionPool.add(Charge.class);
    }

    public void setCodePool()
    {
        super.setCodePool();
    }

    public void setPerkPool()
    {
        super.setPerkPool();
    }

}
