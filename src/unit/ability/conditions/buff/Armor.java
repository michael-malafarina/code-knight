package unit.ability.conditions.buff;

import unit.ability.action.Tag;
import unit.ability.conditions.Buff;
import ui.Images;

public class Armor extends Buff
{
	public Armor()
	{
		super();
		name = "Armor";
		icon = Images.conditionDefense;
		blockScalar = 1.5f;
		setColor(50, 80, 250);
		tags.add(Tag.BUFF);
		tags.add(Tag.BLOCK);
		tags.add(Tag.ALL);
	}

	public String getDescription() 
	{
		return "Increases your next [BLOCK]Block[] gained by 50%.";
	}

	public void onBlockGained()
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
