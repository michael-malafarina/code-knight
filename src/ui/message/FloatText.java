package ui.message;


import core.Main;
import core.Utility;
import core.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.TrueTypeFont;
import ui.Fonts;
import ui.Text;
import unit.Unit;

public class FloatText extends Message
{
    public final static int DEFAULT_DURATION = 400;
    public final static float FADE_DELAY_PERCENT = .3f;

    private Unit owner;
    private float x;
    private float y;
    private TrueTypeFont myFont;
    private float ySpeed;
    private float xSpeed;


    private int value = -1;

    public FloatText(Unit u, String message)
    {
        this(u, message, DEFAULT_DURATION);
    }

    public FloatText(Unit u, String message, int duration)
    {
        this(u, message, duration, Color.white);
    }

    public FloatText(Unit u, String message, Color color)
    {
        this(u, message, DEFAULT_DURATION, color);
    }

    public FloatText(Unit u, String message, int duration, Color color)
    {
        super(message, duration, color);
        owner = u;
        myFont = Fonts.bigFont;
        float variance = Utility.random(-Main.getScreenWidth() * .025f, Main.getScreenWidth() * .025f);

        x = owner.getX() + owner.getWidth() * .5f + variance;
        y = owner.getY() + owner.getHeight() * .2f + variance;
        ySpeed = -.5f * Main.getGameScale();
        xSpeed = (float) (Math.random() * .05f * Main.getGameScale());
    }

    public FloatText(Unit u, int message)
    {
        this(u, message, Color.white);
    }

    public FloatText(Unit u, int message, Color color)
    {
        this(u, "" + message, color);
        value = message;
        setFontBasedOnValue();
    }

    private void setFontBasedOnValue()
    {
        if (value == -1)
        {
            return;
        }

        if (value >= 20)
        {
            myFont = Fonts.gargantuanFont;
        }
        else if (value >= 10)
        {
            myFont = Fonts.colossalFont;
        }
        else if (value >= 5)
        {
            myFont = Fonts.massiveFont;
        }
        else
        {
            myFont = Fonts.bigFont;
        }
    }

    public void update()
    {
        if(Main.isPaused())
        {
            return;
        }

        super.update();

        setFontBasedOnValue();


        x = x + xSpeed;
        y = y + ySpeed;
        ySpeed += ySpeed * -.005f;
    }

    public void render(Graphics g)
    {
        // Do not show messages if they are hidden
//		if(!Settings.showFloatTextUnit && owner instanceof Unit)
//		{
//			return;
//		}

        //g.setColor(color.);

        float fade = 1;

        if (percentRemaining() < 1 - FADE_DELAY_PERCENT)
        {
            fade = Math.max(percentRemaining() * (1 + FADE_DELAY_PERCENT), 0);
        }

//		System.out.println(fade);
        float alpha = (float) Math.pow(fade, 8);
        //System.out.println(message + " " + color);


        color.setAlpha(alpha);

        //color = Utility.modifyAlpha(color, alpha);

        Text.shadowOn();
        Text.shadowSize(3);
        Text.shadowAlpha(alpha);
        Text.setFont(myFont);
        Text.alignTop();
        Text.alignCenter();


        Text.setColor(color);
        Text.draw(message, x, y);
        Text.shadowOff();

        //Text.setColor(Utility.modifyAlpha(color,percentComplete()));

        //g.setColor(Utility.modifyAlpha(color,percentComplete()));

    }


}
