package states.combat;

import unit.ability.action.Action;
import animation.AnimationManager;
import battlefield.Battlefield;
import campaign.EnemyManager;
import campaign.HeroManager;
import core.Color;
import core.Main;
import core.Settings;
import org.newdawn.slick.Input;
import ui.Fonts;
import ui.Text;
import ui.message.Message;
import ui.reward.RewardOverlay;
import ui.sound.Sounds;
import unit.Unit;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.util.ArrayList;


public class Combat extends BasicGameState
{
    private int id;
    private StateBasedGame sbg;

    private static CombatState combatState;

    private static Action currentAction;

    private static int actionTimer;
    private static boolean actionMode;
    private static Unit actingUnit;
    private static boolean rewardGranted;

    private static int timer;

    private static InitiativeQueue initiativeQueue;
    private static CombatHUD combatHUD;
    // Constructor and Init

    public static final int TURN_SWITCH_TIMER = 60;
    private static int nextTurnTimer = 0;


    public Combat(int id)
    {
        this.id = id;
    }

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException
    {
        this.sbg = sbg;
        gc.setShowFPS(false);
        initiativeQueue = new InitiativeQueue();
        combatHUD = new CombatHUD();
    }

    // Acessors
    public int getID()
    {
        return id;
    }


    public static CombatState getCombatState()
    {
        return combatState;
    }

    public static boolean inActionMode()
    {
        return actionMode;
    }

    public static boolean isRewardGranted()
    {
        return rewardGranted;
    }

    public static Unit getActingUnit()
    {
        return actingUnit;
    }

    public static ArrayList<Unit> getUnits()
    {
        ArrayList<Unit> units = HeroManager.getUnits();
        units.addAll(EnemyManager.getUnits());
        return units;
    }

    public static Action getCurrentAction()
    {
        if (actingUnit == null)
        {
            return null;
        }

        return currentAction;
    }

    public static int getActionTimer()
    {
        return actionTimer;
    }

    public static int getTimer()
    {
        return timer;
    }

    // Setters


    public static void setCombatState(CombatState state)
    {
combatState = state;
    }

    public static void addMessage(Message m)
    {
        combatHUD.addMessage(m);
    }


    // Core Methods


    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException
    {
        timer++;

        AnimationManager.update();

        // Update player units
        for (Unit u : HeroManager.getUnits())
        {
            u.update();
        }

        // Update enemy units
        for (Unit u : EnemyManager.getUnits())
        {
            u.update();
        }

        if (combatState == CombatState.BATTLE)
        {
            updateBattle();
        }
        else if(combatState == CombatState.SETUP)
        {
            updateSetup();
        }

        if (combatState == CombatState.END)
        {
            updateEnd();
        }

        combatHUD.update();


    }



    public void updateSetup()
    {
        // Lock starting cells for future fights based on arrangement
        for (Unit u : HeroManager.getUnits())
        {
            u.setStartingCell(u.getCell());
        }
    }

    public void updateBattle()
    {
        for (int i = 0; i < Settings.gameSpeed; i++)
        {
            if (!isBattleOver())
            {
//                if(actingUnit != null)
//                {
//                    System.out.println(currentAction);
//                }


                updateBattleTimers();
                advanceTurn();
                useFunction();
                endTurn();
            }
            endBattle();
        }

    }

    public void updateEnd()
    {
        if(wonBattle() && combatHUD.endState())
        {
            resetBattle();
            sbg.enterState(Main.MAP_ID);
        }

    }

    private void endBattle()
    {
        if (wonBattle() && combatState == CombatState.BATTLE && !rewardGranted)
        {
            RewardOverlay.begin();
            rewardGranted = true;
            combatState = CombatState.END;

            if(Settings.sfxOn)
            {
                Sounds.combatWin.play();
            }

            for(Unit u : HeroManager.getUnits())
            {
                u.endBattle();
            }
        }



//        System.out.println(wonBattle() + " " + combatHUD.endState());



        if (lostBattle())
        {
            HeroManager.newParty();
            resetBattle();
            sbg.enterState(Main.LOSE_ID);
        }
    }

    public static void assignCurrentAction(Action a)
    {
        currentAction = a;
        a.resetTarget();
        actingUnit = a.getUnit();
        actionTimer = a.getTime();
        actionMode = true;
    }


    public static void startTurn(Unit u)
    {
        actionMode = true;
        actingUnit = u;
        actingUnit.startTurn();

        Action nextAction = actingUnit.getAlgorithm().getNextAction();

        if (nextAction.canUse())
        {
            assignCurrentAction(nextAction);
        }
        else if(actingUnit.getTurn() == 1)      // special case for if you can't use anything this turn
        {
            forceEndTurn();
        }
    }


    public void useFunction()
    {
        if (!actionMode)
        {
            return;
        }

        if (actionTimer == getCurrentAction().getTime() / 2)
        {
//            System.out.println(actingUnit + " uses " + currentAction);

            actingUnit.act(currentAction);
        }
    }

