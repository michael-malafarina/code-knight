package unit.enemy.goblin.actions.archer;

import unit.ability.action.EnemyAction;
import unit.ability.action.MovementType;
import unit.ability.action.Tag;
import unit.ability.conditions.buff.Critical;
import unit.ability.conditions.buff.Power;
import unit.ability.conditions.buff.Strength;

public class GoblinAim extends EnemyAction
{
	public void setup()
	{
		name = "Aim";
		addCondition(Critical.class, 2);
		addTag(Tag.BUFF);
		setAnimation(MovementType.STEP);
	}

	public void setTarget()
	{
		targets.add(self());
	}

	public void use()
	{
		gainMana();
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
		return "Gain " + getStacksText() + " [BUFF]Critical[].";
	}
}
