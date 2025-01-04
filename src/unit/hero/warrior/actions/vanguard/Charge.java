package unit.hero.warrior.actions.vanguard;

import unit.ability.action.*;
import ui.Images;
import ui.sound.Sounds;

public class Charge extends Action
{
	public void setup()
	{
		name = "Charge";

		addTag(Tag.ATTACK);
		addTag(Tag.PHYSICAL);
		setUpgrade(Upgrade.STRENGTH);

	}

	public void rarity()
	{
		damage = scale(8);
	}

	public void setTarget()
	{
		targets.add(getFrontEnemy());
	}

	public void setMovement()
	{
		if(hasForwardCell())
		{
			movement = MovementType.NONE;
		}
		else
		{
			movement = MovementType.RUSH;
		}
	}


	public void use()
	{
		if(hasForwardCell())
		{
			damage(getTarget(), DamageType.PHYSICAL, 1.5f);
		}
		else
		{
			damage(getTarget(), DamageType.PHYSICAL);
		}

		advance();
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
		return "Deal " + getDamageText() + " [PHYSICAL]Physical Damage[] and [KEY]Advance[] one space.  If you move, deals " + getDamageText(.5f) + " extra damage.";
	}
}
