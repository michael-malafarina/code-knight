package unit.ability.conditions.buff;

import unit.ability.action.Tag;
import unit.ability.conditions.Buff;
import ui.Images;

public class Energize extends Buff
{
	public Energize()
	{
		name = "Focus";
		icon = Images.conditionFocus;
		setColor(255, 255, 0);
		energyBonus = 1;
		tags.add(Tag.BUFF);
	}
		
	public void onStartTurn()
	{
		getOwner().addEnergy(energyBonus);		// <--- this line is causing null
		removeStack();
		animation();
		sound();
	}
	
	public String getDescription() 
	{
		return "Gain 1 [KEY]Action[] at the start of each turn, then remove a stack.";
	}

	public void sound() 
	{

	}

	public void animation() 
	{

	}



}
