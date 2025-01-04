package unit.enemy.skeleton.actions.skeleton;

import unit.ability.action.*;
import unit.ability.conditions.debuff.Bleed;
import ui.Images;
import ui.sound.Sounds;

public class Skewer extends EnemyAction
{
	public void setup()
	{
		name = "Skewer";
		energy = 1;
		damage = 5;
		addCondition(Bleed.class, 1);

		addTag(Tag.ATTACK);
		addTag(Tag.PHYSICAL);
		setAnimation(MovementType.RUSH);
	}

	public void setTarget()
	{
		targets.add(getFrontEnemy());
	}

	public void use()
	{
		damage(getFrontEnemy(), DamageType.PHYSICAL);
		applyConditions(getFrontEnemy());
	}

	public void sound()
	{
		Sounds.slashMetal.play();
	}

	public void animation()
	{
		animate(Images.animSlash, getFrontEnemy());
	}

	public String getDescription() 
	{
		return "Deal " + getDamageText() + " [PHYSICAL]physical damage[] to the front enemy.";
	}
}
