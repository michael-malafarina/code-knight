package unit.enemy.skeleton.actions.skeletonArcher;

import unit.ability.action.*;
import unit.ability.conditions.buff.Power;

public class Aim extends EnemyAction
{
	public void setup()
	{
		name = "Aim";
		energy = 1;
		addCondition(Power.class, 1);

		addTag(Tag.BUFF);

		setAnimation(MovementType.STEP);
	}

	public void setTarget()
	{
		targets.add(self());
	}

	public void use()
	{
		applyConditions();
	}

	public void sound()
	{

	}

	public void animation()
	{

	}

	public String getDescription() 
	{
		return "Deal " + getDamageText() + " [PHYSICAL]physical damage[] to a random enemy.";
	}
}
