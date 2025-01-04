package unit.hero.knight.actions.shadowKnight;

import ui.Images;
import ui.sound.Sounds;
import unit.ability.action.*;
import unit.ability.conditions.debuff.Decay;

public class DarkStrike extends Action
{
    public void setup()
    {
        name = "Dark Strike";
        addTag(Tag.ATTACK);
        addTag(Tag.PHYSICAL);
        setAnimation(MovementType.RUSH);
        setUpgrade(Upgrade.STRENGTH);
    }

    public void rarity()
    {
        damage = scale(9) - 1;
        addCondition(Decay.class, 1);
    }

    public void setTarget()
    {
        targets.add(getFrontEnemy());
    }

    public void use()
    {
        damage(DamageType.PHYSICAL);
        applyConditions();
    }

    public void sound()
    {
        Sounds.slashMetal.play();
    }

    public void animation()
    {
        animate(Images.animSlash, getTarget());
        animate(Images.animSlashing, self());
    }

    public String getDescription()
    {
        return "Deal " + getDamageText() + " [PHYSICAL]Physical Damage[] and apply [Debuff]Decay[]" + getStacksText() + " to the front enemy.";
    }
}
