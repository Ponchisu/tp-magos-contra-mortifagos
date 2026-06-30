package stateCharacter;

import character.Character;
import exceptions.AutoAttackException;
import exceptions.SpellTypeException;
import spell.Spell;
import spell.SpellType;

public class Invulnerable extends StateCharacter {
	private int duration;

	public Invulnerable(int duration) {
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
		
		if(duration > 0) {
			System.out.println(attacker.getName() + " seguira siendo invulnerable por " + duration + " turnos");
			return this;
		} else {
			System.out.println(attacker.getName() + " dejo de ser invulnerable");
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
		
		spell.use(support, target);
		
		duration --;
		
		if(duration > 0) {
			System.out.println(support.getName() + " seguira siendo invulnerable por " + duration + " turnos");
			return this;
		} else {
			System.out.println(support.getName() + " dejo de ser invulnerable");
			return new Idle();
		}
	}
	
	@Override
	public StateCharacter receiveDamage(Character character, int damage) {
		System.out.println(character.getName() + " detuvo el ataque");
		System.out.println(character.getName() + " ya no es invulnerable");
		
		return new Idle();
	}
	
	@Override
	public StateCharacter stun(Character character, int duration) {
		System.out.println(character.getName() + "detuvo el stun");
		System.out.println(character.getName() + " ya no es invulnerable");
		
		return new Idle();
	}
	
	@Override
	public StateCharacter burnt(Character character, int fireDamage, int duration) {
		System.out.println(character.getName() + " detuvo la quemadura");
		System.out.println(character.getName() + " ya no es invulnerable");
		
		return new Idle();
	}
	
	@Override
	public StateCharacter wounded(Character character, int bleendingDamage, int duration) {
		System.out.println(character.getName() + " detuvo el sangrado");
		System.out.println(character.getName() + " ya no es invulnerable");
		
		return new Idle();
	}
	
	@Override
	public StateCharacter electrocute(Character character, int electricDamage, int duration) {
		System.out.println(character.getName() + " detuvo la electricidad");
		System.out.println(character.getName() + " ya no es invulnerable");
		
		return new Idle();
	}
	
	@Override
	public StateCharacter healing(Character character, int health) {
		character.healthUp(health);
		return this;
	}
	
	@Override
	public StateCharacter confuse(Character character, int duration) {
		System.out.println(character.getName() + "detuvo la confusion");
		System.out.println(character.getName() + " ya no es invulnerable");
		
		return new Idle();
	}
}
