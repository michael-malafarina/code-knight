package unit.hero.mage.actions.storm;

import ui.Images;
import ui.sound.Sounds;
import unit.ability.action.*;
import unit.ability.conditions.debuff.Burning;
import unit.ability.conditions.debuff.Shock;

public class Zap extends Action
{
	public void setup()
	{
		name = "Zap";

		addTag(Tag.ATTACK);
		addTag(Tag.MAGICAL);
		addTag(Tag.LIGHTNING);
		setAnimation(MovementType.CAST);
	}

	public void rarity()
	{
		damage = scale(10) - 4;
		addCondition(Shock.class, 2);
	}

	public void setTarget()
	{
		targets.add(getRandomEnemy());
	}

	public void use()
	{
		damage(getTarget(), DamageType.LIGHTNING);
		applyConditions(getTarget());

	}

	public void sound()
	{
		Sounds.magicImpact.play();
	}

	public void animation()
	{
		animate(Images.animCastingGray, self(), Tag.FIRE.getColor());
		animate(Images.animHoly, getTarget(), .75f);
	}

	public String getDescription() 
	{
		return "Deal " + getDamageText() + " [LIGHTNING]Lightning Damage[] and apply " + getStacksText() + " [SHOCK]Shock[] to a random enemy";
	}
}
