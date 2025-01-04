package unit.ability.conditions.buff;

import unit.ability.Ability;
import unit.ability.action.DamageType;
import unit.ability.action.Tag;
import unit.ability.conditions.Buff;
import unit.ability.conditions.debuff.Chill;
import ui.Images;

public class FrostShieldBuff extends Buff
{
	public FrostShieldBuff()
	{
		name = "Frost Shield";
		damageReceivedScalar = .80f;
		setColor(100, 180, 255);
		icon = Images.conditionFrostShield;
		tags.add(Tag.BUFF);
		tags.add(Tag.ALL);
	}

	public void onDamageTaken(DamageType type, Ability source)
	{
		source.getOwner().addCondition(Chill.class, getStacks()+1, self());
		removeStack();
	}

	public String getDescription() 
	{
		return "When hit, consume a stack to decrease damage received by 20% and apply [Debuff]Chill[] to the attacker equal to the number of stacks of Frost Shield.";
	}

	public void sound() 
	{

 	}

	public void animation() 
	{

 	}



}
