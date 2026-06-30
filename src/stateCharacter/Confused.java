package stateCharacter;

import character.Character;
import exceptions.AutoAttackException;
import exceptions.SpellTypeException;
import spell.Spell;
import spell.SpellType;

public class Confused extends StateCharacter {
	private int duration;
	
	public Confused(int duration) {
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
		
		duration --;
		System.out.println(attacker.getName() + " esta confundido");
		spell.use(attacker, attacker);
		
		if(duration > 0) {
			System.out.println(attacker.getName() + " seguira confundido por " + duration + " turnos");
			return this;
		} else {
			System.out.println(attacker.getName() + " dejo de estar confundido");
			return new Idle();
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
		
		duration --;
		System.out.println(support.getName() + " esta confundido");
		spell.use(support, support);
		
		if(duration > 0) {
			System.out.println(support.getName() + " seguira confundido por " + duration + " turnos");
			return this;
		} else {
			System.out.println(support.getName() + " dejo de estar confundido");
			return new Idle();
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
		System.out.println(character.getName() + " dejo de estar confundido");
		return new Idle();
	}
	
	@Override
	public StateCharacter stun(Character character, int duration) {
		System.out.println(character.getName() + " no fue stuneado ya que esta confundido por " + this.duration + " turnos");
		return this;
	}
	
	@Override
	public StateCharacter invulnerability(Character character, int duration) {
		System.out.println(character.getName() + " no es invulnerable ya que esta confundido por " + this.duration + " turnos");
		return this;
	}

	@Override
	public StateCharacter burnt(Character character, int fireDamage, int duration) {
		System.out.println(character.getName() + " recibio " + fireDamage + " de daño de fuego");
		
		character.healthDown(fireDamage);
		
		if(character.getHealthPoints() == 0) {
			System.out.println(character.getName() + " ha muerto");
			return new Death();
		} else {
			return this;
		}
	}
	
	@Override
	public StateCharacter wounded(Character character, int bleendingDamage, int duration) {
		System.out.println(character.getName() + " recibio " + bleendingDamage + " de daño de sangrado");
		
		character.healthDown(bleendingDamage);
		
		if(character.getHealthPoints() == 0) {
			System.out.println(character.getName() + " ha muerto");
			return new Death();
		} else {
			return this;
		}
	}
	
	@Override
	public StateCharacter electrocute(Character character, int electricDamage, int duration) {
		System.out.println(character.getName() + " recibio " + electricDamage + " de daño electrico");
		
		character.healthDown(electricDamage);
		
		if(character.getHealthPoints() == 0) {
			System.out.println(character.getName() + " ha muerto");
			return new Death();
		} else {
			return this;
		}
	}
	
	@Override
	public StateCharacter healing(Character character, int health) {
		character.healthUp(health);
		return this;
	}
}
