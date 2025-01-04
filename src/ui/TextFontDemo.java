//// This file just represents a basic demo for how to use it instead of the full wrapper class
//// This code won't run it is just to give an idea
//import org.newdawn.slick.Graphics;
//import org.newdawn.slick.SlickException;
//import org.newdawn.slick.UnicodeFont;
//import org.newdawn.slick.font.effects.ColorEffect;
//
//// UnicodeFont does not suffer from the same font size bug as TrueTypeFonts do. You can scale the font up to any font size
//// and UnicodeFont will handle it.
//int fontSize = 32;
//
//// UnicodeFonts do not use fonts already installed on the system like using java.awt.Font would
//// UnicodeFont instead takes a filepath to either a TTF or OTF file
//
//// The last two parameters in the UnicodeFont constructor replace the use of java.awt.Font style constants (REGULAR, BOLD, ITALIC)
//// Instead if we want a bold font we set the `bold` parameter to true and likewise for italics.
//boolean bold = false;
//boolean italic = false;
//UnicodeFont font = new UnicodeFont("path/to/fontfile.ttf", fontSize, bold, italic);
//
//// UnicodeFonts do require 3 extra steps after creating the Font Object
//
//// Honestly I haven't done my research into ColorEffect. I assume it's just a tint over the font. However below I will show a better
//// way to change your font color. As far as I know you can just add the White color effect
//font.getEffects.add(new ColorEffect(java.awt.Color.white));
//
//// Next is our last 2 steps which is loading in the glyphs
//        font.addAsciiGlyphs();
//font.loadGlyphs();
//
//// Now we have a font. UnicodeFont, just like TrueTypeFont, implements Slick2D's Font interface hence we can use it in the exact
//// same way
//
//g.setFont(font);
//g.drawString(...);
//g.resetFont();
//
//// If we want to change the color, we can just use g.setColor
//g.setColor(...);
//
//// Just like TrueTypeFonts, once created you cannot change the size of the font. However because we can create much larger fonts
//// A good approach would be to create a large font, and then scale it down. This is how the `TextFont` class provided is implemented
//
//g.setFont(font);
//g.scale(...);
//g.drawString(...);
//g.resetTransformation();
//g.resetFont();