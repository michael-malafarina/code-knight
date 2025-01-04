package unit.ability.conditions.buff;

import unit.ability.action.Tag;
import unit.ability.conditions.Buff;
import ui.Images;

public class Defense extends Buff
{
	public Defense()
	{
		name = "Defense";
		blockBonus = getStacks();
		setColor(10, 80, 255);
		icon = Images.conditionDefense;
		tags.add(Tag.BUFF);
		tags.add(Tag.ALL);
	}

	public String getDescription() 
	{
		return "Add " + getStacks()+ " to [KEY]Block[] until the end of combat.";
	}

	public void sound() 
	{

 	}

	public void animation() 
	{

 	}



}
