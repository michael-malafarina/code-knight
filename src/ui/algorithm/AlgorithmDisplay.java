package ui.algorithm;

import core.Color;
import core.Main;
import org.newdawn.slick.Graphics;
import ui.Fonts;
import ui.Text;
import ui.panel.Panel;
import unit.Team;
import unit.Unit;
import unit.ability.Algorithm;
import unit.ability.action.Action;
import unit.ability.perks.Perk;
import unit.hero.Hero;

import java.util.ArrayList;
import java.util.Collections;

public class AlgorithmDisplay extends Panel
{
    private Algorithm algorithm;
    private ArrayList<ActionPanel> actionPanels;

    private float x;
    private float y;
    private float width;
    private float height;
    private Team team;

    protected final Color borderColor = ManaPanel.BORDER_COLOR_DEFAULT_BASE;

    public Algorithm getAlgorithm() {
        return algorithm;
    }

    public Team getTeam()
    {
        return team;
    }

    public AlgorithmDisplay(Team team)
    {
        algorithm = team.getAlgorithm();
        actionPanels = new ArrayList<>();

        this.team = team;
        this.width = Main.getScreenWidth() * .13f;
        this.height = Main.getScreenHeight(); //getHeight();

        if(team == Team.PLAYER)
        {
            this.x = Main.getScreenWidth() * .01f;
        }
        else
        {
            x = Main.getScreenWidth() - Main.getScreenWidth() * .01f - width;
//            x = Main.getScreenWidth() - width;

        }

        this.y = 0;//Main.getScreenHeight() * .015f;


    }

    public ActionPanel getActionPanel(int index)
    {
        return actionPanels.get(index);
    }

    public float getX()
    {
        return x;
    }

    public float getY()
    {
        return Main.getScreenHeight() * .5f - getHeight() * .5f;
    }

    public float getWidth()
    {
        return width;
    }

    public float getHeight()
    {
        return Main.getScreenHeight() * .02f + algorithm.getActions().size() * (ActionPanel.HEIGHT + ActionPanel.SPACING) * Main.getGameScale();
    }


    public void begin()
    {


        actionPanels.clear();

        for (Action a : algorithm.getActions())
        {
            ActionPanel panel = new ActionPanel(a, this);
            actionPanels.add(panel);
        }
    }

    public void update()
    {

        // Sort the y axis of panels
        if (hasGrabbedPanel())
        {
            for (ActionPanel p : actionPanels)
            {
                p.updateIndexesWhileGrabbedMoves(getGrabbedPanel());
            }

            Collections.sort(actionPanels);
        }
    }



    public void mousePressed(int button, int x, int y)
    {
//        System.out.println("mouse pressed on algo disp");
        if (hasGrabbedPanel())
        {
//            System.out.println("ungrabbing");

            getGrabbedPanel().setGrabbed(false);
        }
        else if (hasMouseOverPanel())
        {

            getMouseOverPanel().setGrabbed(true);
        }

    }

    public void renderGrabbedPanel(Graphics g)
    {
        if (hasGrabbedPanel())
        {
            getGrabbedPanel().render(g);
        }
    }

    public void selectDelete()
    {
        if (getMouseOverPanel() != null)
        {
            for (ActionPanel a : actionPanels)
            {
                a.setDeleteTarget(false);
            }

            getMouseOverPanel().setDeleteTarget(true);
        }
    }

    public boolean hasMouseOverPanel()
    {
        for (ActionPanel p : actionPanels)
        {
            if (p.isMouseOver())
            {
                return true;
            }
        }
        return false;
    }

    public ActionPanel getMouseOverPanel()
    {
        for (ActionPanel p : actionPanels)
        {
            if (p.isMouseOver())
            {
                return p;
            }
        }
        return null;
    }

    public boolean hasGrabbedPanel()
    {
        for (ActionPanel p : actionPanels)
        {
            if (p.isGrabbed())
            {
                return true;
            }
        }
        return false;
    }

    public ActionPanel getGrabbedPanel()
    {
        for (ActionPanel p : actionPanels)
        {
            if (p.isGrabbed())
            {
                return p;
            }
        }
        return null;
    }


