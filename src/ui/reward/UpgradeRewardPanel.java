package ui.reward;

import unit.ability.action.Upgrade;
import core.Color;
import core.Main;
import core.Utility;
import org.newdawn.slick.Graphics;
import ui.Fonts;
import ui.Text;
import unit.hero.Hero;

import java.util.ArrayList;

public class UpgradeRewardPanel extends RewardPanel
{
    protected Upgrade upgrade;

    protected Hero unit;

    public UpgradeRewardPanel(RewardPanelSet owner, Hero unit, int index)
    {
        super(owner, index);

        this.unit = unit;

        width = (Main.getScreenWidth() * .66f) / owner.getNumRewardsUpper();
        height = width * .6f;
        float spacing = (Main.getScreenWidth() * .34f) / (owner.getNumRewardsUpper() + 1);

        x = (spacing * (index + 1)) + (width * index);
        y = Main.getScreenHeight() * .45f;


        bgColor = new Color(20, 20, 20, 245);
        maximumTextWidth = 32;
    }


    public Upgrade getRandomUpgrade(ArrayList<Upgrade> existingRewards)
    {
        ArrayList<Upgrade> upgradePool = unit.getUpgradePool();
        int randomIndex = Utility.random(upgradePool.size());
        Upgrade newUpgrade = upgradePool.get(randomIndex);


        // SET RARITY

//        newAction.setRarity(Rarity.getRandomRarityFromLevel(unit.getLevel()));

//        System.out.println("Reward Gen " + newAction + " " + newAction.getCurrentRarity());


        for(Upgrade a : existingRewards)
        {
            if(newUpgrade.equals(a))
            {
                return getRandomUpgrade(existingRewards);
            }
        }

        return newUpgrade;

    }

    public void setUpgrade(Upgrade a)
    {

        upgrade = a;
        name = a.getName();
        description = a.getDescription();

//        System.out.println("Reward Set " + action + " " + action.getCurrentRarity());


//        for(Condition c : action.getConditionInstances())
//        {
//            tooltips.add(new Tooltip(this, c.getName(), c.getDescription()));
//        }
    }



    protected void renderText(Graphics g)
    {
        // Name
        Text.alignCenter();
        Text.alignMiddle();
        Text.setColor(upgrade.getColor());
        Text.setFont(Fonts.hugeFont);
        Text.draw(name.toUpperCase(), x + width/2 + xPadding * .5f, y + height * .20f, maximumTextWidth);
        Text.setColor(new Color(255, 255, 255));

        // Amount
        Text.setFont(Fonts.gargantuanFont);
        Text.alignCenter();
        Text.alignMiddle();
        String amountText = "+"+upgrade.getAmount();
        Text.draw(amountText, x + width/2 + xPadding * .5f, y + height * .48f, maximumTextWidth);

        // Description
        Text.setColor(descColor);
        Text.setFont(Fonts.mediumFontMono);
        Text.alignCenter();
        Text.alignMiddle();
        Text.draw(description, x + width/2 + xPadding * .5f, y + height * .78f - Text.getHeight(description, maximumTextWidth)/2, maximumTextWidth);

    }

    @Override
    public void giveReward()
    {
        upgrade.applyUpgrade(unit);
//        unit.getAlgorithm().getDisplay().begin();       // updates the display
//        action.getUpgrade().applyUpgrade(unit);


        switch (unit.getLevel())
        {
            // Ability
            case 2, 5, 8:
                owner.beginActionReward(unit);
                break;

            // Code
            case 3, 6:
                owner.beginCodeReward(unit);
                break;

            // Subclass --> PLACEHOLDER
            case 4:
                owner.beginPerkReward(unit);
                break;

            // PERK --> PLACEHOLDER
            case 7, 9:
                owner.beginPerkReward(unit);
                break;
        }





    }
}
