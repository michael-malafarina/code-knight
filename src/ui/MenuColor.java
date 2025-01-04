package ui;

import core.Color;

public enum MenuColor 
{
	AVERAGE, HIGH, LOW,
	HEALTH, BASE,  DISABLE, HIGHLIGHT, TITLE,

	MENU_BACKGROUND;

	
	public Color getColor()
	{
		switch(this)
		{
		case AVERAGE:
			return new Color(255, 255, 255);
		case HIGH:
			return new Color(100, 255, 100);
		case LOW:
			return new Color(255, 100, 100);
		
		case HEALTH:
			return new Color(255, 70, 70);

		case BASE:
			return new Color(255, 255, 255);
		case DISABLE:
			return new Color(180, 180, 180);
		case HIGHLIGHT:
			return new Color(255, 255, 100);

						
		case TITLE:
			return new Color(255, 255, 255);


		case MENU_BACKGROUND:
			return new Color(35, 35, 40, 240);

						
		default:
			return null;
		}
		
	}
	
}
