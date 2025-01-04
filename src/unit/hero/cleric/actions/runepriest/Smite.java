package unit.hero.cleric.actions.runepriest;

import unit.ability.action.*;
import unit.ability.conditions.debuff.Vulnerable;
import ui.Images;
import ui.sound.Sounds;

public class Smite extends Action
{
	public void setup()
	{
		name = "Smite";

		addTag(Tag.ATTACK);
		addTag(Tag.DEBUFF);
		addTag(Tag.PHYSICAL);
		setAnimation(MovementType.RUSH);
	}

	public void rarity()
	{
		mana = 2;
		damage = scale(8);
		addCondition(Vulnerable.class, 1);
	}

	public void setTarget()
	{
		targets.add(getFrontEnemy());
	}

	public void use()
	{
		damage(getTarget(), DamageType.PHYSICAL);
		applyConditions(getTarget());
	}

	public void sound()
	{
		Sounds.bashHeavy.play();
	}

	public void animation()
	{
		animate(Images.animSlashingGray, self(), Tag.RADIANT.getColor());
		animate(Images.animHoly, getTarget());
	}

	public String getDescription() 
	{
		return "Deal " + getDamageText() + " [PHYSICAL]Physical Damage[] and apply [DEBUFF]Vulnerable[] " + getStacksText() + " to the front enemy.";
	}
}
