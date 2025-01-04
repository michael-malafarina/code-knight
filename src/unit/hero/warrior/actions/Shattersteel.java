package unit.hero.warrior.actions;

import unit.ability.action.*;
import ui.Images;
import ui.sound.Sounds;

public class Shattersteel extends Action
{
	public void setup()
	{
		name = "Shattersteel";
		addTag(Tag.ATTACK);
		addTag(Tag.PHYSICAL);
		setAnimation(MovementType.RUSH);
		setUpgrade(Upgrade.STRENGTH);
	}

	public void rarity()
	{
		damage = scale(10);
		mana = 2;
	}

	public void setTarget()
	{
		targets.add(getFrontEnemy());
	}

	public void use()
	{

		if(getTarget().getBlock() >= 1)
		{
			damage(getTarget(), DamageType.PHYSICAL, 1.5f);
		}
		else
		{
			damage(getTarget(), DamageType.PHYSICAL);
		}

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
		return "Deal " + getDamageText() + " [PHYSICAL]Physical Damage[].  If the target has block, deals " + getDamageText(.5f) + " extra damage.";
	}
}
