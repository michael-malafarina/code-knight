package unit.enemy.goblin.actions.general;

import unit.ability.action.*;
import ui.Images;
import ui.sound.Sounds;

public class GoblinBlock extends EnemyAction
{
	public void setup()
	{
		name = "Block";
		block = 6;
		setRarity(Rarity.UNCOMMON);
		addTag(Tag.BLOCK);
		setAnimation(MovementType.STEP);
	}

	public void setTarget()
	{
		targets.add(self());
	}

	public void use()
	{
		block();
	}

	public void sound()
	{
		Sounds.armorUp.play();
	}

	public void animation()
	{
		animate(Images.animShield);
	}

	public String getDescription() 
	{
		return "Gain " + getBlock() + " [BLOCK]Block[].";
	}
}
