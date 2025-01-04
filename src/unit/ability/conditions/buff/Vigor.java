package unit.ability.conditions.buff;

import unit.ability.action.Tag;
import unit.ability.conditions.Buff;
import ui.Images;

public class Vigor extends Buff
{
	public Vigor() 
	{
		name = "Vigor";
		icon = Images.conditionVigor;
		setColor(40, 200, 80);
		tags.add(Tag.BUFF);
		tags.add(Tag.ALL);
	}

	public int getHealingReceivedBonus()
	{
		return getStacks();
	}

	public String getDescription() 
	{
		return "Increase HP gained from [HEAL]Healing[] by " + getStacks();
	}

	public void sound() 
	{

	}

	public void animation() 
	{

	}

}
