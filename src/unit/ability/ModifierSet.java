package unit.ability;


import unit.ability.action.Action;
import unit.ability.action.DamageType;
import unit.ability.action.Tag;
import unit.ability.action.code.Code;
import core.Utility;
import unit.ability.conditions.Condition;
import unit.ability.conditions.Debuff;
import unit.ability.conditions.buff.Glory;
import unit.ability.perks.Perk;
import org.newdawn.slick.Graphics;
import states.combat.Combat;
import unit.Unit;

import java.util.ArrayList;

public class ModifierSet
{
    private ArrayList<Modifier> recentModifiers;
    private ArrayList<Modifier> modifiers;
    private Unit owner;

    public ModifierSet(Unit owner)
    {
        this.owner = owner;
        modifiers = new ArrayList<>();
        recentModifiers = new ArrayList<>();
    }

    public boolean isOwner()
    {
        return owner != null;
    }

    public Unit getOwner()
    {
        return owner;
    }

    public ArrayList<Modifier> getModifiers()
    {
        return modifiers;
    }

    public ArrayList<Condition> getConditions()
    {
        ArrayList<Condition> conditions = new ArrayList<Condition>();

        for (Modifier m : modifiers)
        {
            if (m instanceof Condition)
            {
                conditions.add((Condition) m);
            }
        }

        return conditions;
    }

    public boolean hasDebuff()
    {
        return getDebuff() != null;
    }

    public Condition getDebuff()
    {
        for (Condition c : getConditions())
        {
            if (c.hasTag(Tag.DEBUFF))
            {
                return c;
            }
        }
        return null;
    }

    public boolean hasCondition(Class<? extends Condition> clazz)
    {
        return getCondition(clazz) != null;
    }

    public Condition getCondition(Class<? extends Condition> clazz)
    {
        for (Condition c : getConditions())
        {
            if (clazz.isInstance(c))
            {
                return c;
            }
        }
        return null;
    }

    public int getBonusEnergy()
    {
        int bonusEnergy = 0;

        for (Modifier m : modifiers)
        {
            if (m.hasEnergyBonus())
            {
                bonusEnergy += m.getEnergyBonus();
            }
        }

        return bonusEnergy;
    }

    public int getBonusMana()
    {
        int bonusMana = 0;

        for (Modifier m : modifiers)
        {
            if (m.hasEnergyBonus())
            {
                bonusMana += m.getEnergyBonus();
            }
        }

        return bonusMana;
    }


    /***************** Modified Attributes ************************/

    public ArrayList<Perk> getPerks()
    {
        ArrayList<Perk> perks = new ArrayList<>();

        for (Modifier m : modifiers)
        {
            if (m instanceof Perk)
            {
                perks.add((Perk) m);
            }
        }

        return perks;
    }

    public int getModifiedDamage(int baseDamage, ArrayList<Tag> tags)
    {
//        System.out.println(getOwner());

        float modifiedDamage = baseDamage;
//        System.out.println(modifiedDamage);


        // Add base damage from stats
//        modifiedDamage += owner.getStrength();
//        System.out.println(modifiedDamage);



        // Add bonuses
        for (Modifier m : modifiers)
        {
            modifiedDamage += m.getDamageBonusModifier(tags);
//            System.out.println(getOwner().getName() + " " + m.getName() + " " + m.getDamageBonusModifier(tags));


        }

        // Add scalars
        for (Modifier m : modifiers)
        {
            modifiedDamage *= m.getDamageScalarModifier(tags);
        }
//        System.out.println(modifiedDamage);



        return (int) modifiedDamage;
    }

    public int getModifiedDamageRecieved(int baseDamage, ArrayList<Tag> tags)
    {
        float modifiedDamage = baseDamage;

        for (Modifier m : modifiers)
        {
            modifiedDamage += m.getDamageReceivedBonusModifier(tags);
        }

        for (Modifier m : modifiers)
        {
            modifiedDamage *= m.getDamageReceivedScalarModifier(tags);
        }

        return (int) modifiedDamage;
    }

//    public float getEvadeChance(ArrayList<Tag> tags)
//    {
//        float evadeChance = 0;
//
//        for (Modifier m : modifiers)
//        {
//            evadeChance += m.getEvadeChance(tags);
//        }
//
//        return evadeChance;
//    }

