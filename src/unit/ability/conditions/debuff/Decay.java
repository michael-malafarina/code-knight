package unit.ability.conditions.debuff;

import unit.ability.action.Tag;
import unit.ability.conditions.Debuff;
import ui.Images;

public class Decay extends Debuff
{
    public Decay()
    {
        name = "Decay";
        icon = Images.conditionDecay;
        setColor(100, 70, 100);
        healingBonus = -1;
        tags.add(Tag.DEBUFF);
    }

//    public void onHealingRecieved()
//    {
//        removeStack();
//    }

    public String getDescription()
    {
        return "Decrease HP gained [HEAL]Healing[] by " + getStacks() + " until the end of combat.";
    }

    public void sound()
    {

    }

    public void animation()
    {

    }


}
