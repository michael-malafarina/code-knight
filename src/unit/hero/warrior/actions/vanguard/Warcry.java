package unit.hero.warrior.actions.vanguard;

import ui.Images;
import ui.sound.Sounds;
import unit.Unit;
import unit.ability.action.Action;
import unit.ability.action.MovementType;
import unit.ability.action.Speed;
import unit.ability.action.Tag;

public class Warcry extends Action
{
	int bonusSpeed;

	public void setup()
	{
		name = "Warcry";

		addTag(Tag.BUFF);
		setAnimation(MovementType.CAST);
	}

	public void rarity()
	{
		mana = 2 + getRarity().getValue();
		bonusSpeed = scale(20);
		speed = Speed.VERY_FAST;
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
		//Sounds.heroWarcry.play();
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
