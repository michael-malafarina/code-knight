package battlefield;

import core.Color;
import core.Main;
import org.newdawn.slick.Image;
import states.combat.Combat;
import ui.Fonts;
import ui.Images;
import ui.Mouse;
import ui.Text;
import unit.Team;
import unit.Unit;
import org.newdawn.slick.Graphics;

public class Cell
{

    private float indent;
    private float width;
    private float height;
    private float spacing;
    private float stagger;

    private int position = 0;
    private Unit unit;
    private Row row;


    private Image imageSelected;
    private Image imageBasic;

    public Cell(Row row, int position)
    {
        imageSelected = Images.uiCell.copy();
        imageBasic = Images.uiCell.copy();
        imageBasic.setAlpha(.5f);

        this.row = row;
        this.position = position;

        indent = 72 * Main.getGameScale();


//        indent = 48 * Main.getGameScale();
        width = 32 * Main.getGameScale();
        height = 64 * Main.getGameScale();
//        spacing = 8 * Main.getGameScale();

       spacing = 4 * Main.getGameScale();


        // stagger = 64 * Main.getGameScale() * .25f;
        stagger = 48 * Main.getGameScale() * .35f;

    }

    public Row getRow()
    {
        return row;
    }

    public float getX()
    {
        if (row == Team.PLAYER.getRow())
        {
            return indent + (4 - position) * (width + spacing) - width / 2;
        }
        else
        {
            return Main.getScreenWidth() - (4 - position) * (width + spacing) - width / 2 - indent;
        }
    }

    public int getPosition()
    {
        return position;
    }

    public float getXCenter()
    {
        return getX() + getWidth() / 2;
    }

    public float getY()
    {
        float baseY = Main.getScreenHeight() * .73f - height;
        if (position % 2 == 0)
        {
            return baseY + stagger;
        }
        else
        {
            return baseY;
        }

    }

    public float getYCenter()
    {
        return getY() + getHeight() / 2;
    }

    public float getWidth()
    {
        return width;
    }

    public float getHeight()
    {
        return height;
    }

    public void setUnit(Unit u)
    {
        unit = u;
    }

    public void clear()
    {
        unit = null;
    }

    public boolean hasUnit()
    {
        return unit != null;
    }

    public Unit getUnit()
    {
        return unit;
    }


    public void render(Graphics g, boolean visible)
    {
        float x = getX();
        float y = getY() + getHeight() * .22f;
        float w = getWidth();
        float h = getHeight() * .84f;

        if (visible && isMouseOver())
        {
            imageSelected.draw(x, y, w, h);

        }
        else if (visible)
        {
            imageBasic.draw(x, y, w, h);
        }

//        Text.setFont(Fonts.massiveFontMono);
//        Text.setColor(Color.white);
//        Text.draw(""+position, x, y);

    }

    public boolean isMouseOver()
    {
        return Mouse.getX() >= getX() && Mouse.getX() <= getX() + getWidth() && Mouse.getY() >= getY() && Mouse.getY() <= getY() + getHeight();
    }

}
