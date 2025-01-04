package states.map;

import core.Main;
import ui.Images;
import ui.reward.RewardOverlay;

public class RecruitNode extends Node
{
    public RecruitNode(NodeSet owner, int x, int y)
    {
        super(owner, x, y);

        name = "RECRUIT";
        description = ""+stage;
        icon = Images.mapRecruitColor.copy();
        iconGray = Images.mapRecruitGray.copy();

    }


    @Override
    public void triggerEvent()
    {
        owner.setCurrentNode(this);
        RewardOverlay.begin();
        Map.setLoadState(Main.RECRUIT_ID);
    }
}
