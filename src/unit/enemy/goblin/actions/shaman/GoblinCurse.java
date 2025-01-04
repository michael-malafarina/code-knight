package unit.enemy.goblin.actions.shaman;

import ui.Images;
import ui.sound.Sounds;
import unit.ability.action.*;
import unit.ability.conditions.debuff.Weak;

public class GoblinCurse extends EnemyAction
{
	public void setup()
	{
		name = "Curse";

		damage = 7;

		addCondition(Weak.class, 1);

		addTag(Tag.ATTACK);
		addTag(Tag.SHADOW);
		setAnimation(MovementType.CAST);
		setUpgrade(Upgrade.STRENGTH);
	}

	public void setTarget()
	{
		targets.add(getRandomEnemy());
	}
			
	public void use()
	{
		damage(getTarget(), DamageType.SHADOW);
		applyConditions(getTarget());
	}

	public void sound()
	{
		Sounds.fireballFlames.play();
	}

	public void animation()
	{
		animate(Images.animCastingGray, self(), Tag.SHADOW.getColor());
		animate(Images.animDark, getTarget());
	}

	public String getDescription() 
	{
		return "Deal " + getDamageText() + " [SHADOW]Shadow Damage[] and apply "
				+ "[DEBUFF]Weak[] " + getStacksText() + " to a random enemy.";
	}
}
