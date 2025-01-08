package ui.reward;

import unit.ability.action.Action;
import unit.ability.action.Rarity;
import core.Utility;
import unit.hero.Hero;

import java.util.ArrayList;

public class CodeRewardPanel extends ActionRewardPanel
{
    public CodeRewardPanel(RewardPanelSet owner, Hero unit, int index)
    {
        super(owner, unit, index);
    }

    public Action getRandomAction(ArrayList<Action> existingRewards)
    {
        ArrayList<Class<? extends Action>> codePool = unit.getCodePool();
        int randomIndex = Utility.random(codePool.size());
        Class<? extends Action> clazz = codePool.get(randomIndex);
        Action newAction = actionFactory(clazz, unit);

        // SET RARITY

        newAction.setRarity(Rarity.COMMON);

        for(Action a : existingRewards)
        {
            if(newAction.getClass().equals(a.getClass()))
            {
                return getRandomAction(existingRewards);
            }
        }

        return newAction;

    }

    public void giveReward()
    {
//        unit.addAction(action);
//        unit.getAlgorithm().getDisplay().begin();       // updates the display
//        action.getUpgrade().applyUpgrade(unit);
        RewardOverlay.end();

    }
}
