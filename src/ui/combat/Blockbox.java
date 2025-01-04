package ui.combat;

import core.Color;
import core.Main;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import ui.Fonts;
import ui.Images;
import ui.Text;
import unit.Unit;

public class Blockbox
{
    private Unit unit;
    private float xOff;
    private float yOff;
    private float width;
    private float height;

    public Blockbox(Unit unit)
    {
        this.unit = unit;

    }

    public void render(Graphics g)
    {
        if(unit.getBlock() == 0)
        {
            return;
        }

        xOff = -8 * Main.getGameScale();
        yOff = -2 * Main.getGameScale() + unit.getAnimation().getHeight();
        width = 8 * Main.getGameScale();
        height = 10 * Main.getGameScale();

        float x = unit.getX() + xOff;
        float y = unit.getY() + yOff;

        int blue = Math.max(255 - (int) Math.pow(unit.getTimeSinceBlockChanged(), 2.2), 140);

        // Draw Box

        Image image = Images.uiBlockShield;
        Color c = new Color(35, 65, blue);
        image.draw(x, y, width, height, c);


//        g.setColor(new Color(0, 0, 100));
//        Images.uiBlockShield.draw(x, y, width, height);

        // Draw Block Value
        Text.setFont(Fonts.mediumFontMono);
        Text.setColor(Color.white);
        Text.alignMiddle();
        Text.alignCenter();
        Text.draw(unit.getBlock()+"", x + 4 * Main.getGameScale(), y + 5f * Main.getGameScale());
    }
}
