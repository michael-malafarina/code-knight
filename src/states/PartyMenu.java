package states;


import core.Main;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class PartyMenu extends BasicGameState 
{	
	private int id;
	private StateBasedGame sbg;

	public PartyMenu(int id) 
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

	}

	public void enter(GameContainer gc, StateBasedGame sbg) throws SlickException 
	{		

	}

	public void leave(GameContainer gc, StateBasedGame sbg) 
	{

	}

	public void keyPressed(int key, char c)
	{

	}
	public void mousePressed(int button, int x, int y)
	{
		// This code happens every time the user presses the mouse
	}




}
