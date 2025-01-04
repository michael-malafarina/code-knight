package ui.panel;

import core.Color;
import core.Main;
import org.newdawn.slick.Graphics;
import ui.Fonts;
import ui.MenuColor;
import ui.Text;

public class Tooltip extends Panel
{
	int offset = 2 * Main.getGameScale();
	Panel owner;

	public Tooltip(Panel owner, String name, String description)
	{
		super(owner.getX() + owner.getWidth(), owner.getY(), 0, 0);
		this.owner = owner;
		this.name = name;
		this.description = description;
		x += offset;

		bgColor = new Color(30, 30, 30, 255);



		nameFont = Fonts.mediumFont;
		descFont = Fonts.smallFontMono;

		setSizeToText();

	}

	public void render(Graphics g, float previousHeight)
	{
		setSizeToText();

		x = owner.getX() + owner.getWidth() + offset;
		y = owner.getY() + previousHeight;

		if(x + width > Main.getScreenWidth())
		{
			x = owner.getX() - width - offset;
		}

		super.render(g);
	}



}
