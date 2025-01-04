package unit.ability.action;

import unit.ability.Algorithm;
import animation.AnimatedSpriteSheet;
import battlefield.Cell;
import battlefield.Row;

import unit.ability.Ability;
import unit.ability.conditions.todo.Aim;
import unit.ability.conditions.Condition;
import core.Settings;
import org.newdawn.slick.Image;
import ui.Images;
import ui.sound.Sounds;
import unit.Unit;


import java.util.ArrayList;

abstract public class Action extends Ability
{
    public final int MID_ACTIVATION_TIME = 10;
    public final int END_ACTIVATION_TIME = 20;
    protected int activationTimer = 0;
    protected boolean activating = false;

    public static final int BASE_ACTION_TIME = 60;
    protected int time = BASE_ACTION_TIME;

    boolean delete;
    protected String name;
    protected Image icon;
    protected boolean used;
    protected boolean paidMana;

    protected boolean miss;

    private Rarity currentRarity;
    private Rarity trueRarity;

    protected ArrayList<Unit> targets;

    protected boolean global = false;
    protected int damage = 0;
    protected int healing = 0;
    protected int manaGain = 0;
    protected int block = 0;
    protected Speed speed = Speed.AVERAGE;
    protected int attacks = 1;
    protected boolean disabled = false;

    protected int energy = 1;
    protected int mana;

    protected Upgrade upgrade;

    protected MovementType movement = MovementType.STEP;


    abstract public void setup();

    abstract public void use();

    abstract public void animation();

    abstract public void sound();

    public Unit getTarget()
    {
        return targets.getFirst();
    }

    public ArrayList<Unit> getTargets()
    {
        return targets;
    }

    public String getName()
    {
        return name;
    }

    public int getTime()
    {
        return time;
    }

    abstract public String getDescription();

    public void linkUnit(Unit unit)
    {
        this.self = unit;
    }


    public Image getIcon()
    {
        return icon;
    }

    public Upgrade getUpgrade()
    {
        return upgrade;
    }

    public Rarity getRarity()
    {
        return currentRarity;
    }
    public Rarity getTrueRarity()
    {
        return trueRarity;
    }


//	public boolean hasForwardAlly()
//	{
//		return getForwardAlly() != null;
//	}



    public boolean isUsed()
    {
        return used;
    }



    public Unit getFrontAlly()
    {
        return getAlliedRow().getFrontUnit();
    }

    public Unit getFrontEnemy()
    {
        return getEnemyRow().getFrontUnit();
    }

    public Unit getSecondEnemy()
    {
        return getEnemyRow().getSecondUnit();
    }

    public Unit getLowestHealthEnemy()
    {
        return getEnemyRow().getLowestHealthUnit();
    }

    public Unit getMostDamagedAlly()
    {
        return getAlliedRow().getMostDamagedUnit();
    }

    public Unit getHighestHealthEnemy()
    {
        return getEnemyRow().getHighestHealthUnit();
    }

    public Unit getHighestHealthAlly()
    {
        return getAlliedRow().getHighestHealthUnit();
    }

    public Unit getHighestBlockEnemy()
    {
        return getEnemyRow().getHighestBlockUnit();
    }

    public Unit getHighestBlockAlly()
    {
        return getAlliedRow().getHighestBlockUnit();
    }

    public boolean aboveHalfHealth()
    {
        return self.getCurHealth() >= self.getMaxHealth() * .5f;
    }

    public boolean belowHalfHealth()
    {
        return self.getCurHealth() <= self.getMaxHealth() * .5f;
    }

    public Unit getAllyWithMostActions(Tag type, Tag secondary)
    {
        Unit most = null;
        int mostActions = 0;

        for(Unit u : getAllies())
        {
            int count;

            if(secondary == null)
            {
                count = u.getAlgorithm().countActions(type);
            }
            else
            {
                count = u.getAlgorithm().countActions(type, secondary);
            }

            if(count > mostActions)
            {
                most = u;
                mostActions = count;
            }
        }

        return most;
    }

    public Unit getRandomEnemy()
    {
        if (self().getModifiers().hasCondition(Aim.class))
        {
            return getLowestHealthEnemy();
        }
        else
        {
            return getEnemyRow().getRandomUnit();
        }
    }



    public Unit getRandomAlly()
    {
        return getAlliedRow().getRandomUnit();
    }

    public boolean isActivating()
    {
        return activating;
    }

    public ArrayList<Unit> getAllies()
    {
        return getAlliedRow().getUnits();
    }

    public ArrayList<Unit> getEnemies()
    {
        return getEnemyRow().getUnits();
    }

    public int countEnemies()
    {
        return getEnemyRow().getUnits().size();
    }

    public Unit hasMostStacksOfConditionEnemy(Class<? extends Condition> condition)
    {
        return getEnemyRow().hasMostStacksOfCondition(condition);
    }

    public Unit hasMostStacksOfConditionAlly(Class<? extends Condition> condition)
    {
        return getAlliedRow().hasMostStacksOfCondition(condition);
    }

