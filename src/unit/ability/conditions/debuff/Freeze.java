package unit.ability.conditions.debuff;

import unit.ability.action.Tag;
import unit.ability.conditions.Debuff;
import ui.Images;

public class Freeze extends Debuff
{
	public Freeze()
	{
		name = "Freeze";
		setColor(130, 180, 255);
		icon = Images.conditionFreeze;
		tags.add(Tag.DEBUFF);
	}

	public void onEndTurn()
	{
		removeStack();
	}

	public String getDescription() 
	{
		return "Stunned, TODO.";
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
