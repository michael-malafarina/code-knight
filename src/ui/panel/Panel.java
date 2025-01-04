package ui.panel;

import core.Color;
import core.Main;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.TrueTypeFont;
import ui.*;

import java.util.ArrayList;

public abstract class Panel
{
    protected float x;
    protected float y;
    protected float width;
    protected float height;
    protected ArrayList<Tooltip> tooltips;
    protected String name;
    protected String description;
    protected TrueTypeFont nameFont;
    protected TrueTypeFont descFont;
    protected Color bgColor;
    private Color borderColor;
    protected Color borderColorDefault;
    protected Color borderColorHighlight;
    protected Color nameColor;
    protected Color descColor;

    protected int borderWidth;
    protected boolean highlighted;

    public static final Color BORDER_COLOR_HIGHLIGHT_BASE = new Color(200, 200, 50, 255);
    public static final Color BORDER_COLOR_DEFAULT_BASE = new Color(10, 10, 10, 255);
//    public static final Color BORDER_COLOR_DEFAULT_BASE = new Color(60, 60, 60, 255);

    protected int yPadding = 3 * Main.getGameScale();
    protected int xPadding = 3 * Main.getGameScale();

    public int maximumTextWidth = 30;
    public int minimumPanelWidth;

    protected ButtonEvent clickEvent;


    public float getX()
    {
        return x;
    }

    public float getY()
    {
        return y;
    }

    public boolean isHighlighted()
    {
        return highlighted;
    }

    public boolean hasTooltip()
    {
        return !tooltips.isEmpty();
    }

    public float getWidth()
    {
        return width;
    }

    public float getHeight()
    {
        return height;
    }

    protected float getNamePadding()
    {
        return yPadding * .6f;
    }

    protected float getDescriptionPadding()
    {
        return yPadding * .2f;
    }

    public Panel()
    {
        this(0, 0, 0, 0);
    }

    public Panel(float x, float y, float width, float height)
    {
        this(x, y, width, height, "", "");
    }

    public Panel(float x, float y, float width, float height, String name, String description)
    {
        this.name = name;
        this.description = description;

       tooltips = new ArrayList<>();

        nameFont = Fonts.mediumFont;
        descFont = Fonts.smallFontMono;

        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        borderWidth = 2;

        nameColor = new Color(255, 255, 255);
        descColor = new Color(255, 255, 255);

        bgColor = new Color(30, 30, 30, 190);

        borderColorDefault = BORDER_COLOR_DEFAULT_BASE;
        borderColorHighlight = BORDER_COLOR_HIGHLIGHT_BASE;
        borderColor = borderColorDefault;

        minimumPanelWidth = 50 * Main.getGameScale();
    }

    public int getNameWidth()
    {
        return Text.getWidth(nameFont, name, maximumTextWidth);
    }

    public int getNameHeight()
    {
        return Text.getHeight(nameFont, name, maximumTextWidth);
    }

    public int getDescriptionWidth()
    {
        return Text.getWidth(descFont, description, maximumTextWidth);
    }

    public int getDescriptionHeight()
    {
        return Text.getHeight(descFont, description, maximumTextWidth);
    }

    public void setSizeToText()
    {
        width = Math.max(Math.max(getNameWidth(), getDescriptionWidth()), minimumPanelWidth)+xPadding;
        height = getNameHeight() + getDescriptionHeight() + yPadding;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setDescription(String name)
    {
        this.description = description;
    }

    public void setPosition(float x, float y)
    {
        this.x = x;
        this.y = y;

        for(Tooltip t : tooltips)
        {
            t.setPosition(x, y);
            y = y + t.getHeight();
        }

    }

    public void setSize(float width, float height)
    {
        this.width = width;
        this.height = height;

        for(Tooltip t : tooltips)
        {
            t.setSize(width, height);
        }

    }

    public void setHighlighted(boolean highlighted)
    {
        this.highlighted = highlighted;
    }

    public void render(Graphics g)
    {
        renderBackground(g);
        renderBorder(g);
        renderText(g);


    }

    protected void renderBackground(Graphics g)
    {
        if(isMouseOver())
        {
            g.setColor(bgColor.brighter(.7f));
        }
        else
        {
            g.setColor(bgColor);
        }
        g.fillRect(getX(), getY(), getWidth(), getHeight());
    }

    protected void renderBorder(Graphics g)
    {
        borderColor = borderColorDefault;

        if(isHighlighted())
        {
            borderColor = borderColorHighlight;
        }

        if(isMouseOver())
        {
            g.setColor(borderColor.brighter(1.05f));
        }
        else
        {
            g.setColor(borderColor);
        }

        g.setLineWidth(borderWidth);
        g.drawRect(getX(), getY(), getWidth(), getHeight());
        g.resetLineWidth();
    }

    protected void renderText(Graphics g)
    {
        Text.alignLeft();
        Text.alignTop();
        Text.setColor(nameColor);
        Text.setFont(nameFont);
        Text.draw(name, x + xPadding * .5f, y + getNamePadding(), maximumTextWidth);

        Text.setColor(descColor);
        Text.setFont(descFont);

        Text.alignTop();
        Text.draw(description, x + xPadding * .5f, y + getNameHeight() + getNamePadding() + getDescriptionPadding(), maximumTextWidth);
    }

    public void renderTooltip(Graphics g)
    {
        if (hasTooltip() && isMouseOver())
        {
            for(int i = 0; i < tooltips.size(); i++)
            {
                float previousHeight = 0;
                if(i > 0)
                {
                    previousHeight += tooltips.get(i-1).getHeight();
                }
                tooltips.get(i).render(g, previousHeight);
            }
        }
    }

    public boolean isMouseOver()
    {
        return Mouse.getX() > getX() && Mouse.getX() < getX() + getWidth() && Mouse.getY() > getY() && Mouse.getY() < getY() + getHeight();
    }

    protected void setClickEvent(ButtonEvent clickEvent)
    {
        this.clickEvent = clickEvent;
    }

    public void mousePressed(int button, int x, int y)
    {
        if(isMouseOver())
        {
            clicked(clickEvent);
        }
    }

    protected void clicked(ButtonEvent f)    {
        if(f != null ) {
            f.activate();
        }
    }



}
