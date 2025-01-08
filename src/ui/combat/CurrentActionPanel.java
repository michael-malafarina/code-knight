package ui.combat;

import unit.ability.action.Action;
import unit.ability.action.code.Code;
import battlefield.Cell;
import core.Color;
import core.Main;
import org.newdawn.slick.Graphics;
import states.combat.Combat;
import ui.Fonts;
import ui.Text;
import ui.panel.Panel;

public class CurrentActionPanel extends Panel
{
    float iconSize;

    public CurrentActionPanel()
    {
        super();
        width = Main.getScreenWidth() * .12f;
        height = Main.getScreenHeight() * .05f;
        iconSize = height * .55f;
    }

    public void render(Graphics g)
    {

        Action action = Combat.getCurrentTeam().getAlgorithm().getCurrentAction();

        if (action == null)
        {
            return;
        }

        if(action instanceof Code)
        {
            return;
        }

        if(!Combat.inActionMode())
        {
            return;
        }

        Cell c = action.getUnit().getCell();

        if(c == null)
        {
            return;
        }

        float xPadding = 6 * Main.getGameScale();
        name = action.getName();
        nameFont = Fonts.hugeFont;

        width = xPadding * 2.5f + getNameWidth() + iconSize;

        x = c.getX() + c.getWidth() / 2 - width / 2;
        y = c.getY() - height;

        renderBackground(g);
        renderBorder(g);

        if(action.getIcon() != null)
        {
            action.getIcon().draw(x + xPadding, y + height / 2 - iconSize / 2, iconSize, iconSize);
        }

        Text.alignLeft();
        Text.alignMiddle();
        Text.setColor(Color.white);
        Text.setFont(nameFont);
        Text.draw(name, x + xPadding * 1.5f + iconSize, y + height / 2);



    }


}
