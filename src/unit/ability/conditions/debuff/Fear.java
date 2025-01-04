package unit.ability.conditions.debuff;

import ui.Images;
import unit.ability.action.Action;
import unit.ability.action.Tag;
import unit.ability.conditions.Debuff;

public class Fear extends Debuff
{
	public Fear()
	{
		name = "Fear";
		icon = Images.conditionFear;
		setColor(200, 120, 200);
		tags.add(Tag.DEBUFF);
		tags.add(Tag.ALL);
	}

	public int getDamageBonus()
	{
		return -1 * (stacks);
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
		return "Reduces [DAMAGE]Damage[] by the number of stacks.  Remove 1 stack each attack.";
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
