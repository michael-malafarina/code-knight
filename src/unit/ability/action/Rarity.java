package unit.ability.action;

import core.Color;
import core.Utility;

public enum Rarity
{
    STARTER, COMMON, UNCOMMON, RARE, EPIC, LEGENDARY;

    public Color getColor()
    {
        return switch (this)
        {
            case STARTER -> new Color(255, 255, 255);
            case COMMON -> new Color(255, 255, 255);
            case UNCOMMON -> new Color(30, 255, 0);
            case RARE -> new Color(10, 130, 255);

           // case RARE -> new Color(0, 112, 221);
            case EPIC -> new Color(163, 53, 238);
            case LEGENDARY -> new Color(255, 128, 0);
        };
    }

    public Rarity downgrade()
    {
        return switch (this)
        {
            case STARTER -> STARTER;
            case COMMON, UNCOMMON -> COMMON;
            case RARE -> UNCOMMON;
            case EPIC -> RARE;
            case LEGENDARY -> EPIC;
        };
    }

    public Rarity upgrade()
    {
        return switch (this)
        {
            case STARTER, COMMON -> UNCOMMON;
            case UNCOMMON -> RARE;
            case RARE -> EPIC;
            case EPIC, LEGENDARY -> LEGENDARY;
        };
    }

    public float scaling()
    {
        return switch (this)
        {
            case STARTER -> 1;
            case COMMON -> 1;
            case UNCOMMON -> 1.25f;
            case RARE -> 1.5f;
            case EPIC -> 1.75f;
            case LEGENDARY -> 2.0f;
        };
    }

    public static Rarity getRandomRarityFromLevel(int level)
    {
        int value = level + Utility.random(0, 2);

        if(value >= 12)
        {
            return LEGENDARY;
        }
        else if(value >= 9)
        {
            return EPIC;
        }
        else if(value >= 6)
        {
            return RARE;
        }
        else
        {
            return UNCOMMON;
        }

    }

    public int getValue()
    {
        return switch (this)
        {
            case STARTER -> 0;
            case COMMON -> 0;
            case UNCOMMON -> 1;
            case RARE -> 2;
            case EPIC -> 3;
            case LEGENDARY -> 4;
        };
    }
}
