package unit.hero.mage.actions.storm;

import unit.ability.action.*;
import ui.Images;
import ui.sound.Sounds;
import unit.Unit;

public class LightningBolt extends Action
{
	public void setup()
	{
		name = "Lightning Bolt";

		addTag(Tag.ATTACK);
		addTag(Tag.MAGICAL);
		addTag(Tag.LIGHTNING);
		setAnimation(MovementType.CAST);
	}

	public void rarity()
	{
		mana = 1;
		damage = scale(8);
	}

	public void setTarget()
	{
		targets.addAll(getEnemies());
	}

	public void use()
	{
		float scaling = 1f;
		for(Unit u : getTargets())
		{
			damage(u, DamageType.LIGHTNING, scaling);
			scaling -= .25f;
		}

		applyConditions(getTargets());

	}

	public void sound()
	{
		Sounds.magicImpact.play();
	}

	public void animation()
	{
		animate(Images.animCastingGray, self(), Tag.LIGHTNING.getColor());

		float scaling = 1f;
		for(Unit u : getTargets())
		{
			animate(Images.animHoly, u, scaling);
			scaling -= .25f;
		}

	}

	public String getDescription() 
	{
		return "Deal " + getDamageText() + " [LIGHTNING]Lightning Damage[] to the front enemy.  Pierces, dealing 25% less to each following enemy. ";
	}
}
