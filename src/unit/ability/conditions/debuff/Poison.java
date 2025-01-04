package unit.ability.conditions.debuff;

import unit.ability.action.DamageType;
import unit.ability.action.Tag;
import animation.AnimationUnitTarget;
import animation.AnimationManager;
import unit.ability.conditions.Debuff;
import ui.Images;
import ui.sound.Sounds;

public class Poison extends Debuff
{
	public Poison() 
	{
		name = "Poison";
		icon = Images.conditionPoison;
		setColor(0, 255, 0);

		tags.add(Tag.POISON);
		tags.add(Tag.DEBUFF);
	}
		
	public void onStartTurn()
	{
		getOwner().takeDamage(getStacks(), DamageType.POISON, this);
		animation();
		sound();
	}
	
	public String getDescription() 
	{
		return "Take 1 [POISON]Poison Damage[] per stack at start of each turn, then remove one stack.";
	}

	public void sound() 
	{

	}

	public void animation() 
	{
//		AnimationManager.add(new AnimationUnitTarget(Images.animSlash, getOwner()));
	}



}
