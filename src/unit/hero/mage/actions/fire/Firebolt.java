package unit.hero.mage.actions.fire;

import ui.Images;
import ui.sound.Sounds;
import unit.ability.action.*;
import unit.ability.conditions.debuff.Burning;

public class Firebolt extends Action
{
	public void setup()
	{
		name = "Firebolt";

		addTag(Tag.ATTACK);
		addTag(Tag.MAGICAL);
		addTag(Tag.FIRE);
		setAnimation(MovementType.CAST);
	}

	public void rarity()
	{
		damage = scale(7);
	}

	public void setTarget()
	{
		targets.add(getLowestHealthEnemy());
	}

	public void use()
	{
		damage(getTarget(), DamageType.FIRE);
		applyConditions(getTarget());
	}

	public void sound()
	{
		Sounds.fireballFlames.play();
	}

	public void animation()
	{
		animate(Images.animCastingGray, self(), Tag.FIRE.getColor());
		animate(Images.animFlame, getTarget(), .75f);
	}

	public String getDescription() 
	{
		return "Deal " + getDamageText() + " [FIRE]Fire Damage[] to the lowest health enemy.";
	}
}
