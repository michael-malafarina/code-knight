package core;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.util.Log;
import states.*;
import states.combat.Combat;
import states.map.Map;

public class Main extends StateBasedGame
{
    public final static int FRAMES_PER_SECOND = 60;
    private static AppGameContainer appgc;
    private static boolean paused = false;

    public static final int TITLE_ID = 0;
    public static final int COMBAT_ID = 1;
    public static final int MAP_ID = 2;
    public static final int WIN_ID = 3;
    public static final int LOSE_ID = 4;
    public static final int PARTY_ID = 5;
    public static final int RECRUIT_ID = 6;

    private BasicGameState combat, title, map, win, loss, party, startParty;



    public Main(String name)
    {
        super(name);

        combat = new Combat(COMBAT_ID);
        map = new Map(MAP_ID);
        loss = new Loss(LOSE_ID);
        party = new PartyMenu(PARTY_ID);
        startParty = new Recruit(RECRUIT_ID);
        title = new Title(TITLE_ID);
        win = new Win(WIN_ID);

    }

    public static int getScreenWidth()
    {
        return appgc.getScreenWidth();
    }

    public static int getScreenHeight()
    {
        return appgc.getScreenHeight();
    }

    public static Input getInput()
    {
        return appgc.getInput();
    }

    public static GameContainer getGameContainer()
    {
        return appgc;
    }

    public static int getGameScale()
    {
        if (getScreenHeight() >= 1440)
        {
            return 5;
        } else if (getScreenHeight() >= 1080)
        {
            return 4;
        } else
        {
            return 2;
        }
    }

    public void initStatesList(GameContainer gc) throws SlickException
    {
        addState(title);
        addState(combat);
        addState(map);
        addState(win);
        addState(loss);
        addState(party);
        addState(startParty);
    }

    public static void main(String[] args)
    {
        try
        {
            appgc = new AppGameContainer(new Main("Project Steelheart"));
            System.setProperty("org.lwjgl.opengl.Window.undecorated", "true");

            appgc.setDisplayMode(appgc.getScreenWidth(), appgc.getScreenHeight(), false);
            appgc.setTargetFrameRate(FRAMES_PER_SECOND);
            appgc.start();
            appgc.setVSync(true);
            appgc.setShowFPS(false);
            Log.setVerbose(false);

        }
        catch (SlickException e)
        {
            e.printStackTrace();
        }

    }

    public static void setPaused(boolean pause)
    {
        paused = pause;
    }

    public static boolean isPaused()
    {
        return paused;
    }
}