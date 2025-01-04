package unit.ability.conditions.buff;

import unit.ability.action.Tag;
import unit.ability.conditions.Buff;
import ui.Images;

public class Spirit extends Buff
{
	public Spirit()
	{
		name = "Spirit";
		icon = Images.conditionSpirit;
		setColor(255, 255, 200);
		tags.add(Tag.BUFF);
		tags.add(Tag.HEAL);
	}

	@Override
	public int getHealingBonus()
	{
		return getStacks();
	}

	public String getDescription()
	{
		return "Add " + getStacks() + " to [HEAL]Healing[] until the end of combat.";
	}

	public void sound()
	{

	}

	public void animation()
	{

	}



}
