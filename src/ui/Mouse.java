package ui;

import core.Main;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class Mouse
{
//	static Cursor cursor;
	

	public static int getX()				{	return Main.getInput().getMouseX();	}
	public static int getY()				{	return Main.getInput().getMouseY();	}


//	public static void renderCursor(Graphics g)
//	{
//		g.setColor(Color.blue);
//		g.fillOval(getXPixel()-5,  getYPixel()-5,  10,  10);
//	}
	
//	public static void setMouseCursor(Cursor c)
//	{
//		if(cursor == c)
//		{
//			return;
//		}
//
//		cursor = c;
//
//		try
//		{
//			Main.getGameContainer().setMouseCursor(cursor.getImage(), cursor.getHotspotX(), cursor.getHotspotY());
//		}
//		catch (SlickException e)
//		{
//			e.printStackTrace();
//		}
//	}
}
