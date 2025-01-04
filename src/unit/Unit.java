package unit;

// A unit lives in a cell, has health, can fight.  Is a hero or monster

import unit.ability.Algorithm;
import unit.ability.action.*;
import animation.Animation;
import battlefield.Cell;
import battlefield.Row;
import core.Main;
import core.Color;
import unit.ability.Ability;
import unit.ability.ModifierSet;
import unit.ability.conditions.Condition;
import unit.ability.conditions.buff.Defense;
import unit.ability.conditions.buff.Focus;
import unit.ability.conditions.buff.Spirit;
import unit.ability.conditions.buff.Strength;
import unit.ability.perks.Perk;
import core.Utility;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import states.combat.Combat;
import states.combat.CombatState;
import states.combat.InitiativeQueue;
import ui.combat.Blockbox;
import ui.combat.Healthbar;
import ui.combat.EnergyDiamonds;

import ui.Mouse;
import ui.message.FloatText;

import java.util.ArrayList;

public class Unit implements Comparable<Unit>
{
    protected final int TINT_TIME = 36;

    protected Color classColor = new Color(238, 195, 25);

    protected String description;
    protected String name;
    protected boolean bloodied;
    protected boolean lowMana;
    protected boolean isAlive;
    private int curHealth;
    private int maxHealth;
    private int curMana;
    private int maxMana;
    private int block;
    private int startingBlock;
    private int focus = 0;
    private int strength = 0;
    private int defense = 0;
    private int spirit = 0;

    protected int curSpeed;
    protected int maxSpeed;

    protected int curEnergy;
    protected int maxEnergy;
    protected int manaPerTurn;
    protected int speedPerTurn;

    protected int energyPerTurn;
    private int energyAtStartOfTurn;
    protected Color tint;
    protected int tintTimer;
    protected int turn;
    protected Image icon;

    protected MovementType movementType;
    protected MovementType movementEffect;
    protected int timeSinceBlockChanged;

    private Cell cell;
    protected Cell startingCell;
    private Team team;
    protected Animation animation;
    protected Algorithm algorithm;
    protected int delay;
    protected float x;
    protected float y;

//    protected float xMovementOffset;

    // Upgrades


    protected ArrayList<Class<? extends Action>> actionPool;
    protected ArrayList<Upgrade> upgradePool;
    protected ArrayList<Class<? extends Action>> codePool;
    protected ArrayList<Class<? extends Perk>> perkPool;

    protected ModifierSet modifiers;

    // UI Elements
    protected Healthbar healthbar;
    // protected Blockbar blockbar;

    protected Blockbox blockbox;
    protected EnergyDiamonds energyDiamonds;

    Cell cellDestination;

    public Unit()
    {
        maxEnergy = 9999;
        energyPerTurn = 1;
        algorithm = new Algorithm(this);
        isAlive = true;
        maxSpeed = 100;
        curSpeed = 0;  // don't change this, it breaks tooltips ?
        speedPerTurn = 0;
        healthbar = new Healthbar(this);
        //     blockbar = new Blockbar(this);

        blockbox = new Blockbox(this);
        energyDiamonds = new EnergyDiamonds(this);
        modifiers = new ModifierSet(this);
        actionPool = new ArrayList<>();
        codePool = new ArrayList<>();
        perkPool = new ArrayList<>();
        upgradePool = new ArrayList<>();

        resetTimers();
        tint = Color.white;
        turn = 1;


    }

    public ArrayList<Class<? extends Action>> getActionPool()
    {
        return actionPool;
    }

    public ArrayList<Upgrade> getUpgradePool()
    {
        return upgradePool;
    }

    public ArrayList<Class<? extends Action>> getCodePool()
    {
        return codePool;
    }

    public ArrayList<Class<? extends Perk>> getPerkPool()
    {
        return perkPool;
    }


    /*****************************************************************\

     ACCESSORS

     \*****************************************************************/
    public int getCurHealth()
    {
        return curHealth;
    }

    public int getMaxHealth()
    {
        return maxHealth;
    }

