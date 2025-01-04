package unit.ability.conditions.buff;

import unit.ability.action.Action;
import unit.ability.action.Tag;
import unit.ability.conditions.Buff;
import ui.Images;

public class Power extends Buff
{
	public Power()
	{
		name = "Power";
		icon = Images.conditionMight;
		damageScalar = 1.5f;
		setColor(200, 30, 30);
		tags.add(Tag.BUFF);
		tags.add(Tag.ATTACK);
		tags.add(Tag.ALL);
	}

	public void onActionUsed(Action action)
	{
		if(action.getType() == Tag.ATTACK)
		{
			removeStack();
		}
	}

	public String getDescription()
	{
		return "Your next attack deals 50% more damage, then removes one stack.";
	}

	public void sound()
	{

	}

	public void animation()
	{

	}



}
