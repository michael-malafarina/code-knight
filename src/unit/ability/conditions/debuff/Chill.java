package unit.ability.conditions.debuff;

import unit.ability.action.Action;
import unit.ability.action.Tag;
import unit.ability.conditions.Debuff;
import ui.Images;

public class Chill extends Debuff
{
	public Chill()
	{
		name = "Chill";
		icon = Images.conditionChill;
		setColor(130, 180, 255);
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

	}

	public void animation() 
	{
//		AnimationManager.add(new AnimationUnitTarget(Images.animCold, getOwner(), Math.min(2f, .4f + .1f * getApplyStacks())));
	}



}
