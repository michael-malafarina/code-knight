package unit.hero;

import unit.Unit;

public abstract class Hero extends Unit
{
    protected int level;
    protected String superClassName;

    public Hero()
    {
        setup();
        level = 1;
        setPerkPool();
        setUpgradePool();
        setActionPool();
        setCodePool();
        setStartingAbilities();
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
}
