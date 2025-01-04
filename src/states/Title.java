package states;


import animation.AnimationManager;
import battlefield.Battlefield;
import campaign.EnemyManager;
import campaign.HeroManager;
import core.Color;
import core.Main;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import ui.Images;
import ui.Text;
import ui.reward.RewardOverlay;
import ui.sound.AudioManager;
import ui.Fonts;
import ui.title.StartGameButton;

public class Title extends BasicGameState 
{	
	private int id;
	private StateBasedGame sbg;

	public Title(int id) 
	{
		this.id = id;
	}
	
	public int getID() 
	{
		return id;		
	}

	private  StartGameButton start;

	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException 
	{
		this.sbg = sbg;

		Images.loadImages();
		Fonts.loadFonts();
		Battlefield.init();

		AudioManager.loadSFX();
		AudioManager.loadMusic();
		AnimationManager.init();
		Text.init(gc.getGraphics());

		start = new StartGameButton();
//		Factions.init();
//		UI.init();
//
//		DisciplineManager.init();
//
//
//		sbg.enterState(Main.START_PARTY_ID);


		HeroManager.init();
		EnemyManager.init();

		RewardOverlay.init();

	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException
	{	
		
//		AudioManager.update();
//

		if(start.isDone())
		{
			sbg.enterState(Main.MAP_ID);
		}
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException 
	{
		Images.backgroundTitle.draw(0, 0, Main.getScreenWidth(), Main.getScreenHeight());

		start.render(g);
//		Text.setColor(new Color(0, 0, 0));
//		Text.alignCenter();
//		Text.alignMiddle();
//		Text.setFont(Fonts.gargantuanFont);
//		Text.draw("CODE KNIGHT", Main.getScreenWidth() / 3, Main.getScreenHeight() / 5 - 100);
//		Text.setFont(Fonts.hugeFont);
//		Text.setColor(new Color(0, 0, 0));
//		Text.draw("Press space to begin", Main.getScreenWidth() / 3, Main.getScreenHeight() / 5 + 50);

	//	Images.title.draw(0, 0, Main.getScreenWidth(), Main.getScreenHeight());
//		g.drawString("Project Steelheart", 50, 50);
	}
	
	public void enter(GameContainer gc, StateBasedGame sbg) throws SlickException 
	{
		// This code happens when you enter a gameState.  
	}

	public void leave(GameContainer gc, StateBasedGame sbg) 
	{
		// This code happens when you leave a gameState. 
	}

	public void keyPressed(int key, char c)
	{
		sbg.enterState(Main.MAP_ID);
	}
	
	public void mousePressed(int button, int x, int y)
	{
	start.mousePressed(button, x, y);
//		sbg.enterState(Main.START_PARTY_ID);
	}
	
	


}
