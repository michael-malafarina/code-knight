package unit.ability.conditions.buff;

import unit.ability.action.Tag;
import unit.ability.conditions.Buff;
import ui.Images;

public class Strength extends Buff
{
	public Strength()
	{
		name = "Strength";
		setColor(255, 255, 0);
		icon = Images.conditionStrength;
		tags.add(Tag.BUFF);
		tags.add(Tag.PHYSICAL);
	}

	@Override
	public int getDamageBonus()
	{
		return getStacks();
	}

	public String getDescription()
	{
		return "Add " + getStacks()+ " to [PHYSICAL]Physical Damage[] until the end of combat.";
	}

	public void sound() 
	{

 	}

	public void animation() 
	{

 	}



}