    public MovementType getMovement()
    {
        return movement;
    }

    public void resetTarget()
    {
        targets = new ArrayList<>();
        setTarget();

    }

    public void resetBattle()
    {
        used = false;
        paidMana = false;
        resetRarity();
    }

    abstract public void setTarget();

    public boolean isDelete()
    {
        return delete;
    }

    public void setDelete(boolean toDelete)
    {
        delete = toDelete;
    }

    public void setMovement()
    {

    }

    public Action()
    {
        super();
        currentRarity = Rarity.COMMON;
        upgrade = Upgrade.MAX_HP;
        setup();
        setIcon();
    }

    public void setRarity(Rarity r)
    {
        trueRarity = r;
        setRarityTemporary(r);
    }

    protected void setRarityTemporary(Rarity r)
    {
        this.currentRarity = r;
        clearConditions();
        rarity();
    }

    protected void downgrade()
    {
        setRarityTemporary(getRarity().downgrade());
    }

    public void resetRarity()
    {
        setRarityTemporary(trueRarity);
    }

    abstract public void rarity();

    public Tag getType()
    {
        return tags.getFirst();
    }

    public void setIcon()
    {
        if (tags.getFirst() == Tag.ATTACK)
        {
            icon = Images.iconAttack;
        }
        else if (tags.getFirst() == Tag.BLOCK)
        {
            icon = Images.iconBlock;
        }
        else if (tags.getFirst() == Tag.HEAL)
        {
            icon = Images.iconHeal;
        }
        else if (tags.getFirst() == Tag.BUFF)
        {
            icon = Images.iconBuff;
        }
        else if (tags.getFirst() == Tag.DEBUFF)
        {
            icon = Images.iconDebuff;
        }
        else if (tags.getFirst() == Tag.CODE)
        {
            icon = Images.iconCode;
        }
        else
        {
            icon = Images.iconVanguard;
        }
    }


    public void push()
    {
        push(getTarget());
    }

    public void pull()
    {
        pull(getTarget());
    }

    public void applyConditions()
    {
        applyConditions(getTargets());
    }

    /*************** Accessors ***************/

    public int getBaseDamage()
    {
        return damage;
    }

    public int getBaseDamage(float scalar)
    {
        return Math.round (damage * scalar);
    }

    public int getBaseHealing()
    {
        return healing;
    }

    public int getBaseHealing(float scalar)
    {
        return Math.round (healing * scalar);
    }


    public int getBaseGuard()
    {
        return block;
    }

    public int getBaseGuard(float scalar)
    {
        return Math.round(block * scalar);
    }

    public int getAttacks()
    {
        return attacks;
    }

    public Speed getSpeed()
    {
        return speed;
    }

    public int getEnergyCost()
    {
        return energy;
    }

    public int getManaCost()
    {
        return mana;
    }

    public boolean isDisabled()
    {
        return disabled;
    }

    // Modified Attributes

    public Algorithm getAlgorithm()
    {
        return self().getAlgorithm();
    }

    public int getDamage()
    {
        return self().getModifiers().getModifiedDamage(damage, tags);
    }

    public int getDamage(float scalar)
    {
        return self().getModifiers().getModifiedDamage((int) (damage * scalar), tags);
    }

    public int getHealing()
    {
        return self().getModifiers().getModifiedHealing(healing, tags);
    }

    public int getHealing(float scalar)
    {
        return self().getModifiers().getModifiedHealing((int) (healing * scalar), tags);
    }

    public int getManaGain(float scalar)
    {
        return (int) (manaGain * scalar);
//        return self().getModifiers().getModifiedHealing((int) (healing * scalar), tags);
    }



    public int getBlock()
    {
        return self().getModifiers().getModifiedBlock(block, tags);
    }

    public int getBlock(float scalar)
    {
        return self().getModifiers().getModifiedBlock((int) (block * scalar), tags);
    }

    public void update()
    {
//		System.out.println(this + " " + activationTimer + " / " + END_ACTIVATION_TIME);

        if (activating)
        {
            activationTimer++;
        }

        if (activationTimer == MID_ACTIVATION_TIME)
        {
            activationMid();
        }

        if (activationTimer == END_ACTIVATION_TIME)
        {
            activationEnd();
        }
    }

    public void activate()
    {
        if (self() == null || !self().isAlive())
        {
            return;
        }


        self().loseEnergy(energy);

        if(self().getCurMana() >= getManaCost())
        {
            self.loseMana(mana, this);
            animation();
            sound();
            paidMana = true;
        }
        else
        {
            Sounds.noMana.play();
        }

        activating = true;
        used = true;

    }

    public void activationMid()
    {
        if(paidMana)
        {
            use();
            self().getModifiers().triggerActionUsed(this);
            getUnit().addSpeed(getSpeed().getValue());
        }
    }

