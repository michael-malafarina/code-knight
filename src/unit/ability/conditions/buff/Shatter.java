package unit.ability.conditions.buff;

import unit.ability.action.Tag;
import unit.ability.conditions.Debuff;
import ui.Images;

public class Shatter extends Debuff
{
	public Shatter()
	{
		name = "Shatter";
		icon = Images.conditionShattered;
		blockScalar = .5f;
		setColor(201, 87, 26);
		tags.add(Tag.DEBUFF);
		tags.add(Tag.BLOCK);
		tags.add(Tag.ALL);
	}

	public String getDescription()
	{
		return "Decreases your next [BLOCK]Block[] gained by 50%.";
	}

	public void onBlockGained()
	{
		removeStack();
	}

	public void sound() 
	{

	}

	public void animation() 
	{

 	}



}
