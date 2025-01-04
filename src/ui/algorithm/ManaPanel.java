package ui.algorithm;

import core.Color;
import core.Main;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import ui.Fonts;
import ui.Images;
import ui.Text;
import ui.panel.Panel;
import ui.panel.Tooltip;
import unit.Unit;

public class ManaPanel extends Panel
{

    Unit unit;

    public ManaPanel(AlgorithmDisplay owner, float x, float y, float w, float h, Unit unit)
    {
        super(x, y, w, h);

        this.unit = unit;
        tooltips.add(new Tooltip(this, "Mana: [MANA]" + unit.getCurMana(),
                 "[KEY]Mana[] is a resource used for magic and powerful effects."));

    }

    public void render(Graphics g)
    {
        float padding = 4 * Main.getGameScale();
        float percentSize = (width + padding) * Math.min(unit.getPercentMana(), 1);

        Image empty = Images.uiManaEmpty;
        Image full =  Images.uiMana;
//
//        if(unit.isManaBurned())
//        {
//            empty = Images.uiManaRed;
//        }

        empty.draw(x - padding/2, y-padding/2, width+padding, height+padding);

//        if(unit.getCurMana() > 0)
//        {
            full.draw(x - padding/2, y - padding/2, percentSize, height+padding);
//        }

//        full.draw(x - padding/2, y + (height-percentSize)+padding/2, width+padding, percentSize);
        g.setColor(borderColorDefault);
        g.drawRect(x - padding/2, y-padding/2, width+padding, height+padding);


        Text.shadowOn();
        Text.setColor(Color.white);
        Text.setFont(Fonts.tinyFontMono);
        Text.alignCenter();
        Text.alignMiddle();
        Text.draw(""+unit.getCurMana() + "/" + unit.getMaxMana(), x+width/2, y+height/2);

        //        Text.draw(unit.getCurMana()+" (+" +unit.getManaPerTurn() + ")", x+width/2, y+height/2);
        Text.shadowOff();

//        if(unit.getManaPerTurn() == 0)
//        {
//            return;
//        }

//        float size = 6 * Main.getGameScale();
//        g.setColor(new Color(20, 50, 154));
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
//        Text.draw("+" + unit.getManaPerTurn(), x+width+padding/2, y+height+padding/2);
    }



}
