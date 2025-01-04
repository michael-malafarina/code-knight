package unit.ability.action;

import core.Color;

public enum Tag 
{
	//  Type
	ATTACK,	HEAL, BLOCK, BUFF, DEBUFF, CODE,

	PHYSICAL, MAGICAL, FORCE, FIRE, COLD, LIGHTNING,
	RADIANT, PSYCHIC, SHADOW,
	
	SHOCK, BURNING, BLEED, POISON, CHILL,

	CLEANSE,

	HEALTH, MANA,

	ALL;				
	
	public Color getColor()
	{
		if(this == HEALTH) return new Color(255, 0, 0);
		if(this == MANA) return new Color(80, 120, 255);

		if(this == ATTACK) return new Color(255, 0, 0);
		if(this == HEAL) return new Color(30, 255, 80);
		if(this == BLOCK) return new Color(60, 140, 255);
		if(this == BUFF) return new Color(255, 210, 30);
		if(this == DEBUFF) return new Color(175, 40, 120);
		if(this == CODE) return new Color(0, 255, 200);

		// PRIMARY DAMAGE TYPES FIRST
		if(this == PHYSICAL) return new Color(180, 140, 100);
		if (this == FORCE) return new Color(255, 255, 255);

		if(this == FIRE) return new Color(255, 125, 0);
		if(this == COLD) return new Color(170, 170, 255);
		if(this == LIGHTNING) return new Color(255, 255, 125);

		if(this == RADIANT) return new Color(255, 255, 200);
		if(this == PSYCHIC) return new Color(255, 140, 200);
		if(this == SHADOW) return new Color(180, 180, 180);

		// HEALING TYPES
		if(this == CLEANSE) return new Color(50, 210, 210);

		// SECONDARY DAMAGE TYPES NEXT
		if(this == BLEED) return new Color(230, 50, 50);
		if(this == POISON) return new Color(50, 230, 50);
		if(this == BURNING) return new Color(255, 125, 0);
		if(this == SHOCK) return new Color(255, 255, 125);
		if(this == CHILL) return new Color(170, 170, 255);







		
		else return null;
	}
	
	
	
}
