package unit.ability.conditions.debuff;

import unit.ability.Ability;
import unit.ability.action.DamageType;
import unit.ability.action.Tag;
import unit.ability.conditions.Debuff;
import ui.Images;

public class Vulnerable extends Debuff
{
	public Vulnerable()
	{
		name = "Vulnerable";
		damageReceivedScalar = 1.50f;
		setColor(180, 180, 0);
		icon = Images.conditionVulnerable;
		tags.add(Tag.DEBUFF);
		tags.add(Tag.ALL);		
	}

	public void onDamageTaken(DamageType type, Ability source)
	{
		removeStack();
	}

	public String getDescription() 
	{
		return "Increases damage received from the next attack by 50%.";
	}

	public void sound() 
	{
	//	Sounds.grunt.play();
	}

	public void animation() 
	{
		//AnimationManager.add(new AnimationCell(Images.animSlash, getOwner()));
	}



}
