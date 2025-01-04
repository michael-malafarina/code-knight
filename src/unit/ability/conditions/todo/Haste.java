package unit.ability.conditions.todo;

import unit.ability.action.Tag;
import unit.ability.conditions.Buff;
import ui.Images;

public class Haste extends Buff
{
	public Haste() 
	{
		name = "Haste";
		icon = Images.conditionHaste;
		tags.add(Tag.BUFF);
	}
		
	public void onEndTurn()
	{
		removeStack();
	}
	
	public String getDescription() 
	{
		return "+30% speed";
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
