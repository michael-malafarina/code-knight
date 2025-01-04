package battlefield;

import core.Main;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import states.combat.CombatHUD;
import ui.Images;

import java.util.ArrayList;

public class Battlefield
{
    private static Image background;
    protected static Row heroRow;
    protected static Row enemyRow;

    public static void init()
    {
        background = Images.backgroundDungeon;
        heroRow = new Row();
        enemyRow = new Row();
    }

    public static Cell getCell(Row row, int position)
    {
        return row.getCell(position);
    }

    public static Row getHeroRow()
    {
        return heroRow;
    }

    public static Row getEnemyRow()
    {
        return enemyRow;
    }

    public static ArrayList<Cell> getCells()
    {
        ArrayList<Cell> cells = new ArrayList<>();
        cells.addAll(heroRow.getCells());
        cells.addAll(enemyRow.getCells());
        return cells;
    }

    public static void render(Graphics g)
    {
        background.draw(0, 0, Main.getScreenWidth(), Main.getScreenHeight());
        CombatHUD.renderCells(g);
    }
}
