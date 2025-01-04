package unit.hero.mage.perks.cold;

import unit.ability.action.Tag;
import unit.ability.perks.Perk;
import ui.Images;

public class ColdMastery extends Perk
{

    public void setup()
    {
        name = "Cold Mastery";
        icon = Images.perkFrostMastery;
        damageBonus = 2;
        tags.add(Tag.COLD);
    }

    public String getDescription()
    {
        return "[COLD]Cold Damage[] is increased by 2.";
    }

}
