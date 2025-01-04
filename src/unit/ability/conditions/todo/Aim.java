package unit.ability.conditions.todo;

import unit.ability.action.Tag;
import unit.ability.conditions.Buff;
import ui.Images;

public class Aim extends Buff
{
	public Aim()
	{
		name = "Aim";
		icon = Images.conditionAim;
		tags.add(Tag.BUFF);
	}

	public String getDescription() 
	{
		return "When an attack would target a random enemy, it instead targets the lowest health enemy.";
	}

	public void onEndTurn()
	{
		removeStack();
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
