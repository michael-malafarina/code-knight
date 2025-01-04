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
        width = 400;
        height = 120;
        x = Main.getScreenWidth() * .85f - width * .5f;
        y = Main.getScreenHeight() * .85f - height * .5f;

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
