package unit.hero.cleric.actions.dawnbringer;

import ui.Images;
import ui.sound.Sounds;
import unit.ability.action.*;
import unit.ability.conditions.debuff.Burning;

public class Flamestrike extends Action
{
	public void setup()
	{
		name = "Flamestrike";

		addTag(Tag.ATTACK);
		addTag(Tag.MAGICAL);
		addTag(Tag.FIRE);
		setAnimation(MovementType.CAST);
	}

	public void rarity()
	{
		mana = 2;
		damage = scale(10)-3;
		addCondition(Burning.class, 2);
	}

	public void setTarget()
	{
		targets.add(getLowestHealthEnemy());
	}

	public void use()
	{
		damage(getTarget(), DamageType.FIRE);
		applyConditions(getTarget());
	}

	public void sound()
	{
		Sounds.fireballFlames.play();
	}

	public void animation()
	{
		animate(Images.animCastingGray, self(), Tag.FIRE.getColor());
		animate(Images.animFlame, getTarget(), .75f);
	}

	public String getDescription() 
	{
		return "Deal " + getDamageText() + " [FIRE]Fire Damage[] and apply " + getStacksText() + " [BURNING]Burning[] to the lowest health enemy";
	}
}
