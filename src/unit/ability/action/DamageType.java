package unit.ability.action;

import core.Color;
import core.Utility;
import ui.Images;

public enum DamageType
{
    PHYSICAL, FORCE, FIRE, COLD, LIGHTNING, RADIANT, SHADOW, PSYCHIC, BLEED, POISON, BURNING;

    public Color getColor()
    {
        // PRIMARY DAMAGE TYPES FIRST
        if (this == PHYSICAL) return new Color(255, 255, 255);
        if (this == FORCE) return new Color(255, 255, 255);

        if (this == FIRE) return new Color(255, 125, 0);
        if (this == COLD) return new Color(170, 170, 255);
        if (this == LIGHTNING) return new Color(255, 255, 125);

        if (this == RADIANT) return new Color(255, 255, 200);
        if (this == PSYCHIC) return new Color(255, 140, 200);
        if (this == SHADOW) return new Color(180, 180, 180);

        // SECONDARY DAMAGE TYPES NEXT
        if (this == BLEED) return new Color(200, 30, 30);
        if (this == POISON) return new Color(50, 230, 50);
        if (this == BURNING) return new Color(255, 125, 0);

        else return null;
    }

    public static DamageType getRandomElementalType()
    {

        int random = Utility.random(3);

        return switch (random)
        {
            case 0 -> DamageType.FIRE;
            case 1 -> DamageType.COLD;
            default -> DamageType.LIGHTNING;
        };
    }

    public boolean ignoresBlock()
    {
        return this == BLEED || this == POISON || this == BURNING;
    }

    public boolean primaryDamageType()
    {
        return this != BLEED && this != POISON && this != BURNING;
    }
}
