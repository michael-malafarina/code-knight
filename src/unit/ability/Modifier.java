package unit.ability;

import unit.ability.action.Action;
import unit.ability.action.DamageType;
import unit.ability.action.Tag;
import core.Color;
import org.newdawn.slick.Image;
import unit.Unit;

import java.util.ArrayList;

public abstract class Modifier extends Ability
{


    protected Color color;
    protected int stacks;

    protected String name;
    protected Image icon;

    protected int healingBonus = 0;
    protected float healingScalar = 1;
    protected int healingReceivedBonus = 0;
    protected float healingRecievedScalar = 1;
    protected int damageBonus = 0;
    protected float damageScalar = 1;
    protected int damageReceivedBonus = 0;
    protected float damageReceivedScalar = 1;
    protected int blockBonus = 0;
    protected float blockScalar = 1;
    protected int speedBonus = 0;
    protected int energyBonus = 0;
    protected int manaBonus = 0;
//    protected float evadeChance = 0;

    public String getName()
    {
        return name;
    }

    //	public boolean hasHealingBonus()			{	return healingBonus != 0;				}
//	public boolean hasHealingScalar()			{	return healingScalar != 1;				}
//	public boolean hasHealingReceivedBonus()	{	return healingReceivedBonus != 0;		}
//	public boolean hasHealingReceivedScalar()	{	return healingRecievedScalar != 1;		}
    protected boolean hasDamageBonus()
    {
        return damageBonus != 0;
    }

    protected boolean hasDamageScalar()
    {
        return damageScalar != 1;
    }

    protected boolean hasDamageReceivedBonus()
    {
        return damageReceivedBonus != 0;
    }

    protected boolean hasDamageReceivedScalar()
    {
        return damageReceivedScalar != 1;
    }

//    protected boolean hasEvadeChance()
//    {
//        return evadeChance != 0;
//    }


    //	public boolean hasBlockBonus()				{	return blockBonus != 0;					}
//	public boolean hasBlockScalar()				{	return blockScalar != 1;					}
//	public boolean hasSpeedBonus()				{	return speedBonus != 0;					}
    public boolean hasEnergyBonus()
    {
        return energyBonus != 0;
    }

    public boolean hasIcon()
    {
        return getIcon() != null;
    }

    public Image getIcon()
    {
        return icon;
    }

    public int getHealingBonus()
    {
        return healingBonus;
    }

    public float getHealingScalar()
    {
        return healingScalar;
    }

    public int getHealingReceivedBonus()
    {
        return healingReceivedBonus;
    }

    public float getHealingRecievedScalar()
    {
        return healingRecievedScalar;
    }

    public int getDamageBonus()
    {
        return damageBonus;
    }

    public float getDamageScalar()
    {
        return damageScalar;
    }

    public int getDamageReceivedBonus()
    {
        return damageReceivedBonus;
    }

    public float getDamageReceivedScalar()
    {
        return damageReceivedScalar;
    }

    public int getBlockBonus()
    {
        return blockBonus;
    }

    public float getBlockScalar()
    {
        return blockScalar;
    }

//    public float getEvadeChance()
//    {
//        return evadeChance;
//    }

    public int getStacks()
    {
        return stacks;
    }

    public int getSpeedBonus()
    {
        return speedBonus;
    }

    public int getEnergyBonus()
    {
        return energyBonus;
    }

    public int getManaBonus()
    {
        return manaBonus;
    }

    abstract public void sound();

    abstract public void animation();


    public Modifier()
    {
        color = new Color(255, 255, 255);
    }

    abstract public String getDescription();

    public String getDescriptionShort()
    {
        return getDescription();
    }

    // DAMAGE MODIFIERS

    public boolean modifiesDamage(ArrayList<Tag> tags)
    {
        if (hasDamageBonus() || hasDamageScalar())
        {
            for (Tag t : tags)
            {
                if (hasTag(t) || hasTag(Tag.ALL))
                {
                    return true;
                }
            }

            return false;
        }
        else
        {
            return false;
        }
    }

