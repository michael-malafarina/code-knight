package ui;

import core.Main;
import org.newdawn.slick.TrueTypeFont;

import java.awt.*;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;

public class Fonts
{
	public static TrueTypeFont gargantuanFont;
	public static TrueTypeFont colossalFont;
	public static TrueTypeFont massiveFont;
	public static TrueTypeFont hugeFont;
	public static TrueTypeFont bigFont;
	public static TrueTypeFont mediumFont;
	public static TrueTypeFont smallFont;
	public static TrueTypeFont tinyFont;

	public static TrueTypeFont gargantuanFontMono;
	public static TrueTypeFont colossalFontMono;
	public static TrueTypeFont massiveFontMono;
	public static TrueTypeFont hugeFontMono;
	public static TrueTypeFont bigFontMono;
	public static TrueTypeFont mediumFontMono;
	public static TrueTypeFont smallFontMono;
	public static TrueTypeFont tinyFontMono;


	private static TrueTypeFont gargantuanFont1440;
	private static TrueTypeFont colossalFont1440;
	private static TrueTypeFont massiveFont1440;
	private static TrueTypeFont hugeFont1440;
	private static TrueTypeFont bigFont1440;
	private static TrueTypeFont mediumFont1440;
	private static TrueTypeFont smallFont1440;
	private static TrueTypeFont tinyFont1440;

	private static TrueTypeFont gargantuanFontMono1440;
	private static TrueTypeFont colossalFontMono1440;
	private static TrueTypeFont massiveFontMono1440;
	private static TrueTypeFont hugeFontMono1440;
	private static TrueTypeFont bigFontMono1440;
	private static TrueTypeFont mediumFontMono1440;
	private static TrueTypeFont smallFontMono1440;
	private static TrueTypeFont tinyFontMono1440;

	private static TrueTypeFont gargantuanFont1080;
	private static TrueTypeFont colossalFont1080;
	private static TrueTypeFont massiveFont1080;
	private static TrueTypeFont hugeFont1080;
	private static TrueTypeFont bigFont1080;
	private static TrueTypeFont mediumFont1080;
	private static TrueTypeFont smallFont1080;
	private static TrueTypeFont tinyFont1080;

	private static TrueTypeFont gargantuanFontMono1080;
	private static TrueTypeFont colossalFontMono1080;
	private static TrueTypeFont massiveFontMono1080;
	private static TrueTypeFont hugeFontMono1080;
	private static TrueTypeFont bigFontMono1080;
	private static TrueTypeFont mediumFontMono1080;
	private static TrueTypeFont smallFontMono1080;
	private static TrueTypeFont tinyFontMono1080;


	private static TrueTypeFont gargantuanFont800;
	private static TrueTypeFont colossalFont800;
	private static TrueTypeFont massiveFont800;
	private static TrueTypeFont hugeFont800;
	private static TrueTypeFont bigFont800;
	private static TrueTypeFont mediumFont800;
	private static TrueTypeFont smallFont800;
	private static TrueTypeFont tinyFont800;

	private static TrueTypeFont gargantuanFontMono800;
	private static TrueTypeFont colossalFontMono800;
	private static TrueTypeFont massiveFontMono800;
	private static TrueTypeFont hugeFontMono800;
	private static TrueTypeFont bigFontMono800;
	private static TrueTypeFont mediumFontMono800;
	private static TrueTypeFont smallFontMono800;
	private static TrueTypeFont tinyFontMono800;

	public static void loadFonts()
	{
		if(Main.getScreenWidth() > 1920)
		{
			loadFonts1440();
		}
		else if(Main.getScreenWidth() == 1920)
		{
			loadFonts1080();
		}
		else
		{
			loadFonts800();
		}
	}

