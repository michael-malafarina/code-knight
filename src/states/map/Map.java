package states.map;

import core.Color;
import core.Main;
import core.Settings;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.*;
import ui.Images;
import ui.sound.Sounds;


public class Map extends BasicGameState
{
    private int id;
    private StateBasedGame sbg;

    private static int idLoad = -1;
    private static int stage = 0;
    private static NodeSet nodes;

    int timer;

    private static Image background;

    public Map(int id)
    {
        this.id = id;

    }

    public int getID()
    {
        return id;
    }

    public static int getStage()
    {
        return stage;
    }

    public static void setStage(int value)
    {
        stage = value;
    }

    public static Node getCurrentNode()
    {
        return nodes.getCurrentNode();
    }

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException
    {
        this.sbg = sbg;
        gc.setShowFPS(false);
        background = Images.backgroundDungeonMap;
        reset();
    }

    public static void reset()
    {
        stage = 0;
        nodes = new NodeSet();
        nodes.begin();
    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException
    {

        loadState();

    }


    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException
    {
        g.setBackground(new Color(40, 40, 40));
        background.draw(0, 0, Main.getScreenWidth(), Main.getScreenHeight());

        g.setColor(new Color(20, 20, 20, Math.min(timer, 200)));
        g.fillRect(Main.getScreenWidth() * .1f, Main.getScreenHeight() * .1f, Main.getScreenWidth() * .8f, Main.getScreenHeight() * .8f * Math.min(1, timer/200.0f));


        timer += 15;

        if (timer > 300)
        {
            nodes.render(g);
        }

    }


    public void enter(GameContainer gc, StateBasedGame sbg) throws SlickException
    {
        timer = 0;
    }


    public void leave(GameContainer gc, StateBasedGame sbg)
    {

    }

    public void keyPressed(int key, char c)
    {

    }

    public void mousePressed(int button, int x, int y)
    {
        nodes.mousePressed(button, x, y);
    }

    public void mouseMoved(int oldX, int oldY, int newX, int newY)
    {

    }

    public void mouseDragged(int oldX, int oldY, int newX, int newY)
    {

    }

    public static void setLoadState(int ID)
    {
        idLoad = ID;
    }

    private void loadState()
    {
        if (idLoad != -1)
        {
            int loading = idLoad;
            idLoad = -1;

            if (loading == Main.COMBAT_ID)
            {
                sbg.enterState(loading, new FadeOutTransition(Color.black, 350), null);
                if (Settings.sfxOn)
                {
                    Sounds.combatStart.play();
                }
            }
            else
            {
                sbg.enterState(loading, new FadeOutTransition(Color.black, 240), new FadeInTransition(Color.black, 240));
            }

        }
    }

    public static Image getBackground()
    {
        return  background;
    }


}
