package deathEater;
// Agrupa a varios mortífagos en una unidad.

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import character.Character;
import character.CharacterType;
import exceptions.AllyFireException;
import spell.Spell;
import spell.SpellCategory;
import spell.SpellType;

public class BattalionDeathEater extends DeathEater {
	private List<DeathEater> deathEaters;

	// Historial de hechizos lanzados por cada personaje
	private Map<DeathEater, List<Spell>> spellHistory;
	
	public BattalionDeathEater() {
		super("BattalionDeathEater", 0, 0, 0, 0);
		deathEaters = new ArrayList<DeathEater>();
	}

	@Override
	public void attack(Character target) {
		System.out.println("###########################################################################\n");
		System.out.println(this.getName() + "\n");
		System.out.println("###########################################################################\n");

		if(target.getType() == CharacterType.DEATHEATER) {
			throw new AllyFireException("No se puede atacar a un aliado");
		}

	    Set<Spell> usedSpells = new HashSet<>();
	    Random random = new Random();

		//Recorre los personajes
	    for(DeathEater deathEater : deathEaters) {
			System.out.println("---------------------------------------------------------------------------\n");
	    	Character objective = target.pickTarget(random);

			//Seleccion un hechizo aleatorio disponible
	        Spell spell = getRandomSpell(deathEater, SpellType.OFFENSIVE, usedSpells);

	        if(spell == null) {
	            continue;
	        }

			//Agrega el hechizo a los historiales
	        usedSpells.add(spell);

	        if(!spellHistory.containsKey(deathEater)) {
			    spellHistory.put(deathEater, new ArrayList<>());
			}

			spellHistory.get(deathEater).add(spell);
			//Se realiza la accion
			deathEater.attack(objective, spell.getName());
			System.out.println("---------------------------------------------------------------------------\n");
		}
		System.out.println("###########################################################################\n");
	}
	
	@Override
	public void attack(Character target, String spellName) {
		System.out.println("###########################################################################\n");
		System.out.println(this.getName() + "\n");
		System.out.println("###########################################################################\n");

		for(DeathEater deathEater : deathEaters) {
			System.out.println("---------------------------------------------------------------------------\n");
			try {
				deathEater.attack(target, spellName);				
			} catch (Exception e) {
				System.out.println(e);
			}
			System.out.println("---------------------------------------------------------------------------\n");
		}
		System.out.println("###########################################################################\n");
	}

	@Override
	public void specialSpell(Character target)  {
		System.out.println("###########################################################################\n");
		System.out.println(this.getName() + "\n");
		System.out.println("###########################################################################\n");
		
		if(target.getType() == CharacterType.DEATHEATER) {
			throw new AllyFireException("No se puede atacar a un aliado");
		}

	    Set<Spell> usedSpells = new HashSet<>();
	    Random random = new Random();

		//Recorre los personajes
	    for(DeathEater deathEater : deathEaters) {
			System.out.println("---------------------------------------------------------------------------\n");
	    	Character objective = target.pickTarget(random);

			//Seleccion un hechizo aleatorio disponible
	        Spell spell = getRandomSpell(deathEater, SpellType.SPECIAL, usedSpells);

	        if(spell == null) {
	            continue;
	        }

			//Agrega el hechizo a los historiales
	        usedSpells.add(spell);

	        if(!spellHistory.containsKey(deathEater)) {
			    spellHistory.put(deathEater, new ArrayList<>());
			}

			spellHistory.get(deathEater).add(spell);
			//Se realiza la accion
			deathEater.attack(objective, spell.getName());
			System.out.println("---------------------------------------------------------------------------\n");
		}
		System.out.println("###########################################################################\n");
	}
	
