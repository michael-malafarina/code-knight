package unit.ability.conditions;

import core.Main;
import unit.ability.Modifier;
import org.newdawn.slick.Graphics;
import ui.Images;
import ui.algorithm.ConditionPanel;
import unit.Unit;

public abstract class Condition extends Modifier
{
	ConditionPanel p;

	protected Unit source;
	public static final int ICON_SIZE = 6;

	public Condition(int stacks)
	{
		super();
		icon = Images.conditionBleed;
		this.stacks = stacks;
	}

	public Condition()
	{
		this(1);
	}
	
	private boolean sameTurnCastingOver;

	public boolean isExpired()	{	return stacks <= 0;	}
	public Unit getSource()	{	return source;			}

//	public int getHealingBonus()				{	return stacks * healingBonus;				}
//	public int getHealingReceivedBonus()		{	return stacks * healingReceivedBonus;		}
//	public int getDamageBonus()					{	return stacks * damageBonus;				}
//	public int getDamageReceivedBonus()			{	return stacks * damageReceivedBonus;		}
//	public int getGuardBonus()					{	return stacks * blockBonus;					}
//	public int getSpeedBonus()					{	return stacks * speedBonus;					}

	public void setSource(Unit source)
	{
		this.source = source;
	}


	
	public void render(Graphics g, int index)
	{
		if(stacks <= 0 || getOwner() == null)
		{
			return;
		}

		float x = getOwner().getX() + index * (ICON_SIZE+1) * Main.getGameScale();
		float y = getOwner().getY() + getOwner().getHeight() + 7 * Main.getGameScale();

		p = new ConditionPanel(x, y, this);

		p.render(g);

	}

	public void renderSecond(Graphics g)
	{
		p.renderTooltip(g);
	}


	


	
}
