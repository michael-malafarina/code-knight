package unit.hero.mage.actions.fire;

import unit.ability.action.*;
import unit.ability.conditions.debuff.Burning;
import ui.Images;
import ui.sound.Sounds;

public class Kindle extends Action
{
	public void setup()
	{
		name = "Kindle";
		addTag(Tag.DEBUFF);
		addTag(Tag.MAGICAL);
		addTag(Tag.FIRE);
		setAnimation(MovementType.CAST);
	}

	public void rarity()
	{
		mana = 4;

		switch (getRarity())
		{
			case Rarity.COMMON:
				speed = Speed.VERY_SLOW;
				break;
			case Rarity.UNCOMMON:
				speed = Speed.SLOW;
				break;
			case Rarity.RARE:
				speed = Speed.AVERAGE;
				break;
			case Rarity.EPIC:
				speed = Speed.FAST;
				break;
			case Rarity.LEGENDARY:
				speed = Speed.VERY_FAST;
				break;
		}
	}

	public void setTarget()
	{
		targets.add(hasMostStacksOfConditionEnemy(Burning.class));
	}

	public void use()
	{
		if(getTarget() == null)
		{
			return;
		}

		int stacks = getTarget().getModifiers().getCondition(Burning.class).getStacks();
		addCondition(Burning.class, stacks);

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
		return "Double the number of [BURNING]Burning[] stacks on the enemy with the most [BURNING]Burning[] stacks.";
	}
}
