package character;

import java.util.ArrayList;
import java.util.List;

import spell.Spell;
import spell.SpellCategory;
import stateCharacter.Idle;
import stateCharacter.StateCharacter;

public abstract class Character {
	private String name;
	private int magicLevel;
	private int healthPoints;
	private int defense;
	private double accuracy;
	private int defenseBuff;
	private int defenseBuffDuration;
	private int defenseNerf;
	private int defenseNerfDuration;
	protected StateCharacter state;
	private List<Spell> spells;
	
	public Character(String name, int magicLevel, int healthPoints, int defense, double accuracy) {
		this.name = name;
		this.magicLevel = magicLevel;
		this.healthPoints = healthPoints;
		this.defense = defense;
		this.accuracy = accuracy;
		defenseBuff = 0;
		defenseBuffDuration = 0;
		defenseNerf = 0;
		defenseNerfDuration = 0;
		state = new Idle();
		spells = new ArrayList<Spell>();
	}
	
	
	public void increaseDefense(int defense, int duration) {
		if(defense < 0) {
			throw new IllegalArgumentException("La defensa no puede ser negativa");
		}
		
		if(duration < 0) {
			throw new IllegalArgumentException("La duracion no puede ser negativa");
		}
		
		defenseBuffDuration = duration;
		defenseBuff = defense;
	}
	
	public void decreaseDefense(int defense, int duration) {
		if(defense < 0) {
			throw new IllegalArgumentException("La defensa no puede ser negativa");
		}
		
		if(duration < 0) {
			throw new IllegalArgumentException("La duracion no puede ser negativa");
		}
		
		defenseNerfDuration = duration;
		defenseNerf = defense * -1;
	}
	
	public void healthUp(int health) {
		if(health < 0) {
			throw new IllegalArgumentException("La vida no puede ser negativa");
		}
		
		this.healthPoints += health;
	}
	
	public void healthDown(int health) {
		if(health < 0) {
			throw new IllegalArgumentException("La vida no puede ser negativa");
		}
		
		if(healthPoints < health) {
			healthPoints = 0;
		} else {
			healthPoints -= health;
		}
	}
	
	public boolean addSpell(Spell spell) {
		return spells.add(spell);
	}
	
	public Spell getSpell(String spellName) {
		for(Spell spell : spells) {
			if(spell.getName().equals(spellName)) {
				return spell;
			}
		}
		return null;
	}

	public abstract int getAffinity(SpellCategory category);

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
		return defense + defenseBuff - defenseNerf;
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
	
	public void receiveDamage(int damage) {
		if(defenseBuffDuration > 0) {
			defenseBuffDuration --;			
		} else {
			defenseBuff = 0;
		}
		
		if(defenseNerfDuration > 0) {
			defenseNerfDuration --;
		} else {
			defenseNerf = 0;
		}
		
		healthPoints -= damage - getDefense() * 0.25;
	}
}
