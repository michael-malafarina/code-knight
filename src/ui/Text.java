package ui;

import unit.ability.action.Tag;
import core.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.TrueTypeFont;

import java.util.ArrayList;

public class Text
{
    private static Graphics g;
    private static TrueTypeFont font;
    private static Alignment alignHorizontal;
    private static Alignment alignVertical;
    private static Color color;
    private static boolean hasShadow;
    private static int shadowSize;
    private static float shadowAlpha;

    //	private static ArrayList<TagParse> tags;

    public static void init(Graphics g)
    {
        Text.g = g;
        alignHorizontal = Alignment.LEFT;
        alignHorizontal = Alignment.TOP;
        font = Fonts.mediumFont;
        color = Color.white;
        shadowAlpha = 1;
    }

    public static void setColor(Color c)
    {
        Color newColor = new Color(c.r, c.g, c.b, c.a);
        g.setColor(newColor);
        color = newColor;
    }

    public static void setColor(MenuColor c)
    {
        setColor(c.getColor());
    }

    public static void setFont(TrueTypeFont f)
    {
        font = f;
        g.setFont(f);
    }

    public static boolean isAlignedLeft()
    {
        return alignHorizontal == Alignment.LEFT;
    }

    public static boolean isAlignedCenter()
    {
        return alignHorizontal == Alignment.CENTER;
    }

    public static boolean isAlignedRight()
    {
        return alignHorizontal == Alignment.RIGHT;
    }

    public static boolean isAlignedTop()
    {
        return alignVertical == Alignment.TOP;
    }

    public static boolean isAlignedMiddle()
    {
        return alignVertical == Alignment.MIDDLE;
    }

    public static boolean isAlignedBottom()
    {
        return alignVertical == Alignment.BOTTOM;
    }

    public static void alignLeft()
    {
        alignHorizontal = Alignment.LEFT;
    }

    public static void alignCenter()
    {
        alignHorizontal = Alignment.CENTER;
    }

    public static void alignRight()
    {
        alignHorizontal = Alignment.RIGHT;
    }

    public static void alignTop()
    {
        alignVertical = Alignment.TOP;
    }

    public static void alignMiddle()
    {
        alignVertical = Alignment.MIDDLE;
    }

    public static void alignBottom()
    {
        alignVertical = Alignment.BOTTOM;
    }

    public static void shadowOn()
    {
        hasShadow = true;
        shadowAlpha = 1;
    }

    public static void shadowOff()
    {
        hasShadow = false;
    }

    public static void shadowSize(int size)
    {
        shadowSize = size;
    }

    public static void shadowAlpha(float alpha)
    {
        shadowAlpha = alpha;
    }

    public void setAlignment(Alignment h, Alignment v)
    {
        alignHorizontal = h;
        alignVertical = v;
    }

    public static int getHeight()
    {
        return font.getHeight("0");
    }

    public static int getWidth(String str)
    {
        return font.getWidth(str);
    }

    public static int getWidth(TrueTypeFont f, String str)
    {
        return f.getWidth(str);
    }

    public static int getHeight(String str)
    {
        return font.getHeight(str);
    }

    public static int getHeight(TrueTypeFont f, String str)
    {
        return f.getHeight(str);
    }


    public static void draw(String text, float xPos, float yPos)
    {

        draw(text, xPos, yPos, Integer.MAX_VALUE);
        //draw(text, xPos, yPos, 45);

    }

    public static int countLines(String text, int maxWidth)
    {
        int widthCount = 0;
        int lines = 1;
        String simple = parseText(text).simpleText;

        for (int i = 0; i < simple.length(); i++)
        {
            widthCount++;
            String letter = simple.substring(i, i + 1);

            if (widthCount > maxWidth && letter.equals(" "))
            {
                lines++;
                widthCount = 0;
            }

        }

        return lines;
    }

    public static int getWidth(String text, int maxWidth)
    {
        return getWidth(font, text, maxWidth);
    }

    public static int getWidth(TrueTypeFont font, String text, int maxWidth)
    {
        if (text == null)
        {
            return 0;
        }

        int widthCount = 0;
        int widestPoint = 0;

        String simple = parseText(text).simpleText;

        for (int i = 0; i < simple.length(); i++)
        {
            widthCount++;
            String letter = simple.substring(i, i + 1);

            if (widestPoint < widthCount)
            {
                widestPoint = widthCount;
            }

            if (widthCount > maxWidth && letter.equals(" "))
            {

                widthCount = 0;
            }

        }

        return getWidth(font, simple.substring(0, widestPoint));
    }

    public static int getHeight(String text, int maxWidth)
    {
        return getHeight(text) * countLines(text, maxWidth);
    }

    public static int getHeight(TrueTypeFont font, String text, int maxWidth)
    {
        return getHeight(font, text) * countLines(text, maxWidth);
    }

