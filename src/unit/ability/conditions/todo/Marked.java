package unit.ability.conditions.todo;

import unit.ability.action.Tag;
import unit.ability.conditions.Debuff;
import ui.Images;

public class Marked extends Debuff
{
	public Marked() 
	{
		name = "Marked";
		icon = Images.conditionMark;
		tags.add(Tag.DEBUFF);
		tags.add(Tag.ALL);		
	}
		
	public void onEndTurn()
	{
		removeStack();
	}
	
	public String getDescription() 
	{
		return "?????";
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
