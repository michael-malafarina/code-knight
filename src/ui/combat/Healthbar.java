package ui.combat;

import core.Color;
import core.Main;
import org.newdawn.slick.Graphics;
import ui.Fonts;
import ui.Text;
import unit.Unit;

public class Healthbar
{
    private Unit unit;
    private float xOff;
    private float yOff;
    private float width;
    private float height;

    public Healthbar(Unit unit)
    {
        this.unit = unit;
    }

//    public void setup()
//    {
//
//    }

    public void render(Graphics g)
    {
        xOff = -1 * Main.getGameScale();
        yOff = 0 * Main.getGameScale() + unit.getAnimation().getHeight();
        width = 38 * Main.getGameScale();
        height = 5 * Main.getGameScale();

        float x = unit.getX() + xOff;
        float y = unit.getY() + yOff;

        Color background = new Color(50, 9, 0);
        Color foreground = new Color(180, 0, 0);

        if(unit.getBlock() > 0)
        {
            background = new Color(0, 0, 50);
            foreground = new Color(20, 60, 180);
        }
        else if(unit.isLowHealth())
        {
            background = new Color(30, 0, 0);
            foreground = new Color(140, 0, 0);
        }

        // Draw Healthbar
        g.setLineWidth(2);
        g.setColor(background);
        g.fillRect(x, y, width, height);
        g.setColor(new Color(0, 0, 0));
        g.drawRect(x, y, width, height);

        g.setColor(foreground);
        g.fillRect(x, y, width * unit.getPercentHealth(), height);
        g.setColor(new Color(0, 0, 0));
        g.drawRect(x, y, width * unit.getPercentHealth(), height);
        g.resetLineWidth();

        // Draw Health Value
        Text.shadowOn();
        Text.shadowSize(1);
        Text.setFont(Fonts.smallFontMono);
        Text.setColor(Color.white);
        Text.alignCenter();
        Text.alignMiddle();

        Text.draw(unit.getCurHealth()+"/" + unit.getMaxHealth(), x + width/2, y + height/2+2);
        Text.shadowOff();
    }
}
