package unit.ability.conditions.debuff;

import unit.ability.action.DamageType;
import unit.ability.action.Tag;
import animation.AnimationManager;
import animation.AnimationUnitTarget;
import unit.ability.conditions.Debuff;
import ui.Images;

public class Burning extends Debuff
{
	public Burning()
	{
		name = "Burning";
		icon = Images.conditionBurn;
		setColor(230, 120, 0);
		tags.add(Tag.BURNING);
		tags.add(Tag.DEBUFF);
	}
		
	public void onStartTurn()
	{
		getOwner().takeDamage(getStacks(), DamageType.BURNING, this);
		removeStack();
		animation();
		sound();
	}
	
	public String getDescription() 
	{
		return "Take 1 [BURNING]Burning Damage[] per stack at start of each turn, then remove one stack.";
	}

	public void sound() 
	{

	}

	public void animation() 
	{
		AnimationManager.add(new AnimationUnitTarget(Images.animFlame, getOwner(), Math.min(2f, .4f + .1f * getStacks())));
	}



}