    public static void draw(String text, float xPos, float yPos, int maxWidth)
    {
        int widthCount = 0;

        // Set font and colors.  Varies if outlined.
        TrueTypeFont currentFont = font;
        Color c = color;

        // Parse String Data
        ParsedString parsed = parseText(text);
        String simple = parsed.simpleText;
        ArrayList<TagParse> tags = parsed.tags;

        // Quit if empty after parse
        if (simple.length() == 0)
        {
            return;
        }

        // Data
        int x = (int) xPos;
        int y = (int) yPos;

        //System.out.println("|" + simple + "|");
        int width = (font.getWidth(simple));

        //System.out.println(simple + " width... " + width);
//
        //	System.out.println("text width at base: " + width);

        if (simple.length() > maxWidth)
        {
            width = getWidth(simple, maxWidth);
            //	System.out.println("text width reduced to: " + width);
        }

        int height = font.getHeight(simple);
        int runningWidth = 0;
        int xOff = 0;
        int yOff = 0;

        // Alignment
        if (isAlignedCenter())
        {
            xOff -= width * .5f;
        } else if (isAlignedRight())
        {
            xOff -= width;
        }

        if (isAlignedMiddle())
        {
            yOff -= height * .5f;
        } else if (isAlignedBottom())
        {
            yOff -= height;
        }

        // Loop through and draw each letter with color coding
        for (int i = 0; i < simple.length(); i++)
        {
            // Remove each tag and set color
            if (!tags.isEmpty() && i == tags.get(0).start)
            {
                c = colorCode(tags.get(0).code);
                tags.remove(0);
            }

            // Get the current letter
            String letter = simple.substring(i, i + 1);
            int letterWidth = font.getWidth(letter);


            // Draw shadow
            if (hasShadow)
            {
                int alpha = (int) (shadowAlpha * 255);
                g.setColor(new Color(0, 0, 0, alpha));
                g.drawString("" + letter, x + xOff + runningWidth + shadowSize, y + yOff + shadowSize);
            }

            // Draw normal text
            g.setColor(c);
            g.setFont(currentFont);
            g.drawString("" + letter, x + xOff + runningWidth, y + yOff);

            runningWidth += letterWidth;


            widthCount++;
            if (widthCount > maxWidth && letter.equals(" "))
            {
                runningWidth = 0;
                widthCount = 0;
                yOff += height;
            }


        }

    }


    public static ParsedString parseText(String text)
    {
        if (text == null)
        {
            return new ParsedString("", null);
        }
//		System.out.println("parsing");

        String simpleString = "";
        ArrayList<TagParse> tags = new ArrayList<TagParse>();

        for (int i = 0; i < text.length(); i++)
        {
            if (text.charAt(i) == '[')
            {
                int start = text.indexOf("[");
                int end = text.indexOf("]");

                String one = text.substring(0, start);
                String code = text.substring(start + 1, end);
                String two = text.substring(end + 1);

                TagParse t = new TagParse(start + simpleString.length(), code);
                tags.add(t);

                simpleString += one;
                text = two;
                i = 0;


            }
        }


        simpleString += text;


        return new ParsedString(simpleString, tags);
    }

    public static String simpleText(String text)
    {
        String simple = "";

        for (int i = 0; i < text.length(); i++)
        {
            if (text.charAt(i) == '[')
            {
                int start = text.indexOf("[");
                int end = text.indexOf("]");

                String one = text.substring(0, start);
                String two = text.substring(end + 1);

                simple += one;
                text = two;
                i = 0;
            }
        }

        System.out.println(simple);

        return simple;
    }


    public static Color colorCode(String code)
    {

        switch (code)
        {

            case "HEALTH":
                return Tag.HEALTH.getColor();
            case "MANA":
                return Tag.MANA.getColor();
            case "SPEED":
                return new Color(255, 185, 0);

            case "ATTACK":
                return Tag.ATTACK.getColor();
            case "HEAL":
                return Tag.HEAL.getColor();
            case "BLOCK":
                return Tag.BLOCK.getColor();
            case "BUFF":
                return Tag.BUFF.getColor();
            case "DEBUFF":
                return Tag.DEBUFF.getColor();
            case "CODE":
                return Tag.CODE.getColor();


            // DAMAGE TYPES
            case "PHYSICAL":
                return Tag.PHYSICAL.getColor();
            case "FORCE":
                return Tag.FORCE.getColor();
            case "RADIANT":
                return Tag.RADIANT.getColor();
            case "PSYCHIC":
                return Tag.PSYCHIC.getColor();
            case "SHADOW":
                return Tag.SHADOW.getColor();
            case "FIRE":
                return Tag.FIRE.getColor();
            case "COLD":
                return Tag.COLD.getColor();
            case "LIGHTNING":
                return Tag.LIGHTNING.getColor();


                // RECOVERY


            case "CLEANSE":
                return Tag.HEAL.getColor();


            case "KEY":
                return new Color(222,189,104);

            // CONDITIONS
            case "BLEED":
                return Tag.BLEED.getColor();
            case "BURNING":
                return Tag.BURNING.getColor();
            case "SHOCK":
                return Tag.SHOCK.getColor();
            case "POISON":
                return Tag.POISON.getColor();
            case "CHILL":
                return Tag.CHILL.getColor();

                // ATTRIBUTES
            case "HIGH":
                return MenuColor.HIGH.getColor();
            case "AVERAGE":
                return MenuColor.AVERAGE.getColor();
            case "LOW":
                return MenuColor.LOW.getColor();


            default:
                return new Color(255, 255, 255);
        }
    }


}


class ParsedString
{
    String simpleText;
    ArrayList<TagParse> tags;

    ParsedString(String simpleText, ArrayList<TagParse> tags)
    {
        this.simpleText = simpleText;
        this.tags = tags;
    }
}

class TagParse
{
    int start;
    String code;

    TagParse(int start, String code)
    {
        this.start = start;
        this.code = code;
    }
}


