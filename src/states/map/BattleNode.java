package states.map;

import core.Main;
import ui.Images;

import states.map.*;

public class BattleNode extends Node
{
     public BattleNode(NodeSet owner, int x, int y)
    {
        super(owner, x, y);

        name = "BATTLE";
        description = ""+stage;
        icon = Images.mapBattleColor.copy();
        iconGray = Images.mapBattleGray.copy();


    }


//    public void setRandomAction()
//    {
//        ArrayList<Class<? extends Action>> actionPool = unit.getActionPool();
//        int randomIndex = Utility.random(actionPool.size());
//        Class<? extends Action> clazz = actionPool.get(randomIndex);
//        action = actionFactory(clazz, unit);
//        name = action.getName();
//        description = action.getDescription();
//    }


//    public Action actionFactory(Object o, Unit unit)
//    {
//        Class<? extends Action> clazz = (Class<? extends Action>) o;
//
//        Action a = null;
//
//        try
//        {
//            // When I create a new condition, set its duration to the actual duration after modifiers
//            a = clazz.newInstance();
//            a.linkUnit(unit);
//        }
//        catch (InstantiationException | IllegalAccessException e)
//        {
//            e.printStackTrace();
//        }
//
//        return a;
//    }

//    protected void renderText(Graphics g)
//    {
//        Text.alignCenter();
//        Text.alignMiddle();
//        Text.setColor(nameColor);
//        Text.setFont(nameFont);
//        Text.draw(name, x + width/2 + xPadding * .5f, y + height * .15f, maximumTextWidth);
//
//        Text.setColor(descColor);
//        Text.setFont(descFont);
//
//        Text.alignMiddle();
//        Text.draw(description, x + width/2 + xPadding * .5f, y + height * .5f, maximumTextWidth);
//    }

    @Override
    public void triggerEvent()
    {
        owner.setCurrentNode(this);
        Map.setLoadState(Main.COMBAT_ID);
    }
}
