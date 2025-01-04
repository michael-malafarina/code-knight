package unit.enemy.skeleton.actions.skeletonKnight;

import unit.ability.action.EnemyAction;
import unit.ability.action.MovementType;
import unit.ability.action.Tag;
import ui.Images;
import ui.sound.Sounds;

public class Guard extends EnemyAction
{
	public void setup()
	{
		name = "Guard";
		energy = 1;

		block = 9;

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
