package ui.combat;

import core.Color;
import core.Main;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import states.combat.Combat;
import ui.Fonts;
import ui.Text;
import ui.panel.Panel;
import unit.Team;
import unit.Unit;

public class InitiativePanel extends Panel
{
    protected Image icon;
    protected float iconSize;
    protected float spacing;
    protected Unit unit;

    public static final float WIDTH = .07f;

    public InitiativePanel(float x, float y, Unit unit)
    {
        super(x, y, 0, 0, unit.getName(), "actions!");


        this.unit = unit;

        width = Main.getScreenHeight() * .07f;
        //iconBuffer = 2 * Main.getGameScale();
        icon = unit.getAnimation().getFirstImage().getSubImage(4, 16, 24, 24);
        height = Main.getScreenHeight() * .075f;
        spacing = Main.getScreenWidth() * .004f;
        name = name.toUpperCase();
        nameFont = Fonts.mediumFont;
        iconSize = width - spacing * 2;

        if(unit.getTeam() == Team.PLAYER)
        {
            bgColor = new Color(20, 40, 80, 255);
        }
        else
        {
            bgColor = new Color(60, 10, 10, 255);
        }
//        name =  name.toUpperCase();
    }

    public void render(Graphics g)
    {
        renderBackground(g);
        renderBorder(g);

        renderText(g);

        if (icon != null || !unit.isAlive())
        {
            g.setColor(new Color(30, 30, 30));
            g.fillRect(x + spacing, y + spacing, iconSize, iconSize);

            icon.draw(x + spacing, y + spacing, iconSize, iconSize);
            g.setColor(Color.black);
            g.drawRect(x + spacing, y + spacing, iconSize, iconSize);

        }

    }


    protected void renderText(Graphics g)
    {
//        Text.alignCenter();
//        Text.alignTop();
//        Text.setColor(Color.white);
//        Text.setFont(Fonts.bigFont);
//        Text.draw(unit.getDelay()+"", x + width / 2, y + spacing * 2 + iconSize);


//        Text.alignCenter();
//        Text.alignTop();
//        Text.setColor(new Color(190,190,190));
//        Text.setFont(Fonts.smallFontMono);
//        Text.draw(unit.getName(), x + width / 2, y + height + spacing);

////        Text.setColor(MenuColor.BASE);
//        Text.setFont(Fonts.bigFont);
//        Text.alignMiddle();
//        Text.draw(unit.getDelay()+"", x + width/2, y + height/2);

      //  renderIntents(g);
    }

}


