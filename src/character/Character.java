package character;
// Representa a un personaje del juego con atributos, estados y hechizos.

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.Set;
import spell.Spell;
import spell.SpellType;
import spell.SpellCategory;
import stateCharacter.Idle;
import stateCharacter.StateCharacter;
import exceptions.AllyFireException;
import exceptions.AllyNerfException;
import exceptions.AutoAttackException;
import exceptions.HelpEnemyException;
import exceptions.InvalidSpellTypeException;
import exceptions.SpellNotFoundException;
import exceptions.SpellTypeException;

public abstract class Character {
	private static int number = 1;
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
	private Set<Spell> spells;
	
	public Character(String name, int magicLevel, int healthPoints, int defense, double accuracy, CharacterType type) {
		this.name = name + String.format("%03d", number);
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
		spells = new HashSet<Spell>();
		
		number ++;
	}
	
	public void attack(Character target) {
		List<Spell> spells = new ArrayList<Spell>(this.spells);
		Random random = new Random();
		System.out.println("###########################################################################");
		System.out.println(this.getName() + " Inicio de ataque\n\n");	
		state = state.castSpell(this, target, spells.get(random.nextInt(spells.size())));
		System.out.println("\n" + this.getName() + " Fin de atque");	
		System.out.println("###########################################################################\n");
	}
	
	public void attack(Character target, String spellName) {
		Spell spell = validCastSpell(target, spellName, SpellType.OFFENSIVE);
		System.out.println("###########################################################################");
		System.out.println(this.getName() + " Inicio de ataque\n\n");		
		state = state.castSpell(this, target, spell);
		System.out.println("");
		System.out.println("\n" + this.getName() + " Fin de atque");	
		System.out.println("###########################################################################\n");	
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
		defenseNerf = defense;
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
		int defense = (int)(getDefense() * 0.25);
		
		if(damage < defense) {
			damage = 0;
		} else {
			damage -= defense;
		}
		
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
		throw new SpellNotFoundException("El personaje no posee ese hechizo o no existe");
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

	public Set<Spell> getSpells() {
		return new HashSet<Spell>(spells); // Devuelve una copia y no la referencia a la lista
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

	public Spell validCastSpell(Character target, String spellName, SpellType type) {
		if(spellName == null) {
			throw new IllegalArgumentException("El hechizo no puede ser nulo");
		}
		
		Spell spell = this.getSpell(spellName);
		
		if(target == null) {
			throw new IllegalArgumentException("El objetivo no puede ser nulo");
		}
		if(spell.getType() != type) {
			throw new InvalidSpellTypeException("El hechizo no es de tipo " + type);
			//throw new IllegalArgumentException("El hechizo no es de tipo " + type);
		}
		if(!spells.contains(spell)) {
			throw new SpellTypeException(name + " no posee el hechizo " + spellName);
		}
		if(spell.getType() == SpellType.OFFENSIVE && target == this) {
			throw new AutoAttackException("No se puede lanzar un hechizo ofensivo sobre uno mismo");
		}
		if(spell.getType() == SpellType.OFFENSIVE && target.type == this.type) {
			throw new AllyFireException("No se puede lanzar un hechizo ofensivo sobre un miembro del equipo");
		}
		if(spell.getType() == SpellType.SPECIAL && target.type == this.type) {
			throw new AllyNerfException("No se puede lanzar una habilidad especial sobre un miembro del equipo");
		}
		if(spell.getType() == SpellType.SUPPORT && target.type != this.type) {
			throw new HelpEnemyException("No se puede lanzar un hechizo de soporte sobre un enemigo");
		}
		
		return spell;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(magicLevel, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Character other = (Character) obj;
		return magicLevel == other.magicLevel && Objects.equals(name, other.name);
	}
	
	public Character pickTarget(Random random) {
	    return this;
	}

	@Override
	public String toString() {
		return "Character [name=" + name + ", magicLevel=" + magicLevel + ", healthPoints=" + healthPoints
				+ ", defense=" + defense + ", accuracy=" + accuracy + ", state=" + state + ", spells=" + spells + "]";
	}
}
