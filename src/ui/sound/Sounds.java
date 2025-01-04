package ui.sound;

import core.Utility;
import org.newdawn.slick.SlickException;
import ui.sound.music.Song;
import ui.sound.sfx.SmartSound;

import java.util.ArrayList;

public class Sounds 
{

	// Abilities
	public static SmartSound armorUp;
	public static SmartSound grunt;

	public static SmartSound slashMetal;
	public static SmartSound slashCut;
	public static SmartSound slashLight;

	public static SmartSound fireballImpact;
	public static SmartSound fireballFlames;
	public static SmartSound bashHeavy;
	public static SmartSound combatStart;
	public static SmartSound combatWin;
	public static SmartSound heal;
	public static SmartSound teleport;
	public static SmartSound slow;
	public static SmartSound haste;
	public static SmartSound heroWarcry;

	public static SmartSound magicImpact;
	public static SmartSound arrow;
	public static SmartSound magicChime;
	public static SmartSound noMana;

	public static SmartSound buffHoly;
	public static SmartSound fireballHeavy;
	public static SmartSound iceballHeavy;
	public static SmartSound wind;

	public static SmartSound goblinHurt;
	public static SmartSound goblinYell;


	static ArrayList<Song> songs;

	public static void loadGameMusicFile(Song m) throws SlickException
	{
		m.loadMusic();
	}
	
	public static Song getRandomSong()
	{
		return songs.get(Utility.random(0, songs.size()-1));	
	}
	
	static void loadSFX() throws SlickException 
	{
		Sounds.arrow = new SmartSound("arrow");

		Sounds.armorUp = new SmartSound("armorUp");
		Sounds.bashHeavy = new SmartSound("bashHeavy");

		Sounds.buffHoly = new SmartSound("buffHoly");
		Sounds.combatStart = new SmartSound("startCombat");
		Sounds.combatWin = new SmartSound("winTheme");
		Sounds.fireballFlames = new SmartSound("fireball");
		Sounds.fireballHeavy = new SmartSound("fireballHeavy");
		Sounds.fireballImpact = new SmartSound("firebolt");
		Sounds.grunt = new SmartSound("grunt");

		Sounds.haste = new SmartSound("haste");
//		Sounds.heroWarcry = new SmartSound("heroWarcry");

		Sounds.heal = new SmartSound("heal");
		Sounds.iceballHeavy = new SmartSound("iceballHeavy");

		Sounds.slashMetal = new SmartSound("slashMetal");
		Sounds.slashCut = new SmartSound("slashDeep");
		Sounds.slashLight = new SmartSound("slashLight");


		Sounds.teleport = new SmartSound("teleport");
		Sounds.slow = new SmartSound("slow");			
		Sounds.magicImpact = new SmartSound("magicImpact");
		Sounds.magicChime = new SmartSound("magicChime");
		Sounds.noMana = new SmartSound("noMana");
		Sounds.wind = new SmartSound("wind");

		Sounds.goblinHurt = new SmartSound("goblinHurt");
		Sounds.goblinYell = new SmartSound("goblinYell");
	}
	
	static void loadSongList() throws SlickException 
	{		
		String path = "res/music/";
		
		songs = new ArrayList<Song>();
		
		songs.add(new Song("???", "Driving Force", path + "drivingForce.wav"));		
		songs.add(new Song("???", "Ice And Fire", path + "iceAndFire.wav"));		
		songs.add(new Song("???", "Knight Power", path + "knightPower.wav"));
		songs.add(new Song("???", "Protection of Kingdom", path + "protectionOfKingdom.wav"));

	}


	
}
