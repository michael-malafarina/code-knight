package ui.reward;

import core.Color;
import core.Main;
import core.Utility;
import unit.ability.conditions.Condition;
import unit.ability.perks.Perk;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import ui.Fonts;
import ui.Text;
import ui.panel.Tooltip;
import unit.Unit;

import java.util.ArrayList;

public class PerkRewardPanel extends RewardPanel
{
    protected Perk perk;

    protected Unit unit;

    public PerkRewardPanel(RewardPanelSet owner, Unit unit, int index)
    {
        super(owner, index);

        this.unit = unit;

        width = (Main.getScreenWidth() * .66f) / owner.getNumRewardsUpper();
        height = width * .6f;
        float spacing = (Main.getScreenWidth() * .34f) / (owner.getNumRewardsUpper() + 1);

        x = (spacing * (index + 1)) + (width * index);
        y = Main.getScreenHeight() * .45f;

        nameFont = Fonts.hugeFont;
        descFont = Fonts.mediumFontMono;
        bgColor = new Color(20, 20, 20, 245);
        maximumTextWidth = 34;
    }

    public Perk getRandomPerk(ArrayList<Perk> existingRewards)
    {
        ArrayList<Class<? extends Perk>> perkPool = unit.getPerkPool();
        int randomIndex = Utility.random(perkPool.size());
        Class<? extends Perk> clazz = perkPool.get(randomIndex);
        Perk newPerk = perkFactory(clazz, unit);

//        System.out.println(newPerk.getColor().getRed() + " " + newPerk.getColor().getBlue());

        for(Perk a : existingRewards)
        {
            if(newPerk.getClass().equals(a.getClass()))
            {
                return getRandomPerk(existingRewards);
            }
        }

        return newPerk;

    }

    public void setPerk(Perk p)
    {

        perk = p;
        name = p.getName();
        description = p.getDescription();

        for(Condition c : perk.getConditionInstances())
        {
            tooltips.add(new Tooltip(this, c.getName(), c.getDescription()));
        }

    }

    public Perk perkFactory(Object o, Unit unit)
    {
        Class<? extends Perk> clazz = (Class<? extends Perk>) o;

        Perk a = null;

        try
        {
            // When I create a new condition, set its duration to the actual duration after modifiers
            a = clazz.newInstance();
            a.setOwner(unit);
        }
        catch (InstantiationException | IllegalAccessException e)
        {
            e.printStackTrace();
        }

        return a;
    }

    protected void renderText(Graphics g)
    {
        // Actual Text
        Text.alignCenter();
        Text.alignMiddle();
        Text.setColor(nameColor);
        Text.setFont(nameFont);
        Text.draw(name, x + width/2 + xPadding * .5f, y + width * .10f, maximumTextWidth);

        Text.setColor(new Color(255, 255, 255));
        Text.setFont(Fonts.mediumFontMono);

        Text.alignMiddle();
        Text.draw(description, x + width/2 + xPadding * .5f, y + height * .64f - Text.getHeight(description, maximumTextWidth)/2, maximumTextWidth);


        Image type = perk.getIcon();
        type.setColor(0, perk.getColor().r+.4f, perk.getColor().g+.4f, perk.getColor().b+.4f);
        type.setColor(1, perk.getColor().r+.2f, perk.getColor().g+.2f, perk.getColor().b+.2f);
        type.setColor(2, perk.getColor().r+.0f, perk.getColor().g+.0f, perk.getColor().b+.0f);
        type.setColor(3, perk.getColor().r+.2f, perk.getColor().g+.2f, perk.getColor().b+.2f);

        type.draw(x + width * .04f, y + width * .04f, width * .16f, width * .16f);

        g.setColor(new Color(60, 60, 60, 255));
        g.drawRect(x + width * .04f, y + width * .04f,
                width * .16f, width * .16f);

        // Character Icon

//        Image portrait = unit.getIcon().copy();
//        portrait.draw(x + width * .04f, y + width * .04f, width * .12f, width * .12f);
//
//        g.setColor(new Color(60, 60, 60, 255));
//        g.drawRect(x + width * .04f-Main.getGameScale(), y + width * .04f-Main.getGameScale(),
//                width * .12f+Main.getGameScale()*2, width * .12f+Main.getGameScale()*2);


    }

    @Override
    public void giveReward()
    {
        unit.addPerk(perk);
//        unit.getTeam().getAlgorithm().getDisplay().begin();       // updates the display

        RewardOverlay.end();

    }
}
