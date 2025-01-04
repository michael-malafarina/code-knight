package ui.algorithm;

import unit.ability.action.Action;
import core.Color;
import core.Main;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.TrueTypeFont;
import ui.Fonts;
import ui.MenuColor;
import ui.Text;
import ui.panel.Panel;
import ui.panel.Tooltip;

public class ActionTooltip extends Tooltip
{
	private Action action;
	protected TrueTypeFont delayFont;
	protected String delayText;

    public ActionTooltip(Panel owner, String name, Action action)
    {
        super(owner, name, action.getDescription());

		this.action = action;


		yPadding = 6 * Main.getGameScale();

		delayFont = Fonts.smallFontMono;
		delayText = action.getSpeed().getDescription() + " (" + action.getSpeed().getValue() + " speed)";

        setSizeToText();
    }

	public float getNamePadding()
	{
		return yPadding * .36f;
	}

	public float getDelayPadding()
	{
		return yPadding * .30f;
	}

	public float getDescriptionPadding()
	{
			return yPadding * .24f;
	}


    public void setSizeToText()
    {
        width = Math.max(Math.max(getNameWidth(), getDescriptionWidth()), minimumPanelWidth) + xPadding;
        height = getNameHeight() + getDescriptionHeight() + getDelayHeight() + yPadding;
    }

	public int getDelayHeight()
	{
		if(delayFont == null)
		{
			return 0;
		}
		return Text.getHeight(delayFont, delayText, maximumTextWidth);
	}

    protected void renderText(Graphics g)
    {
        Text.alignLeft();
        Text.alignTop();
        Text.setColor(action.getRarity().getColor());
        Text.setFont(nameFont);
        Text.draw(name, x + xPadding * .5f, y + getNamePadding(), maximumTextWidth);

		Text.alignRight();
		Text.alignMiddle();
		Text.setColor(new Color(60, 140, 255));
		Text.setFont(Fonts.mediumFont);
		if(action.getManaCost() > 0)
		{
			Text.draw(action.getManaCost() + " Mana", x + width - xPadding * .5f, y + getNamePadding() + getNameHeight() / 2);
		}

		Text.alignLeft();
		Text.alignTop();
		Text.setColor(new Color(170, 170, 170));
		Text.setFont(delayFont);
		Text.draw(delayText, x + xPadding * .5f, y + getNameHeight() + getNamePadding() + getDelayPadding());

		Text.alignLeft();
		Text.alignTop();
        Text.setColor(MenuColor.BASE);
        Text.setFont(descFont);
        Text.alignTop();
        Text.draw(description, x + xPadding * .5f, y + getDelayHeight() + getNameHeight() + getNamePadding() + getDelayPadding() + getDescriptionPadding(), maximumTextWidth);



    }
//
//
//	public void renderEnergyCost(Graphics g)
//	{
//		if(action.getEnergyCost() == 0)
//		{
//			return;
//		}
//
//		float indentBoxX = width * .80f;
//		float indentBoxY = height * .15f;
//		float indentTextX = width * .88f;
//		float indentTextY = height * .45f;
//
//		float h = getNameHeight() * .8f;
//		float w = getNameHeight() * .8f;
//
//
//		if(action.getUnit().getTeam() == Team.PLAYER)
//		{
//			nameColor = new Color(255, 255, 255);
//			bgColor = new Color(20, 40, 80, 255);
//
//			if (action.isDisabled())
//			{
//				g.setColor(new Color(15, 35, 90));
//			}
//
//		}
//		else
//		{
//			nameColor = new Color(255, 255, 255);
//			bgColor = new Color(60, 10, 10, 255);
//
//			if (action.isDisabled())
//			{
//				g.setColor(new Color(15, 35, 90));
//			}
//		}
//
//
//		g.setColor(new Color(30, 70, 180));
//
//		g.fillRect(x + indentBoxX, y + indentBoxY, w, h);
//
//		Text.alignMiddle();
//		Text.alignCenter();
//		Text.setColor(nameColor);
//		Text.setFont(Fonts.mediumFontMono);
//		Text.draw(action.getEnergyCost()+"", x + indentTextX, y + indentTextY);
//	}


}
