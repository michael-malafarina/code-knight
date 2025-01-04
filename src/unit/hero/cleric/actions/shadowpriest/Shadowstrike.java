package unit.hero.cleric.actions.shadowpriest;

import unit.ability.action.*;
import unit.ability.conditions.debuff.Weak;
import ui.Images;
import ui.sound.Sounds;

public class Shadowstrike extends Action
{
	public void setup()
	{
		name = "Shadowstrike";

		addTag(Tag.ATTACK);
		addTag(Tag.SHADOW);
		setAnimation(MovementType.CAST);
		setUpgrade(Upgrade.STRENGTH);
	}

	public void rarity()
	{
		mana = 4;
		addCondition(Weak.class, 1);
		damage = scale(12)-5;
	}

	public void setTarget()
	{
		targets.add(getLowestHealthEnemy());
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
				+ "[DEBUFF]Weak[] " + getStacksText() + " to the lowest health enemy.";
	}
}
