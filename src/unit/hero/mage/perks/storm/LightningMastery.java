package unit.hero.mage.perks.storm;

import unit.ability.action.Tag;
import unit.ability.perks.Perk;
import ui.Images;

public class LightningMastery extends Perk
{

    public void setup()
    {
        name = "Lightning Mastery";
        icon = Images.perkLightningMastery;
        damageBonus = 2;
        //speedBonus = 5;
        tags.add(Tag.LIGHTNING);
    }

    public String getDescription()
    {
        return "[LIGHTNING]Lightning Damage[] is increased by 2.";
    }

}