    public int getModifiedHealing(int baseHealing, ArrayList<Tag> tags)
    {
        float modifiedHealing = baseHealing;

        // Add base stats
//        modifiedHealing += owner.getSpirit();

        // Add bonuses
        for (Modifier m : modifiers)
        {
            modifiedHealing += m.getHealingBonus();
        }

        // Add scalars
        for (Modifier m : modifiers)
        {
            modifiedHealing *= m.getHealingScalar();
        }

        return (int) modifiedHealing;
    }

    public int getModifiedHealingRecieved(int baseHealing, ArrayList<Tag> tags)
    {
        float modifiedHealing = baseHealing;

        for (Modifier m : modifiers)
        {
            modifiedHealing += m.getHealingReceivedBonus();
        }

        for (Modifier m : modifiers)
        {
            modifiedHealing *= m.getHealingRecievedScalar();
        }

        return (int) modifiedHealing;
    }

    public int getModifiedBlock(int baseBlock, ArrayList<Tag> tags)
    {
        float modifiedBlock = baseBlock;

        // add base stats
//        modifiedBlock += owner.getDefense();

        // add bonus
        for (Modifier m : modifiers)
        {
            modifiedBlock += m.getBlockBonus();
        }

        // add scalar
        for (Modifier m : modifiers)
        {
            modifiedBlock *= m.getBlockScalar();
        }

        return (int) modifiedBlock;
    }


    /*************************** MUTATORS ************************/

    public void reset()
    {
        for (Modifier m : modifiers)
        {
            m.reset();
        }
    }

    public void addLater(Condition c)
    {
//        System.out.println("ADDING " + c + "TO RECENT MODIFIERS");

        recentModifiers.add(c);
//        System.out.println("Mods" + recentModifiers.size());

    }

    public void updateModifiers()
    {
//        System.out.println("CALLING UPDATE MODIFIERS");
//
//        System.out.println("Mods" + recentModifiers.size());

        for(Unit u : Combat.getUnits())
        {
            for (Modifier m : u.getModifiers().recentModifiers)
            {
//                System.out.println("RECENT MOD CHECK: " + m);

                if (m instanceof Condition)
                {
                    u.getModifiers().add((Condition) m);
//                    System.out.println("ADDING " + m + "NORMALLY NOW");
                }
                else if (m instanceof Perk)
                {
                    addPerk((Perk) m);
                }
            }

            u.getModifiers().recentModifiers.clear();
            u.getModifiers().clearExpired();

        }

    }

    public void addPerk(Perk p)
    {
        modifiers.add(p);
    }

    public void add(Condition c)
    {
        // Remove incoming stacks based on glory
        for (Modifier m : modifiers)
        {
            if (m instanceof Glory)
            {
                Glory g = (Glory) m;

                // More glory - this ends here
                if (g.getStacks() >= c.getStacks())
                {
                    g.setStacks(g.getStacks() - c.getStacks());
                    return;
                }

                // Not enough glory, only mitigates
                else
                {
                    c.setStacks(c.getStacks() - g.getStacks());
                }
            }
        }


        for (Modifier m : modifiers)
        {
            if (m instanceof Condition && c.getClass().isInstance(m))
            {
//                System.out.println("ADDING more stacks of " + m);

                m.addStacks(c.getStacks());
                return;
            }
        }

        modifiers.add(c);
    }


    public void removeRandomDebuffStack()
    {
        removeRandomDebuffStacks(1);
    }

    public void removeRandomDebuffStacks(int amount)
    {
        ArrayList<Debuff> debuffs = new ArrayList<Debuff>();

        for (Modifier m : modifiers)
        {
            if (m instanceof Debuff)
            {
                debuffs.add((Debuff) m);
            }
        }

        if (debuffs.isEmpty())
        {
            return;
        }

        Debuff debuff = debuffs.get(Utility.random(debuffs.size()));

        debuff.removeStacks(amount);
    }

    public void removeRandomDebuff()
    {
        ArrayList<Debuff> debuffs = new ArrayList<Debuff>();

        for (Modifier m : modifiers)
        {
            if (m instanceof Debuff)
            {
                debuffs.add((Debuff) m);
            }
        }

        if (debuffs.isEmpty())
        {
            return;
        }

        Debuff debuff = debuffs.get(Utility.random(debuffs.size()));

        debuff.removeAll();
    }

