package ui.algorithm;

import core.Color;
import core.Main;
import unit.ability.conditions.Condition;
import unit.ability.perks.Perk;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import ui.Fonts;
import ui.Text;
import ui.panel.Panel;
import ui.panel.Tooltip;

public class PerkPanel extends Panel
{
    Perk perk;
    Image icon;


//    public PerkPanel(Perk perk,  UnitDisplay owner)
//    {
//        this.perk = perk;
//
//        x = owner.getX();
//        y = owner.getY() + Main.getScreenHeight() * .04f;
//        width = 64 * Main.getGameScale();
//        height = width;
//
//        tooltips.add(new Tooltip(this, perk.getName(), perk.getDescription()));
//
//        for(Condition c : perk.getConditionInstances())
//        {
//            if(c != null)
//            {
//                tooltips.add(new Tooltip(this, c.getName(), c.getDescription()));
//            }
//        }
//        icon = perk.getIcon().copy();
//
//
//    }
//
//
//
////
////    public boolean isMouseOver()
////    {
////        return true;
////    }
//
//    public void render(Graphics g)
//    {
//
//
//        icon.setColor(0, perk.getColor().r+.4f, perk.getColor().g+.4f, perk.getColor().b+.4f);
//        icon.setColor(1, perk.getColor().r+.2f, perk.getColor().g+.2f, perk.getColor().b+.2f);
//        icon.setColor(2, perk.getColor().r+.0f, perk.getColor().g+.0f, perk.getColor().b+.0f);
//        icon.setColor(3, perk.getColor().r+.2f, perk.getColor().g+.2f, perk.getColor().b+.2f);
//        icon.setFilter(Image.FILTER_LINEAR);
//
//        icon.draw(x, y, width, height);
//
//        g.setColor(new Color(perk.getColor().r, perk.getColor().g, perk.getColor().b));
//
//        g.drawRect(x - Main.getGameScale() * .5f, y - Main.getGameScale() * .5f,
//                width + Main.getGameScale(), height + Main.getGameScale());
//
//        if(perk.getStacks() <= 0)
//        {
//            return;
//        }
//
//        Text.setFont(Fonts.bigFontMono);
//        Text.alignCenter();
//        Text.alignMiddle();
//        Text.shadowOn();
//        Text.shadowSize(2);
//        Text.draw(""+perk.getStacks(), x + width / 2, y + height/2);
//        Text.shadowOff();
//
//    }
}
