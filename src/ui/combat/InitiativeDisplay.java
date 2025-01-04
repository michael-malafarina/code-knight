package ui.combat;

import core.Color;
import core.Main;
import org.newdawn.slick.Graphics;
import states.combat.InitiativeQueue;
import ui.Fonts;
import ui.Text;
import unit.Unit;

import java.util.ArrayList;
import java.util.Collections;

public class InitiativeDisplay
{
    float x;
    float y;
    float width;
    float height;
    float spacing;
    private ArrayList<InitiativePanel> initiativePanels;

    public static final float SPACING = 1.5f;

    public InitiativeDisplay()
    {
        initiativePanels = new ArrayList<>();

        y = Main.getScreenHeight() * .87f;
//        height = Main.getScreenHeight() * .16f;
        spacing = Main.getScreenWidth() * .006f;

    }

    public void update()
    {
        initiativePanels.clear();

        float step = 0;

        ArrayList<Unit> units = InitiativeQueue.getUnits();
        Collections.sort(units);

        for (int i = 0; i < units.size(); i++)
        {
            Unit u = InitiativeQueue.getUnits().get(i);

            InitiativePanel p;

//            if(i == 0)
//            {
//                p = new InitiativePanelBig(x+spacing, y+spacing, u);
//            }
//            else
//            {
                p = new InitiativePanel(x+spacing+step, y+spacing, u);
//            }

            initiativePanels.add(p);
            step += p.getWidth() + spacing;
        }


        width = (InitiativePanel.WIDTH * Main.getScreenHeight() + spacing) * initiativePanels.size() + spacing;
        x = Main.getScreenWidth() * .5f - width / 2;

    }

    public void render(Graphics g)
    {
//        float iconSize = FunctionPanel.HEIGHT * Main.getGameScale() * 1.35f;

//        float xPos = unit.getCell().getX() - iconSize;
//        float yPos = Main.getScreenHeight() * .02f;
//        float width = unit.getWidth() + iconSize;
//        float height = functionPanels.size() * (FunctionPanel.HEIGHT + SPACING) * Main.getGameScale() + Main.getScreenHeight() * .085f;

//        g.setColor(new Color(30, 30, 30, 200));
//        g.fillRect(x, y, width, height);
//        g.setColor(new Color(80, 80, 80, 200));
//        g.drawRect(x, y, width, height);


        Text.setFont(Fonts.bigFont);
        Text.setColor(Color.white);
        Text.alignCenter();
        Text.alignMiddle();

        for (InitiativePanel i : initiativePanels)
        {
            i.render(g);
        }



     //   renderEnergy(g);
    }

    public void renderTooltip(Graphics g)
    {
        for (InitiativePanel i : initiativePanels)
        {
            i.renderTooltip(g);
        }
    }

//    public void renderEnergy(Graphics g)
//    {
//
//        float xOff = 0 * Main.getGameScale();
//        float yOff = 8 * Main.getGameScale() + unit.getAnimation().getHeight();
//        float width = 4 * Main.getGameScale();
//        float height = 4 * Main.getGameScale();
//
//        float x = unit.getCell().getX() + xOff;
//        float y = Main.getScreenHeight() * .070f;
//
//        for (int i = 0; i < unit.getCurEnergy(); i++)
//        {
//            Images.energyDiamondFull.draw(x, y, width, height);
//            x += width;
//        }
//
//        for (int i = unit.getCurEnergy(); i < unit.getMaxEnergy(); i++)
//        {
//            Images.energyDiamondEmpty.draw(x, y, width, height);
//            x += width;
//        }
//
//
//    }


}
