package ui.combat;

import core.Color;
import core.Main;
import org.newdawn.slick.Graphics;
import ui.Fonts;
import ui.Images;
import ui.Text;
import unit.Unit;

public class EnergyDiamonds
{
    private Unit unit;
    private float xOff;
    private float yOff;
    private float width;
    private float height;

    public EnergyDiamonds(Unit unit)
    {
        this.unit = unit;
    }

    public void render(Graphics g)
    {
        xOff = -2 * Main.getGameScale();
        yOff = 8 * Main.getGameScale() + unit.getAnimation().getHeight();
        width = 4 * Main.getGameScale();
        height = 4 * Main.getGameScale();

        float x = unit.getX() + xOff;
        float y = unit.getY() + yOff;

        for(int i = 0; i < unit.getCurEnergy(); i++)
        {
            Images.energyDiamondFull.draw(x, y, width, height);
            x += width;
        }

        for(int i = unit.getCurEnergy(); i < unit.getMaxEnergy(); i++)
        {
            Images.energyDiamondEmpty.draw(x, y, width, height);
            x += width;
        }


//        // Draw Box
//        g.setLineWidth(2);
//        g.setColor(new Color(100, 100, 100));
//        g.fillRect(x, y, width, height);
//        g.setColor(new Color(0, 0, 0));
//        g.drawRect(x, y, width, height);
//
//        // Draw Block Value
//        Text.setFont(Fonts.mediumFont);
//        Text.setColor(Color.white);
//        Text.alignMiddle();
//        Text.alignCenter();
//        Text.draw(unit.getBlock()+"", x + 4 * Main.getGameScale(), y + 6f * Main.getGameScale());
    }
}
