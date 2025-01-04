package unit.ability.conditions.buff;

import unit.ability.action.Tag;
import unit.ability.conditions.Buff;
import ui.Images;

public class Focus extends Buff
{
	public Focus()
	{
		name = "Focus";
		icon = Images.conditionFocus;
		setColor(255, 255, 0);
		tags.add(Tag.BUFF);
		tags.add(Tag.MAGICAL);
	}

	@Override
	public int getDamageBonus()
	{
		return getStacks();
	}

	public String getDescription() 
	{
		return "Add " + getStacks()+ " to [MAGICAL]Magical Damage[] until the end of combat.";
	}

	public void sound() 
	{

	}

	public void animation() 
	{

	}



}
