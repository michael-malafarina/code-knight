package states;



import core.Color;
import core.Main;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import ui.Fonts;
import ui.Text;

public class Win extends BasicGameState 
{	
	private int id;
	private StateBasedGame sbg;
	
//	LearnAbilityPanel abilityPanel;
	int upgradeIndex;
	
	public Win(int id) 
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
		Text.draw("WINNER", Main.getScreenWidth() / 2, Main.getScreenHeight() / 2 - 100);
		Text.setFont(Fonts.bigFont);
		Text.draw("Press space to go to another fight", Main.getScreenWidth() / 2, Main.getScreenHeight() / 2 + 50);

	}
	
	public void enter(GameContainer gc, StateBasedGame sbg) throws SlickException 
	{		

	}

	public void leave(GameContainer gc, StateBasedGame sbg) 
	{
	}

	public void keyPressed(int key, char c)
	{
		sbg.enterState(Main.COMBAT_ID);
	}
	public void mousePressed(int button, int x, int y)
	{

	}
	
	


}
