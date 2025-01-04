package unit.ability.conditions.buff;

import ui.Images;
import unit.ability.action.Action;
import unit.ability.action.Tag;
import unit.ability.conditions.Buff;

public class Critical extends Buff
{
	public Critical()
	{
		name = "Critical";
		icon = Images.conditionCritical;
		damageScalar = .50f;
		setColor(200, 30, 30);
		tags.add(Tag.BUFF);
		tags.add(Tag.ATTACK);
		tags.add(Tag.ALL);
	}

	public float getDamageScalar()
	{
		return damageScalar * getStacks();
    }

	public void onActionUsed(Action action)
	{
		if(action.getType() == Tag.ATTACK)
		{
			removeAll();
		}
	}

	public String getDescription()
	{
		return "Your next attack deals an additional 50% damage per critical stack, then removes all stacks.";
	}

	public void sound()
	{

	}

	public void animation()
	{

	}



}
