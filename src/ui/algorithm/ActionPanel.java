package ui.algorithm;

import unit.ability.action.Action;
import unit.ability.action.code.Code;
import unit.ability.action.code.LinkedCode;
import core.Color;
import core.Main;
import unit.ability.conditions.Condition;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import states.combat.Combat;
import ui.Fonts;
import ui.Images;
import ui.Mouse;
import ui.Text;
import ui.panel.Panel;
import ui.panel.Tooltip;
import unit.Team;

public class ActionPanel extends Panel implements Comparable<ActionPanel>
{
    private Image icon;
    private float iconSize;
    private float iconBuffer;
    private boolean grabbed;


    public boolean indented = false;

    public static final int INDENT = 3;
    public static final int HEIGHT = 8;
    private int index;

    protected Action action;
    protected Action previous;
    protected int diamondSize;

    protected boolean deleteTarget;

    public static final float SPACING = 1.5f;
    AlgorithmDisplay owner;

    float yBase;

    public ActionPanel(Action action, AlgorithmDisplay owner)
    {
        super(0, 0, 0, 0, action.getName(), "");

        // Action
        this.action = action;
        this.owner = owner;
        index = action.getUnit().getAlgorithm().getIndex(action);

        // Size
        height = HEIGHT * Main.getGameScale();
        width = owner.getWidth() * .72f;

        // Position
        this.yBase = Main.getScreenHeight() * .095f;

        // Tooltip
        setTooltips();

        // Icon and Font
        iconBuffer = Main.getScreenWidth() * .007f;
        icon = action.getIcon();
        iconSize = height * .7f;
        diamondSize = (int) (3f * Main.getGameScale());
        nameFont = Fonts.smallFont;

    }

    public void setTooltips()
    {
        tooltips.clear();

        tooltips.add(new ActionTooltip(this, action.getName(), action));

        for(Condition c : action.getConditionInstances())
        {
            tooltips.add(new Tooltip(this, c.getName(), c.getDescription()));
        }
    }

    public Action getAction()
    {
        return action;
    }

    public float getX()
    {
        if (isGrabbed())
        {
            return Mouse.getX() - width / 2;
        }
        if (action.getUnit().isAlive())
        {
            if (index > 0 && owner.getActionPanel(index - 1).getAction() instanceof LinkedCode)
            {
                indented = true;
                return owner.getX() + iconBuffer + iconSize + INDENT * Main.getGameScale();
            }
            else
            {
                indented = false;
                return owner.getX() + iconBuffer + iconSize;
            }
        }
        else
        {
            return 0;
        }

    }

    public float getY()
    {
        if (isGrabbed())
        {
            return Mouse.getY() - height / 2;
        }

        return calculateYByIndex();
    }

    public float getWidth()
    {
        if (indented)
        {
            return width - INDENT * Main.getGameScale();
        }
        else
        {
            return width;
        }
    }

    public float calculateYByIndex()
    {
        float yScaling = (height + SPACING * Main.getGameScale()) * index;
        return yBase + yScaling;
    }

    public void updateIndexesWhileGrabbedMoves(ActionPanel grabbedAction)
    {
        if (grabbedAction == null)
        {
            return;
        }

        // Don't allow code cards to go to the last place
        if (grabbedAction.getAction() instanceof Code &&
                grabbedAction.getY() > getY() &&
                index == action.getAlgorithm().getIndex(action.getAlgorithm().getLastAction()))
        {
            return;
        }

        if (grabbedAction.index > index && grabbedAction.getY() < getY())
        {
            int temp = index;
            index = grabbedAction.index;
            grabbedAction.index = temp;

            action.getAlgorithm().swap(index, grabbedAction.index);
        }

        if (grabbedAction.index < index && grabbedAction.getY() > getY())
        {
            int temp = index;
            index = grabbedAction.index;
            grabbedAction.index = temp;


            action.getAlgorithm().swap(index, grabbedAction.index);
        }
    }

