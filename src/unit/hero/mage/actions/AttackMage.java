package unit.hero.mage.actions;

import unit.ability.action.*;
import ui.Images;
import ui.sound.Sounds;

public class AttackMage extends Action
{
    public void setup()
    {
        name = "Attack";
        setRarity(Rarity.STARTER);

        addTag(Tag.ATTACK);
        addTag(Tag.PHYSICAL);
        setAnimation(MovementType.RUSH);
    }

    public void rarity()
    {
        damage = scale(9);
    }

    public void setTarget()
    {
        targets.add(getFrontEnemy());
    }

    public void use()
    {
        damage(getTarget(), DamageType.PHYSICAL);
        applyConditions(getTarget());
    }

    public void sound()
    {
        Sounds.bashHeavy.play();
    }

    public void animation()
    {
        animate(Images.animBlunt, getTarget());
        animate(Images.animSlashing, self());
    }

    public String getDescription()
    {
        return "Deal " + getDamageText() + " [PHYSICAL]Physical Damage[] to the front enemy.";
    }
}