    public void activationEnd()
    {
//		System.out.println("adding " + getDelay() + " delay");
       // getUnit().addDelay(getDelay());
        //getUnit().addDelay(10);


//		System.out.println(this + "ending activation");

        activationTimer = 0;
        activating = false;
        disabled = true;
        paidMana = false;

    }

    /*************** Text Display Methods ***************/


    public String getModifiedText(int modified, int base)
    {
        if (modified > base)
        {
            return "[]" + modified + "[]";
        }
        else if (modified < base)
        {
            return "[]" + modified + "[]";
        }
        else
        {
            return "[]" + modified + "[]";
        }
    }



    public String getDamageText()
    {
        return getModifiedText(getDamage(), getBaseDamage());
    }

    public String getDamageModifierText()
    {
        if (self().getStrength() >= 1)
        {
            return " + " + self.getStrength();
        }
        return "";
    }

    public String getPercentText(float percent)
    {
        return (int) (percent * 100) + "%";
    }



    public String getDamageText(float scalar)
    {
        return getModifiedText(getDamage(scalar), getBaseDamage(scalar));
    }

    public String getHealingText()
    {
        return getModifiedText(getHealing(), getBaseHealing());
    }

    public String getHealingText(float scalar)
    {
        return getModifiedText(getHealing(scalar), getBaseHealing(scalar));
    }

    public String getHealingModifierText()
    {
        if (self().getSpirit() >= 1)
        {
            return " + " + self.getSpirit();
        }
        return "";
    }

    public String getBlockText()
    {
        return getModifiedText(getBlock(), getBaseGuard());
    }

    public String getBlockText(float scalar)
    {
        return getModifiedText(getBlock(scalar), getBaseGuard(scalar));
    }



    /*************** Application Methods ***************/



    // Scales by rarity
    public int scale(int value)
    {
        return Math.round(value * getRarity().scaling());
    }

    public void damage(DamageType type)
    {
        damage(getTargets(), type);
    }

    public void block()
    {
        block(getTargets());
    }

    public void heal()
    {
        heal(getTargets());
    }

    public void damage(Unit target, DamageType type)
    {
        if (target != null)
        {
            target.takeDamage(getDamage(), type, this);
        }
    }

    public void damage(Unit target, DamageType type, float scalar)
    {
        if (target != null)
        {
            target.takeDamage(Math.round(getDamage() * scalar), type, this);
        }
    }

    public void damage(ArrayList<Unit> targets, DamageType type)
    {
        for (Unit u : targets)
        {
            damage(u, type);
        }
    }

    public void block(Unit target)
    {
        if (target != null)
        {
            target.addBlock(getBlock());
        }
    }

    public void block(ArrayList<Unit> targets)
    {
        for (Unit u : targets)
        {
            block(u);
        }
    }

    public void block(Unit target, float scalar)
    {
        if (target != null)
        {
            target.addBlock(getBlock(scalar));
        }
    }

    public void heal(Unit target)
    {
        if (target != null)
        {
            target.heal(getHealing(), this);
        }
    }

    public void heal(Unit target, float scalar)
    {
        if (target != null)
        {
            target.heal((Math.round(getHealing() * scalar)), this);
        }
    }

    public void heal(ArrayList<Unit> targets)
    {
        for (Unit u : targets)
        {
            heal(u);
        }
    }

    public void gainMana()
    {
        for (Unit u : targets)
        {
            gainMana(u);
        }
    }

    public void gainMana(Unit target)
    {
        if (target != null)
        {
            target.gainMana(getManaGain(1));
        }
    }

    // This is different from heal, as it won't apply some modifiers
    // Use this for small incidental heals
    public void regainHealth(int amount, Unit target)
    {
        target.regainHealth(amount);
    }

    public boolean canUse()
    {
        return self().getCurEnergy() >= energy && !self().getAlgorithm().reachedEnd();
    }

    /******************* MUTATORS **************************/

    public void disable()
    {
        disabled = true;

//		System.out.println(this + " disabled");
    }

    public void enable()
    {
        disabled = false;

//		System.out.println(this + " enabled");

    }

    public void animate(AnimatedSpriteSheet anim)
    {
        animate(anim, getTargets(), 1);
    }


    protected void setAnimation(MovementType type)
    {
        movement = type;
    }

    protected void setUpgrade(Upgrade type)
    {
        upgrade = type;
    }

    protected void addTag(Tag tag)
    {
        tags.add(tag);
    }


//    public int compareTo(Ability other)
//	{
//    	// Activated First
//    	if(this instanceof ActivatedAbility && other instanceof PassiveAbility)
//    	{
//    		return -1;
//    	}
//    	else if(this instanceof PassiveAbility && other instanceof ActivatedAbility)
//    	{
//    		return 1;
//    	}
//
//    	// Learned First
//		if(isLearned() && !other.isLearned())
//    	{
//    		return -1;
//    	}
//    	else if(!isLearned() && other.isLearned())
//    	{
//    		return 1;
//    	}
//
//	}
//

}
