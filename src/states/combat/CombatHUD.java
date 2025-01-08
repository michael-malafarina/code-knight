package states.combat;

import animation.Animation;
import battlefield.Battlefield;
import battlefield.Cell;
import campaign.EnemyManager;
import campaign.HeroManager;
import core.Color;
import core.Main;
import core.Settings;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import states.map.Map;
import ui.Fonts;
import ui.Mouse;
import ui.Text;
import ui.algorithm.AlgorithmDisplay;
import ui.combat.*;
import ui.message.Message;
import ui.message.MessageList;
import ui.reward.RewardOverlay;
import unit.Team;
import unit.Unit;

public class CombatHUD
{
    private int lastSpeed = 1;

    private static AlgorithmDisplay algorithmDisplayPlayer;
    private static AlgorithmDisplay algorithmDisplayEnemy;
    private CurrentActionPanel currentAction;
    private GameSpeedPanel gameSpeedPanel;

    private EndCombatButton endCombatButton;
    private StartCombatButton startCombatButton;

    private MessageList messages;


    private static boolean movementMode;
    private static Unit movingUnit;


    public CombatHUD()
    {
        messages = new MessageList();
        algorithmDisplayPlayer = new AlgorithmDisplay(Team.PLAYER);
        algorithmDisplayEnemy = new AlgorithmDisplay(Team.ENEMY);
        currentAction = new CurrentActionPanel();
        gameSpeedPanel = new GameSpeedPanel();
        endCombatButton = new EndCombatButton();
        startCombatButton = new StartCombatButton();

    }

    public void setRewardGranted()
    {

    }

    public void begin()
    {
        endCombatButton = new EndCombatButton();
        startCombatButton = new StartCombatButton();
        algorithmDisplayPlayer.begin();
        algorithmDisplayEnemy.begin();

    }

    public void update()
    {
        messages.update();
        //        System.out.println("*********");
        algorithmDisplayPlayer.update();
        algorithmDisplayPlayer.update();



        if (inMovementMode())
        {
            Animation a = movingUnit.getAnimation();
            a.setPosition(Mouse.getX() - a.getWidth() / 2, Mouse.getY() - a.getHeight() / 2);
        }

//        {
//            Action a = Combat.getActingUnit().getAlgorithm().getNextFunction().getAction();
//            tooltip = new Tooltip(a.getName(), a.getDescription());
//        }

    }

    public void render(Graphics g)
    {
        gameSpeedPanel.render(g);
        algorithmDisplayPlayer.render(g);
        algorithmDisplayEnemy.render(g);

        // Always draw moving units on top
        if (movingUnit != null)
        {
            movingUnit.render(g);
        }

        if (!Combat.isBattleOver() && !RewardOverlay.isActive())
        {
            currentAction.render(g);
            messages.render(g);
        }

        algorithmDisplayPlayer.renderGrabbedPanel(g);
        algorithmDisplayEnemy.renderGrabbedPanel(g);

        if (Combat.getCombatState() == CombatState.SETUP)
        {
            startCombatButton.render(g);
        }


        if (Combat.isRewardGranted() && !RewardOverlay.isActive())
        {
            endCombatButton.render(g);
        }

        RewardOverlay.render(g);

        if (Settings.debugMode)
        {
            Text.setColor(Color.white);
            Text.setFont(Fonts.mediumFont);
            Text.alignTop();
            Text.alignLeft();
            Text.draw("" + Combat.getCombatState(), 550, 50);
            Text.draw("Stage " + Map.getStage(), 550, 100);
            Text.draw("Diff " + EnemyManager.getCombatDifficulty(), 550, 150);

        }

        if (Main.isPaused())
        {
            Text.setColor(Color.white);
            Text.setFont(Fonts.massiveFontMono);
            Text.alignMiddle();
            Text.alignCenter();
            Text.draw("PAUSED", Main.getScreenWidth() / 2, Main.getScreenHeight() / 2);
        }

        for (Unit u : Combat.getUnits())
        {
            if (u.isAlive())
            {
                u.getModifiers().render(g);
            }

            if (u.isAlive())
            {
                u.getModifiers().renderSecond(g);
            }
        }
    }

    public static void renderCells(Graphics g)
    {

        if (Combat.getCombatState() == CombatState.END)
        {
            return;
        }

        for (Cell c : Battlefield.getCells())
        {
            boolean selected = Combat.getCombatState() != CombatState.BATTLE;
            c.render(g, selected);
        }
    }


    public void mousePressed(int button, int x, int y)
    {
        if (Combat.getCombatState() == CombatState.SETUP && !RewardOverlay.isActive())
        {
            movingUnits();
            algorithmDisplayPlayer.mousePressed(button, x, y);
            algorithmDisplayEnemy.mousePressed(button, x, y);

        }

        if (RewardOverlay.isActive() && RewardOverlay.isReplacement())
        {
            algorithmDisplayPlayer.selectDelete();
            algorithmDisplayEnemy.selectDelete();

        }

        gameSpeedPanel.mousePressed(button, x, y);

        RewardOverlay.mousePressed(button, x, y);


        if (Combat.getCombatState() == CombatState.SETUP)
        {
            startCombatButton.mousePressed(button, x, y);
        }

        if (Combat.getCombatState() == CombatState.END && !RewardOverlay.isActive())
        {
            endCombatButton.mousePressed(button, x, y);
        }
    }


    public void movingUnits()
    {

        if (inMovementMode())
        {
            // Place units into cells
            for (Cell c : Battlefield.getHeroRow().getCells())
            {
                if (c.isMouseOver())
                {
                    movingUnit.setCell(c, true);
                    movingUnit.resetPosition();
                    releaseUnit();
                    return;
                }
            }
        }
        else
        {
            // Grab units
            for (Unit u : HeroManager.getUnits())
            {
                if (u.isMouseOver() && u.isPlayer())
                {
                    grabUnit(u);
                    return;
                }
            }
        }
    }


    public void grabUnit(Unit u)
    {
        if (inMovementMode())
        {
            return;
        }
        movingUnit = u;
        movementMode = true;
    }

    public void releaseUnit()
    {
        if (!inMovementMode())
        {
            return;
        }
        movementMode = false;
        movingUnit = null;
    }

    public static boolean inMovementMode()
    {
        return movementMode;
    }

    public void addMessage(Message m)
    {
        messages.add(m);
    }


    public void clear()
    {
        messages.clear();
    }


    public void keyPressed(int key, char c)
    {
        if (key == Input.KEY_P)
        {
            if (Settings.gameSpeed == 0)
            {
                Settings.gameSpeed = lastSpeed;
                Main.setPaused(false);
            }
            else
            {
                lastSpeed = Settings.gameSpeed;
                Settings.gameSpeed = 0;
                Main.setPaused(true);
            }
        }


        if (key == Input.KEY_1)
        {
            Settings.gameSpeed = 1;
        }

        if (key == Input.KEY_2)
        {
            Settings.gameSpeed = 2;
        }

        if (key == Input.KEY_3)
        {
            Settings.gameSpeed = 3;
        }


        if (key == Input.KEY_0)
        {
            Settings.gameSpeed = 10;
        }


        if (Combat.getCombatState() == CombatState.SETUP)
        {


            if (Settings.debugMode && c == 'r')
            {
                RewardOverlay.begin();
            }
        }
    }

    public boolean startBattle()
    {
        return startCombatButton.isDone();

        //   return endCombatButton.isDone();
    }

    public boolean endState()
    {
        return Combat.getCombatState() == CombatState.END && !RewardOverlay.isActive();
        //   return endCombatButton.isDone();
    }


}
