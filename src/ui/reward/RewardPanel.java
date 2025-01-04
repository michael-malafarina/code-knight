package ui.reward;

import org.newdawn.slick.Graphics;
import ui.Text;
import ui.panel.ButtonEvent;
import ui.panel.Panel;

public abstract class RewardPanel extends Panel
{
    abstract public void giveReward();
    protected int index;
    protected RewardPanelSet owner;

    RewardPanel(RewardPanelSet owner, int index)
    {
        this.owner = owner;
        this.index = index;
        setClickEvent(this::giveReward);
    }

}
