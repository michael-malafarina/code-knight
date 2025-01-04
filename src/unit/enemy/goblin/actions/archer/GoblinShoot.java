package unit.enemy.goblin.actions.archer;

import unit.ability.action.*;
import ui.Images;
import ui.sound.Sounds;

public class GoblinShoot extends EnemyAction
{
	public void setup()
	{
		name = "Shoot";
		damage = 9;
		speed = Speed.FAST;
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
		damage(DamageType.PHYSICAL);
		applyConditions();
	}

	public void sound()
	{
		Sounds.arrow.play();
	}

	public void animation()
	{
		animate(Images.animSlash);
	}

	public String getDescription() 
	{
		return "Deal " + getDamageText() + " [PHYSICAL]physical damage[] to a random enemy.";
	}
}
