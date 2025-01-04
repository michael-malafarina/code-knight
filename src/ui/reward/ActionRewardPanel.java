package ui.reward;

import unit.ability.action.Action;
import unit.ability.action.Rarity;
import core.Color;
import core.Main;
import core.Utility;
import unit.ability.conditions.Condition;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import ui.Fonts;
import ui.Images;
import ui.Text;
import ui.panel.Tooltip;
import unit.Unit;
import unit.hero.Hero;

import java.util.ArrayList;

public class ActionRewardPanel extends RewardPanel
{
    protected Action action;

    protected Hero unit;

    public ActionRewardPanel(RewardPanelSet owner, Hero unit, int index)
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
        maximumTextWidth = 32;
    }


    public Action getRandomAction(ArrayList<Action> existingRewards)
    {
        ArrayList<Class<? extends Action>> actionPool = unit.getActionPool();
        int randomIndex = Utility.random(actionPool.size());
        Class<? extends Action> clazz = actionPool.get(randomIndex);
        Action newAction = actionFactory(clazz, unit);

        // SET RARITY

        newAction.setRarity(Rarity.getRandomRarityFromLevel(unit.getLevel()));

//        System.out.println("Reward Gen " + newAction + " " + newAction.getCurrentRarity());


        for(Action a : existingRewards)
        {
            if(newAction.getClass().equals(a.getClass()))
            {
                return getRandomAction(existingRewards);
            }
        }

        return newAction;

    }

    public void setAction(Action a)
    {

        action = a;
        name = a.getName();
        description = a.getDescription();

//        System.out.println("Reward Set " + action + " " + action.getCurrentRarity());


        for(Condition c : action.getConditionInstances())
        {
            tooltips.add(new Tooltip(this, c.getName(), c.getDescription()));
        }
    }


    public Action actionFactory(Object o, Unit unit)
    {
        Class<? extends Action> clazz = (Class<? extends Action>) o;

        Action a = null;

        try
        {
            // When I create a new condition, set its duration to the actual duration after modifiers
            a = clazz.newInstance();
            a.linkUnit(unit);
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
        Text.setColor(action.getRarity().getColor());

        Text.setFont(nameFont);
        Text.draw(name, x + width/2 + xPadding * .5f, y + width * .10f, maximumTextWidth);

        Text.setColor(new Color(125, 125, 125));
        Text.setFont(Fonts.mediumFontMono);

        Text.alignMiddle();
        String delayText = action.getSpeed().getDescription() + " (" + action.getSpeed().getValue() + " speed)";

        Text.draw(delayText, x + width/2 + xPadding * .5f, y + height * .32f, maximumTextWidth);
        Text.setColor(descColor);
        Text.setFont(descFont);

        Text.alignMiddle();
        Text.draw(description, x + width/2 + xPadding * .5f, y + height * .64f - Text.getHeight(description, maximumTextWidth)/2, maximumTextWidth);

//        Text.setFont(Fonts.mediumFont)

//        g.setColor(bgColor);
//        g.fillRect(x + width/2 + xPadding * .5f - width*.25f, y + height - height * .125f, width * .5f, height * .25f);
//        g.setColor(borderColorDefault);
//        g.drawRect(x + width/2 + xPadding * .5f - width*.25f, y + height - height * .125f, width * .5f, height * .25f);
//
//        Text.setFont(Fonts.smallFontMono);
//        Text.setColor(new Color(125, 125, 125));
//        Text.alignMiddle();
//        Text.draw("Passive Bonus", x + width/2 + xPadding * .5f, y + height * .94f, maximumTextWidth);
//
//
//        Text.setFont(Fonts.mediumFontMono);
//        Text.setColor(new Color(200, 255, 200));
//        Text.alignMiddle();
//        Text.draw("+" +  action.getUpgrade().getAmount() + " " + action.getUpgrade(), x + width/2 + xPadding * .5f, y + height * 1.04f, maximumTextWidth);

        // Type Icon

        Image type = action.getIcon().copy();
        type.setFilter(Image.FILTER_NEAREST);
        type.draw(x + width * .04f, y + width * .04f, width * .12f, width * .12f);

        g.setColor(new Color(60, 60, 60, 255));
        g.drawRect(x + width * .04f-Main.getGameScale(), y + width * .04f-Main.getGameScale(),
                width * .12f+Main.getGameScale()*2, width * .12f+Main.getGameScale()*2);

        // Energy Diamond

        if(action.getManaCost() == 0)
        {
            return;
        }

        Image diamond = Images.manaBoxBlue;
        diamond.setFilter(Image.FILTER_NEAREST);
        Images.manaBoxBlue.draw(x + width * .86f, y + width * .04f, width * .12f, width * .12f);

        Text.alignMiddle();
        Text.alignCenter();
        Text.shadowOn();
        Text.setColor(nameColor);
        Text.setFont(Fonts.bigFontMono);
        Text.draw(action.getManaCost() + "", getX() + width * .92f, getY() + width * .10f);
        Text.shadowOff();
    }

    @Override
    public void giveReward()
    {
        unit.replaceAction(action);
        unit.getAlgorithm().getDisplay().begin();       // updates the display
//        action.getUpgrade().applyUpgrade(unit);

        RewardOverlay.end();

    }
}
