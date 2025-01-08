package ui.combat;

import core.Color;
import core.Main;
import org.newdawn.slick.Graphics;
import ui.Fonts;
import ui.Text;
import unit.Unit;

public class Manabar
{
    private Unit unit;
    private float xOff;
    private float yOff;
    private float width;
    private float height;

    public Manabar(Unit unit)
    {
        this.unit = unit;
    }

//    public void setup()
//    {
//
//    }

    public void render(Graphics g)
    {
        if(unit.getMaxMana() == 0)
        {
            return;
        }

        xOff = -1 * Main.getGameScale();
        yOff = 0 * Main.getGameScale() + unit.getAnimation().getHeight();
        width = 38 * Main.getGameScale();
        height = 4 * Main.getGameScale();

        float x = unit.getX() + xOff;
        float y = unit.getY() + yOff + height;

        Color background = new Color(0, 22, 50);
        Color foreground = new Color(0, 90, 180);

        // Draw Healthbar
        g.setLineWidth(2);
        g.setColor(background);
        g.fillRect(x, y, width, height);
        g.setColor(new Color(0, 0, 0));
        g.drawRect(x, y, width, height);

        g.setColor(foreground);
        g.fillRect(x, y, width * unit.getPercentMana(), height);
        g.setColor(new Color(0, 0, 0));
        g.drawRect(x, y, width * unit.getPercentMana(), height);
        g.resetLineWidth();

        // Draw Health Value
        Text.shadowOn();
        Text.shadowSize(1);
        Text.setFont(Fonts.tinyFontMono);
        Text.setColor(Color.white);
        Text.alignCenter();
        Text.alignMiddle();

        Text.draw(unit.getCurMana()+"/" + unit.getMaxMana(), x + width/2, y + height/2+2);
        Text.shadowOff();
    }
}
