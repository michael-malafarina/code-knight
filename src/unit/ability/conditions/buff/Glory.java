package unit.ability.conditions.buff;

import unit.ability.action.Tag;
import unit.ability.conditions.Buff;
import ui.Images;

public class Glory extends Buff
{
	public Glory()
	{
		super();
		name = "Glory";
		setColor(255, 255, 0);
		icon = Images.conditionGlory;
		tags.add(Tag.BUFF);
		tags.add(Tag.ALL);		
	}

	public String getDescription() 
	{
		return "Incoming debuffs are reduced by one stack, consuming stacks of [BUFF]Glory[].";
	}

	public void sound() 
	{

 	}

	public void animation() 
	{

 	}



}
