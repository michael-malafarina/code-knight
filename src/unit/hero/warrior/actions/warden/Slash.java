package unit.hero.warrior.actions.warden;

import unit.ability.action.*;
import unit.ability.conditions.debuff.Bleed;
import ui.Images;
import ui.sound.Sounds;

public class Slash extends Action
{
	public void setup()
	{
		name = "Slash";
		addTag(Tag.ATTACK);
		addTag(Tag.PHYSICAL);
		setAnimation(MovementType.RUSH);
		setUpgrade(Upgrade.STRENGTH);
	}

	public void rarity()
	{
		damage = scale(9) - 2;
		addCondition(Bleed.class, 2);
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
		Sounds.slashMetal.play();
	}

	public void animation()
	{
		animate(Images.animSlash, getTarget());
		animate(Images.animSlashing, self());
	}

	public String getDescription() 
	{
		return "Deal " + getDamageText() + " [PHYSICAL]Physical Damage[] apply [BLEED]Bleed[] " + getStacksText() + " to the front enemy.";
	}
}