    public int getDamageBonusModifier(ArrayList<Tag> tags)
    {
//		if(modifiesDamage(tags))
//		{
        for (Tag t : tags)
        {
//				System.out.println("tags....");
//				System.out.print(t + " " );
            if (hasTag(t) || hasTag(Tag.ALL))
            {
//					System.out.println("found it" );

                return getDamageBonus();
            }
        }
//		System.out.println("nope" );

        return 0;
//		}
//		return 0;
    }

    public float getDamageScalarModifier(ArrayList<Tag> tags)
    {
        if (modifiesDamage(tags))
        {
            for (Tag t : tags)
            {
                if (hasTag(t) || hasTag(Tag.ALL))
                {
                    return getDamageScalar();
                }
            }
            return 1;
        }
        return 1;
    }

    // DAMAGE RECIEVED MODIFIERS


    public boolean modifiesDamageReceived(ArrayList<Tag> tags)
    {
        if (hasDamageReceivedBonus() || hasDamageReceivedScalar())
        {
            for (Tag t : tags)
            {
                if (hasTag(t) || hasTag(Tag.ALL))
                {
                    return true;
                }
            }

            return false;
        }
        else
        {
            return false;
        }
    }

    public int getDamageReceivedBonusModifier(ArrayList<Tag> tags)
    {
        if (modifiesDamageReceived(tags))
        {
            for (Tag t : tags)
            {
                if (hasTag(t) || hasTag(Tag.ALL))
                {
                    return getDamageReceivedBonus();
                }
            }
            return 0;
        }
        return 0;
    }

    public float getDamageReceivedScalarModifier(ArrayList<Tag> tags)
    {
        if (modifiesDamageReceived(tags))
        {
            for (Tag t : tags)
            {
                if (hasTag(t) || hasTag(Tag.ALL))
                {
                    return getDamageReceivedScalar();
                }
            }
            return 1;
        }
        return 1;
    }

//    public float getEvadeChance(ArrayList<Tag> tags)
//    {
//        for (Tag t : tags)
//        {
//            if (hasTag(t) || hasTag(Tag.ALL))
//            {
//                return getEvadeChance();
//            }
//        }
//        return 1;
//    }

    public String toString()
    {
        return getName();
    }

    public boolean hasTag(Tag tag)
    {
        for (Tag t : tags)
        {
            if (t == tag)
            {
                return true;
            }
        }
        return false;
    }


//	public Color getColor()
//	{
//		for(Tag t : tags)
//		{
//			if(t.getColor() != null)
//			{
//				return t.getColor();
//			}
//		}
//		return Color.white;
//	}


    public int getDamage(int baseDamage)
    {
        return self().getModifiers().getModifiedDamage(baseDamage, tags);
    }

    public int getHealing(int baseHealing)
    {
        return self().getModifiers().getModifiedHealing(baseHealing, tags);
    }

    public int getBlock(int baseBlock)
    {
        return self().getModifiers().getModifiedBlock(baseBlock, tags);
    }


    public Color getColor()
    {
        return color;
    }

    // Mutators

    public void setColor(int red, int green, int blue)
    {
        color = new Color(red, green, blue);
    }

    public void setStacks(int amount)
    {
        stacks = amount;
    }

    public void addStacks(int amount)
    {
        stacks += amount;
    }

    public void removeStacks(int amount)
    {
        stacks -= amount;
    }

    public void removeAll()
    {
        stacks = 0;
    }

    public void removeStack()
    {
        stacks--;
    }

    public void halfStacks()
    {
        stacks /= 2;
    }

    // Triggers

    public void onStartCombat()
    {

    }

    public void onStartTurn()
    {

    }

    public void onEndTurn()
    {

    }

    public void onEndTurnSecond()
    {

    }

    public void onHealingRecieved()
    {

    }

    public void onDamageDealt(int amount, Unit target, DamageType type)
    {

    }


    public void onDamageTaken(DamageType type, Ability source)
    {

    }

    public void onBlockGained()
    {

    }

    public void onActionUsed(Action action)
    {

    }

    public void onBloodied()
    {

    }

    public void onLowMana()
    {

    }

    public void reset()
    {
        stacks = 0;
    }


}
