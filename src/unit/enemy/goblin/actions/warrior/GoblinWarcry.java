package unit.enemy.goblin.actions.warrior;

import core.Color;
import ui.Images;
import ui.sound.Sounds;
import unit.ability.action.*;
import unit.ability.conditions.buff.Defense;
import unit.ability.conditions.buff.Strength;

public class GoblinWarcry extends EnemyAction
{
	public void setup()
	{
		name = "Warcry";
		setRarity(Rarity.UNCOMMON);
		addCondition(Strength.class, 1);
		addTag(Tag.BUFF);
		setAnimation(MovementType.STEP);
	}

	public void setTarget()
	{
		targets.addAll(getAllies());
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
		animate(Images.animCastingGray, self(), new Color(180, 180, 255));
	}

	public String getDescription() 
	{
		return "All allies gain " + getStacksText() + " [BUFF]Strength[].";
	}
}
