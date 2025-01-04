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

public class ActionsPanel extends Panel
{

    Unit unit;

    public ActionsPanel(AlgorithmDisplay owner, float x, float y, float w, float h, Unit unit)
    {
        super(x, y, w, h);

        this.unit = unit;
        tooltips.add(new Tooltip(this,  "Actions: " + Math.max(unit.getEnergyPerTurn(), unit.getCurEnergy()) , "A unit can take one [KEY]Action[] each turn.  Gain additional [KEY]Actions[] through speed and special abilities."));
    }

    public void render(Graphics g)
    {

        float padding = 4 * Main.getGameScale();

        Images.uiActionsEmpty.draw(x - padding/2, y-padding/2, width+padding, height+padding);

        if( Math.max(unit.getEnergyPerTurn(), unit.getCurEnergy()) > 1)
        {
            Images.uiActions.draw(x - padding/2, y-padding/2, width+padding, height+padding);
        }

        g.setColor(borderColorDefault);
        g.drawRect(x - padding/2, y-padding/2, width+padding, height+padding);

        Text.setColor(Color.white);
        Text.setFont(Fonts.smallFontMono);
        Text.alignCenter();
        Text.alignMiddle();
        Text.draw( ""+(Math.max(unit.getEnergyPerTurn(), unit.getCurEnergy())), x+width/2, y+height/2);

    }



}
