package unit.ability.conditions.todo;

import unit.ability.action.Tag;
import unit.ability.conditions.Buff;
import ui.Images;

public class Power extends Buff
{
	public Power()
	{
		name = "Power";
		icon = Images.conditionPower;
		tags.add(Tag.BUFF);
		tags.add(Tag.ALL);
	}

	public String getDescription() 
	{
		return "TODO - Adds energy on next turn?";
	}

	public void sound() 
	{
	//	Sounds.grunt.play();
	}

	public void animation() 
	{
		//AnimationManager.add(new AnimationCell(Images.animSlash, getOwner()));
	}



}
