package ui.algorithm;

import unit.ability.Algorithm;
import unit.ability.action.Action;
import core.Color;
import core.Main;
import unit.ability.perks.Perk;
import org.newdawn.slick.Graphics;
import states.combat.Combat;
import ui.Fonts;
import ui.Images;
import ui.Text;
import unit.Unit;
import unit.hero.Hero;

import java.util.ArrayList;
import java.util.Collections;

public class AlgorithmDisplay implements Comparable<AlgorithmDisplay>
{
    private Unit unit;
    private Algorithm algorithm;
    private ArrayList<ActionPanel> actionPanels;
    private ArrayList<PerkPanel> perkPanels;

    private AlgorithmDisplaySet owner;
    private ManaPanel manaPanel;
    private SpeedPanel speedPanel;
    private ActionsPanel actionsPanel;

    private float x;
    private float y;
    private float width;
    private float height;

    protected final Color borderColor = ManaPanel.BORDER_COLOR_DEFAULT_BASE;

    public AlgorithmDisplay(Unit unit, AlgorithmDisplaySet owner, float width)
    {
        this.unit = unit;
        algorithm = unit.getAlgorithm();
        algorithm.setDisplay(this);
        actionPanels = new ArrayList<>();
        perkPanels = new ArrayList<>();
        this.owner = owner;
        this.x = 0;
        this.y = Main.getScreenHeight() * .015f;
        this.width = width;
        this.height = getHeight();

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
        return y;
    }

    public float getWidth()
    {
        return width;
    }

    public float getHeight()
    {
        return Main.getScreenHeight() * .10f + algorithm.getActions().size() * (ActionPanel.HEIGHT + ActionPanel.SPACING) * Main.getGameScale();
    }

    public Unit getUnit()
    {
        return unit;
    }

    public void begin()
    {
        if (!unit.isAlive())
        {
            return;
        }

        actionPanels.clear();
        perkPanels.clear();

        for (Action a : algorithm.getActions())
        {
            ActionPanel panel = new ActionPanel(a, this);
            actionPanels.add(panel);
        }

        for (Perk p : unit.getModifiers().getPerks())
        {
            PerkPanel panel = new PerkPanel(p, this);
            perkPanels.add(panel);
        }
    }

    public AlgorithmDisplaySet getOwner()
    {
        return owner;
    }

    public void update(float xPos)
    {
        this.x = xPos;

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
        if (!unit.isAlive())
        {
            return;
        }

//        float iconSize = ActionPanel.HEIGHT * Main.getGameScale() * 1.35f;
//
//        float xPos = unit.getCell().getX() - iconSize;
//        float yPos = Main.getScreenHeight() * .02f;
//        float width = unit.getCell().getWidth() + Main.getScreenWidth() * .025f;
//
//        float height = actionPanels.getLast().calculateYByIndex() - y + Main.getScreenHeight() * .04f;
//
//
//        for(ActionPanel a : actionPanels)
//        {
//            height += actionPanels.size() * a.getHeight();
//        }
//
//        final float SPACING = 4;
//        height = ((actionPanels.size() * ActionPanel.HEIGHT) + ((actionPanels.size() - algorithm.countLinkedCodeActions()) * SPACING)) * Main.getGameScale() + Main.getScreenHeight() * .083f;
//        if(unit.isPlayer())
//        {
        Color c = unit.getClassColor();
        g.setColor(new Color(c.r * .2f, c.g * .2f, c.b * .2f, .8f));
//        }
//        else
//        {
//            g.setColor(new Color(60, 10, 10, 255));
//        }
//        g.setColor(new Color(30, 30, 30, 200));
        g.fillRect(x, y, width, height);
        g.setColor(borderColor);
        g.drawRect(x, y, width, height);


        renderNameAndLevel(g);


        for (ActionPanel f : actionPanels)
        {
            f.render(g);
        }

        renderPerks(g);
       // renderEnergy(g);
        renderMana(g);
        renderSpeed(g);
        renderActions(g);

    }

    public void renderNameAndLevel(Graphics g)
    {
        // Name
        float buffer = 2; //width * .05f;
        // float buffer = width;


        g.setColor(new Color(30, 30, 30, 200));

//        if(unit.isPlayer())
//        {
//            g.setColor(new Color(20, 40, 80, 255));
//        }
//        else
//        {
//            g.setColor(new Color(60, 10, 10, 255));
//        }

        float h = Main.getScreenHeight() * .030f;
        //  g.setColor(new Color(45, 45, 45));
        g.fillRect(x + buffer, y + buffer, width - buffer * 2, h);
//        g.setColor(new Color(100, 100, 100));
//        g.drawRect(x + buffer, y + buffer, width * .7f, Main.getScreenHeight() * .025f);

        g.setColor(borderColor);
        g.drawLine(x, y + h, x + width, y + h);

        Text.setFont(Fonts.mediumFont);
        Text.setColor(Color.white);
        Text.alignLeft();
        Text.alignMiddle();

        String name = unit.getName();
        Text.draw(name, x + width * .02f + buffer, y + h / 2);

        // Level
        //   g.setColor(new Color(45, 45, 45));
        //      g.fillRect(x + width - buffer - Main.getScreenHeight() * .025f, y + buffer, Main.getScreenHeight() * .025f, Main.getScreenHeight() * .025f);
//        g.drawRect(x + width - buffer - Main.getScreenHeight() * .025f, y + buffer, Main.getScreenHeight() * .025f, Main.getScreenHeight() * .025f);

        Text.alignCenter();
        Text.alignMiddle();
        Text.setFont(Fonts.mediumFontMono);

        if (unit instanceof Hero)
        {
            Text.draw("" + ((Hero) unit).getLevel(), x + width - buffer - (Main.getScreenHeight() * .025f) / 2, y + h / 2);
        }
    }

