package ui.algorithm;

import campaign.EnemyManager;
import campaign.HeroManager;
import core.Main;
import org.newdawn.slick.Graphics;
import states.combat.Combat;
import unit.Unit;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

public class AlgorithmDisplaySet
{
    private ArrayList<AlgorithmDisplay> heroes;
    private ArrayList<AlgorithmDisplay> enemies;

    public AlgorithmDisplaySet()
    {
        heroes = new ArrayList<>();
        enemies = new ArrayList<>();
    }

    float spacing;
    float width;
    float padding;

    public AlgorithmDisplay getAlgorithmDisplay(Unit hero)
    {
        for (AlgorithmDisplay u : heroes)
        {
            if (u.getUnit() == hero)
            {
                return u;
            }
        }
        return null;
    }


    public void begin()
    {
        width = Main.getScreenWidth() * .37f / 4;

        heroes.clear();
        enemies.clear();

        for (int i = 0; i < HeroManager.getUnits().size(); i++)
        {
            Unit u = HeroManager.getUnits().get(i);
            heroes.add(new AlgorithmDisplay(u, this, width));
        }

        for (int i = 0; i < EnemyManager.getUnits().size(); i++)
        {
            Unit u = EnemyManager.getUnits().get(i);
            enemies.add(new AlgorithmDisplay(u, this, width));
        }

        for (AlgorithmDisplay a : heroes)
        {
            a.begin();
        }

        for (AlgorithmDisplay a : enemies)
        {
            a.begin();
        }
    }

    public boolean hasGrabbedActionPanel()
    {
        for (AlgorithmDisplay a : heroes)
        {
            if (a.hasGrabbedPanel())
            {
                return true;
            }
        }
        return false;
    }

    public ActionPanel getGrabbedActionPanel()
    {
        for (AlgorithmDisplay a : heroes)
        {
            if (a.hasGrabbedPanel())
            {
                return a.getGrabbedPanel();
            }
        }
        return null;
    }


    public void renderGrabbedPanel(Graphics g)
    {
        if (hasGrabbedActionPanel())
        {
            getGrabbedActionPanel().render(g);
        }
    }

    public void update()
    {
        Collections.sort(heroes);
        Collections.sort(enemies);

        int outerSpacingWeight = 9;
        int innerSpacingWeight = 5;
        int spaces = 3;
        int spacingWeight = outerSpacingWeight + innerSpacingWeight + spaces;

        spacing = Main.getScreenWidth() * .13f / spacingWeight;        // distribute chunks of spacing

        for (int i = 0; i < heroes.size(); i++)
        {
            float xPos = i * (width + spacing) + spacing * outerSpacingWeight;
            heroes.get(i).update(xPos);
        }
        for (int i = 0; i < enemies.size(); i++)
        {
            float xPos = i * (width + spacing) + spacing * innerSpacingWeight + Main.getScreenWidth() * .5f;
            enemies.get(i).update(xPos);
        }
    }

    public void render(Graphics g)
    {
//        g.drawRect(Main.getScreenWidth()/2, 0, 0, Main.getScreenHeight());

        for (AlgorithmDisplay a : heroes)
        {
            a.render(g);
        }

        for (AlgorithmDisplay a : enemies)
        {
            a.render(g);
        }

        for (AlgorithmDisplay a : heroes)
        {
            a.renderTooltip(g);
        }
        for (AlgorithmDisplay a : enemies)
        {
            a.renderTooltip(g);
        }
    }

    public void movingActionCards(int button, int x, int y)
    {
        for (AlgorithmDisplay a : heroes)
        {
            a.mousePressed(button, x, y);
        }

    }

    public void selectDeleteCard(Unit u)
    {
        for (AlgorithmDisplay a : heroes)
        {
            if (a.getUnit() == u)
            {
                a.selectDelete();
            }
        }

    }

}