    public int getCurSpeed()
    {
        return curSpeed;
    }

    public int getMaxSpeed()
    {
        return maxSpeed;
    }



    public String getDescription()
    {
        return description;
    }

    public int getCurEnergy()
    {
        return curEnergy;
    }

    public int getMaxEnergy()
    {
        return maxEnergy;
    }

    public int getCurMana()
    {
        return curMana;
    }

    public int getManaCostTotal()
    {
        int cost = 0;
        for(Action a : algorithm.getActions())
        {
            cost += a.getManaCost();
        }

        return cost;
    }


    public int getMaxMana()
    {
        return maxMana;
    }

    public int getStrength()
    {
        return strength;
    }

    public int getDefense()
    {
        return defense;
    }

    public int getFocus()
    {
        return focus;
    }

    public int getSpirit()
    {
        return spirit;
    }

    public int getEnergyPerTurn()
    {
        return energyPerTurn;
    }

    public int getManaPerTurn()
    {
        return manaPerTurn;
    }

    public int getSpeedPerTurn()
    {
        return speedPerTurn;
    }

    public int getEnergyAtStartOfTurn()
    {
        // used for UI stuff
        return energyAtStartOfTurn;
    }

    public int getBonusEnergy()
    {
        return modifiers.getBonusEnergy();
    }

    public int getBonusMana()
    {
        return modifiers.getBonusMana();
    }

    public float getPercentHealth()
    {
        return (float) getCurHealth() / (float) getMaxHealth();
    }

    public float getPercentMana()
    {
        if(getMaxMana() == 0)
        {
            return 0;
        }
        return (float) getCurMana() / (float) getMaxMana();
    }

    public float getPercentSpeed()
    {
        return (float) getCurSpeed() / (float) getMaxSpeed();
    }

    public boolean isLowHealth()
    {
        return getPercentHealth() < .5f;
    }

    public boolean isHighHealth()
    {
        return getPercentHealth() >= .5f;
    }

    public boolean isLowMana()
    {
        return getPercentMana() < .5f;
    }


    public Team getTeam()
    {
        return team;
    }

    public boolean isAlive()
    {
        return isAlive;
    }

    public Algorithm getAlgorithm()
    {
        return algorithm;
    }

    public int getTurn()
    {
        return turn;
    }


    public ModifierSet getModifiers()
    {
        return modifiers;
    }

    public String getName()
    {
        return name;
    }

    public Image getIcon()
    {
        return icon;
    }

    public float getX()
    {
        return x;
    }

    public float getY()
    {
        return y;
    }

    public float getWidth()
    {
        return animation.getWidth();
    }

    public float getHeight()
    {
        return animation.getHeight();
    }

    public int getBlock()
    {
        return block;
    }

    public int getTimeSinceBlockChanged()
    {
        return timeSinceBlockChanged;
    }

    public Cell getCell()
    {
        return cell;
    }

    public Row getRow()
    {
        return team.getRow();
    }

    public boolean isPlayer()
    {
        return getTeam() == Team.PLAYER;
    }

    public boolean isEnemy()
    {
        return getTeam() == Team.ENEMY;
    }

    public int getDelay()
    {
        return delay;
    }


    public int getStartingBlock()
    {
        return startingBlock;
    }

    public Cell getStartingCell()
    {
        return startingCell;
    }

    public Animation getAnimation()
    {
        return animation;
    }

    public Color getClassColor()
    {
        return classColor;
    }

    public int compareTo(Unit other)
    {
        if (getDelay() < other.getDelay())
        {
            return -1;
        }
        else if (getDelay() > other.getDelay())
        {
            return 1;
        }
        else
        {
            return 0;
        }
    }


    /*****************************************************************\

     MUTATORS

     \*****************************************************************/

    public void setTeam(Team team)
    {
        this.team = team;
    }

