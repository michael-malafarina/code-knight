package unit.enemy.goblin.actions.warrior;

import core.Color;
import ui.Images;
import ui.sound.Sounds;
import unit.ability.action.*;

public class GoblinSlash extends EnemyAction
{
	public void setup()
	{
		name = "Slash";
		damage = 7;
		speed = Speed.FAST;
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
		return "Deal " + getDamageText() + " [PHYSICAL]physical damage[] to the front enemy.";
	}
}
