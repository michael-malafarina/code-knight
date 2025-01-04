package unit.ability.conditions.buff;

import ui.Images;
import unit.ability.Ability;
import unit.ability.action.DamageType;
import unit.ability.action.Tag;
import unit.ability.conditions.Buff;
import unit.ability.conditions.debuff.Chill;

public class Thorns extends Buff
{
	public Thorns()
	{
		name = "Thorns";
		setColor(100, 180, 255);
		icon = Images.conditionThorns;
		tags.add(Tag.BUFF);
		tags.add(Tag.ALL);
	}

	public void onDamageTaken(DamageType type, Ability source)
	{
		source.getOwner().takeDamage(getStacks(), DamageType.PHYSICAL, this);
		removeStack();
	}

	public String getDescription() 
	{
		return "When hit, damage the attack equal to number of Thorns stacks, then expend 1 stack.";
	}

	public void sound() 
	{

 	}

	public void animation() 
	{

 	}



}
