package unit.enemy.goblin.actions.general;

import core.Color;
import unit.ability.action.*;
import ui.Images;
import ui.sound.Sounds;

public class GoblinPunch extends EnemyAction
{
	public void setup()
	{
		name = "Punch";
		damage = 5;

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

//		advance();

//		getTarget().newMoveTo(getBackwardCell(getTarget()), 20 / Settings.gameSpeed);
	}

	public void sound()
	{
		Sounds.bashHeavy.play();
	}

	public void animation()
	{
		animate(Images.animBlunt);
		animate(Images.animSlashingGray, self(), new Color(0, 180, 0));
	}

	public String getDescription() 
	{
		return "Deal " + getDamageText() + " [PHYSICAL]physical damage[] to the front enemy.";
	}
}
