package ui.algorithm;

import core.Color;
import core.Main;
import unit.ability.conditions.Condition;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import ui.Fonts;
import ui.Text;
import ui.panel.Panel;
import ui.panel.Tooltip;

public class ConditionPanel extends Panel
{
    public static final int ICON_SIZE = 6;
    Condition condition;
    Image icon;

    public ConditionPanel(float x, float y, Condition condition)
    {
        super();

        this.condition = condition;
        this.icon = condition.getIcon();
        this.x = x;
        this.y = y;
        width = ICON_SIZE * Main.getGameScale();
        height = ICON_SIZE * Main.getGameScale();
        tooltips.add(new Tooltip(this, condition.getName(), condition.getDescription()));
    }

    public void render(Graphics g)
    {
        // Icon and border colors and drawing
        icon.setImageColor(condition.getColor().r, condition.getColor().g, condition.getColor().b);
        icon.draw(x, y, width, height);

        g.setLineWidth(1);
        g.setColor(new Color(condition.getColor().r, condition.getColor().g, condition.getColor().b));
        g.drawRect(x - 1, y - 2, width + 2, height + 2);

        Text.setFont(Fonts.mediumFontMono);
        Text.alignCenter();
        Text.alignMiddle();
        Text.shadowOn();
        Text.shadowSize(1);
        Text.draw("" + condition.getStacks(), x + width / 2, y + (ICON_SIZE + 2) * Main.getGameScale());
        Text.shadowOff();

    }



}
