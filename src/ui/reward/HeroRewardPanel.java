package ui.reward;

import campaign.HeroManager;
import core.Color;
import core.Main;
import core.Utility;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import ui.Fonts;
import ui.Text;
import unit.Unit;
import unit.ability.action.Action;
import unit.ability.action.Rarity;
import unit.hero.*;
import unit.hero.cleric.Cleric;
import unit.hero.cleric.Dawnbringer;
import unit.hero.cleric.Oracle;
import unit.hero.cleric.Runepriest;
import unit.hero.knight.Knight;
import unit.hero.knight.Paladin;
import unit.hero.knight.ShadowKnight;
import unit.hero.mage.FireMage;
import unit.hero.mage.Mage;
import unit.hero.mage.StormMage;
import unit.hero.warrior.Vanguard;
import unit.hero.warrior.Warden;
import unit.hero.warrior.Warrior;

import java.util.ArrayList;

public class HeroRewardPanel extends RewardPanel
{
    private Hero hero;

    public HeroRewardPanel(RewardPanelSet owner, int index)
    {
        super(owner, index);

        width = (Main.getScreenWidth() * .66f) / owner.getNumRewardsUpper();
        height = width * .6f;
        float spacing = (Main.getScreenWidth() * .34f) / (owner.getNumRewardsUpper() + 1);

        x = (spacing * (index + 1)) + (width * index);
        y = Main.getScreenHeight() * .45f;

        nameFont = Fonts.massiveFont;
        descFont = Fonts.mediumFontMono;
        bgColor = new Color(20, 20, 20, 245);
        maximumTextWidth = 34;
    }

    public Hero getHero()
    {
        return hero;
    }

     public void setRandomUnit(ArrayList<Unit> previous)
    {
        int randomIndex = Utility.random(8);


        switch(randomIndex)
        {
            case 0: hero = new Dawnbringer(); break;
            case 1: hero = new Runepriest(); break;
            case 2: hero = new FireMage(); break;
            case 3: hero = new StormMage(); break;
            case 4: hero = new Warden(); break;
            case 5: hero = new Vanguard(); break;
            case 6: hero = new Paladin(); break;
            case 7: hero = new ShadowKnight(); break;
        }

        for(Unit u : previous)
        {
            if(hero.getClass().isInstance(u))
            {
                setRandomUnit(previous);
                return;
            }
        }

        for(Unit u : HeroManager.getUnits())
        {
            if(hero.getClass().isInstance(u))
            {
                setRandomUnit(previous);
                return;
            }
        }

        name = hero.getName();
        description = hero.getDescription();
    }


    protected void renderText(Graphics g)
    {
        // Actual Text
        Text.alignCenter();
        Text.alignMiddle();
        Text.setColor(nameColor);
        Text.setFont(nameFont);
        Text.draw(hero.getName().toUpperCase(), x + width * .6f + xPadding * .5f, y + width * .10f, maximumTextWidth);

        Image type = hero.getIcon().getFlippedCopy(true, false);
        type.setFilter(Image.FILTER_NEAREST);
        type.draw(x + width * .04f, y + width * .04f, width * .22f, width * .22f);

        g.setColor(new Color(60, 60, 60, 255));
        g.drawRect(x + width * .04f-Main.getGameScale(), y + width * .04f-Main.getGameScale(),
                width * .22f+Main.getGameScale()*2, width * .22f+Main.getGameScale()*2);

        Text.setColor(new Color(180, 180, 180));
        Text.setFont(Fonts.bigFont);
        Text.draw("Class " + hero.getSuperClassName(), x + width * .6f + xPadding * .5f, y + height * .37f, maximumTextWidth);

        Text.setColor(descColor);
        Text.setFont(descFont);
        Text.alignMiddle();
        Text.draw(description, x + width/2 + xPadding * .5f, y + height * .72f - Text.getHeight(description, maximumTextWidth)/2, maximumTextWidth);
//
////        Text.setFont(Fonts.mediumFont)
//
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
//
//        // Type Icon
//
//        Image type = unit.getIcon().copy();
//        type.setFilter(Image.FILTER_NEAREST);
//        type.draw(x + width * .04f, y + width * .04f, width * .12f, width * .12f);
//
//        g.setColor(new Color(60, 60, 60, 255));
//        g.drawRect(x + width * .04f-Main.getGameScale(), y + width * .04f-Main.getGameScale(),
//                width * .12f+Main.getGameScale()*2, width * .12f+Main.getGameScale()*2);
//
//        // Energy Diamond
//
//        Image diamond = Images.energyDiamondBlueEnergy;
//        diamond.setFilter(Image.FILTER_NEAREST);
//        Images.energyDiamondBlueEnergy.draw(x + width * .86f, y + width * .04f, width * .12f, width * .12f);
//
//        Text.alignMiddle();
//        Text.alignCenter();
//        Text.shadowOn();
//        Text.setColor(nameColor);
//        Text.setFont(Fonts.bigFontMono);
//        Text.draw(action.getEnergyCost() + "", getX() + width * .92f, getY() + width * .10f);
//        Text.shadowOff();
    }

    @Override
    public void giveReward()
    {

        HeroManager.addUnit(hero);
//        unit.addAction(action);
//        unit.getAlgorithm().getDisplay().begin();       // updates the display
//        action.getUpgrade().applyUpgrade(unit);

        RewardOverlay.end();

    }
}
