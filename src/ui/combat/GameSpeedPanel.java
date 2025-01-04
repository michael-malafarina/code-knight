package ui.combat;

import core.Color;
import core.Main;
import core.Settings;
import ui.Fonts;
import ui.Text;
import ui.panel.Panel;
import org.newdawn.slick.Graphics;
import ui.reward.RewardOverlay;

public class GameSpeedPanel extends Panel
{
    public GameSpeedPanel()
    {
        width = Main.getScreenWidth() * .03f;
        height = width;
        x = Main.getScreenWidth() * .5f - width/2;
        y = Main.getScreenHeight() * .02f;
        nameFont = Fonts.hugeFont;
        setClickEvent(this::changeSpeed);
descColor = new Color(200,200,200);
        description = "Speed";
    }

    public void render(Graphics g)
    {
        if(Settings.gameSpeed == 1)
        {
            name = ">";
        }
        if(Settings.gameSpeed == 2)
        {
            name = ">>";
        }
        if(Settings.gameSpeed == 3)
        {
            name = ">>>";
        }
        if(Settings.gameSpeed == 10)
        {
            name = "10x";
        }


        super.render(g);
    }

    protected void renderText(Graphics g)
    {
        Text.alignMiddle();
        Text.alignCenter();
        Text.setColor(nameColor);
        Text.setFont(nameFont);
        Text.draw(name, x + width * .5f, y + height * .40f);

        Text.setColor(descColor);
        Text.setFont(descFont);
        Text.draw(description, x + width * .5f, y + height * .78f);
    }

    public void changeSpeed()
    {
        if(Settings.gameSpeed < 3)
        {
            Settings.gameSpeed++;
        }
        else
        {
            Settings.gameSpeed = 1;
        }
    }
}