    public void removeAllDebuffs()
    {
        for (int i = 0; i < modifiers.size(); i++)
        {
            if (modifiers.get(i) instanceof Debuff)
            {
                modifiers.remove(i);
                i--;
            }
        }
    }

    public void removeAllConditions()
    {
        for (int i = 0; i < modifiers.size(); i++)
        {
            if (modifiers.get(i) instanceof Condition)
            {
                modifiers.remove(i);
                i--;
            }
        }
    }

    public void removeAllConditions(Tag t)
    {
        for (int i = 0; i < modifiers.size(); i++)
        {
            if (modifiers.get(i) instanceof Condition && modifiers.get(i).hasTag(t))
            {
                modifiers.remove(i);
                i--;
            }
        }
    }

    public void update()
    {

    }

    public void render(Graphics g)
    {
        int conditionCount = 0;

        for (Modifier m : modifiers)
        {
            if (m instanceof Condition)
            {
                Condition c = (Condition) m;
                c.render(g, conditionCount);
                conditionCount++;
            }
        }
    }

    public void renderSecond(Graphics g)
    {
        for (Modifier m : modifiers)
        {
            if (m instanceof Condition c)
            {
                c.renderSecond(g);
            }
        }
    }

    public void clearExpired()
    {
        for (int i = 0; i < modifiers.size(); i++)
        {
            if (modifiers.get(i) instanceof Condition)
            {
                Condition c = (Condition) modifiers.get(i);

                if (c.isExpired())
                {
                    modifiers.remove(i);
                    i--;
                }
            }
        }
    }

    public void clearConditions()
    {
        for (int i = 0; i < modifiers.size(); i++)
        {
            if (modifiers.get(i) instanceof Condition)
            {
                modifiers.remove(i);
                i--;
            }
        }
    }

    /*************** Triggers *****************/

    public void triggerStartTurn()
    {
        for (int i = 0; i < modifiers.size(); i++)
        {
            Modifier m = modifiers.get(i);
            if (!getOwner().isAlive())
            {
                return;
            }
            m.onStartTurn();
        }

        updateModifiers();

    }


    public void triggerEndTurn()
    {
//        System.out.println(this + " " + this.getOwner());

        for (Modifier m : modifiers)
        {
            m.onEndTurn();
        }

        for (Modifier m : modifiers)
        {
            m.onEndTurnSecond();
        }

        updateModifiers();

    }


    public void triggerActionUsed(Action action)
    {

        if (action instanceof Code)
        {
            return;
        }

        for (int i = 0; i < modifiers.size(); i++)
        {
            modifiers.get(i).onActionUsed(action);
//            System.out.println("CALLING ACTION USED FOR EACH MODIFIER");
//            System.out.println("Mods " + recentModifiers.size());

        }

//        System.out.println("END TRIGGER ACTION USED");
//        System.out.println("Mods " + recentModifiers.size());


        updateModifiers();

    }


    public void triggerStartCombat()
    {
        for (Modifier m : modifiers)
        {
            m.onStartCombat();
        }

        updateModifiers();
    }

    public void triggerOnDamageDealt(int amount, Unit target, DamageType type)
    {
        for (Modifier m : modifiers)
        {
            m.onDamageDealt(amount, target, type);
        }

        updateModifiers();

    }

    public void triggerOnDamageTaken(DamageType type, Ability source)
    {
        for (Modifier m : modifiers)
        {
            m.onDamageTaken(type, source);
        }

        updateModifiers();

    }

    public void triggerOnBlockGained()
    {
        for (Modifier m : modifiers)
        {
            m.onBlockGained();
        }

        updateModifiers();

    }

    public void triggerOnHealingReceived()
    {
        for (Modifier m : modifiers)
        {
            m.onHealingRecieved();
        }

        updateModifiers();

    }

    public void triggerOnBloodied()
    {
        for (Modifier m : modifiers)
        {
            m.onBloodied();
        }

        updateModifiers();

    }

    public void triggerOnLowMana()
    {
        for (Modifier m : modifiers)
        {
            m.onLowMana();
        }

        updateModifiers();

    }

    public Condition conditionFactory(Class<? extends Condition> clazz, int stacks)
    {
        Condition c = null;

        try
        {
            c = clazz.newInstance();
            c.setStacks(stacks);
        }
        catch (InstantiationException | IllegalAccessException e)
        {
            e.printStackTrace();
        }

        return c;
    }
}

