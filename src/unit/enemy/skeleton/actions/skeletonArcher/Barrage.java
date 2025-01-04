package unit.enemy.skeleton.actions.skeletonArcher;

import unit.ability.action.*;
import ui.Images;
import ui.sound.Sounds;
import unit.Unit;

public class Barrage extends EnemyAction
{
	Unit targetOne;
	Unit targetTwo;
	Unit targetThree;

	public void setup()
	{
		name = "Barrage";
		energy = 2;

		damage = 7;

		addTag(Tag.ATTACK);
		addTag(Tag.PHYSICAL);
		setAnimation(MovementType.STEP);
	}

	public void setTarget()
	{
		targets.add(getRandomEnemy());
		targets.add(getRandomEnemy());
		targets.add(getRandomEnemy());

	}
			
	public void use()
	{
		damage(getTargets(), DamageType.PHYSICAL);
	}

	public void sound()
	{
		Sounds.slashMetal.play();
	}

	public void animation()
	{
		animate(Images.animSlash, getTargets());
	}

	public String getDescription() 
	{
		return "Deal " + getDamageText() + " [PHYSICAL]physical damage[] three times to random enemies.";
	}
}
