package wizard;
// Agrupa a varios magos en una unidad de combate.

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import character.Character;
import spell.Spell;
import spell.SpellCategory;
import spell.SpellType;

public class BattalionWizard extends Wizard {
	private List<Wizard> wizards;
	
	// Historial de hechizos lanzados por cada personaje
	private Map<Wizard, List<Spell>> spellHistory;

	public BattalionWizard() {
		super("BattalionWizard", 0, 0, 0, 0);
		wizards = new ArrayList<Wizard>();
	}
	
	public boolean addWizard(Wizard wizard) {
		return wizards.add(wizard);
	}
	
	@Override
	public void attack(Character target) {

	    Set<Spell> usedSpells = new HashSet<>();
	    Random random = new Random();

		//Recorre los personajes
	    for (Wizard wizard : wizards) {
			//Selecciona un objetivo aleatorio del batallon enemigo
	        Character objective =
	                target.get(random.nextInt(target.getBattalionSize()));

			//Seleccion un hechizo aleatorio disponible
	        Spell spell = getRandomSpell(
	                wizard,
	                SpellType.OFFENSIVE,
	                usedSpells);

	        if (spell == null) {
	            continue;
	        }

			//Agrega el hechizo a los historiales
	        usedSpells.add(spell);

	        if (!spellHistory.containsKey(wizard)) {
			    spellHistory.put(wizard, new ArrayList<>());
			}

			spellHistory.get(wizard).add(spell);
			//Se realiza la accion
			wizard.attack(objective, spell.getName());
		}
	}
	
	@Override
	public void support(Character target) {
		
	    Set<Spell> usedSpells = new HashSet<>();
	    Random random = new Random();
		//Recorre los personajes
	    for (Wizard wizard : wizards) {
			//Selecciona un objetivo aleatorio del batallon enemigo
	        Character objective =
	                target.get(random.nextInt(target.getBattalionSize()));
			//Seleccion un hechizo aleatorio disponible
	        Spell spell = getRandomSpell(
	                wizard,
	                SpellType.SUPPORT,
	                usedSpells);

	        if (spell == null) {
	            continue;
	        }
			//Agrega el hechizo a los historiales
	        usedSpells.add(spell);

	        if (!spellHistory.containsKey(wizard)) {
			    spellHistory.put(wizard, new ArrayList<>());
			}
			
			spellHistory.get(wizard).add(spell);
			//Se realiza la accion
			wizard.attack(objective, spell.getName());
		}
	}

	private Spell getRandomSpell(Wizard wizard, SpellType type, Set<Spell> usedSpells) {

	    List<Spell> availableSpells = new ArrayList<>();

	    for (Spell spell : wizard.getSpells()) {
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

	@Override
	public void increaseDefense(int defense, int duration) {
		for(Wizard wizard : wizards) {
			wizard.increaseDefense(defense, duration);
		}
	}
	
	@Override
	public void decreaseDefense(int defense, int duration) {
		for(Wizard wizard : wizards) {
			wizard.decreaseDefense(defense, duration);
		}
	}
	
	@Override
	public void healthUp(int health) {
		for(Wizard wizard : wizards) {
			wizard.healthUp(health);
		}
	}
	
	@Override
	public void healthDown(int damage) {
		for(Wizard wizard : wizards) {
			wizard.healthDown(damage);
		}
	}
	
	@Override
	public boolean addSpell(Spell spell) {
		boolean addReturn = true;
		
		for(Wizard wizard : wizards) {
			if(!wizard.addSpell(spell)) {
				addReturn = false;
			}
		}
		return addReturn;
	}
	
	@Override
	public Spell getSpell(String spellName) {
		Spell spell = null;
		for(Wizard wizard : wizards) {
			if(!wizard.addSpell(spell)) {
				spell = wizard.getSpell(spellName);
				if(spell != null) {
					return spell;
				}
			}
		}
		return null;
	}
	
	@Override
	public boolean isLive() {
		for(Wizard wizard : wizards) {
			if(wizard.isLive()) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public int getAffinity(SpellCategory category) {
		int cont = 0;
		int affinity = 0;
		for(Wizard wizard : wizards) {
			affinity += wizard.getAffinity(category);
			cont ++;
		}
		return affinity / cont;
	}
	
	@Override
	public int getMagicLevel() {
		int cont = 0;
		int magicLevel = 0;
		for(Wizard wizard : wizards) {
			magicLevel += wizard.getMagicLevel();
			cont ++;
		}
		return magicLevel / cont;
	}

	@Override
	public int getHealthPoints() {
		int healthPoints = 0;
		for(Wizard wizard : wizards) {
			healthPoints += wizard.getHealthPoints();
		}
		return healthPoints;
	}
	
	@Override
	public int getDefense() {
		int cont = 0;
		int defense = 0;
		for(Wizard wizard : wizards) {
			defense += wizard.getMagicLevel();
			cont ++;
		}
		return defense / cont;
	}
	
	@Override
	public double getAccuracy() {
		int cont = 0;
		double accuracy = 0;
		for(Wizard wizard : wizards) {
			accuracy += wizard.getMagicLevel();
			cont ++;
		}
		return accuracy / cont;
	}

	
	@Override
	public Set<Spell> getSpells() {
		Set<Spell> spellsBattalion = new HashSet<Spell>();
		
		for(Wizard wizard : wizards) {
			if(wizard.isLive()) {
				Set<Spell> spellsWizard = wizard.getSpells();
				for(Spell spell : spellsWizard) {
					spellsBattalion.add(spell);
				}				
			}
		}
		
		return spellsBattalion;
	}
	
	@Override
	public void receiveDamage(int damage) {
		for(Wizard wizard : wizards) {
			wizard.receiveDamage(damage);
		}
	}
	
	@Override
	public void stun(int duration) {
		for(Wizard wizard : wizards) {
			wizard.stun(duration);
		}
	}
	
	@Override
	public void invulnerability(int duration) {
		for(Wizard wizard : wizards) {
			wizard.invulnerability(duration);
		}
	}
	
	@Override
	public void burnt(int fireDamage, int duration) {
		for(Wizard wizard : wizards) {
			wizard.burnt(fireDamage, duration);;
		}
	}
	
	@Override
	public void cleanState() {
		for(Wizard wizard : wizards) {
			wizard.cleanState();
		}
	}
	
	@Override
	public void wounded(int bleendingDamage, int duration) {
		for(Wizard wizard : wizards) {
			wizard.wounded(bleendingDamage, duration);
		}
	}
	
	public void electrocute(int electricDamage, int duration) {
		for(Wizard wizard : wizards) {
			wizard.electrocute(electricDamage, duration);
		}
	}
	
	public void healing(int health) {
		for(Wizard wizard : wizards) {
			wizard.healing(health);
		}
	}
	
	public void confuse(int duration) {
		for(Wizard wizard : wizards) {
			wizard.confuse(duration);
		}
	}

	@Override
	public int getBattalionSize() {
		return wizards.size();
	}

	@Override
	public Character get(int index) {
	    return wizards.get(index);
	}

}