    public void setCell(Cell newCell, boolean permanent)
    {
        // Players and Enemies can't cross positions
//        if((getTeam() == Team.PLAYER && Team.ENEMY.getRow() == newCell.getRow()) ||
//           (getTeam() == Team.ENEMY && Team.PLAYER.getRow() == newCell.getRow()))
//        {
//            return;
//        }

        if (newCell == null)
        {
            return;
        }

        // Store existing data
        Cell oldCell = getCell();
        Unit otherUnit = newCell.getUnit();

        // Remove from old cell if it has an old cell
        if (oldCell != null)
        {
            oldCell.clear();
        }

        // Assign to new cell
        newCell.setUnit(this);
        cell = newCell;

        if (permanent)
        {
            startingCell = newCell;
        }

        // Swap if needed
        if (otherUnit != null)
        {
            oldCell.setUnit(otherUnit);
            otherUnit.cell = oldCell;

            if (permanent)
            {
                otherUnit.startingCell = oldCell;
            }

            otherUnit.resetPosition();
        }

        if (movementType == null && movementEffect == null)
        {
            resetPosition();
        }

    }

    public void setCellLater(Cell cell)
    {
        cellDestination = cell;

    }

    public void setCellLaterResolution()
    {
        if (cellDestination == null)
        {
            return;
        }

        setCell(cellDestination, false);

    }

    public void setCell(int position)
    {
        setCell(getRow().getCell(position), false);
    }

    public void loseMana(int amount, Ability source)
    {
        curMana -= amount;

        if (isLowMana() && !lowMana)
        {
            modifiers.triggerOnLowMana();
            lowMana = true;
        }


        if (curMana < 0)
        {

            curMana = 0;
        }
    }

    public void gainMana(int amount)
    {
//        System.out.println("gaining mana" + curMana + " + " + amount);

        curMana += amount;

//
        if (curMana > maxMana)
        {
            curMana = maxMana;
        }
    }

    public void takeDamage(int amount, DamageType type, Ability source)
    {

        int totalDamage = amount;

        // DoT effects do not trigger damage taken effects or damage received scalars
        if (type.primaryDamageType())
        {
            totalDamage = modifiers.getModifiedDamageRecieved(totalDamage, source.getTags());
            modifiers.triggerOnDamageTaken(type, source);
            source.applyDamageDealtTriggers(amount, this, type);
        }

        // Block
        int blockDamage = 0;

        if (!type.ignoresBlock())
        {
            blockDamage = (int) (Math.min(amount, block));
//            actualBlockLoss = Math.round(damageDirectedToBlock * 1.0f);
            loseBlock(blockDamage);
        }


        // Actual Damage
        int healthDamage = totalDamage - blockDamage;

        curHealth -= healthDamage;

        // Track first bloody triggers
        if (isLowHealth() && !bloodied)
        {
            modifiers.triggerOnBloodied();
            bloodied = true;
        }


        if (curHealth <= 0)
        {
            curHealth = 0;
            isAlive = false;
            clearCell();
        }

//        if(actualDamage > 0)
//        {
        Combat.addMessage(new FloatText(this, totalDamage, Color.white));
        tint = new Color(240, 60, 60, 200);
        tintTimer = TINT_TIME;
//        }
//        else
//        {
//            Combat.addMessage(new FloatText(this, "Blocked!", type.getColor()));
//        }
    }

    public void heal(int amount, Ability ability)
    {
        amount = modifiers.getModifiedHealingRecieved(amount, ability.getTags());
        modifiers.triggerOnHealingReceived();
        regainHealth(amount);
    }


    public void regainHealth(int amount)
    {
        curHealth += amount;

        if (curHealth >= maxHealth)
        {
            curHealth = maxHealth;
        }

        if (amount > 0)
        {
            Combat.addMessage(new FloatText(this, amount, new Color(0, 255, 0)));
        }
    }

    public void addStrength(int value)
    {
        strength += value;
    }

    public void addDefense(int value)
    {
        defense += value;
    }

    public void addSpirit(int value)
    {
        spirit += value;
    }

    public void addFocus(int value)
    {
        focus += value;
    }