    public void renderPerks(Graphics g)
    {
        int maxPerks = 4;

        float spacing = width * .25f / (maxPerks + 1);
        float size = width * .75f / maxPerks;
//16 * Main.getGameScale();

        for (int i = 0; i < perkPanels.size(); i++)
        {

            PerkPanel p = perkPanels.get(i);
            p.setPosition(getX() + i * (size + spacing) + spacing, Main.getScreenHeight() * .055f);
            p.setSize(size, size);
            p.render(g);

        }
    }

    public void renderTooltip(Graphics g)
    {
        for (ActionPanel f : actionPanels)
        {
            f.renderTooltip(g);
        }

        for (PerkPanel p : perkPanels)
        {
            p.renderTooltip(g);
        }

        if (manaPanel != null)
        {
            manaPanel.renderTooltip(g);
        }
        speedPanel.renderTooltip(g);
        actionsPanel.renderTooltip(g);

    }

    public void renderActions(Graphics g)
    {
        float w = width / 12;
        float h = 3 * Main.getGameScale();
        float xPos = getX() + width * .5f - w / 2;
        float yPos = y + getHeight() - h / 2;
        actionsPanel = new ActionsPanel(this, xPos, yPos, w, h, unit);
        actionsPanel.render(g);
    }

    public void renderSpeed(Graphics g)
    {
        float w = width / 4;
        float h = 2 * Main.getGameScale();
        float xPos = getX() + width * .10f;
        float yPos = y + getHeight() - h / 2;
        speedPanel = new SpeedPanel(this, xPos, yPos, w, h, unit);

        speedPanel.render(g);
    }

    public void renderMana(Graphics g)
    {
//        if (unit.getMaxMana() == 0)
//        {
//            return;
//        }

        float w = width / 4;
        float h = 2 * Main.getGameScale();
//        float xPos = getX() + width - w;
        float xPos = getX() + width * .90f - w;

        float yPos = y + getHeight() - h / 2;
        manaPanel = new ManaPanel(this, xPos, yPos, w, h, unit);

        manaPanel.render(g);

    }

    public void renderEnergy(Graphics g)
    {
        renderEnergyActingUnit(g);
        renderEnergyStandard(g);
    }

    public void renderEnergyActingUnit(Graphics g)
    {
//        if (Combat.getActingUnit() != unit)
//        {
//            return;
//        }
//
//        float energyIconSize = 4 * Main.getGameScale();
//        float energySetWidth = energyIconSize * unit.getEnergyAtStartOfTurn();
//        float xPos = getX() + width / 2 - energySetWidth / 2;
//        float yPos = y + getHeight() - energyIconSize / 2;
//        float padding = 4 * Main.getGameScale();

//        g.setColor(new Color(30, 30, 30));
//        g.fillRect(xPos - padding / 2, yPos - padding / 2, energySetWidth + padding, energyIconSize + padding);
//        g.setColor(borderColor);
//        g.drawRect(xPos - padding / 2, yPos - padding / 2, energySetWidth + padding, energyIconSize + padding);

        // Draw actual energy
//        for (int i = 0; i < unit.getCurEnergy(); i++)
//        {
//            Images.energyDiamondFull.draw(xPos, yPos, energyIconSize, energyIconSize);
//            xPos += energyIconSize;
//        }
//
//        for (int i = 0; i < unit.getEnergyAtStartOfTurn() - unit.getCurEnergy(); i++)
//        {
//            Images.energyDiamondEmpty.draw(xPos, yPos, energyIconSize, energyIconSize);
//            xPos += energyIconSize;
//        }

    }

    public void renderEnergyStandard(Graphics g)
    {
        if (Combat.getActingUnit() == unit)
        {
            return;
        }

        float energyIconSize = 4 * Main.getGameScale();
        float energySetWidth = energyIconSize * (unit.getEnergyPerTurn() + unit.getBonusEnergy() + unit.getCurEnergy());
        float xPos = getX() + width / 2 - energySetWidth / 2;
        float yPos = y + getHeight() - energyIconSize / 2;


        float padding = 4 * Main.getGameScale();
        g.setColor(new Color(30, 30, 30));
        g.fillRect(xPos - padding / 2, yPos - padding / 2, energySetWidth + padding, energyIconSize + padding);
        g.setColor(borderColor);
        g.drawRect(xPos - padding / 2, yPos - padding / 2, energySetWidth + padding, energyIconSize + padding);


        // Draw actual energy
        for (int i = 0; i < unit.getCurEnergy(); i++)
        {
            Images.energyDiamondFull.draw(xPos, yPos, energyIconSize, energyIconSize);
            xPos += energyIconSize;
        }

        for (int i = 0; i < unit.getEnergyPerTurn() + unit.getBonusEnergy(); i++)
        {
            Images.energyDiamondNext.draw(xPos, yPos, energyIconSize, energyIconSize);
            xPos += energyIconSize;
        }
    }

    @Override
    public int compareTo(AlgorithmDisplay other)
    {
        if (unit.getX() < other.unit.getX())
        {
            return -1;
        }
        else if (unit.getX() > other.unit.getX())
        {
            return 1;
        }
        else
        {
            return 0;
        }
    }


}
