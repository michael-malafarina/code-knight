package unit.ability.conditions.todo;

import unit.ability.action.Tag;
import unit.ability.conditions.Debuff;
import ui.Images;

public class Slow extends Debuff
{
	public Slow() 
	{
		name = "Slow";
		icon = Images.conditionSlow;
		tags.add(Tag.BUFF);
	}
		
	public void onEndTurn()
	{
		removeStack();
	}
	
	public String getDescription() 
	{
		return "-30% Speed";
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
