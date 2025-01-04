package unit.hero.cleric.perks;

import unit.ability.action.Tag;
import unit.ability.perks.Perk;
import ui.Images;

public class Morninglord extends Perk
{

    public void setup()
    {
        name = "Morninglord";
        icon = Images.perkMorninglord;
        damageBonus = 2;
        tags.add(Tag.RADIANT);
    }

    public String getDescription()
    {
        return "[RADIANT]Radiant Damage[] is increased by 2.";
    }

}
