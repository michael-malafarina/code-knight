package unit.hero.mage.actions.fire;

import unit.ability.action.*;
import unit.ability.conditions.debuff.Burning;
import ui.Images;
import ui.sound.Sounds;

public class Emberstorm extends Action
{
	public void setup()
	{
		name = "Emberstorm";

		addTag(Tag.ATTACK);
		addTag(Tag.MAGICAL);
		addTag(Tag.FIRE);
		setAnimation(MovementType.CAST);
		setUpgrade(Upgrade.MAX_MANA);
	}

	public void rarity()
	{
		mana = 2;
		damage = 4;
		addCondition(Burning.class, getRarity().getValue() + 2);
	}

	public void setTarget()
	{
		targets.addAll(getEnemies());
	}
			
	public void use()
	{
		damage(getTargets(), DamageType.FIRE);
		applyConditions(getTargets());
	}

	public void sound()
	{
		Sounds.fireballImpact.play();
	}

	public void animation()
	{
		animate(Images.animCastingGray, self(), Tag.FIRE.getColor());
		animate(Images.animFlame, getTargets(), .75f);
	}

	public String getDescription() 
	{
		return "Deal " + getDamageText() + " [FIRE]Fire Damage[] and apply " + getStacksText() + " [BURNING]Burning[] to all enemies.";
	}
}
