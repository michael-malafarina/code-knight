package unit.hero.knight.actions.shadowKnight;

import core.Color;
import ui.Images;
import ui.sound.Sounds;
import unit.ability.action.*;
import unit.ability.conditions.buff.Defense;

public class Blackwall extends Action
{
    public void setup()
    {
        name = "Blackwall";
        addTag(Tag.BLOCK);
        setAnimation(MovementType.BLOCK);
        setUpgrade(Upgrade.DEFENSE);
    }

    public void rarity()
    {
        mana = 1;
        addCondition(Defense.class, 1);
        block = scale(11)-5;
    }

    public void setTarget()
    {
        targets.add(self());
    }

    public void use()
    {
        damage(DamageType.SHADOW);
        block();
        applyConditions();
    }

    public void sound()
    {
        Sounds.armorUp.play();
    }

    public void animation()
    {
        animate(Images.animShield, self());
        animate(Images.animCastingGray, self(), new Color(255, 255, 0));
    }

    public String getDescription()
    {
        return "Gain " + getBlockText() + " [BLOCK]Block[] and add [BUFF]Defense[] " + getStacksText();
    }
}
