package unit.ability.conditions.buff;

import animation.AnimationManager;
import animation.AnimationUnitTarget;
import ui.Images;
import ui.sound.Sounds;
import unit.ability.action.Tag;
import unit.ability.conditions.Buff;

public class Rage extends Buff
{
	public Rage()
	{
		super();
		name = "Rage";
		icon = Images.conditionRage;

		setColor(30, 240, 30);
		tags.add(Tag.BUFF);
		tags.add(Tag.PHYSICAL);

		damageBonus = 1;
		damageReceivedBonus = -1;

	}

	public int getDamageReceivedBonus()
	{
		return damageReceivedBonus * getStacks();
	}

	public int getDamageBonus()
	{
		return damageBonus * getStacks();
	}

	public String getDescription()
	{
		return "Gain " + getStacks()+ " [PHYSICAL]Physical Damage[] and [PHYSICAL]Physical Resistance[] until the end of combat.";
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
