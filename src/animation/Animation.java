package animation;

import battlefield.Cell;
import core.Color;
import core.Main;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;
import ui.Alignment;
import unit.Unit;

public class Animation
{
    protected int duration;
    protected float x;
    protected float y;
    protected float width;
    protected float height;
    protected Color color;

    protected int timer;
    protected int currentFrame;
    protected float imageScaling = 1;

    protected SpriteSheet sheet;
    protected Image imageBase;
    protected Image image;
    protected boolean looping;

    protected int frames;
    protected int frameRate;
    protected boolean flipImage;
    protected boolean paused;

    private static Alignment alignHorizontal;
    private static Alignment alignVertical;

    public boolean isAlignedLeft()
    {
        return alignHorizontal == Alignment.LEFT;
    }

    public boolean isAlignedCenter()
    {
        return alignHorizontal == Alignment.CENTER;
    }

    public boolean isAlignedRight()
    {
        return alignHorizontal == Alignment.RIGHT;
    }

    public boolean isAlignedTop()
    {
        return alignVertical == Alignment.TOP;
    }

    public boolean isAlignedMiddle()
    {
        return alignVertical == Alignment.MIDDLE;
    }

    public boolean isAlignedBottom()
    {
        return alignVertical == Alignment.BOTTOM;
    }

    public void alignLeft()
    {
        alignHorizontal = Alignment.LEFT;
    }

    public void alignCenter()
    {
        alignHorizontal = Alignment.CENTER;
    }

    public void alignRight()
    {
        alignHorizontal = Alignment.RIGHT;
    }

    public void alignTop()
    {
        alignVertical = Alignment.TOP;
    }

    public void alignMiddle()
    {
        alignVertical = Alignment.MIDDLE;
    }

    public void alignBottom()
    {
        alignVertical = Alignment.BOTTOM;
    }

    public Animation(AnimatedSpriteSheet sheet)
    {
        this(sheet, 0, 0);
    }

    public Animation(AnimatedSpriteSheet sheet, Cell c)
    {
        this(sheet, c.getX(), c.getY());
    }

    public Animation(AnimatedSpriteSheet sheet, float x, float y)
    {
        this.sheet = sheet;

        // Default values based on the sprite sheet itself
        this.duration = sheet.getDuration();
        this.frames = sheet.getFrames();
        this.frameRate = sheet.getFrameRate();
        this.looping = sheet.isLooping();
        this.x = x;
        this.y = y;
    }

    // Changes duration from default and modifies framerate
    public void updateDuration(int duration)
    {
        this.duration = duration;
        frameRate = duration / frames;
    }

    public void setHero()
    {
        flipImage = true;
    }

    public void setEnemyEffect()
    {
        flipImage = true;
    }

    public void setPosition(float x, float y)
    {
        this.x = x;
        this.y = y;
    }

    public void setPosition(Cell c)
    {
        if(c == null)
        {
            return;
        }
//        this.x = c.getX();
//        this.y = c.getY();

        this.x = c.getX() + c.getWidth()/2 - getWidth()/2;
        this.y = c.getY() + c.getHeight()/2 - getHeight()/2;
    }

    public void setPosition(Unit u)
    {
        if(u == null)
        {
            return;
        }

//        this.x = u.getX();
//        this.y = u.getY();

        this.x = u.getX() + u.getWidth()/2 - getWidth()/2;
        this.y = u.getY() + u.getHeight()/2 - getHeight()/2;
    }

    public void setImageScaling(float imageScaling)
    {
      this.imageScaling = imageScaling;
    }


    public Image getFirstImage()
    {
        return sheet.getSprite(0, 0);
    }

    public void setColor(Color color)
    {
        this.color = color;
    }

    public boolean isDone()
    {
        // Negative values represent an object that never goes away
        if (duration < 0)
        {
            return false;
        } else
        {
            return getTimer() >= duration;
        }

    }

    public int getTimer()
    {
        return timer;
    }

    public int getFrame()
    {
        return currentFrame;
    }

    public int getFrameRate()
    {
        return frameRate;
    }

    public int getFadeAlphaValue()
    {
        return (int) (255.0f * getPercentLeft());
    }

    public float getPercentLeft()
    {
        return 1 - ((float) getTimer()) / ((float) duration);
    }

    public float getPercentComplete()
    {
        return ((float) getTimer()) / ((float) duration);
    }

    public float getX()
    {
        return x;
    }

    public float getY()
    {
        return y;
    }

    public float getWidth()
    {
        if(image == null)
        {
            return 0;
        }
        return image.getWidth() * Main.getGameScale() * imageScaling;
    }

    public float getHeight()
    {
        if(image == null)
        {
            return 0;
        }
        return image.getHeight() * Main.getGameScale() * imageScaling;
    }


    public void update()
    {
        if(paused || Main.isPaused())
        {
            return;
        }

        timer++;

        // Advance frame
        if (frameRate > 0 && timer % frameRate == 0)
        {
            currentFrame++;
        }

        // Loop animation
        if (looping && currentFrame >= frames)
        {
            currentFrame = 0;
        }

        // Set baseImage and the current image
        if (sheet != null && currentFrame < frames)
        {
            imageBase = sheet.getSprite(currentFrame, 0);
            image = sheet.getSprite(currentFrame, 0);

            if (flipImage)
            {
                image = imageBase.getFlippedCopy(true, false);
            }
        } else
        {
            imageBase = null;
            image = null;
        }
    }

    public void render(Graphics g)
    {

//        System.out.println(image);
//        System.out.println(frames + " - " + getFrame() + " > " + frameRate);

        float xPos = x;
        float yPos = y;

        if (isAlignedCenter())
        {
            xPos += getWidth() / 2;
        }

        if (isAlignedRight())
        {
            xPos += getWidth();
        }

        if (isAlignedMiddle())
        {
            yPos += getHeight() / 2;
        }

        if (isAlignedCenter())
        {
            yPos += getHeight();
        }

        if (image != null)
        {
            if (color != null)
            {
                image.setImageColor(color.r, color.g, color.b);
            }
            image.draw(xPos, yPos, getWidth(), getHeight());
        }
//        else if (frames - getFrame() > frameRate)
//        {
//            g.drawString("NO IMAGE", xPos, yPos);
//        }
    }

    public void setPaused(boolean paused)
    {
        this.paused = paused;
    }




}
