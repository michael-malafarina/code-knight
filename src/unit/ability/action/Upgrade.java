package unit.ability.action;

import core.Utility;
import unit.Unit;
import core.Color;

public enum Upgrade
{
    MAX_HP, MAX_MANA, SPEED, STRENGTH, FOCUS, DEFENSE, SPIRIT;

    public static Upgrade getRandom()
    {
        int r = Utility.random(Upgrade.values().length);
        return Upgrade.values()[r];
    }

    public String getDescription()
    {
        return switch (this)
        {
            case MAX_HP -> "Increases [HEALTH]Maximum Health[]";
            case MAX_MANA -> "Increases [MANA]Maximum Mana[]";
            case SPEED -> "Gain [SPEED]Speed[] Each Turn";
            case STRENGTH -> "Improves [PHYSICAL]Physical Damage[]";
            case FOCUS -> "Improves [MAGICAL]Magical Damage[].";
            case DEFENSE -> "Improves [BLOCK]Block[] gained from abilities.";
            case SPIRIT -> "Improves [HEAL]Healing[] provided by your abilities.";
        };
    }

    public String getName()
    {
        return toString();
    }

    public String toString()
    {
        return switch (this)
        {
            case MAX_HP -> "Health";
            case MAX_MANA -> "Mana";
            case SPEED -> "Speed";
            case STRENGTH -> "Strength";
            case FOCUS -> "Focus";
            case DEFENSE -> "Defense";
            case SPIRIT -> "Spirit";
        };
    }

    public Color getColor()
    {
        return new Color(255, 255, 255);
    }

    public int getAmount()
    {
        return switch (this)
        {
            case MAX_HP -> 10;
            case MAX_MANA -> 4;
            case SPEED -> 5;
            default -> 1;
        };
    }

    public void applyUpgrade(Unit u)
    {
        switch (this)
        {
            case MAX_HP -> u.addMaxHealth(getAmount());
            case MAX_MANA -> u.addMaxMana(getAmount());
            case SPEED -> u.addSpeedPerTurn(getAmount());
            case STRENGTH -> u.addStrength(getAmount());
            case FOCUS -> u.addFocus(getAmount());
            case DEFENSE -> u.addDefense(getAmount());
            case SPIRIT -> u.addSpirit(getAmount());
        };
    }
}
