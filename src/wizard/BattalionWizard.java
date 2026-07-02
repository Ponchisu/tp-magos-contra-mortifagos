package wizard;
// Agrupa a varios magos en una unidad de combate.

import java.util.ArrayList;
import java.util.HashMap;
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

public class BattalionWizard extends Wizard {
	private Set<Wizard> wizards;
	private Map<Wizard, List<Spell>> spellHistory;

	public BattalionWizard() {
		super("BattalionWizard", 0, 0, 0, 0);
		wizards = new HashSet<Wizard>();
		spellHistory = new HashMap<Wizard, List<Spell>>();
	}
	
	public boolean addWizard(Wizard wizard) {
		return wizards.add(wizard);
	}
	
	@Override
	public void attack(Character target) {
		System.out.println("###########################################################################");
		System.out.println(this.getName() + " Inicio de ataque\n\n");
		
		if(target.getType() == CharacterType.WIZARD) {
			throw new AllyFireException("No se puede atacar a un aliado");
		}
		

	    Set<Spell> usedSpells = new HashSet<>();
	    Random random = new Random();

		//Recorre los personajes
	    for (Wizard wizard : wizards) {
	    	Character objective = target.pickTarget(random);
	    	
	    	if(objective == null) {
	    		return;
	    	}

			//Seleccion un hechizo aleatorio disponible
	        Spell spell = getRandomSpell(wizard, SpellType.OFFENSIVE, usedSpells);

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
		System.out.println("\n" + this.getName() + " Fin de atque");	
		System.out.println("###########################################################################\n");
	}
	
	@Override
	public void attack(Character target, String spellName) {
		System.out.println("\n" + this.getName() + " Inicio de atque");	
		System.out.println("###########################################################################\n");
		
		for(Wizard wizard : wizards) {
			try {
				wizard.attack(target, spellName);				
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		System.out.println("\n" + this.getName() + " Fin de atque");	
		System.out.println("###########################################################################\n");
	}
	
	@Override
	public void support(Character target) {
		System.out.println("###########################################################################");
		System.out.println(this.getName() + " Inicio de supporteo\n\n");	
		
		if(target.getType() == CharacterType.DEATHEATER) {
			throw new AllyFireException("No se puede ayudar a un enemigo");
		}
		
	    Set<Spell> usedSpells = new HashSet<>();
	    Random random = new Random();
		//Recorre los personajes
	    for (Wizard wizard : wizards) {
	    	Character objective = target.pickTarget(random);
	    	
	    	if(objective == null) {
	    		return;
	    	}

			//Seleccion un hechizo aleatorio disponible
	        Spell spell = getRandomSpell(wizard, SpellType.SUPPORT, usedSpells);

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
			wizard.support(objective, spell.getName());
		}
		System.out.println("\n" + this.getName() + " Fin de supporteo");	
		System.out.println("###########################################################################\n");	
	}
	
	@Override
	public void support(Character target, String spellName) {
		System.out.println("###########################################################################");
		System.out.println(this.getName() + " Inicio de supporteo\n\n");	
		
		for(Wizard wizard : wizards) {
			try {
				wizard.support(target, spellName);				
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		System.out.println("\n" + this.getName() + " Fin de supporteo");	
		System.out.println("###########################################################################\n");	
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
	
	public int getBattalionSize() {
		return wizards.size();
	}

	public Character get(int index) {
		List<Wizard> wizardsList = new ArrayList<Wizard>(wizards);
	    return wizardsList.get(index);
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
	
	@Override
	public void electrocute(int electricDamage, int duration) {
		for(Wizard wizard : wizards) {
			wizard.electrocute(electricDamage, duration);
		}
	}
	
	@Override
	public void healing(int health) {
		for(Wizard wizard : wizards) {
			wizard.healing(health);
		}
	}
	
	@Override
	public void confuse(int duration) {
		for(Wizard wizard : wizards) {
			wizard.confuse(duration);
		}
	}
	
	@Override
	public Character pickTarget(Random random) {
		if(!this.isLive()) {
			return null;
		}
		Character character = null;
		do {
			character = get(random.nextInt(getBattalionSize()));
		} while (!character.isLive());
	    return character;
	}

	@Override
	public String toString() {
		return getName() + "\n[wizards=" + wizards + "]";
	}
	
	
}
