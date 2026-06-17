package character;

import java.util.ArrayList;
import java.util.List;

import spell.Spell;

public abstract class Character {
	protected String name;
	protected int magicLevel;
	protected double healthPoints;
	protected int defense;
	protected int defenseBuffDuration;
	protected List<Spell> spells;
	
	public Character() {
		name = "Character";
		magicLevel = 1;
		healthPoints = 100;
		defense = 0;
		defenseBuffDuration = 0;
		spells = new ArrayList<Spell>();
	}
	
	public void receiveDamage(double damage) {
		if (defenseBuffDuration > 0) {
			defenseBuffDuration --;			
		}
		healthPoints -= damage - defense * 0.25;
	}
	
	public void increaseDefense(int defense) {
		defenseBuffDuration = 3;
		this.defense += defense;
	}
	
	public void healthUp(double health) {
		this.healthPoints += health;
	}

	public String getName() {
		return name;
	}

	public double getMagicLevel() {
		return magicLevel;
	}

	public double getHealthPoints() {
		return healthPoints;
	}
	
	public int getDefense() {
		return defense;
	}
	
	public int getDefenseBuffDuration() {
		return defenseBuffDuration;
	}

	public List<Spell> getSpells() {
		return spells;
	}
}
