package ui.title;

import core.Color;
import core.Main;
import org.newdawn.slick.Graphics;
import ui.Fonts;
import ui.Text;
import ui.panel.Panel;

public class StartGameButton extends Panel
{
    private boolean done;

    public StartGameButton()
    {
        width = 300;
        height = 80;
        x = Main.getScreenWidth() * .15f - width * .5f;
        y = Main.getScreenHeight() * .85f - height * .5f;

        name = "START";

        bgColor = new Color(15, 15, 15, 200);
        nameFont = Fonts.colossalFontMono;

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
