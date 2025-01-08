package ui.reward;

import campaign.HeroManager;
import org.newdawn.slick.Graphics;
import states.combat.CombatHUD;
import states.map.Map;
import unit.Unit;

public class RewardOverlay
{
    private static RewardPanelSet rewardPanelSet;
    private static boolean active;

    private static boolean replacement;


    public static void init()
    {
        rewardPanelSet = new RewardPanelSet();
    }

    public static void begin()
    {
        if (!active)
        {
            if(Map.getCurrentNode() instanceof states.map.BattleNode)
            {
                HeroManager.recover();
                rewardPanelSet.beginLevelUpReward();

                // Automatically mark the first item for deletion
            }
            else
            {
                rewardPanelSet.beginUnitReward();
            }
            active = true;
        }
    }

    public static void update()
    {

    }

    public static void render(Graphics g)
    {
        if (isActive())
        {
            rewardPanelSet.render(g);
        }
    }


    public static void mousePressed(int button, int x, int y)
    {
        if (isActive())
        {
            rewardPanelSet.mousePressed(button, x, y);
        }
    }

    public static void end()
    {
        setActive(false);
    }

    public static boolean isActive()
    {
        return active;
    }
    public static boolean isReplacement()
    {
        return rewardPanelSet.isReplacement();
    }

    private static void setActive(boolean isActive)
    {
        active = isActive;
    }

    public static Unit getUnit()
    {
       return rewardPanelSet.getUnit();
    }

}