	public static void loadFonts1440()
	{
		tinyFont1440 = new TrueTypeFont(createFont("voltaire", Font.PLAIN,  15), true);
		smallFont1440 = new TrueTypeFont(createFont("voltaire", Font.PLAIN,  18), true);
		mediumFont1440 = new TrueTypeFont(createFont("voltaire", Font.PLAIN, 22), true);
		bigFont1440 = new TrueTypeFont(createFont("voltaire", Font.PLAIN, 26), true);
		hugeFont1440 = new TrueTypeFont(createFont("voltaire", Font.BOLD,  32), true);
		massiveFont1440 = new TrueTypeFont(createFont("voltaire", Font.BOLD, 38), true);
		colossalFont1440 = new TrueTypeFont(createFont("voltaire", Font.BOLD, 44), true);
		gargantuanFont1440 = new TrueTypeFont(createFont("voltaire", Font.BOLD, 50), true);

		tinyFontMono1440 = new TrueTypeFont(createFont("roboto_mono", Font.PLAIN,  14), true);
		smallFontMono1440 = new TrueTypeFont(createFont("roboto_mono", Font.PLAIN,  17), true);
		mediumFontMono1440 = new TrueTypeFont(createFont("roboto_mono", Font.PLAIN, 19), true);
		bigFontMono1440 = new TrueTypeFont(createFont("roboto_mono", Font.PLAIN, 28), true);
		hugeFontMono1440 = new TrueTypeFont(createFont("roboto_mono", Font.BOLD,  32), true);
		massiveFontMono1440 = new TrueTypeFont(createFont("roboto_mono", Font.BOLD, 42), true);
		colossalFontMono1440 = new TrueTypeFont(createFont("roboto_mono", Font.BOLD, 48), true);
		gargantuanFontMono1440 = new TrueTypeFont(createFont("roboto_mono", Font.BOLD, 64), true);

		gargantuanFontMono = Fonts.gargantuanFontMono1440;
		colossalFontMono = Fonts.colossalFontMono1440;
		massiveFontMono = Fonts.massiveFontMono1440;
		hugeFontMono = Fonts.hugeFontMono1440;
		bigFontMono = Fonts.bigFontMono1440;
		mediumFontMono = Fonts.mediumFontMono1440;
		smallFontMono = Fonts.smallFontMono1440;
		tinyFontMono = Fonts.tinyFontMono1440;

		gargantuanFont = Fonts.gargantuanFont1440;
		colossalFont = Fonts.colossalFont1440;
		massiveFont = Fonts.massiveFont1440;
		hugeFont = Fonts.hugeFont1440;
		bigFont = Fonts.bigFont1440;
		mediumFont = Fonts.mediumFont1440;
		smallFont = Fonts.smallFont1440;
		tinyFont = Fonts.tinyFont1440;
	}

	public static void loadFonts1080()
	{
		tinyFont1080 = new TrueTypeFont(createFont("voltaire", Font.PLAIN,  12), true);
		smallFont1080 = new TrueTypeFont(createFont("voltaire", Font.PLAIN,  15), true);
		mediumFont1080 = new TrueTypeFont(createFont("voltaire", Font.PLAIN, 18), true);
		bigFont1080 = new TrueTypeFont(createFont("voltaire", Font.PLAIN, 22), true);
		hugeFont1080 = new TrueTypeFont(createFont("voltaire", Font.BOLD,  28), true);
		massiveFont1080 = new TrueTypeFont(createFont("voltaire", Font.BOLD, 34), true);
		colossalFont1080 = new TrueTypeFont(createFont("voltaire", Font.BOLD, 37), true);
		gargantuanFont1080 = new TrueTypeFont(createFont("voltaire", Font.BOLD, 44), true);

		tinyFontMono1080 = new TrueTypeFont(createFont("roboto_mono", Font.PLAIN,  12), true);
		smallFontMono1080 = new TrueTypeFont(createFont("roboto_mono", Font.PLAIN,  14), true);
		mediumFontMono1080 = new TrueTypeFont(createFont("roboto_mono", Font.PLAIN, 17), true);
		bigFontMono1080 = new TrueTypeFont(createFont("roboto_mono", Font.PLAIN, 22), true);
		hugeFontMono1080 = new TrueTypeFont(createFont("roboto_mono", Font.BOLD,  28), true);
		massiveFontMono1080 = new TrueTypeFont(createFont("roboto_mono", Font.BOLD, 34), true);
		colossalFontMono1080 = new TrueTypeFont(createFont("roboto_mono", Font.BOLD, 37), true);
		gargantuanFontMono1080 = new TrueTypeFont(createFont("roboto_mono", Font.BOLD, 44), true);

		gargantuanFontMono = Fonts.gargantuanFontMono1080;
		colossalFontMono = Fonts.colossalFontMono1080;
		massiveFontMono = Fonts.massiveFontMono1080;
		hugeFontMono = Fonts.hugeFontMono1080;
		bigFontMono = Fonts.bigFontMono1080;
		mediumFontMono = Fonts.mediumFontMono1080;
		smallFontMono = Fonts.smallFontMono1080;
		tinyFontMono = Fonts.tinyFontMono1080;

		gargantuanFont = Fonts.gargantuanFont1080;
		colossalFont = Fonts.colossalFont1080;
		massiveFont = Fonts.massiveFont1080;
		hugeFont = Fonts.hugeFont1080;
		bigFont = Fonts.bigFont1080;
		mediumFont = Fonts.mediumFont1080;
		smallFont = Fonts.smallFont1080;
		tinyFont = Fonts.tinyFont1080;

	}

