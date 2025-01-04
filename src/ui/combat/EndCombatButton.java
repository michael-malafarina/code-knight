package ui.combat;

import core.Main;
import org.newdawn.slick.Graphics;
import ui.Fonts;
import ui.Text;
import ui.panel.Panel;
import ui.reward.RewardOverlay;
import ui.reward.RewardPanelSet;

public class EndCombatButton extends Panel
{
    private boolean done;

    public EndCombatButton()
    {
        width = 400;
        height = 120;
        x = Main.getScreenWidth() * .75f - width * .5f;
        y = Main.getScreenHeight() * .5f - height * .5f;

        name = "End Battle";

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