    public void render(Graphics g)
    {
        setTooltips();

        x = getX();
        y = getY();

        if (Combat.getCurrentAction() == action && action != null && action == action.getUnit().getAlgorithm().getNextAction())
        {
            setHighlighted(true);
        }
        else
        {
            setHighlighted(false);

        }

        // Code Cards Color
        if (action instanceof Code)
        {
            nameColor = Color.white;
            bgColor = new Color(50, 50, 50, 255);

            if (action.isDisabled())
            {
                nameColor = new Color(130, 130, 130);
                bgColor = new Color(30, 30, 30, 255);
            }
        }

        // Action  Card Color
        else if (action.getUnit().getTeam() == Team.PLAYER)
        {

//            nameColor = Color.white;
//            bgColor =  getAction().getRarity().getColor().darker();

            //     borderColorDefault = getAction().getRarity().getColor();


            nameColor = getAction().getRarity().getColor();
//            bgColor = new Color(20, 40, 80, 255);
            bgColor = new Color(40, 40, 40, 255);

            if (action.isDisabled())
            {
                nameColor = getAction().getRarity().getColor().darker();
                bgColor = new Color(20, 20, 20, 255);
            }

        }


        // Enemy Card Color
        else
        {
            nameColor = new Color(255, 255, 255);
            bgColor = new Color(60, 10, 10, 255);

            if (action.isDisabled())
            {
                nameColor = new Color(130, 130, 130);
                bgColor = new Color(30, 10, 10, 255);
            }
        }

        if (deleteTarget)
        {
            bgColor = new Color(255, 0, 0);
        }


        super.render(g);


        icon.setFilter(Image.FILTER_NEAREST);
        if (icon != null)
        {
            if (isGrabbed())
            {
                icon.draw(getX() - iconSize - iconBuffer / 2, getY() + height / 2 - iconSize / 2, iconSize, iconSize);
            }
            else
            {
                icon.draw(owner.getX() + iconBuffer / 2, getY() + height / 2 - iconSize / 2, iconSize, iconSize);

            }
        }

        renderManaCost(g);
    }

    public void renderTooltip(Graphics g)
    {
        if (hasTooltip() && isMouseOver() && !owner.getOwner().hasGrabbedActionPanel())
        {
            float previousHeight = 0;
            for(int i = 0; i < tooltips.size(); i++)
            {

                if(i > 0)
                {
//                    System.out.println(" >" + tooltips.get(i-1).getHeight());

                    previousHeight += tooltips.get(i-1).getHeight();
                }
//                System.out.println(previousHeight);
                tooltips.get(i).render(g, previousHeight);
            }

        }
    }

    public void renderManaCost(Graphics g)
    {
        if (action.getManaCost() == 0)
        {
            return;
        }

        float w = getWidth() * .19f;
        float h = width * .19f;

        float indentBoxX = getWidth() * .88f - w / 2;
        float indentBoxY = height / 2 - h / 2 + 1;
        float indentTextX = getWidth() * .88f;
        float indentTextY = height / 2 + 1;

        Image diamond;

        if (action.isDisabled())
        {
            if (action.getManaCost() > action.getUnit().getCurMana())
            {
                diamond = Images.manaBoxRedEmpty;
            }
            else
            {
                diamond = Images.manaBoxBlueEmpty;
            }
        }
        else
        {
            if (action.getManaCost() > action.getUnit().getCurMana())
            {
                diamond = Images.manaBoxRed;
            }
            else
            {
                diamond = Images.manaBoxBlue;
            }
        }

        diamond.setFilter(Image.FILTER_LINEAR);
        diamond.draw(getX() + indentBoxX, getY() + indentBoxY, w, h);
//        g.setColor(new Color(30, 70, 180));
//
//        g.fillRect(x + indentBoxX, y + indentBoxY, w, h);

        Text.alignMiddle();
        Text.alignCenter();
        Text.shadowOn();
        Text.setColor(Color.white);
        Text.setFont(Fonts.mediumFontMono);
        Text.draw(action.getManaCost() + "", getX() + indentTextX, getY() + indentTextY);
        Text.shadowOff();
    }


    public boolean isGrabbed()
    {
        return grabbed;
    }

    public void setGrabbed(boolean grabbed)
    {
//        System.out.println(action.getName() + " grab " + grabbed);
        if (action.getUnit().isPlayer())
        {
            this.grabbed = grabbed;
        }
    }

    @Override
    public int compareTo(ActionPanel other)
    {
        if (index < other.index)
        {
            return -1;
        }
        else if (index > other.index)
        {
            return 1;
        }
        else
        {
            return 0;
        }
    }

    public void setDeleteTarget(boolean isTarget)
    {
        deleteTarget = isTarget;
        action.setDelete(isTarget);
    }
}
