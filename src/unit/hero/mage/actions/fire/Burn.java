package unit.hero.mage.actions.fire;

import unit.ability.action.*;
import unit.ability.conditions.debuff.Burning;
import ui.Images;
import ui.sound.Sounds;

public class Burn extends Action
{
	public void setup()
	{
		name = "Burn";

		addTag(Tag.ATTACK);
		addTag(Tag.MAGICAL);
		addTag(Tag.FIRE);
		setAnimation(MovementType.CAST);
		setUpgrade(Upgrade.FOCUS);
	}

	public void rarity()
	{
		mana = 1;
		damage = 5;
		addCondition(Burning.class, getRarity().getValue() + 2);
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
		return "Deal " + getDamageText() + " [FIRE]Fire Damage[] and apply " + getStacksText() + " [BURNING]Burning[] to the lowest health enemy";
	}
}