    public void addSpeed(int value)
    {
        curSpeed += value;
        if (curSpeed >= maxSpeed)
        {
            addEnergy(1);
            curSpeed -= maxSpeed;
        }

        if (curSpeed <= 0)
        {
            curSpeed = 0;
        }
    }


    public void addMaxHealth(int value)
    {
        maxHealth += value;
        curHealth += value;
    }

    public void addMaxMana(int value)
    {
        maxMana += value;
    }

    public void addManaPerTurn(int value)
    {
        manaPerTurn += value;
    }

    public void addSpeedPerTurn(int value)
    {
        speedPerTurn += value;
    }

    public void addStartingBlock(int value)
    {
        startingBlock += value;
    }

    public void setEnergyPerTurn(int value)
    {
        energyPerTurn = value;
    }

    public void setDelay(int value)
    {
        delay = value;
    }

    public void addDelay(int value)
    {
        delay += value;
    }

    public void reduceDelay(int value)
    {
        delay -= value;
    }

    public void increaseTurn()
    {
        turn++;
    }

    public void addBlock(int value)
    {
        timeSinceBlockChanged = 0;
        block += value;

        modifiers.triggerOnBlockGained();

        Combat.addMessage(new FloatText(this, value, Tag.BLOCK.getColor()));

    }

    public void loseBlock(int value)
    {
        timeSinceBlockChanged = 0;
        block -= value;

        if (block < 0)
        {
            block = 0;
        }

    }

    public void addEnergy(int value)
    {
        curEnergy += value;

        if (curEnergy > maxEnergy)
        {
            curEnergy = maxEnergy;
        }
    }

    public void loseEnergy(int value)
    {
        curEnergy -= value;

        if (curEnergy < 0)
        {
            curEnergy = 0;
        }
    }

    public void addCondition(Class<? extends Condition> clazz, int stacks, Unit source)
    {
        if (stacks > 0)
        {
            Condition c = modifiers.conditionFactory(clazz, stacks);
            addCondition(c, source);
        }
    }

    public void addCondition(Condition c, Unit source)
    {
        modifiers.add(c);
        c.setOwner(this);
        c.setSource(source);
    }

    public void addConditionLater(Condition c, Unit source)
    {
        modifiers.addLater(c);
        c.setOwner(this);
        c.setSource(source);
    }

    public void addPerk(Perk perk)
    {
        modifiers.addPerk(perk);
        perk.setOwner(this);
    }


    public void setPosition(float x, float y)
    {
        this.x = x;
        this.y = y;
//        System.out.println(this + " set" + "(" + x + ", " + y + ")" );


    }

    public void addAction(Action action)
    {
        algorithm.add(action);
    }

    public void replaceAction(Action action)
    {
        algorithm.replace(action);
    }

    public void resetPosition()
    {
        x = getCell().getX();
        y = getCell().getY();
//        xMovementOffset = 0;
    }

    public void reset()
    {
        //  Attributes

        revive();
        regainHealth();
        resetEnergy();
        resetMana();
        resetSpeed();
        resetBlock();
        resetTimers();
        modifiers.reset();

        // Position
        setCell(getStartingCell(), false);
        resetPosition();

        // Conditions
        clearConditions();

//        if (getFocus() > 0)
//        {
//            Condition focus = new Energize();
//            focus.setStacks(getFocus());
//            addCondition(focus, this);
//        }

        addCondition(Defense.class, defense, this);
        addCondition(Focus.class, focus, this);
        addCondition(Strength.class, strength, this);
        addCondition(Spirit.class, spirit, this);

        // Actions


        animation.setPaused(false);
        algorithm.reset();
        turn = 1;
    }

    public void regainHealth()
    {
        curHealth = maxHealth;
        isAlive = true;
        bloodied = false;

    }

    public void resetEnergy()
    {
        curEnergy = 0;
    }

    public void resetMana()
    {
        curMana = maxMana;
    }

    public void endCycle()
    {

       // gainMana(manaPerCycle);
    }

    public void resetSpeed()
    {
        curSpeed = 0;
    }