    public void render(Graphics g)
    {
        super.render(g);

        Color c = new Color(50, 50, 100);
        g.setColor(new Color(c.r * .2f, c.g * .2f, c.b * .2f, .8f));

//        g.fillRect(x, y, width, height);
//        g.setColor(borderColor);
//        g.drawRect(x, y, width, height);

//        renderNameAndLevel(g);

        for (ActionPanel f : actionPanels)
        {
            f.render(g);
        }

        for (ActionPanel f : actionPanels)
        {
            f.renderTooltip(g);
        }

    }

//    public void renderNameAndLevel(Graphics g)
//    {
//        // Name
//        float buffer = 2; //width * .05f;
//        // float buffer = width;
//
//
//        g.setColor(new Color(30, 30, 30, 200));
//
////        if(unit.isPlayer())
////        {
////            g.setColor(new Color(20, 40, 80, 255));
////        }
////        else
////        {
////            g.setColor(new Color(60, 10, 10, 255));
////        }
//
//        float h = Main.getScreenHeight() * .030f;
//        //  g.setColor(new Color(45, 45, 45));
//        g.fillRect(x + buffer, y + buffer, width - buffer * 2, h);
////        g.setColor(new Color(100, 100, 100));
////        g.drawRect(x + buffer, y + buffer, width * .7f, Main.getScreenHeight() * .025f);
//
//        g.setColor(borderColor);
//        g.drawLine(x, y + h, x + width, y + h);
//
//        Text.setFont(Fonts.mediumFont);
//        Text.setColor(Color.white);
//        Text.alignLeft();
//        Text.alignMiddle();
//
//        String name = unit.getName();
//        Text.draw(name , x + width * .02f + buffer, y + h / 2);
//
//        // Level
//        //   g.setColor(new Color(45, 45, 45));
//        //      g.fillRect(x + width - buffer - Main.getScreenHeight() * .025f, y + buffer, Main.getScreenHeight() * .025f, Main.getScreenHeight() * .025f);
////        g.drawRect(x + width - buffer - Main.getScreenHeight() * .025f, y + buffer, Main.getScreenHeight() * .025f, Main.getScreenHeight() * .025f);
//
//        Text.alignCenter();
//        Text.alignMiddle();
//        Text.setFont(Fonts.mediumFontMono);
//
//        if (unit instanceof Hero)
//        {
//            Text.draw("" + ((Hero) unit).getLevel(), x + width - buffer - (Main.getScreenHeight() * .025f) / 2, y + h / 2);
//        }
//    }

//    public void renderPerks(Graphics g)
//    {
//        int maxPerks = 4;
//
//        float spacing = width * .25f / (maxPerks + 1);
//        float size = width * .75f / maxPerks;
////16 * Main.getGameScale();
//
//        for (int i = 0; i < perkPanels.size(); i++)
//        {
//
//            PerkPanel p = perkPanels.get(i);
//            p.setPosition(getX() + i * (size + spacing) + spacing, Main.getScreenHeight() * .055f);
//            p.setSize(size, size);
//            p.render(g);
//
//        }
//    }

    public void renderTooltip(Graphics g)
    {
        for (ActionPanel f : actionPanels)
        {
            f.renderTooltip(g);
        }

        System.out.println("tooltips");
//        for (PerkPanel p : perkPanels)
//        {
//            p.renderTooltip(g);
//        }
//
//        if (manaPanel != null)
//        {
//            manaPanel.renderTooltip(g);
//        }
//        speedPanel.renderTooltip(g);
//        actionsPanel.renderTooltip(g);

    }

//    public void renderActions(Graphics g)
//    {
//        float w = width / 12;
//        float h = 3 * Main.getGameScale();
//        float xPos = getX() + width * .5f - w / 2;
//        float yPos = y + getHeight() - h / 2;
//        actionsPanel = new ActionsPanel(this, xPos, yPos, w, h, unit);
//        actionsPanel.render(g);
//    }
//
//
//    public void renderMana(Graphics g)
//    {
////        if (unit.getMaxMana() == 0)
////        {
////            return;
////        }
//
//        float w = width / 4;
//        float h = 2 * Main.getGameScale();
////        float xPos = getX() + width - w;
//        float xPos = getX() + width * .90f - w;
//
//        float yPos = y + getHeight() - h / 2;
//        manaPanel = new ManaPanel(this, xPos, yPos, w, h, unit);
//
//        manaPanel.render(g);
//
//    }

//    @Override
//    public int compareTo(AlgorithmDisplay other)
//    {
//        if (unit.getX() < other.unit.getX())
//        {
//            return -1;
//        }
//        else if (unit.getX() > other.unit.getX())
//        {
//            return 1;
//        }
//        else
//        {
//            return 0;
//        }
//    }

    protected void renderBackground(Graphics g)
    {

        g.setColor(new Color(20, 20, 20, 255));

        g.fillRect(getX(), getY(), getWidth(), getHeight());
    }

}
