package unit.hero.cleric.actions.dawnbringer;

import unit.ability.action.*;
import ui.Images;
import ui.sound.Sounds;

public class Dawnbolt extends Action
{
	public void setup()
	{
		name = "Dawnbolt";

		addTag(Tag.ATTACK);
		addTag(Tag.RADIANT);
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
		damage(getTarget(), DamageType.RADIANT);
	}

	public void sound()
	{
		Sounds.fireballFlames.play();
	}

	public void animation()
	{
		animate(Images.animCastingGray, self(), Tag.RADIANT.getColor());
		animate(Images.animHoly, getTarget());
	}

	public String getDescription() 
	{
		return "Deal " + getDamageText() + " [RADIANT]Radiant Damage[] to the lowest health enemy.";
	}
}