    public void resetBlock()
    {
        block = startingBlock;
    }

    public void resetTimers()
    {
        timeSinceBlockChanged = 5000;
    }

    public void revive()
    {
        isAlive = true;
    }

    public void clearCell()
    {
        if (cell != null)
        {
            cell.clear();
//            cell = null;
        }
    }

    public void clearConditions()
    {
        modifiers.clearConditions();
    }

    public void setStartingCell(Cell startingCell)
    {
        this.startingCell = startingCell;
    }

    // Main Event Loops

    public void updateActions()
    {
        if (Combat.inActionMode() && Combat.getActingUnit() == this)
        {
            getAlgorithm().getNextAction().update();
            getAlgorithm().getNextAction().setMovement();      // sets the movement method
            movement();

        }
    }


    /*****************************************************************\

     CORE METHODS

     \*****************************************************************/

    public void update()
    {
        if (tintTimer > 0)
        {
            tintTimer--;
            float percent = (float) Math.pow((float) tintTimer / (float) TINT_TIME, 2);
            getAnimation().setColor(Color.getScalingTint(tint, percent));
        }
        else
        {
            getAnimation().setColor(Color.white);
        }

        updateForcedMovement();

        animation.update();

        animation.setPosition(getX(), getY());

        timeSinceBlockChanged++;


    }

    public void act(Action a)
    {
        if (Combat.isBattleOver())
        {
            return;
        }

        if (a.canUse())
        {
            a.activate();


        }
    }


    public void render(Graphics g)
    {
        if (isAlive())
        {
            if (Combat.getCombatState() == CombatState.BATTLE && Combat.inActionMode() && Combat.getActingUnit() == this)
            {
                g.setColor(new Color(255, 255, 0, 100));
                g.fillOval(getX(), getY() + getHeight() * .80f, getWidth(), getHeight() * .22f);

                // Double outline
                g.setLineWidth(5);
                g.setColor(new Color(255, 255, 0, 200));
                g.drawOval(getX(), getY() + getHeight() * .80f, getWidth(), getHeight() * .22f - 4);
                g.drawOval(getX(), getY() + getHeight() * .80f, getWidth(), getHeight() * .22f);
                g.setLineWidth(1);
            }

            animation.render(g);
            healthbar.render(g);
            blockbox.render(g);

        }

//        Text.setFont(Fonts.massiveFontMono);
//        Text.setColor(Color.white);
//        Text.draw(""+getCell().getPosition(), x, y);


//        Cell c = getRow().getForwardCell(this);
//
//        if (c != null)
//        {
//            g.setColor(Color.red);
//            g.drawRect(c.getX(), c.getY(), 50, 50);
//        }
//
//        g.setColor(Color.green);
//        g.drawRect(getX(), getY(), 20, 20);
    }

    public boolean isMouseOver()
    {
        return Mouse.getX() >= getCell().getX() && Mouse.getX() <= getCell().getX() + getCell().getWidth() &&
                Mouse.getY() >= getCell().getY() && Mouse.getY() <= getCell().getY() + getCell().getHeight();
    }

    public void startTurn()
    {

        if (getAlgorithm().getFirstAction() == getAlgorithm().getNextAction())
        {
            resetBlock();
        }
         gainMana(getManaPerTurn());
        addSpeed(getSpeedPerTurn());
        addEnergy(getEnergyPerTurn());
        getAnimation().setPaused(true);
        getModifiers().triggerStartTurn();
        energyAtStartOfTurn = getCurEnergy();
    }

    public void enterCombatState()
    {
        reset();
    }

    public void startBattleMode()
    {
        getModifiers().triggerStartCombat();
    }

    public void endTurn()
    {

        clearMovement();
        increaseTurn();
        getAnimation().setPaused(false);
        getModifiers().triggerEndTurn();
        addDelay(InitiativeQueue.DELAY_PER_TURN);

        if (algorithm.getNextAction().isDisabled())
        {
            algorithm.advanceAlgorithm();
        }
    }

