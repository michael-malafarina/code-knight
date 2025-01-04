package unit.ability.perks;

import unit.ability.Modifier;
import unit.Unit;

public abstract class Perk extends Modifier
{
    PerkType type;

    public Perk()
    {
        type = PerkType.CLASS;

        setup();


    }

    abstract public void setup();

    public void setOwner(Unit owner)
    {
        super.setOwner(owner);

        if(type == PerkType.CLASS)
        {
            setColor(owner.getClassColor().getRed(), owner.getClassColor().getGreen(), owner.getClassColor().getBlue());
        }
        else
        {
            setColor(120, 120, 120);
        }
    }

    @Override
    public void sound()
    {

    }

    @Override
    public void animation()
    {

    }


}
