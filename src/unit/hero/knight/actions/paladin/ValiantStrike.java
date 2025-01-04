package unit.hero.knight.actions.paladin;

import unit.ability.action.*;
import ui.Images;
import ui.sound.Sounds;

public class ValiantStrike extends Action
{
    public void setup()
    {
        name = "Valiant Strike";
        addTag(Tag.ATTACK);
        addTag(Tag.PHYSICAL);
        setAnimation(MovementType.RUSH);
        setUpgrade(Upgrade.STRENGTH);
    }

    public void rarity()
    {
        damage = scale(9) - 2;
    }

    public void setTarget()
    {
        targets.add(getFrontEnemy());
    }

    public void use()
    {
        damage(getTarget(), DamageType.PHYSICAL);
        regainHealth(countEnemies(), self());
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
        return "Deal " + getDamageText() + " [PHYSICAL]Physical Damage[] to the front enemy and regain 1 HP for each enemy on the battlefield.";
    }
}
