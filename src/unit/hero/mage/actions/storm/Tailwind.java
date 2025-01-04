package unit.hero.mage.actions.storm;

import ui.Images;
import ui.sound.Sounds;
import unit.Unit;
import unit.ability.action.Action;
import unit.ability.action.DamageType;
import unit.ability.action.MovementType;
import unit.ability.action.Tag;
import unit.ability.conditions.debuff.Shock;

public class Tailwind extends Action
{
	int bonusSpeed;

	public void setup()
	{
		name = "Tailwind";

		addTag(Tag.BUFF);
		addTag(Tag.MAGICAL);
		addTag(Tag.LIGHTNING);
		setAnimation(MovementType.CAST);
	}

	public void rarity()
	{
		mana = 2 + getRarity().getValue();
		bonusSpeed = 10 + scale(20);
	}

	public void setTarget()
	{
		targets.addAll(getAllies());
	}

	public void use()
	{
		for(Unit u : getAllies())
		{
			u.addSpeed(bonusSpeed);
		}
	}

	public void sound()
	{
		Sounds.wind.play();
	}

	public void animation()
	{
		animate(Images.animCastingGray, self(), Tag.LIGHTNING.getColor());
		animate(Images.animCleanse, getTarget(), .75f);
	}

	public String getDescription() 
	{
		return "You and each ally gain " + bonusSpeed + " [KEY]Speed[].";
	}
}
