package unit.hero.cleric.perks;

import unit.ability.action.Action;
import unit.ability.action.DamageType;
import unit.ability.perks.Perk;
import ui.Images;
import unit.Unit;

public class Shadowsight extends Perk
{
    float chance = .10f;

    private Action lastAction;

    public void setup()
    {
        name = "Shadowsight";
        icon = Images.perkShadowsight;
    }

    public void onActionUsed(Action action)
    {
        lastAction = action;
    }

    public void onDamageDealt(int amount, Unit target, DamageType type)
    {
        if(type == DamageType.SHADOW)
        {
            getOwner().gainMana(Math.round(lastAction.getManaCost() *.5f));
        }
    }

    public String getDescription()
    {
        return "When you deal [SHADOW]Shadow Damage[], refund half the mana cost (rounded up).";
    }

}
