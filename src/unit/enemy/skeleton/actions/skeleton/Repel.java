package unit.enemy.skeleton.actions.skeleton;

import unit.ability.action.EnemyAction;
import unit.ability.action.MovementType;
import unit.ability.action.Tag;
import ui.Images;
import ui.sound.Sounds;

public class Repel extends EnemyAction
{
	public void setup()
	{
		name = "Repel";
		energy = 1;

		block = 4;

		addTag(Tag.BLOCK);
		setAnimation(MovementType.BLOCK);
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
		animate(Images.animShield, self());
	}

	public String getDescription()
	{
		return "Gain " + getBlockText() + " [BLOCK]Block[]." ;

	}
}
