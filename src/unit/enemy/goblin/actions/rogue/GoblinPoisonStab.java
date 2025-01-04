package unit.enemy.goblin.actions.rogue;

import core.Color;
import ui.Images;
import ui.sound.Sounds;
import unit.ability.action.*;
import unit.ability.conditions.debuff.Poison;

public class GoblinPoisonStab extends EnemyAction
{
	public void setup()
	{
		name = "Poison Stab";
		damage = 7;
		speed = Speed.VERY_FAST;
		addCondition(Poison.class, 1);
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
		damage(DamageType.PHYSICAL);
	}

	public void sound()
	{
		Sounds.slashCut.play();
	}

	public void animation()
	{
		animate(Images.animSlash);
		animate(Images.animSlashingGray, self(), new Color(180, 180, 255));
	}

	public String getDescription() 
	{
		return "Deal " + getDamageText() + " [PHYSICAL]physical damage[] and apply [POISON]Poison[] "
				+ getStacksText() + " to the front enemy.";
	}
}
