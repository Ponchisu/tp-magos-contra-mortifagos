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
	private CharacterType type;
	private List<Spell> spells;
	
	public Character(String name, int magicLevel, int healthPoints, int defense, double accuracy, CharacterType type) {
		this.name = name;
		this.magicLevel = magicLevel;
		this.healthPoints = healthPoints;
		this.defense = defense;
		this.accuracy = accuracy;
		this.type = type;
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
		
		System.out.println("La defensa de " + name + " aumento " + defense + " puntos durante " + duration + " turnos -- Defensa total: " + getDefense());
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
		System.out.println("La defensa de " + name + " disminuyo " + defense + " puntos durante " + duration + " turnos -- Defensa total: " + getDefense());

	}
	
	public void healthUp(int health) {
		if(health < 0) {
			throw new IllegalArgumentException("La vida no puede ser negativa");
		}
		
		this.healthPoints += health;
		System.out.println("La vida de " + name + " aumento " + health + " puntos -- Vida total: " + getHealthPoints());

	}
	
	public void healthDown(int damage) {
		if(damage < 0) {
			throw new IllegalArgumentException("El daño no puede ser negativo");
		}
		
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
		
		damage = damage - (int)(getDefense() * 0.25);
		
		if(healthPoints < damage) {
			healthPoints = 0;
		} else {
			healthPoints -= damage;
		}
		
		System.out.println(name + " recibio " + damage + " puntos de daño -- Vida total: " + getHealthPoints());
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
	
	public boolean isLive() {
		return this.state.isLive();
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
	
	public CharacterType getType() {
		return type;
	}
	
	public int getDefenseBuffDuration() {
		return defenseBuffDuration;
	}

	public List<Spell> getSpells() {
		return spells;
	}
	
	public void receiveDamage(int damage) {
		
		state = state.receiveDamage(this, damage);
	}
	
	public void stun(int duration) {
		state = state.stun(this, duration);
	}
	
	public void invulnerability(int duration) {
		state = state.invulnerability(this, duration);
	}
	
	public void burnt(int fireDamage, int duration) {
		state = state.burnt(this, fireDamage, duration);
	}
	
	public void cleanState() {
		state = state.cleanState(this);
	}
	
	public void wounded(int bleendingDamage, int duration) {
		state = state.wounded(this, bleendingDamage, duration);
	}
	
	public void electrocute(int electricDamage, int duration) {
		state = state.electrocute(this, electricDamage, duration);
	}
	
	public void healing(int health) {
		state = state.healing(this, health);
	}
	
	public void confuse(int duration) {
		state = state.confuse(this, duration);
	}
}
