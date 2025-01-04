package states;


import core.Color;
import core.Main;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import states.map.Map;
import ui.Fonts;
import ui.Text;

public class Loss extends BasicGameState
{	
	private int id;
	private StateBasedGame sbg;
	
	public Loss(int id)
	{
		this.id = id;
	}
	
	public int getID() 
	{
		return id;		
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException 
	{
		this.sbg = sbg;
	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException
	{	

	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException 
	{
		Text.setColor(Color.white);
		Text.alignCenter();
		Text.alignMiddle();
		Text.setFont(Fonts.hugeFont);
		Text.draw("LOSER", Main.getScreenWidth() / 2, Main.getScreenHeight() / 2 - 100);
		Text.setFont(Fonts.bigFont);
		Text.draw("Press space to go to title", Main.getScreenWidth() / 2, Main.getScreenHeight() / 2 + 50);
	}
	
	public void enter(GameContainer gc, StateBasedGame sbg) throws SlickException 
	{
		Map.reset();
	}

	public void leave(GameContainer gc, StateBasedGame sbg) 
	{
		// This code happens when you leave a gameState. 
	}

	public void keyPressed(int key, char c)
	{
		sbg.enterState(Main.TITLE_ID);
	}
	public void mousePressed(int button, int x, int y)
	{
		// This code happens every time the user presses the mouse
	}
	
	


}
