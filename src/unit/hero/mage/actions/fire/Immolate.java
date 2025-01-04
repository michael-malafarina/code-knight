package unit.hero.mage.actions.fire;

import unit.ability.action.*;
import unit.ability.conditions.debuff.Burning;
import ui.Images;
import ui.sound.Sounds;

public class Immolate extends Action
{
	public void setup()
	{
		name = "Immolate";
		addTag(Tag.DEBUFF);
		addTag(Tag.MAGICAL);
		addTag(Tag.FIRE);
		setAnimation(MovementType.CAST);
		setUpgrade(Upgrade.SPEED);
	}

	public void rarity()
	{
		mana = 4;
		addCondition(Burning.class, scale(6));
	}

	public void setTarget()
	{
		targets.add(getHighestHealthEnemy());
	}

	public void use()
	{
		damage(getTarget(), DamageType.FIRE);
		applyConditions(getTarget());

	}

	public void sound()
	{
		Sounds.fireballImpact.play();
	}

	public void animation()
	{
		animate(Images.animCastingGray, self(), Tag.FIRE.getColor());
		animate(Images.animFlame, getTarget(), .75f);
	}

	public String getDescription() 
	{
		return "Apply " + getStacksText() + " [BURNING]Burning[] to the highest health enemy";
	}
}
