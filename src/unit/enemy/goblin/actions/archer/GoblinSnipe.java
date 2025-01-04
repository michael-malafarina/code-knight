package unit.enemy.goblin.actions.archer;

import unit.ability.action.*;
import ui.Images;
import ui.sound.Sounds;

public class GoblinSnipe extends EnemyAction
{
	public void setup()
	{
		name = "Snipe";
		damage = 11;
		speed = Speed.SLOW;
		setRarity(Rarity.UNCOMMON);
		addTag(Tag.ATTACK);
		addTag(Tag.PHYSICAL);
		setAnimation(MovementType.STEP);
	}

	public void setTarget()
	{
		targets.add(getLowestHealthEnemy());
	}

	public void use()
	{
		damage(DamageType.PHYSICAL);
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
		return "Deal " + getDamageText() + " [PHYSICAL]physical damage[] to the lowest health enemy.";
	}
}
