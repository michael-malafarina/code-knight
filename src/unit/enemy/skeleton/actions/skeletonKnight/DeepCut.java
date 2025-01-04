package unit.enemy.skeleton.actions.skeletonKnight;

import unit.ability.action.*;
import unit.ability.conditions.debuff.Bleed;
import ui.Images;
import ui.sound.Sounds;

public class DeepCut extends EnemyAction
{
	public void setup()
	{
		name = "Deep Cut";

		damage = 6;
		addCondition(Bleed.class, 3);

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
		damage(getTarget(), DamageType.PHYSICAL);
	}

	public void sound()
	{
		Sounds.slashMetal.play();
	}

	public void animation()
	{
		animate(Images.animSlashing, self());
		animate(Images.animSlash, getTarget());
	}

	public String getDescription() 
	{
		return "Deal " + getDamageText() + " [PHYSICAL]physical damage[] to the front enemy.";
	}
}
