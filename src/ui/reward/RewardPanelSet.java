package ui.reward;


import unit.ability.action.Action;
import unit.ability.action.Upgrade;
import campaign.HeroManager;
import core.Color;
import core.Main;
import unit.ability.perks.Perk;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import states.combat.CombatHUD;
import ui.Fonts;
import ui.Text;
import unit.Unit;
import unit.hero.Hero;

import java.util.ArrayList;
import java.util.Objects;

public class RewardPanelSet
{
    Unit unit;
    String label;
    String labelTwo;
    private ArrayList<RewardPanel> rewardPanels;
    public int numRewardsUpper = 3;
    private boolean replacement;

    public RewardPanelSet()
    {
        rewardPanels = new ArrayList<>();
        label = "";
    }

    public int getNumRewardsUpper()
    {
        return numRewardsUpper;
    }

    public void beginLevelUpReward()
    {
        Hero u = HeroManager.getLowestLevelUnit();
        u.levelUp();
        label = u.getName().toUpperCase();
        labelTwo = "Level " + u.getLevel();
        unit = u;

        beginUpgradeReward(u);

//        switch (u.getLevel())
//        {
//            // Ability
//            case 2, 5, 8:
//                beginActionReward(u);
//                break;
//
//            // Code
//            case 3, 6:
//                beginCodeReward(u);
//                break;
//
//            // Subclass --> PLACEHOLDER
//            case 4:
//                beginPerkReward(u);
//                break;
//
//            // PERK --> PLACEHOLDER
//            case 7, 9:
//                beginPerkReward(u);
//                break;
//        }

    }

    public void beginActionReward(Hero u)
    {
        rewardPanels.clear();

        ArrayList<Action> existingChoices = new ArrayList<>();
        replacement = true;

//        CombatHUD.getAlgorithmDisplaySet().getAlgorithmDisplay(getUnit()).getActionPanel(0).setDeleteTarget(true);

        for (int i = 0; i < numRewardsUpper; i++)
        {
            ActionRewardPanel p = new ActionRewardPanel(this, u, i);
            Action a = p.getRandomAction(existingChoices);
            p.setAction(a);
            existingChoices.add(a);
            rewardPanels.add(p);
        }
    }

    public void beginCodeReward(Hero u)
    {
        rewardPanels.clear();

        ArrayList<Action> existingChoices = new ArrayList<>();
        replacement = false;

        for (int i = 0; i < numRewardsUpper; i++)
        {
            ActionRewardPanel p = new CodeRewardPanel(this, u, i);
            Action a = p.getRandomAction(existingChoices);
            p.setAction(a);
            existingChoices.add(a);
            rewardPanels.add(p);
        }
    }

    public void beginPerkReward(Unit u)
    {
        rewardPanels.clear();

        ArrayList<Perk> existingChoices = new ArrayList<>();
        replacement = false;

        for (int i = 0; i < numRewardsUpper; i++)
        {
            PerkRewardPanel panel = new PerkRewardPanel(this, u, i);
            Perk p = panel.getRandomPerk(existingChoices);
            panel.setPerk(p);
            existingChoices.add(p);
            rewardPanels.add(panel);
        }
    }

    public void beginUpgradeReward(Hero u)
    {
        rewardPanels.clear();

        ArrayList<Upgrade> existingChoices = new ArrayList<>();
        replacement = false;

        for (int i = 0; i < numRewardsUpper; i++)
        {
            UpgradeRewardPanel p = new UpgradeRewardPanel(this, u, i);
            Upgrade a = p.getRandomUpgrade(existingChoices);
            p.setUpgrade(a);
            existingChoices.add(a);
            rewardPanels.add(p);
        }
    }

    public void beginUnitReward()
    {
        rewardPanels.clear();

        replacement = false;

        label = "";
        labelTwo = "";
        unit = null;


        ArrayList<Unit> units = new ArrayList<>();
        for (int i = 0; i < numRewardsUpper; i++)
        {
            HeroRewardPanel p = new HeroRewardPanel(this, i);
            p.setRandomUnit(units);
            units.add(p.getHero());

            rewardPanels.add(p);
        }
    }

    public void update()
    {

    }

    public void render(Graphics g)
    {
        renderRibbon(g);

        for (RewardPanel r : rewardPanels)
        {
            r.render(g);
        }

        if (!Objects.equals(label, ""))
        {
            // Upper Panel
            g.setColor(new Color(20, 20, 20, 255));
            g.fillRect(Main.getScreenWidth() * .45f, Main.getScreenHeight() * .3375f, Main.getScreenWidth() * .1f, Main.getScreenHeight() * .075f);
            g.setColor(new Color(100, 100, 100, 255));
            g.drawRect(Main.getScreenWidth() * .45f, Main.getScreenHeight() * .3375f, Main.getScreenWidth() * .1f, Main.getScreenHeight() * .075f);


            int ICON_SIZE = 96;

            // Upper Panel Label
            Text.alignCenter();
            Text.alignMiddle();
            Text.setColor(Color.white);
            Text.setFont(Fonts.hugeFont);
            Text.draw(label, Main.getScreenWidth() * .5f, Main.getScreenHeight() * .365f);

            Text.setFont(Fonts.mediumFontMono);
            Text.draw(labelTwo, Main.getScreenWidth() * .5f, Main.getScreenHeight() * .392f);


            g.setColor(new Color(20, 20, 20, 255));
            g.fillRect(Main.getScreenWidth() * .5f - ICON_SIZE * .5f, Main.getScreenHeight() * .3375f - ICON_SIZE, ICON_SIZE, ICON_SIZE);
            Image type = unit.getIcon().copy();
            type.setFilter(Image.FILTER_NEAREST);
            type.draw(Main.getScreenWidth() * .5f - ICON_SIZE * .5f, Main.getScreenHeight() * .3375f - ICON_SIZE, ICON_SIZE, ICON_SIZE);
            g.setColor(new Color(100, 100, 100, 255));
            g.drawRect(Main.getScreenWidth() * .5f - ICON_SIZE * .5f, Main.getScreenHeight() * .3375f - ICON_SIZE, ICON_SIZE, ICON_SIZE);


        }

        renderTooltips(g);
    }

    public void renderTooltips(Graphics g)
    {
        for(RewardPanel r : rewardPanels)
        {
            r.renderTooltip(g);
        }
    }

    protected void renderRibbon(Graphics g)
    {
//        width = (Main.getScreenWidth() * .6f) / owner.getNumRewards();
//        height = width * .6f;
//
//        float ribbonHeight = height + ;
        g.setColor(new Color(40, 40, 40, 200));
        g.fillRect(0, Main.getScreenHeight() * .375f, Main.getScreenWidth(), Main.getScreenHeight() * .40f);

    }

    public void mousePressed(int button, int x, int y)
    {
        for (int i = 0; i < rewardPanels.size(); i++)
        {
            rewardPanels.get(i).mousePressed(button, x, y);
        }
    }

    public Unit getUnit()
    {
        return unit;
    }

    public boolean isReplacement()
    {
        return replacement;
    }

}
