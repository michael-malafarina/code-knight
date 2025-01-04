package unit.ability.conditions.buff;

import ui.Images;
import unit.ability.Ability;
import unit.ability.action.DamageType;
import unit.ability.action.Tag;
import unit.ability.conditions.Buff;

public class Evade extends Buff
{
	public Evade()
	{
		name = "Evade";
		damageReceivedScalar = 0f;
		setColor(160, 60, 220);
		icon = Images.conditionEvade;
		tags.add(Tag.BUFF);
		tags.add(Tag.MAGICAL);
	}

	public void onDamageTaken(DamageType type, Ability source)
	{
		removeStack();
	}

	public String getDescription() 
	{
		return "No damage received from the next [Physical]Physical Attack[].";
	}

	public void sound() 
	{

 	}

	public void animation() 
	{

 	}



}
