package states.map;

import campaign.HeroManager;
import core.Color;
import core.Main;
import core.Utility;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import ui.Fonts;
import ui.Images;
import ui.panel.Panel;
import ui.reward.RewardPanelSet;

import java.lang.reflect.Array;
import java.util.ArrayList;

public abstract class Node extends Panel
{
    abstract public void triggerEvent();

    ArrayList<Node> nextNodes;
    ArrayList<Node> previousNodes;

    protected int stage;
    protected int xIndex;
    protected int yIndex;
    protected NodeSet owner;
    protected boolean linked;
    protected boolean origin;

    protected Image icon;
    protected Image iconGray;

    protected Image tile;

    public int getStage()
    {
        return stage;
    }

    public int getXIndex()
    {
        return xIndex;
    }

    public int getYIndex()
    {
        return yIndex;
    }


    public boolean isNext(int curStage)
    {
        return stage == curStage + 1;
    }

    public boolean isAdjacent(int x, int y)
    {
        return (xIndex == x - 1 && yIndex == y) ||
                (xIndex == x + 1 && yIndex == y) ||
                (xIndex == x && yIndex == y - 1) ||
                (xIndex == x && yIndex == y + 1);
    }


    public boolean hasLink()
    {
        return linked;
    }

    public boolean isOrigin()
    {
        return origin;
    }

    public void setOrigin()
    {
        origin = true;
    }

    public boolean isLinked(Node other)
    {
        return previousNodes.contains(other) || nextNodes.contains(other);
    }

    public Node(NodeSet owner, int x, int y)
    {
        this.stage = x + y;
        this.xIndex = x;
        this.yIndex = y;
        this.owner = owner;
        this.nextNodes = new ArrayList<>();
        this.previousNodes = new ArrayList<>();

        float size = 32 * Main.getGameScale();
        float xIndent = (Main.getScreenWidth() - (size * NodeSet.WIDTH)) / 2;
        float yIndent = (Main.getScreenHeight() - (size * NodeSet.HEIGHT)) / 2;

        width = size;
        height = size;

        this.x = xIndent + xIndex * size;// + Utility.random(-14, 14);
        this.y = yIndent + yIndex * size;// + Utility.random(-14, 14);

        setClickEvent(this::clicked);

        nameFont = Fonts.mediumFont;
        descFont = Fonts.smallFontMono;
        maximumTextWidth = 30;

        tile = Images.tileGrass.copy();        // temp hardcoding
        tile.setFilter(Image.FILTER_NEAREST);
    }

    public void clicked()
    {
        if (canSelect())
        {
            triggerEvent();
        }
    }

    public void addPreviousNode(Node other)
    {
        previousNodes.add(other);
    }

    public void addNextNode(Node other)
    {
        nextNodes.add(other);
    }

    public boolean canSelect()
    {
        return previousNodes.contains(owner.getCurrentNode()) || (isOrigin() && Map.getStage() == 0);
    }

    public void renderLink(Graphics g)
    {
//        for(Node n : previousNodes)
//        {
//            g.setColor(Color.green);
//            g.drawLine(x+getWidth()/2+5, y+getHeight()/2+5, n.getX()+n.getWidth()/2+5, n.getY()+n.getHeight()/2+5);
//        }

        if(isPast())
        {
            g.setColor(Color.gray);
        }
        else if(canSelect())
        {
            g.setColor(Color.yellow);
        }
        else
        {
            g.setColor(Color.white);
        }


        for(Node n : previousNodes)
        {
            if(n != null)
            {
                g.drawLine(x+getWidth()/2, y+getHeight()/2, n.getX()+n.getWidth()/2, n.getY()+n.getHeight()/2);
            }
        }
    }

    public boolean isPast()
    {
        return owner.getCurrentNode() != null && (getXIndex() < owner.getCurrentNode().getXIndex() || getYIndex() < owner.getCurrentNode().getYIndex());
    }


    public void render(Graphics g)
    {

        g.setColor(new Color(50, 50, 50));



    //    tile.draw(x, y, width, height);
//        g.setLineWidth(6);
//        g.setColor(new Color(0, 0,0));
//        g.drawRect(x, y, width, height);
//
//        g.setLineWidth(2);
//        g.setColor(new Color(50, 50,50));
//        g.drawRect(x, y, width, height);

        Color borderColor;
        Color backgroundColor;

        Image currentIcon;

        if(isPast())
        {
            borderColor = new Color(0,0,0);
            backgroundColor = new Color(30,30,30);
            currentIcon = iconGray;
        }
        else if (canSelect())
        {
            borderColor = new Color(255,255,0);
            backgroundColor = new Color(100,100,100);

            if(isMouseOver())
            {
                borderColor =  borderColor.brighter(.5f);
                backgroundColor = backgroundColor.brighter(.5f);

            }

            currentIcon = icon;
           // icon.setImageColor(255, 255, 0);
        }
        else
        {
            currentIcon = icon;
            borderColor = new Color(60,60,60);
            backgroundColor = new Color(60,60,60);
        }


        g.setColor(backgroundColor);
        g.fillOval(x+width*.1f, y+height*.1f, width * .8f, height *.8f);
        g.setLineWidth(6);
        g.setColor(Color.black);
        g.drawOval(x+width*.1f, y+height*.1f, width * .8f, height *.8f);
        g.setLineWidth(2);
        g.setColor(borderColor);
        g.drawOval(x+width*.1f, y+height*.1f, width * .8f, height *.8f);


        if(owner.getCurrentNode() == this && !HeroManager.getUnits().isEmpty())
        {
            Image tempIcon = HeroManager.getUnits().getFirst().getIcon().getFlippedCopy(true, false);
            tempIcon.setFilter(Image.FILTER_NEAREST);
            tempIcon.draw(x + width * .25f, y + height * .25f, width * .5f, height * .5f);
        }
        else
        {
            currentIcon.setFilter(Image.FILTER_NEAREST);
            currentIcon.draw(x + width * .25f, y + height * .25f, width * .5f, height * .5f);
        }
        // super.renderBorder(g);



//        super.render(g);
    }

    public void renderTwo(Graphics g)
    {
//        if (canSelect())
//        {
//            g.setLineWidth(2);
//            g.setColor(new Color(255, 255, 0));
//            g.drawRect(x, y, width, height);
//        }
    }

}
