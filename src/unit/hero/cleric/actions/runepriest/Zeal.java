package unit.hero.cleric.actions.runepriest;

import ui.Images;
import ui.sound.Sounds;
import unit.ability.action.Action;
import unit.ability.action.DamageType;
import unit.ability.action.MovementType;
import unit.ability.action.Tag;
import unit.ability.conditions.buff.Strength;
import unit.ability.conditions.debuff.Vulnerable;

public class Zeal extends Action
{
	public void setup()
	{
		name = "Zeal";

		addTag(Tag.ATTACK);
		addTag(Tag.DEBUFF);
		addTag(Tag.PHYSICAL);
		setAnimation(MovementType.RUSH);
	}

	public void rarity()
	{
		damage = scale(9)-5;
		addCondition(Strength.class, 1);
	}

	public void setTarget()
	{
		targets.add(getFrontEnemy());
	}

	public void use()
	{
		damage(DamageType.PHYSICAL);
		applyConditions(self());
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
		return "Deal " + getDamageText() + " [PHYSICAL]Physical Damage[] to the front enemy and you gain [BUFF]Strength[] " + getStacksText() + ".";
	}
}
