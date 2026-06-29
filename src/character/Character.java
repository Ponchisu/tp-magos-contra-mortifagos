package character;

import java.util.ArrayList;
import java.util.List;

import spell.Spell;
import spell.SpellCategory;

public abstract class Character {
	private String name;
	private int magicLevel;
	protected int healthPoints;
	protected int defense;
	private double accuracy;
	private int defenseBuff;
	protected int defenseBuffDuration;
	protected List<Spell> spells;
	
	public Character(String name, double accuracy) {
		name = "Character";
		magicLevel = 1;
		healthPoints = 100;
		defense = 0;
		defenseBuff = 0;
		defenseBuffDuration = 0;
		this.accuracy = accuracy;
		spells = new ArrayList<Spell>();
	}
	
	public void receiveDamage(double damage) {
		if (defenseBuffDuration > 0) {
			defenseBuffDuration --;			
		} else if(defenseBuff == 0) {
			defenseBuff = 0;
		}
		
		healthPoints -= damage - getDefense() * 0.25;
	}
	
	public void increaseDefense(int defense, int duration) {
		defenseBuffDuration = duration;
		this.defenseBuff += defense;
	}
	
	public void decreaseDefense(int defense, int duration) {
		defenseBuffDuration = duration;
		if(defense > this.defense) {
			defenseBuff = this.defense;
		} else {
			defenseBuff = defense;
		}
	}
	
	public void healthUp(double health) {
		this.healthPoints += health;
	}

	public String getName() {
		return name;
	}

	public int getMagicLevel() {
		return magicLevel;
	}

	public int getHealthPoints() {
		return healthPoints;
	}
	
	public int getDefense() {
		return defense + defenseBuff;
	}
	
	public double getAccuracy() {
		return accuracy;
	}
	
	public int getDefenseBuffDuration() {
		return defenseBuffDuration;
	}

	public List<Spell> getSpells() {
		return spells;
	}
	
	public abstract int getAffinity(SpellCategory category);
}
