package unit.enemy.goblin.actions.rogue;

import core.Color;
import ui.Images;
import ui.sound.Sounds;
import unit.ability.action.*;
import unit.ability.conditions.debuff.Poison;

public class GoblinGrenade extends EnemyAction
{
	public void setup()
	{
		name = "Poison Grenade";
		setRarity(Rarity.UNCOMMON);
		addCondition(Poison.class, 4);
		addTag(Tag.ATTACK);
		addTag(Tag.PHYSICAL);
		setAnimation(MovementType.STEP);
	}

	public void setTarget()
	{
		targets.addAll(getEnemies());
	}

	public void use()
	{
		applyConditions();
	}

	public void sound()
	{
		Sounds.fireballFlames.play();
	}

	public void animation()
	{
		animate(Images.animDark, getTargets(), new Color(0, 255, 0));
		animate(Images.animCastingGray, self(), new Color(180, 180, 255));
	}

	public String getDescription() 
	{
		return "Apply " + getStacksText() + " [POISON]Poison[] to all enemies.";
	}
}
