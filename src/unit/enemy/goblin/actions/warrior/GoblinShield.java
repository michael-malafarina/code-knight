package unit.enemy.goblin.actions.warrior;

import ui.Images;
import ui.sound.Sounds;
import unit.ability.action.EnemyAction;
import unit.ability.action.MovementType;
import unit.ability.action.Speed;
import unit.ability.action.Tag;

public class GoblinShield extends EnemyAction
{
	public void setup()
	{
		name = "Shield";
		block = 9;
		speed = Speed.SLOW;
		addTag(Tag.BLOCK);
		setAnimation(MovementType.STEP);
	}

	public void setTarget()
	{
		targets.add(self());
	}

	public void use()
	{
		block();
	}

	public void sound()
	{
		Sounds.armorUp.play();
	}

	public void animation()
	{
		animate(Images.animShield);
	}

	public String getDescription() 
	{
		return "Gain " + getBlock() + " [BLOCK]Block[].";
	}
}
