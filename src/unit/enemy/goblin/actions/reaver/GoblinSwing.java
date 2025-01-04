package unit.enemy.goblin.actions.reaver;

import core.Color;
import ui.Images;
import ui.sound.Sounds;
import unit.ability.action.*;

public class GoblinSwing extends EnemyAction
{
	public void setup()
	{
		name = "Wild Swing";
		damage = 12;
		addTag(Tag.ATTACK);
		addTag(Tag.PHYSICAL);
		setAnimation(MovementType.RUSH);
	}

	public void setTarget()
	{
		targets.add(getFrontEnemy());
		targets.add(getSecondEnemy());
	}

	public void use()
	{
		damage(getFrontEnemy(), DamageType.PHYSICAL);
		damage(getSecondEnemy(), DamageType.PHYSICAL, .5f);
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
		return "Deal " + getDamageText() + " [PHYSICAL]physical damage[] to the front enemy and half as much to the second enemy.";
	}
}