	@Override
	public void specialSpell(Character target, String spellName) {
		System.out.println("###########################################################################\n");
		System.out.println(this.getName() + "\n");
		System.out.println("###########################################################################\n");
		
		for(DeathEater deathEater : deathEaters) {
			try {
				deathEater.attack(target, spellName);				
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		System.out.println("###########################################################################\n");
	}

	private Spell getRandomSpell(DeathEater deathEater, SpellType type, Set<Spell> usedSpells) {

	    List<Spell> availableSpells = new ArrayList<>();

	    for (Spell spell : deathEater.getSpells()) {
	        if (spell.getType() == type && !usedSpells.contains(spell)) {
	            availableSpells.add(spell);
	        }
	    }

	    if (availableSpells.isEmpty()) {
	        return null;
	    }

	    Random random = new Random();
	    return availableSpells.get(random.nextInt(availableSpells.size()));
	}
	
	public int getBattalionSize() {
		return deathEaters.size();
	}

	public Character get(int index) {
	    return deathEaters.get(index);
	}
	
	@Override
	public void increaseDefense(int defense, int duration) {
		for(DeathEater deathEater : deathEaters) {
			deathEater.increaseDefense(defense, duration);
		}
	}
	
	@Override
	public void decreaseDefense(int defense, int duration) {
		for(DeathEater deathEater: deathEaters) {
			deathEater.decreaseDefense(defense, duration);
		}
	}
	
	@Override
	public void healthUp(int health) {
		for(DeathEater deathEater: deathEaters) {
			deathEater.healthUp(health);
		}
	}
	
	@Override
	public void healthDown(int damage) {
		for(DeathEater deathEater : deathEaters) {
			deathEater.healthDown(damage);
		}
	}
	
	@Override
	public boolean addSpell(Spell spell) {
		boolean addReturn = true;
		
		for(DeathEater deathEater : deathEaters) {
			if(!deathEater.addSpell(spell)) {
				addReturn = false;
			}
		}
		return addReturn;
	}
	
	@Override
	public Spell getSpell(String spellName) {
		Spell spell = null;
		for(DeathEater deathEater : deathEaters) {
			if(!deathEater.addSpell(spell)) {
				spell = deathEater.getSpell(spellName);
				if(spell != null) {
					return spell;
				}
			}
		}
		return null;
	}
	
	@Override
	public boolean isLive() {
		for(DeathEater deathEater : deathEaters) {
			if(deathEater.isLive()) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public int getAffinity(SpellCategory category) {
		int cont = 0;
		int affinity = 0;
		for(DeathEater deathEater : deathEaters) {
			affinity += deathEater.getAffinity(category);
			cont ++;
		}
		return affinity / cont;
	}
	
	@Override
	public int getMagicLevel() {
		int cont = 0;
		int magicLevel = 0;
		for(DeathEater deathEater : deathEaters) {
			magicLevel += deathEater.getMagicLevel();
			cont ++;
		}
		return magicLevel / cont;
	}

	@Override
	public int getHealthPoints() {
		int healthPoints = 0;
		for(DeathEater deathEater : deathEaters) {
			healthPoints += deathEater.getHealthPoints();
		}
		return healthPoints;
	}
	
	@Override
	public int getDefense() {
		int cont = 0;
		int defense = 0;
		for(DeathEater deathEater : deathEaters) {
			defense += deathEater.getMagicLevel();
			cont ++;
		}
		return defense / cont;
	}
	
	@Override
	public double getAccuracy() {
		int cont = 0;
		double accuracy = 0;
		for(DeathEater deathEater : deathEaters) {
			accuracy += deathEater.getMagicLevel();
			cont ++;
		}
		return accuracy / cont;
	}

	
	@Override
	public Set<Spell> getSpells() {
		Set<Spell> spellsBattalion = new HashSet<Spell>();
		
		for(DeathEater deathEater : deathEaters) {
			if(deathEater.isLive()) {
				Set<Spell> spellsDeathEater = deathEater.getSpells();
				for(Spell spell : spellsDeathEater) {
					spellsBattalion.add(spell);
				}				
			}
		}
		
		return spellsBattalion;
	}
	
	@Override
	public void receiveDamage(int damage) {
		for(DeathEater deathEater : deathEaters) {
			deathEater.receiveDamage(damage);
		}
	}
	
	@Override
	public void stun(int duration) {
		for(DeathEater deathEater : deathEaters) {
			deathEater.stun(duration);
		}
	}
	
	@Override
	public void invulnerability(int duration) {
		for(DeathEater deathEater : deathEaters) {
			deathEater.invulnerability(duration);
		}
	}
	
	@Override
	public void burnt(int fireDamage, int duration) {
		for(DeathEater deathEater : deathEaters) {
			deathEater.burnt(fireDamage, duration);;
		}
	}
	
	@Override
	public void cleanState() {
		for(DeathEater deathEater : deathEaters) {
			deathEater.cleanState();
		}
	}
	
	@Override
	public void wounded(int bleendingDamage, int duration) {
		for(DeathEater deathEater : deathEaters) {
			deathEater.wounded(bleendingDamage, duration);
		}
	}
	
	@Override
	public void electrocute(int electricDamage, int duration) {
		for(DeathEater deathEater : deathEaters) {
			deathEater.electrocute(electricDamage, duration);
		}
	}
	
	@Override
	public void healing(int health) {
		for(DeathEater deathEater : deathEaters) {
			deathEater.healing(health);
		}
	}
	
	@Override
	public void confuse(int duration) {
		for(DeathEater deathEater : deathEaters) {
			deathEater.confuse(duration);
		}
	}

	@Override
	public Character pickTarget(Random random) {
	    return get(random.nextInt(getBattalionSize()));
	}

	@Override
	public String toString() {
		return getName() + "\n[deathEaters=" + deathEaters + "]";
	}	
}
