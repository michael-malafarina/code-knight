package states.combat;

import unit.Team;
import unit.ability.Algorithm;
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


    private static boolean actionMode;
    private static boolean rewardGranted;

    private static int timer;

    private static CombatHUD combatHUD;
    private static Team currentTeam = Team.PLAYER;

    // Constructor and Init
    public Combat(int id)
    {
        this.id = id;
    }

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException
    {
        this.sbg = sbg;
        gc.setShowFPS(false);
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


    public static ArrayList<Unit> getUnits()
    {
        ArrayList<Unit> units = HeroManager.getUnits();
        units.addAll(EnemyManager.getUnits());
        return units;
    }

    public static ArrayList<Unit> getUnits(Team team)
    {
        ArrayList<Unit> units = new ArrayList<>();

        for(Unit u : getUnits())
        {
            if(u.getTeam() == team)
            {
                units.add(u);
            }
        }

        return units;
    }


public static Team getCurrentTeam()
{
    return currentTeam;
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
                combatRound(currentTeam);
            }
            endBattle();
        }
    }


    public void combatRound(Team team)
    {
        Algorithm algorithm = team.getAlgorithm();
        Action action = team.getAlgorithm().getCurrentAction();

        // Start turn for all units if we're on a fresh algorithm
        if(algorithm.atStart() && !action.isActive())
        {
            for(Unit u : getUnits(team))
            {
                u.startTurn();
            }
        }

        // Start actions that are not active
        if(!action.isActive())
        {
            action.start();
        }

        // Update the current action
        action.update();

        // Update the algorithm to disable things based on stuns, kills, etc.
        algorithm.update();


        // If it finished, then advance the algorithm and reset positions
        if(!action.isActive())
        {

            algorithm.advanceAlgorithm();

            for(Unit u : getUnits())
            {
                u.clearMovement();
                u.setCellLaterResolution();
            }
        }

        // If the algorithm is done, end turn for all
        if(algorithm.isDone())
        {
            for(Unit u : getUnits(team))
            {
                u.endTurn();
            }

            algorithm.reset();
            currentTeam = team.getOpponent();
        }
    }

    public void executeAction()
    {

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
            HeroManager.getAlgorithm().reset();

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
            resetBattle();
            HeroManager.newParty();
            sbg.enterState(Main.LOSE_ID);
        }
    }

//    public static void assignCurrentAction(Action a)
//    {
//        currentAction = a;
//        a.resetTarget();
//        actingUnit = a.getUnit();
//        currentActionTimer = a.getTime();
//        actionMode = true;
//    }


//    public static void startTurn(Unit u)
//    {
//        actionMode = true;
//        actingUnit = u;
//        actingUnit.startTurn();
//
//        Action nextAction = actingUnit.getAlgorithm().getNextAction();
//
//        if (nextAction.canUse())
//        {
//            assignCurrentAction(nextAction);
//        }
//        else if(actingUnit.getTurn() == 1)      // special case for if you can't use anything this turn
//        {
//            forceEndTurn();
//        }
//    }


//    public void useFunction()
//    {
//        if (!actionMode)
//        {
//            return;
//        }
//
//        if (currentActionTimer == getCurrentAction().getTime() / 2)
//        {
////            System.out.println(actingUnit + " uses " + currentAction);
//
//            actingUnit.act(currentAction);
//        }
//    }

//    public static void endTurn()
//    {
//        // Only end turn if time is up
//        if (!actionMode)
//        {
//            return;
//        }
//
//        if (!actingUnit.isAlive())
//        {
//            forceEndTurn();
//            return;
//        }
//
//        if (currentActionTimer > 0)
//        {
//            return;
//        }
//
//        // If we got this far, turn is over.  End all movements and resolve positions
//
//        for(Unit u : getUnits())
//        {
//            u.clearMovement();
//            u.setCellLaterResolution();
//        }
//
//        // Add the next action if we can do so
//        if (actingUnit.getAlgorithm().getFutureAction() != null && actingUnit.getAlgorithm().getFutureAction().canUse())
//        {
//            actingUnit.getAlgorithm().advanceAlgorithm();
//            assignCurrentAction(actingUnit.getAlgorithm().getNextAction());
//        }
//        else
//        {
//            forceEndTurn();
//        }
//    }

//    public static void forceEndTurn()
//    {
//
////        System.out.println(actingUnit + " is ending turn");
//
//
//        actingUnit.endTurn();
//        actionMode = false;
//        actingUnit = null;
//        currentActionTimer = 0;
//        nextActionTimer = TURN_SWITCH_TIMER;
//
////        System.out.println(actingUnit + " has ended");
//
//
//    }

//    public void updateBattleTimers()
//    {
//        // If between turns, tick turn timer
//        if (actingUnit == null && nextActionTimer > 0)
//        {
//            nextActionTimer--;
//        }
//
//        // If we are in the middle of a turn, tick action timer
//        else if (actingUnit != null && currentActionTimer > 0)
//        {
//            currentActionTimer--;
//
//            currentAction.update();
//            currentAction.setMovement();
//            actingUnit.movement();
//        }
//
////        if(actionTimer > 0)
////        {
////            for(Unit u : getUnits())
////            {
////                u.movementEffect();
////            }
////
////        }
//
//
//
//    }

//    public void nextAction()
//    {
//        // If no active unit and time is up, go to next unit
//
//        if (actingUnit == null && nextActionTimer == 0)
//        {
//            currentAction =
//        }
//    }

    public void resetBattle()
    {
        HeroManager.recover();
        EnemyManager.clear();
        AnimationManager.clear();
        combatHUD.clear();
        combatState = CombatState.SETUP;
//        currentActionTimer = 0;
        actionMode = false;
//        actingUnit = null;
//        nextActionTimer = 0;
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
