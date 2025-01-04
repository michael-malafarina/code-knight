package ui;

import animation.AnimatedSpriteSheet;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Images 
{

	public static AnimatedSpriteSheet dawnbringer, runepriest;
	public static AnimatedSpriteSheet stormmage, firemage;
	public static AnimatedSpriteSheet shadowknight, paladin;
	public static AnimatedSpriteSheet warden, vanguard;

	public static AnimatedSpriteSheet skeleton;
	public static AnimatedSpriteSheet skeletonArcher;
	public static AnimatedSpriteSheet skeletonKnight;
	public static AnimatedSpriteSheet robotKnight;
	public static AnimatedSpriteSheet robotFireMage;
	public static AnimatedSpriteSheet robotColdMage;
	public static AnimatedSpriteSheet goblin;
	public static AnimatedSpriteSheet goblinWarrior;
	public static AnimatedSpriteSheet goblinRogue;
	public static AnimatedSpriteSheet goblinArcher;
	public static AnimatedSpriteSheet goblinShaman;
	public static AnimatedSpriteSheet goblinReaver;
	// Animations

	public static AnimatedSpriteSheet animSlash;
	public static AnimatedSpriteSheet animBlunt;
	public static AnimatedSpriteSheet animFlame;
	public static AnimatedSpriteSheet animHeal;
	public static AnimatedSpriteSheet animCold;
	public static AnimatedSpriteSheet animShield;
	public static AnimatedSpriteSheet animHoly;
	public static AnimatedSpriteSheet animCleanse;
	public static AnimatedSpriteSheet animTeleport;
	public static AnimatedSpriteSheet animDark;


	public static AnimatedSpriteSheet animSlashing;
	public static AnimatedSpriteSheet animSlashingGray;
	public static AnimatedSpriteSheet animCastingGray;

	// Backgrounds

	public static Image backgroundTitle;
	public static Image backgroundDungeon;
	public static Image backgroundDungeonMap;

	// Condition Icons
	public static Image conditionAim;
	public static Image conditionDefense;
	public static Image conditionAmbush;
	public static Image conditionBarrier;
	public static Image conditionBleed;
	public static Image conditionBlind;
	public static Image conditionBurn;
	public static Image conditionChill;
	public static Image conditionCritical;
	public static Image conditionDaze;
	public static Image conditionDecay;
	public static Image conditionEnergize;
	public static Image conditionEvade;
	public static Image conditionFear;
	public static Image conditionRage;
	public static Image conditionFireShield;
	public static Image conditionFrostShield;
	public static Image conditionFocus;
	public static Image conditionFreeze;
	public static Image conditionGlory;
	public static Image conditionHaste;
	public static Image conditionInvisible;
	public static Image conditionLightningShield;
	public static Image conditionMark;
	public static Image conditionMight;
	public static Image conditionPoison;
	public static Image conditionPower;
	public static Image conditionRegen;
	public static Image conditionRoot;
	public static Image conditionShock;
	public static Image conditionSleep;
	public static Image conditionSlow;
	public static Image conditionShattered;
	public static Image conditionStun;
	public static Image conditionStrength;
	public static Image conditionSpirit;
	public static Image conditionTaunt;
	public static Image conditionThorns;
	public static Image conditionVigor;
	public static Image conditionVulnerable;
	public static Image conditionWeak;

	// Ability Icons

	public static Image iconAttack;
	public static Image iconBlock;
	public static Image iconHeal;
	public static Image iconBuff;
	public static Image iconDebuff;
	public static Image iconCode;


	// Class icons

	public static Image iconShadowKnight, iconPaladin;
	public static Image iconVanguard, iconWarden;
	public static Image iconDawnbringer, iconRunepriest;
	public static Image iconFireMage, iconStormMage;

	public static Image mapBattleColor;
	public static Image mapRecruitColor;
	public static Image mapBattleGray;
	public static Image mapRecruitGray;
	public static Image tileGrass;

	// Perk icons

	// Enemy
	public static Image perkShifty;

	// Knight
	public static Image perkBloodAndIron;
	public static Image perkAuraOfCourage;
	public static Image perkDauntlessStrength;
	public static Image perkDeathward;
	public static Image perkShieldWall;
	public static Image perkGloriousRecovery;
	public static Image perkReactiveShield;
	public static Image perkShiningArmor;


	// Warrior
	public static Image perkArmorBreaker;
	public static Image perkBattlefrontLeader;
	public static Image perkBoundlessEndurance;
	public static Image perkDisarm;
	public static Image perkMomentum;
	public static Image perkOakheart;
	public static Image perkRegrowth;
	public static Image perkStrategist;
	public static Image perkVitalSurge;

	// Warrior
	public static Image perkGrace;
	public static Image perkMorninglord;
	public static Image perkPurity;
	public static Image perkRuneOfPower;
	public static Image perkShadowsight;
	public static Image perkWrath;

	// Mage

	public static Image perkChainLightning;
	public static Image perkConflagration;
	public static Image perkFlameMastery;
	public static Image perkFlarefrost;
	public static Image perkFrostMastery;
	public static Image perkPermafrost;
	public static Image perkMasterOfTheElements;
	public static Image perkPyromaniac;
	public static Image perkRimeshock;
	public static Image perkShatterShards;
	public static Image perkSparkfire;
	public static Image perkSpellstorm;
	public static Image perkStaggeringShock;
	public static Image perkLightningMastery;
	public static Image perkWildSurge;

	public static Image energyDiamondEmpty;
	public static Image manaBoxBlue;
	public static Image manaBoxBlueEmpty;
	public static Image manaBoxRed;
	public static Image manaBoxRedEmpty;


	public static Image uiManaRed;
	public static Image uiManaEmptyRed;
	public static Image uiMana;
	public static Image uiManaEmpty;
	public static Image uiSpeed;
	public static Image uiSpeedEmpty;
	public static Image uiActions;
	public static Image uiActionsEmpty;
	public static Image energyDiamondFull;
	public static Image energyDiamondNext;

	public static Image uiBlockShield;
	public static Image uiCell;

	public static void loadImages()
	{
		try 
		{
			loadBackground();
			loadUnits();
			loadAnimations();
			loadIcons();
			loadPerks();
			loadUI();

		} 
		catch (SlickException e) 
		{
			e.printStackTrace();
		}
	}
	
	public static void loadBackground() throws SlickException
	{
		backgroundTitle = new Image("res/backgrounds/title.png");
		backgroundDungeon = new Image("res/backgrounds/dungeon.png");
		backgroundDungeonMap = new Image("res/backgrounds/dungeonMap.png");
	}
	
	public static void loadUnits() throws SlickException
	{
		// Party
		shadowknight = new AnimatedSpriteSheet("res/units/party/shadowknight.png", 32, 64, -1, 4, 15, true);
		paladin = new AnimatedSpriteSheet("res/units/party/paladin.png", 32, 64, -1, 4, 15, true);

		runepriest = new AnimatedSpriteSheet("res/units/party/runepriest.png", 32, 64, -1, 4, 15, true);
		dawnbringer = new AnimatedSpriteSheet("res/units/party/dawnbringer.png", 32, 64, -1, 4, 15, true);

		firemage = new AnimatedSpriteSheet("res/units/party/firemage.png", 32, 64, -1, 4, 15, true);
		stormmage = new AnimatedSpriteSheet("res/units/party/stormmage.png", 32, 64, -1, 4, 15, true);

		warden = new AnimatedSpriteSheet("res/units/party/warden.png", 32, 64, -1, 4, 15, true);
		vanguard = new AnimatedSpriteSheet("res/units/party/vanguard.png", 32, 64, -1, 4, 15, true);

		// Enemy

		robotKnight = new AnimatedSpriteSheet("res/units/robot/robotKnight.png", 32, 64, -1, 4, 15, true);
		robotFireMage = new AnimatedSpriteSheet("res/units/robot/robotFireMage.png", 32, 64, -1, 4, 15, true);
		robotColdMage = new AnimatedSpriteSheet("res/units/robot/robotColdMage.png", 32, 64, -1, 4, 15, true);

		skeleton = new AnimatedSpriteSheet("res/units/monster/skeleton.png", 32, 64, -1, 4, 15, true);
		skeletonArcher = new AnimatedSpriteSheet("res/units/monster/skeletonArcher.png", 32, 64, -1, 4, 15, true);
		skeletonKnight = new AnimatedSpriteSheet("res/units/monster/skeletonKnight.png", 32, 64, -1, 4, 15, true);

		goblin = new AnimatedSpriteSheet("res/units/goblin/goblin.png", 32, 64, -1, 4, 15, true);
		goblinWarrior = new AnimatedSpriteSheet("res/units/goblin/goblinWarrior.png", 32, 64, -1, 4, 15, true);
		goblinRogue = new AnimatedSpriteSheet("res/units/goblin/goblinRogue.png", 32, 64, -1, 4, 15, true);
		goblinReaver = new AnimatedSpriteSheet("res/units/goblin/goblinReaver.png", 32, 64, -1, 4, 15, true);
		goblinShaman = new AnimatedSpriteSheet("res/units/goblin/goblinShaman.png", 32, 64, -1, 4, 15, true);
		goblinArcher = new AnimatedSpriteSheet("res/units/goblin/goblinArcher.png", 32, 64, -1, 4, 15, true);
	}

	public static void loadAnimations() throws SlickException
	{
		animSlash = new AnimatedSpriteSheet("res/animations/effect/animSlash.png", 32, 64, 18, 5);
		animFlame = new AnimatedSpriteSheet("res/animations/effect/animFlame.png", 32, 64, 40, 5);
		animHeal = new AnimatedSpriteSheet("res/animations/effect/animHeal.png", 32, 64, 40, 5);
		animCold = new AnimatedSpriteSheet("res/animations/effect/animCold.png", 32, 64, 40, 5);
		animShield = new AnimatedSpriteSheet("res/animations/effect/animShield.png", 32, 64, 28, 4);
		animBlunt = new AnimatedSpriteSheet("res/animations/effect/animBlunt.png", 32, 64, 24, 4);
		animHoly = new AnimatedSpriteSheet("res/animations/effect/animHoly.png", 32, 64, 40, 5);
		animCleanse = new AnimatedSpriteSheet("res/animations/effect/animCleanse.png", 32, 64, 40, 5);
		animTeleport = new AnimatedSpriteSheet("res/animations/effect/animTeleport.png", 32, 64, 40, 5);
		animDark = new AnimatedSpriteSheet("res/animations/effect/animDark.png", 32, 64, 40, 5);

		animSlashing = new AnimatedSpriteSheet("res/animations/effect/animSlashAttack.png", 32, 64, 20, 5);
		animSlashingGray = new AnimatedSpriteSheet("res/animations/effect/animSlashAttackGray.png", 32, 64, 20, 5);
		animCastingGray = new AnimatedSpriteSheet("res/animations/effect/animSpellSparkle.png", 32, 64, 40, 5);


	}
	
	public static void loadIcons() throws SlickException
	{
		// Conditions

		conditionAim = new Image("res/icon/conditions/conditionAim.png");
		conditionAmbush = new Image("res/icon/conditions/conditionAmbush.png");
		conditionDefense = new Image("res/icon/conditions/conditionArmor.png");
		conditionBarrier = new Image("res/icon/conditions/conditionBarrier.png");
		conditionBleed = new Image("res/icon/conditions/conditionBleed.png");
		conditionBlind = new Image("res/icon/conditions/conditionBlind.png");
		conditionBurn = new Image("res/icon/conditions/conditionBurn.png");
		conditionChill = new Image("res/icon/conditions/conditionChill.png");
		conditionCritical = new Image("res/icon/conditions/conditionCritical.png");
		conditionDaze = new Image("res/icon/conditions/conditionDaze.png");
		conditionDecay = new Image("res/icon/conditions/conditionDecay.png");
		conditionEnergize = new Image("res/icon/conditions/conditionEnergize.png");
		conditionEvade = new Image("res/icon/conditions/conditionEvade.png");
		conditionFear = new Image("res/icon/conditions/conditionFear.png");
		conditionFocus = new Image("res/icon/conditions/conditionFocus.png");
		conditionFireShield = new Image("res/icon/conditions/conditionFireShield.png");
		conditionFrostShield = new Image("res/icon/conditions/conditionFrostShield.png");
		conditionFreeze = new Image("res/icon/conditions/conditionFreeze.png");
		conditionGlory = new Image("res/icon/conditions/conditionGlory.png");
		conditionHaste = new Image("res/icon/conditions/conditionHaste.png");
		conditionInvisible = new Image("res/icon/conditions/conditionInvisible.png");
		conditionLightningShield = new Image("res/icon/conditions/conditionLightningShield.png");
		conditionMark = new Image("res/icon/conditions/conditionMark.png");
		conditionMight = new Image("res/icon/conditions/conditionMight.png");
		conditionPoison = new Image("res/icon/conditions/conditionPoison.png");
		conditionPower = new Image("res/icon/conditions/conditionPower.png");
		conditionRage = new Image("res/icon/conditions/conditionRage.png");
		conditionRegen = new Image("res/icon/conditions/conditionRegen.png");
		conditionRoot = new Image("res/icon/conditions/conditionRoot.png");
		conditionShock = new Image("res/icon/conditions/conditionShock.png");
		conditionSleep = new Image("res/icon/conditions/conditionSleep.png");
		conditionSlow = new Image("res/icon/conditions/conditionSlow.png");
		conditionSpirit = new Image("res/icon/conditions/conditionSpirit.png");
		conditionShattered = new Image("res/icon/conditions/conditionShattered.png");
		conditionStun = new Image("res/icon/conditions/conditionStun.png");
		conditionStrength = new Image("res/icon/conditions/conditionStrength.png");
		conditionTaunt = new Image("res/icon/conditions/conditionTaunt.png");
		conditionThorns = new Image("res/icon/conditions/conditionThorns.png");
		conditionVigor = new Image("res/icon/conditions/conditionVigor.png");
		conditionVulnerable = new Image("res/icon/conditions/conditionVulnerable.png");
		conditionWeak = new Image("res/icon/conditions/conditionWeak.png");

		// Abilities

		iconAttack = new Image("res/icon/actionType/iconAttack.png");
		iconBlock = new Image("res/icon/actionType/iconBlock.png");
		iconHeal = new Image("res/icon/actionType/iconHeal.png");
		iconBuff = new Image("res/icon/actionType/iconBuff.png");
		iconDebuff = new Image("res/icon/actionType/iconDebuff.png");
		iconCode = new Image("res/icon/actionType/iconCode.png");

		//Classes
		iconShadowKnight = new Image("res/icon/class/iconShadowKnight.png");
		iconPaladin = new Image("res/icon/class/iconPaladin.png");
		iconDawnbringer = new Image("res/icon/class/iconDawnbringer.png");
		iconRunepriest = new Image("res/icon/class/iconRunepriest.png");
		iconFireMage = new Image("res/icon/class/iconFireMage.png");
		iconStormMage = new Image("res/icon/class/iconStormMage.png");
		iconWarden = new Image("res/icon/class/iconWarden.png");
		iconVanguard = new Image("res/icon/class/iconVanguard.png");

		// Map
		mapBattleColor = new Image("res/icon/map/mapBattleColor.png");
		mapBattleGray = new Image("res/icon/map/mapBattleGray.png");
		mapRecruitColor = new Image("res/icon/map/mapRecruitColor.png");
		mapRecruitGray = new Image("res/icon/map/mapRecruitGray.png");
		tileGrass = new Image("res/icon/map/tileGrass.png");

	}

	public static void loadPerks() throws SlickException
	{
		// Enemy
		perkShifty = new Image("res/icon/perk/enemy/perkShifty.png");

		// Knight
		perkAuraOfCourage = new Image("res/icon/perk/knight/perkAuraOfCourage.png");
		perkBloodAndIron = new Image("res/icon/perk/knight/perkBloodAndIron.png");
		perkDauntlessStrength = new Image("res/icon/perk/knight/perkDauntlessStrength.png");
		perkDeathward = new Image("res/icon/perk/knight/perkDeathward.png");
		perkShieldWall = new Image("res/icon/perk/knight/perkShieldWall.png");
		perkGloriousRecovery = new Image("res/icon/perk/knight/perkGloriousRecovery.png");
		perkReactiveShield = new Image("res/icon/perk/knight/perkReactiveShield.png");
		perkShiningArmor = new Image("res/icon/perk/knight/perkShiningArmor.png");

		// Warrior
		perkArmorBreaker = new Image("res/icon/perk/warrior/perkArmorBreaker.png");
		perkBattlefrontLeader = new Image("res/icon/perk/warrior/perkBattlefrontLeader.png");
		perkBoundlessEndurance = new Image("res/icon/perk/warrior/perkBoundlessEndurance.png");
		perkDisarm = new Image("res/icon/perk/warrior/perkDisarm.png");
		perkMomentum = new Image("res/icon/perk/warrior/perkMomentum.png");
		perkOakheart = new Image("res/icon/perk/warrior/perkOakheart.png");
		perkRegrowth = new Image("res/icon/perk/warrior/perkRegrowth.png");
		perkStrategist = new Image("res/icon/perk/warrior/perkStrategist.png");
		perkVitalSurge = new Image("res/icon/perk/warrior/perkVitalSurge.png");

		// Cleric
		perkGrace = new Image("res/icon/perk/cleric/perkGrace.png");
		perkMorninglord = new Image("res/icon/perk/cleric/perkMorninglord.png");
		perkPurity = new Image("res/icon/perk/cleric/perkPurity.png");
		perkRuneOfPower = new Image("res/icon/perk/cleric/perkRuneOfPower.png");
		perkShadowsight = new Image("res/icon/perk/cleric/perkShadowsight.png");
		perkWrath = new Image("res/icon/perk/cleric/perkWrath.png");

		// Mage

		perkChainLightning = new Image("res/icon/perk/mage/perkChainLightning.png");
		perkConflagration = new Image("res/icon/perk/mage/perkConflagration.png");
		perkFlameMastery = new Image("res/icon/perk/mage/perkFlameMastery.png");
		perkFlarefrost = new Image("res/icon/perk/mage/perkFlarefrost.png");
		perkFrostMastery = new Image("res/icon/perk/mage/perkFrostMastery.png");
		perkPermafrost = new Image("res/icon/perk/mage/perkGlacialLock.png");
		perkMasterOfTheElements = new Image("res/icon/perk/mage/perkMasterOfTheElements.png");
		perkPyromaniac = new Image("res/icon/perk/mage/perkPyromaniac.png");
		perkRimeshock = new Image("res/icon/perk/mage/perkRimeshock.png");
		perkShatterShards = new Image("res/icon/perk/mage/perkShatterShards.png");
		perkSparkfire = new Image("res/icon/perk/mage/perkSparkfire.png");
		perkSpellstorm = new Image("res/icon/perk/mage/perkSpellstorm.png");
		perkStaggeringShock = new Image("res/icon/perk/mage/perkStaggeringShock.png");
		perkLightningMastery = new Image("res/icon/perk/mage/perkLightningMastery.png");
		perkWildSurge = new Image("res/icon/perk/mage/perkWildSurge.png");
	}

	public static void loadUI() throws SlickException
	{
		// Conditions

		manaBoxBlue = new Image("res/ui/energyDiamondBlueEnergy.png");
		manaBoxBlueEmpty = new Image("res/ui/energyDiamondBlueEnergyDark.png");
		manaBoxRed = new Image("res/ui/energyDiamondRedEnergy.png");
		manaBoxRedEmpty = new Image("res/ui/energyDiamondRedEnergyDark.png");

		energyDiamondEmpty = new Image("res/ui/energyDiamondEmpty.png");
		energyDiamondFull = new Image("res/ui/energyDiamondFull.png");
		energyDiamondNext = new Image("res/ui/energyDiamondNext.png");

		uiMana = new Image("res/ui/mana.png");
		uiManaEmpty = new Image("res/ui/manaEmpty.png");
		uiManaRed = new Image("res/ui/manaRed.png");
		uiManaEmptyRed = new Image("res/ui/manaEmptyRed.png");
		uiSpeed = new Image("res/ui/speed.png");
		uiSpeedEmpty = new Image("res/ui/speedEmpty.png");
		uiActions = new Image("res/ui/actionsFull.png");
		uiActionsEmpty = new Image("res/ui/actionsEmpty.png");
		uiBlockShield = new Image("res/ui/blockShield.png");
		uiCell = new Image("res/ui/cell.png");

	}
	 
	
//	920 x 540 --> 2x is 1920 x 1080
// 
	
	
}
