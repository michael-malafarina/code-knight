package ui.message;

import core.Color;
import org.newdawn.slick.Graphics;

abstract public class Message
{
    private final int duration;
    private int timeLeft;
    protected String message;
    protected Color color;

    public Message(String message)
    {
        this(message, 0, Color.white);
    }

    public Message(String message, int duration)
    {
        this(message, duration, Color.white);
    }

    public Message(String message, int duration, Color color)
    {
        this.message = message;
        timeLeft = duration;
        this.duration = duration;
        this.color = new Color(color.r, color.g, color.b);
    }

    public void update()
    {
        if(!isDone())
        {
            timeLeft--;
        }
    }

    public boolean isDone()
    {
        return timeLeft == 0;
    }

    public float percentRemaining()
    {
        return (float) timeLeft / (float) duration;
    }

    public float getTimeLeft()
    {
        return timeLeft;
    }


    abstract public void render(Graphics g);
}