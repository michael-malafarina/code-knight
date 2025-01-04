package unit.ability.conditions.buff;

import unit.ability.action.Tag;
import animation.AnimationUnitTarget;
import animation.AnimationManager;
import unit.ability.conditions.Buff;
import ui.Images;
import ui.sound.Sounds;

public class Regen extends Buff
{
	public Regen()
	{
		super();
		name = "Regen";
		icon = Images.conditionRegen;
		setColor(30, 240, 30);
		tags.add(Tag.HEAL);
		tags.add(Tag.BUFF);
		tags.add(Tag.ALL);
	}
		
	public void onStartTurn()
	{
		getOwner().heal(getStacks(), this);
		removeStack();
		animation();
		sound();
	}
	
	public String getDescription() 
	{
		return "[HEAL]Heal[] 1 HP per stack at end of turn, then remove 1 stack.";
	}

	public void sound() 
	{
		Sounds.heal.play();
	}

	public void animation() 
	{
		AnimationManager.add(new AnimationUnitTarget(Images.animHeal, getOwner()));
	}



}
