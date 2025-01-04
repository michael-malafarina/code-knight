package unit.hero.mage.perks.fire;

import unit.ability.action.Tag;
import unit.ability.perks.Perk;
import ui.Images;

public class FireMastery extends Perk
{

    public void setup()
    {
        name = "Fire Mastery";
        icon = Images.perkFlameMastery;
        damageBonus = 2;
        tags.add(Tag.FIRE);
    }

    public String getDescription()
    {
        return "[FIRE]Fire Damage[] is increased by 2.";
    }

}
