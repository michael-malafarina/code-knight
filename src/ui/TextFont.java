// Text Font Wrapper class
// This class wraps over UnicodeFont and allows for using varying font sizes without creating a new Font object
// This is done by creating the font initally at 256px
// Then using Graphics.scale the outputted text is scaled down to the font size the user requests
// By Frankie A

package ui;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;

public class TextFont
{
    private static final int DEFAULT_FONT_SIZE = 256;
    private UnicodeFont baseFont;

    public TextFont(TextFont font)
    {
        this.baseFont = font.baseFont;
    }

    private TextFont(UnicodeFont font)
    {
        this.baseFont = font;
    }

    public static TextFont fromTTF(String filepath, boolean bold, boolean italic) throws SlickException
    {
        UnicodeFont font = new UnicodeFont(filepath, DEFAULT_FONT_SIZE, bold, italic);
        font.getEffects().add(new ColorEffect(java.awt.Color.white));
        font.addAsciiGlyphs();
        font.loadGlyphs();

        return new TextFont(font);
    }

    public void drawString(Graphics g, String text, float x, float y)
    {
        g.setFont(this.baseFont);
        g.drawString(text, x, y);
        g.resetFont();
    }

    public void drawString(Graphics g, String text, float x, float y, float size)
    {
        float scale = size / DEFAULT_FONT_SIZE;

        g.setFont(this.baseFont);
        g.scale(scale, scale);
        g.drawString(text, x / scale, y / scale);
        g.resetTransform();
        g.resetFont();
    }

    public void drawStringCentered(Graphics g, String text, float x, float y)
    {
        float halfWidth = this.baseFont.getWidth(text) / 2f;
        float halfHeight = this.baseFont.getHeight(text) / 2f;

        g.setFont(this.baseFont);
        g.drawString(text, x - halfWidth, y - halfHeight);
        g.resetFont();
    }

    public void drawStringCentered(Graphics g, String text, float x, float y, float size)
    {
        float scale = size / DEFAULT_FONT_SIZE;

        float halfWidth = (this.baseFont.getWidth(text) * scale) / 2f;
        float halfHeight = (this.baseFont.getHeight(text) * scale) / 2f;

        g.setFont(this.baseFont);
        g.scale(scale, scale);
        g.drawString(text, (x - halfWidth) / scale, (y - halfHeight) / scale);
        g.resetTransform();
        g.resetFont();
    }
}