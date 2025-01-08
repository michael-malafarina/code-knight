package unit.hero;

import campaign.HeroManager;
import unit.Team;
import unit.Unit;
import unit.ability.action.Action;
import unit.ability.perks.Perk;

public abstract class Hero extends Unit
{
    protected int level;
    protected String superClassName;

    public Hero()
    {
        setTeam(Team.PLAYER);

        setup();
        level = 1;
        setPerkPool();
        setUpgradePool();
        setActionPool();
        setCodePool();
        setStartingAttributes();

    }

    public String getSuperClassName()
    {
        return superClassName;
    }

    abstract public void setup();

    public int getLevel()
    {
        return level;
    }

    public void levelUp()
    {
        level++;
    }

    abstract public void setUpgradePool();
    abstract public void setActionPool();
    abstract public void setCodePool();
    abstract public void setPerkPool();
    abstract public void setStartingAbilities();
    abstract public void setStartingAttributes();

    public void addAction(Action a)
    {
        HeroManager.getAlgorithm().add(a, this);
    }

}