	public static void loadFonts800()
	{
		tinyFont800 = new TrueTypeFont(createFont("voltaire", Font.PLAIN,  12), true);
		smallFont800 = new TrueTypeFont(createFont("voltaire", Font.PLAIN,  14), true);
		mediumFont800 = new TrueTypeFont(createFont("voltaire", Font.PLAIN, 16), true);
		bigFont800 = new TrueTypeFont(createFont("voltaire", Font.PLAIN, 20), true);
		hugeFont800 = new TrueTypeFont(createFont("voltaire", Font.BOLD,  24), true);
		massiveFont800 = new TrueTypeFont(createFont("voltaire", Font.BOLD, 30), true);
		colossalFont800 = new TrueTypeFont(createFont("voltaire", Font.BOLD, 35), true);
		gargantuanFont800 = new TrueTypeFont(createFont("voltaire", Font.BOLD, 40), true);

		tinyFontMono800 = new TrueTypeFont(createFont("roboto_mono", Font.PLAIN,  12), true);
		smallFontMono800= new TrueTypeFont(createFont("roboto_mono", Font.PLAIN,  14), true);
		mediumFontMono800 = new TrueTypeFont(createFont("roboto_mono", Font.PLAIN, 16), true);
		bigFontMono800= new TrueTypeFont(createFont("roboto_mono", Font.PLAIN, 20), true);
		hugeFontMono800 = new TrueTypeFont(createFont("roboto_mono", Font.BOLD,  24), true);
		massiveFontMono800 = new TrueTypeFont(createFont("roboto_mono", Font.BOLD, 30), true);
		colossalFontMono800 = new TrueTypeFont(createFont("roboto_mono", Font.BOLD, 35), true);
		gargantuanFontMono800 = new TrueTypeFont(createFont("roboto_mono", Font.BOLD, 40), true);

		gargantuanFontMono = Fonts.gargantuanFontMono800;
		colossalFontMono = Fonts.colossalFontMono800;
		massiveFontMono = Fonts.massiveFontMono800;
		hugeFontMono = Fonts.hugeFontMono800;
		bigFontMono = Fonts.bigFontMono800;
		mediumFontMono = Fonts.mediumFontMono800;
		smallFontMono = Fonts.smallFontMono800;
		tinyFontMono = Fonts.tinyFontMono800;

		gargantuanFont = Fonts.gargantuanFont800;
		colossalFont = Fonts.colossalFont800;
		massiveFont = Fonts.massiveFont800;
		hugeFont = Fonts.hugeFont800;
		bigFont = Fonts.bigFont800;
		mediumFont = Fonts.mediumFont800;
		smallFont = Fonts.smallFont800;
		tinyFont = Fonts.tinyFont800;

	}

	public static Font createFont(String filename, int style, float size)
	{
		try
		{
			String path = "res/fonts/" + filename + ".ttf";
			InputStream myStream = new BufferedInputStream(new FileInputStream(path));
			Font customFont = Font.createFont(Font.TRUETYPE_FONT, myStream);
			customFont = customFont.deriveFont(style, size);

//			Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File(filename)).deriveFont(size);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			//register the font
			ge.registerFont(customFont);
			return customFont;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
}



