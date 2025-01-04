package unit.ability.action;

public enum Speed
{
    VERY_SLOW, SLOW, AVERAGE, FAST, VERY_FAST;

    public String getDescription()
    {
        return switch(this)
        {
            case VERY_SLOW -> "Very Slow";
            case SLOW -> "Slow";
            case AVERAGE -> "Average";
            case FAST -> "Fast";
            case VERY_FAST -> "Very Fast";

        };
    }

    public int getValue()
    {
        return switch(this)
        {
            case VERY_SLOW -> -20;
            case SLOW -> -10;
            case AVERAGE -> 0;
            case FAST -> 10;
            case VERY_FAST ->20;

        };
    }
}
