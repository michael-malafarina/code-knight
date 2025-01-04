package unit.ability.conditions.buff;

import unit.ability.Ability;
import unit.ability.action.DamageType;
import unit.ability.action.Tag;
import unit.ability.conditions.Buff;
import ui.Images;

public class Barrier extends Buff
{
	public Barrier()
	{
		name = "Barrier";
		damageReceivedScalar = .50f;
		setColor(0, 40, 200);
		icon = Images.conditionBarrier;
		tags.add(Tag.BUFF);
		tags.add(Tag.MAGICAL);
	}

	public void onDamageTaken(DamageType type, Ability source)
	{
		removeStack();
	}

	public String getDescription() 
	{
		return "Decreases damage received from the next [Magical]Magical Attack[] by 50%.";
	}

	public void sound() 
	{

 	}

	public void animation() 
	{

 	}



}
