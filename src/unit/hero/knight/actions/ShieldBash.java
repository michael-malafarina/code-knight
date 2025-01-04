package unit.hero.knight.actions;

import unit.ability.action.*;
import ui.Images;
import ui.sound.Sounds;

public class ShieldBash extends Action
{
	float multiplier;

	public void setup()
	{
		name = "Shield Bash";

		addTag(Tag.ATTACK);
		addTag(Tag.PHYSICAL);
		setAnimation(MovementType.RUSH);
		setUpgrade(Upgrade.DEFENSE);
	}

	public void rarity()
	{
		switch (getRarity())
		{
			case Rarity.COMMON:
				multiplier = .6f;
				break;
			case Rarity.UNCOMMON:
				multiplier = .7f;
				break;
			case Rarity.RARE:
				multiplier = .8f;
				break;
			case Rarity.EPIC:
				multiplier = .9f;
				break;
			case Rarity.LEGENDARY:
				multiplier = 1.0f;
				break;
		}
	}

	public void setTarget()
	{
		targets.add(getFrontEnemy());
	}
			
	public void use()
	{
		damage = getUnit().getBlock();

		damage(getTarget(), DamageType.PHYSICAL);
//		applyConditions(target);
		
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
		return "Deal [PHYSICAL]Physical Damage[] equal to " + getPercentText(multiplier) +
				" of your current block to the front enemy" + getDamageModifierText() + ".";
	}
}
