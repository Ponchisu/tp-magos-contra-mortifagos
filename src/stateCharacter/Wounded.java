package stateCharacter;

import character.Character;
import exceptions.AutoAttackException;
import exceptions.SpellTypeException;
import spell.Spell;
import spell.SpellType;

public class Wounded extends StateCharacter {
	private int bleendingDamage;
	private int duration;
	
	public Wounded(int bleendingDamage, int duration) {
		this.bleendingDamage = bleendingDamage;
		this.duration = duration;
	}

	@Override
	public StateCharacter attack(Character attacker, Character target, String spellName) {
		if(attacker == null || target == null) {
			throw new IllegalArgumentException("Los personajes no son validos");
		}
		
		Spell spell = attacker.getSpell(spellName);
		
		if(spell == null) {
			throw new IllegalArgumentException("El hechizo no es valido");
		}
		
		if(attacker == target) {
			throw new AutoAttackException("No pueden auto atacarte");
		}
		
		if(spell.getType() == SpellType.SUPPORT) {
			throw new SpellTypeException("No pueden supportear a los rivales");
		}
		
		spell.use(attacker, target);
		
		duration --;
		System.out.println(attacker.getName() + " recibio " + bleendingDamage + " de daño sangrado");
		attacker.healthDown(bleendingDamage);
		
		if(attacker.getHealthPoints() == 0) {
			System.out.println(attacker.getName() + " ha muerto");
			return new Death();
		} else {
			if(duration > 0) {
				System.out.println(attacker.getName() + " seguira sangrando por " + duration + " turnos");
				return this;
			} else {
				System.out.println(attacker.getName() + " dejo de estar sangrando");
				return new Idle();
			}
		}
	}

	@Override
	public StateCharacter support(Character support, Character target, String spellName) {
		if(support == null || target == null) {
			throw new IllegalArgumentException("Los personajes no son validos");
		}
		
		Spell spell = support.getSpell(spellName);
		
		if(spell == null) {
			throw new IllegalArgumentException("El hechizo no es valido");
		}
		
		if(support == target) {
			throw new AutoAttackException("No pueden auto atacarte");
		}
		
		if(spell.getType() == SpellType.OFFENSIVE) {
			throw new SpellTypeException("No pueden atacar a los aliados");
		}
		
		spell.use(support, target);
		
		duration --;
		System.out.println(support.getName() + " recibio " + bleendingDamage + " de daño sangrado");
		support.healthDown(bleendingDamage);
		
		if(support.getHealthPoints() == 0) {
			System.out.println(support.getName() + " ha muerto");
			return new Death();
		} else {
			if(duration > 0) {
				System.out.println(support.getName() + " seguira sangrando por " + duration + " turnos");
				return this;
			} else {
				System.out.println(support.getName() + " dejo de estar sangrando");
				return new Idle();
			}
		}
	}

	@Override
	public StateCharacter receiveDamage(Character character, int damage) {
		character.healthDown(damage);
		
		if(character.getHealthPoints() == 0) {
			System.out.println(character.getName() + " ha muerto");
			return new Death();
		} else {
			return this;
		}
	}
	
	@Override
	public StateCharacter cleanState(Character character) {
		System.out.println(character.getName() + " dejo de estar sangrando");
		return new Idle();
	}
	
	@Override
	public StateCharacter stun(Character character, int duration) {
		System.out.println(character.getName() + "fue stuneado por " + duration + " turnos");
		return new Stuneed(duration);
	}
	
	@Override
	public StateCharacter invulnerability(Character character, int duration) {
		System.out.println(character.getName() + " dejo de estar sangrando");
		return new Idle();
	}
	
	@Override
	public StateCharacter burnt(Character character, int fireDamage, int duration) {
		System.out.println(character.getName() + " recibio 100 de daño de tecnico");
		
		character.healthDown(100);
		
		if(character.getHealthPoints() == 0) {
			System.out.println(character.getName() + " ha muerto");
			return new Death();
		} else {
			System.out.println(character.getName() + " dejo estar sangrando");
			return new Idle();
		}
	}
	
	@Override
	public StateCharacter electrocute(Character character, int electricDamage, int duration) {
		System.out.println(character.getName() + " recibio 100 de daño de tecnico");
		
		character.healthDown(100);
		
		if(character.getHealthPoints() == 0) {
			System.out.println(character.getName() + " ha muerto");
			return new Death();
		} else {
			System.out.println(character.getName() + " dejo de estar sangrando");
			return new Idle();
		}
	}
	
	@Override
	public StateCharacter healing(Character character, int health) {
		character.healthUp(health);
		return this;
	}
	
	@Override
	public StateCharacter confuse(Character character, int duration) {
		System.out.println(character.getName() + " esta confundido por " + duration + " turnos");
		return new Confused(duration);
	}
}