    public static void endTurn()
    {
        // Only end turn if time is up
        if (!actionMode)
        {
            return;
        }

        if (!actingUnit.isAlive())
        {
            forceEndTurn();
            return;
        }

        if (actionTimer > 0)
        {
            return;
        }

        // If we got this far, turn is over.  End all movements and resolve positions

        for(Unit u : getUnits())
        {
            u.clearMovement();
            u.setCellLaterResolution();
        }

        // Add the next action if we can do so
        if (actingUnit.getAlgorithm().getFutureAction() != null && actingUnit.getAlgorithm().getFutureAction().canUse())
        {
            actingUnit.getAlgorithm().advanceAlgorithm();
            assignCurrentAction(actingUnit.getAlgorithm().getNextAction());
        }
        else
        {
            forceEndTurn();
        }
    }

    public static void forceEndTurn()
    {

//        System.out.println(actingUnit + " is ending turn");


        actingUnit.endTurn();
        actionMode = false;
        actingUnit = null;
        actionTimer = 0;
        nextTurnTimer = TURN_SWITCH_TIMER;

//        System.out.println(actingUnit + " has ended");


    }

    public void updateBattleTimers()
    {
        // If between turns, tick turn timer
        if (actingUnit == null && nextTurnTimer > 0)
        {
            nextTurnTimer--;
        }

        // If we are in the middle of a turn, tick action timer
        else if (actingUnit != null && actionTimer > 0)
        {
            actionTimer--;
            actingUnit.updateActions();
        }

//        if(actionTimer > 0)
//        {
//            for(Unit u : getUnits())
//            {
//                u.movementEffect();
//            }
//
//        }



    }

    public void advanceTurn()
    {
        // If no active unit and time is up, go to next unit

        if (actingUnit == null && nextTurnTimer == 0)
        {
            InitiativeQueue.tick();
        }
    }

    public void resetBattle()
    {
        HeroManager.recover();
        EnemyManager.clear();
        AnimationManager.clear();
        combatHUD.clear();
        combatState = CombatState.SETUP;
        actionTimer = 0;
        actionMode = false;
        actingUnit = null;
        nextTurnTimer = 0;
        rewardGranted = false;

    }

    public static boolean isBattleOver()
    {

        return wonBattle() || lostBattle();
    }

    public static boolean wonBattle()
    {
        for (Unit u : EnemyManager.getUnits())
        {
            if (u.isAlive())
            {
                return false;
            }
        }
        return true;
    }


    public static boolean lostBattle()
    {
        for (Unit u : HeroManager.getUnits())
        {
            if (u.isAlive())
            {
                return false;
            }
        }
        return true;
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException
    {

        if (combatState == CombatState.SETUP)
        {
            Text.alignMiddle();
            Text.alignCenter();
            Text.setFont(Fonts.bigFont);
            Text.setColor(new Color(255, 255, 255));
            Text.draw("Setup Battle", Main.getScreenWidth() * .5f, Main.getScreenHeight() * .85f);
            Text.setFont(Fonts.mediumFont);
            Text.draw("Press space to start", Main.getScreenWidth() * .5f, Main.getScreenHeight() * .9f);
        }

        Battlefield.render(g);




        // Draw player units
        for (Unit u : HeroManager.getUnits())
        {
            u.render(g);
        }

        // Draw enemy units
        for (Unit u : EnemyManager.getUnits())
        {
            u.render(g);
        }

        combatHUD.render(g);



        AnimationManager.render(g);

    }

    public void enter(GameContainer gc, StateBasedGame sbg) throws SlickException
    {
        // Add enemies
        EnemyManager.buildEncounter();

        // Start up initiative
        InitiativeQueue.begin(getUnits());
        combatHUD.begin();
        combatState = CombatState.SETUP;

        for(Unit u : HeroManager.getUnits())
        {
            u.enterCombatState();
        }

    }

    public void startBattleMode()
    {
        Combat.setCombatState(CombatState.BATTLE);
        InitiativeQueue.begin(getUnits());

        for(Unit u : HeroManager.getUnits())
        {
            u.startBattleMode();
        }
    }



    public void leave(GameContainer gc, StateBasedGame sbg)
    {
        for(Unit u : HeroManager.getUnits())
        {
            u.endBattle();
        }

    }

    public void keyPressed(int key, char c)
    {
        combatHUD.keyPressed(key, c);

        if (Combat.getCombatState() == CombatState.SETUP && key == Input.KEY_SPACE)
        {
            startBattleMode();
         }


    }

    public void mousePressed(int button, int x, int y)
    {
        combatHUD.mousePressed(button, x, y);

        if(combatHUD.startBattle() && Combat.getCombatState() == CombatState.SETUP)
        {
            startBattleMode();
        }
    }



    public void mouseMoved(int oldX, int oldY, int newX, int newY)
    {

    }

    public void mouseDragged(int oldX, int oldY, int newX, int newY)
    {

    }


}
