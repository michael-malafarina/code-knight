package unit.ability.conditions.debuff;

import unit.ability.Ability;
import unit.ability.action.DamageType;
import unit.ability.action.Tag;
import animation.AnimationManager;
import animation.AnimationUnitTarget;
import unit.ability.conditions.Debuff;
import ui.Images;

public class Shock extends Debuff
{
	public Shock()
	{
		name = "Shock";
		icon = Images.perkLightningMastery;
		setColor(255, 255, 0);
		tags.add(Tag.DEBUFF);
	}
		
	public void onDamageTaken(DamageType type, Ability source)
	{
		if(source instanceof Shock)
		{
			return;
		}

		getOwner().takeDamage(getStacks(), DamageType.LIGHTNING, this);
		removeStack();
		animation();
		sound();
	}
	
	public String getDescription() 
	{
		return "Take 1 [SHOCK]Shock Damage[] per stack when you take damage, then remove one stack.";
	}

	public void sound() 
	{

	}

	public void animation() 
	{
		AnimationManager.add(new AnimationUnitTarget(Images.animFlame, getOwner(), Math.min(2f, .4f + .1f * getStacks())));
	}



}
