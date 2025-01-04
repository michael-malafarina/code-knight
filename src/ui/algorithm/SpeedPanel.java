package ui.algorithm;

import core.Color;
import core.Main;
import org.newdawn.slick.Graphics;
import ui.Fonts;
import ui.Images;
import ui.Text;
import ui.panel.Panel;
import ui.panel.Tooltip;
import unit.Unit;

public class SpeedPanel extends Panel
{

    Unit unit;

    public SpeedPanel(AlgorithmDisplay owner, float x, float y, float w, float h, Unit unit)
    {
        super(x, y, w, h);

        this.unit = unit;
        String ttName = "Speed: " + unit.getCurSpeed() + " / " + unit.getMaxSpeed();

        if(unit.getSpeedPerTurn() > 0)
        {
            ttName += " (+" + unit.getSpeedPerTurn() + " per turn)";
        }

        tooltips.add(new Tooltip(this,  ttName, "[KEY]Speed[] is gained" +
                " through fast actions, perks, and traits.  When [KEY]Speed[] reaches 100, take an extra [KEY]Action[]."));
    }

    public void render(Graphics g)
    {

        float padding = 4 * Main.getGameScale();
        float percentSize = (width + padding) * (unit.getPercentSpeed());

        Images.uiSpeedEmpty.draw(x - padding/2, y-padding/2, width+padding, height+padding);
        Images.uiSpeed.draw(x - padding/2, y - padding/2, percentSize, height+padding);

        g.setColor(borderColorDefault);
        g.drawRect(x - padding/2, y-padding/2, width+padding, height+padding);

        Text.setColor(Color.white);
        Text.setFont(Fonts.tinyFontMono);
        Text.alignCenter();
        Text.alignMiddle();
        Text.draw(unit.getCurSpeed()+"/100", x+width/2, y+height/2);

//        if(unit.getSpeedPerTurn() == 0)
//        {
//            return;
//        }
//        float size = 6 * Main.getGameScale();
//        g.setColor(new Color(100, 90, 30));
//        g.fillOval(x + width + padding/2 - size/2+1, y + height + padding/2 - size/2, size, size);
//        g.setColor(borderColorDefault);
//        g.setLineWidth(2);
//        g.drawOval(x + width + padding/2 - size/2+1, y + height + padding/2 - size/2, size, size);
//        g.resetLineWidth();
//
//        Text.setColor(Color.white);
//        Text.setFont(Fonts.smallFont);
//        Text.alignCenter();
//        Text.alignMiddle();
//        Text.draw("+" + unit.getSpeedPerTurn(), x+width+padding/2, y+height+padding/2);
    }



}
