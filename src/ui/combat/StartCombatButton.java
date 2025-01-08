package ui.combat;

import core.Main;
import org.newdawn.slick.Graphics;
import ui.Fonts;
import ui.Text;
import ui.panel.Panel;

public class StartCombatButton extends Panel
{
    private boolean done;

    public StartCombatButton()
    {
        width = Main.getScreenWidth() * .10f;
        height = 120;
        x = Main.getScreenWidth() * .025f;
        y = Main.getScreenHeight() * .975f - height ;

        name = "Start Battle";

        nameFont = Fonts.colossalFont;

        setClickEvent(this::endState);
    }

    public void endState()
    {
        done = true;
    }

    public boolean isDone()
    {
        return done;
    }

    protected void renderText(Graphics g)
    {
        Text.alignMiddle();
        Text.alignCenter();
        Text.setColor(nameColor);
        Text.setFont(nameFont);
        Text.draw(name, x + width/2, y + height/2, maximumTextWidth);
    }
}
