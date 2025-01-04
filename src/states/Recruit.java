package states;



import campaign.HeroManager;
import core.Main;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import states.map.Map;
import ui.reward.RewardOverlay;

public class Recruit extends BasicGameState
{	
	private int id;
	private StateBasedGame sbg;


	public Recruit(int id)
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

		HeroManager.newParty();

	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException
	{	
		if(!RewardOverlay.isActive())
		{
			sbg.enterState(Main.MAP_ID);
		}

	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException 
	{
		Map.getBackground().draw(0, 0, Main.getScreenWidth(), Main.getScreenHeight());


		RewardOverlay.render(g);

		g.drawString("Recruit", 50, 50);
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
		RewardOverlay.mousePressed(button, x, y);
	}
	
	


}
