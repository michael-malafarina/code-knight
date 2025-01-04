package unit.ability.conditions.debuff;

import unit.ability.action.DamageType;
import unit.ability.action.Tag;
import animation.AnimationUnitTarget;
import animation.AnimationManager;
import unit.ability.conditions.Debuff;
import ui.Images;

public class Bleed extends Debuff
{
	public Bleed() 
	{
		name = "Bleed";
		icon = Images.conditionBleed;
		setColor(200, 0, 0);
		tags.add(Tag.BLEED);
		tags.add(Tag.DEBUFF);
	}
		
	public void onStartTurn()
	{
		getOwner().takeDamage(getStacks(), DamageType.BLEED, this);
		removeStack();
		animation();
		sound();
	}
	
	public String getDescription() 
	{
		return "Take 1 [BLEED]Bleed Damage[] per stack at start of each turn, then remove one stack.";
	}

	public void sound() 
	{

	}

	public void animation() 
	{
		AnimationManager.add(new AnimationUnitTarget(Images.animSlash, getOwner()));
	}



}