    public void endBattle()
    {
        reset();
    }

    /*****************************************************************\

     MOVEMENT STUFF

     \*****************************************************************/


    int moveTimeTotal;
    int moveTimeLeft;
    Cell destination;

    public void forcedMovement(Cell c, int time)
    {
        moveTimeLeft = time;
        moveTimeTotal = time;
        destination = c;

        if(c.hasUnit())
        {
            c.getUnit().forcedMovementHelper(getCell(), time);
        }
    }

    private void forcedMovementHelper(Cell c, int time)
    {
        moveTimeLeft = time;
        moveTimeTotal = time;
        destination = c;
    }

    public void updateForcedMovement()
    {
        if (destination == null || Main.isPaused())
        {
            return;
        }

//        System.out.println(x + " " + xMovementOffset);

        if (Utility.getDistance(x, y, destination.getX() , destination.getY()) < 50)
        {
            setCell(destination, false);
            destination = null;
//            movementType = MovementType.NONE;
            return;
        }

        float xDist = destination.getX() - getCell().getX();
        float yDist = destination.getY() - getCell().getY();

        x += xDist / (float) moveTimeTotal;
        y += yDist / (float) moveTimeTotal;

    }

    public void clearMovement()
    {
        setPosition(cell.getX(), cell.getY());
        movementType = null;
        movementEffect = null;
//        xMovementOffset = 0;
    }

    public void applyMovementEffect(MovementType move)
    {
        this.movementEffect = move;
    }

    public void movement()
    {
        Action action = Combat.getCurrentAction();
        int totalTime = action.getTime();

        if (movementType == null)
        {
            movementType = action.getMovement();
        }

        float movementSpeed = Main.getGameScale() * .7f;

        if (isEnemy())
        {
            movementSpeed *= -1;
        }

        if (movementType.equals(MovementType.CAST))
        {
            cast(totalTime, movementSpeed);
        }
        else if (movementType.equals(MovementType.BLOCK))
        {
            step(totalTime, movementSpeed);
        }
        else if (movementType.equals(MovementType.STEP))
        {
            step(totalTime, movementSpeed);
        }
        else if (movementType.equals(MovementType.RUSH))
        {
            rush(totalTime, movementSpeed);
        }
    }

    public void cast(int totalTime, float speed)
    {
        // move during the first part
        if (Combat.getActionTimer() >= totalTime * .90)
        {
            x += speed * .75f;
//            xMovementOffset += speed * .75f;
        }

        // back in the latter part
        if (Combat.getActionTimer() <= totalTime * .10)
        {
            x -= speed * .75f;
//            xMovementOffset -= speed * .75f;
        }
    }

    public void block(int totalTime, float speed)
    {
        // move during the first part
        if (Combat.getActionTimer() >= totalTime * .65 && Combat.getActionTimer() <= totalTime * .75)
        {
            x -= speed * .75f;
//            xMovementOffset -= speed * .75f;

        }

        // back in the latter part
        if (Combat.getActionTimer() <= totalTime * .35 && Combat.getActionTimer() >= totalTime * .25)
        {
            x = speed * .75f;
//            xMovementOffset += speed * .75f;
        }
    }

    public void step(int totalTime, float speed)
    {
        // move during the first part
        if (Combat.getActionTimer() >= totalTime * .85)
        {
            x += speed;
//            xMovementOffset += speed;
        }

        // back in the latter part
        if (Combat.getActionTimer() <= totalTime * .15)
        {
            x -= speed;
//            xMovementOffset -= speed;
        }
    }

    public void rush(int totalTime, float speed)
    {
        // Rush from 35% to 50%
        if (Combat.getActionTimer() >= totalTime * .50 && Combat.getActionTimer() <= totalTime * .65)
        {
            x += speed * 3;
//            xMovementOffset -= speed * 3;

        }

        // Return from 45% to end
        if (Combat.getActionTimer() <= totalTime * .45)
        {
            x -= speed;
//            xMovementOffset -= speed;
        }
    }
}
