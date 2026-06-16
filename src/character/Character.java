package character;

import java.util.ArrayList;
import java.util.List;

import spell.Spell;

public abstract class Character {
	private String name;
	private double magicLevel;
	private double healthPoints;
	private int defense;
	private List<Spell> spells;
	
	public Character() {
		name = "Character";
		magicLevel = 1;
		healthPoints = 100;
		defense = 0;
		spells = new ArrayList<Spell>();
	}
	
	public void receiveDamage(double damage) {
		healthPoints -= damage - defense * 0.25;
	}
	
	public void increaseDefense(int defense) {
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

	public List<Spell> getSpells() {
		return spells;
	}
}
