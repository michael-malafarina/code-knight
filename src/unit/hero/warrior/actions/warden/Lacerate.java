package unit.hero.warrior.actions.warden;

import unit.ability.action.*;
import unit.ability.conditions.debuff.Bleed;
import ui.Images;
import ui.sound.Sounds;

public class Lacerate extends Action
{
	public void setup()
	{
		name = "Lacerate";
		addTag(Tag.ATTACK);
		addTag(Tag.PHYSICAL);
		setAnimation(MovementType.RUSH);
		setUpgrade(Upgrade.STRENGTH);
	}

	public void rarity()
	{
		switch (getRarity())
		{
			case Rarity.COMMON:
				damage = 3;
				addCondition(Bleed.class, 3);
				break;
			case Rarity.UNCOMMON:
				damage = 4;
				addCondition(Bleed.class, 4);
				break;
			case Rarity.RARE:
				damage = 5;
				addCondition(Bleed.class, 5);
				break;
			case Rarity.EPIC:
				damage = 6;
				addCondition(Bleed.class, 6);
				break;
			case Rarity.LEGENDARY:
				damage = 7;
				addCondition(Bleed.class, 7);
				break;
		}
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
