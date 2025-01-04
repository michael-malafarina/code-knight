package unit.hero.knight.actions;

import unit.ability.action.*;
import ui.Images;
import ui.sound.Sounds;

public class BatteringRam extends Action
{
	public void setup()
	{
		name = "Battering Ram";
		addTag(Tag.ATTACK);
		addTag(Tag.PHYSICAL);
		setAnimation(MovementType.RUSH);
		setUpgrade(Upgrade.STRENGTH);

	}

	public void rarity()
	{
		damage = scale(8);
	}

	public void setTarget()
	{
		targets.add(getHighestHealthEnemy());
	}

	public void use()
	{
		damage(getTarget(), DamageType.PHYSICAL);
		push(getTarget());
	}

	public void sound()
	{
		Sounds.bashHeavy.play();
	}

	public void animation()
	{
		animate(Images.animBlunt, getTarget());
	}

	public String getDescription() 
	{
		return "Deal " + getDamageText() + " [PHYSICAL]Physical Damage[] to the enemy with the most health and push it back.";
	}
}
