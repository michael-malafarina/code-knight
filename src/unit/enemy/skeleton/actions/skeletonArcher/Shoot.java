package unit.enemy.skeleton.actions.skeletonArcher;

import unit.ability.action.*;
import ui.Images;
import ui.sound.Sounds;

public class Shoot extends EnemyAction
{
	public void setup()
	{
		name = "Shoot";
		energy = 1;

		damage = 7;

		addTag(Tag.ATTACK);
		addTag(Tag.PHYSICAL);
		setAnimation(MovementType.STEP);
	}

	public void setTarget()
	{
		targets.add(getRandomEnemy());
	}

	public void use()
	{
		damage(getTarget(), DamageType.PHYSICAL);
	}

	public void sound()
	{
		Sounds.arrow.play();
	}

	public void animation()
	{
		animate(Images.animSlash, getTarget());
	}

	public String getDescription() 
	{
		return "Deal " + getDamageText() + " [PHYSICAL]physical damage[] to a random enemy.";
	}
}
