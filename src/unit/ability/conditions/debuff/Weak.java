package unit.ability.conditions.debuff;

import unit.ability.action.Action;
import unit.ability.action.Tag;
import unit.ability.conditions.Debuff;
import ui.Images;

public class Weak extends Debuff
{
	public Weak()
	{
		name = "Weak";
		icon = Images.conditionWeak;
		setColor(70, 120, 200);

		tags.add(Tag.DEBUFF);
		tags.add(Tag.ALL);
	}

	public String getDescription()
	{
		return "Reduces damage on all attacks by " + getStacks() + "until the end of combat.";
	}

	public int getDamageBonus()
	{
		return getStacks() * -1;
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
