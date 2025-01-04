package unit.enemy.goblin.actions.reaver;

import core.Color;
import ui.Images;
import ui.sound.Sounds;
import unit.ability.action.*;
import unit.ability.conditions.buff.Rage;

public class GoblinRage extends EnemyAction
{
	public void setup()
	{
		name = "Rage";
		addCondition(Rage.class, 2);
		setRarity(Rarity.UNCOMMON);
		addTag(Tag.BUFF);
		setAnimation(MovementType.STEP);
	}

	public void setTarget()
	{
		targets.add(self());
	}

	public void use()
	{
		applyConditions();
	}

	public void sound()
	{
		Sounds.goblinYell.play();
	}

	public void animation()
	{
		animate(Images.animCastingGray, self(), new Color(255, 0, 0));
	}

	public String getDescription() 
	{
		return "Gain " + getStacksText() + " [BUFF]Rage[].";
	}
}
