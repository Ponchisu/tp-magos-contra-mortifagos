package stateCharacter;

import character.Character;
import exceptions.AutoAttackException;
import exceptions.SpellTypeException;
import spell.Spell;
import spell.SpellType;

public class Burned extends StateCharacter {
	private int fireDamage;
	private int duration;
	
	public Burned(int fireDamage, int duration) {
		this.fireDamage = fireDamage;
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
		System.out.println(attacker.getName() + " recibio " + fireDamage + " de daño fuego");
		attacker.healthDown(fireDamage);
		
		if(attacker.getHealthPoints() == 0) {
			System.out.println(attacker.getName() + " ha muerto");
			return new Death();
		} else {
			if(duration > 0) {
				System.out.println(attacker.getName() + " seguira incendiado por " + duration + " turnos");
				return this;
			} else {
				System.out.println(attacker.getName() + " dejo de estar incendiado");
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
		System.out.println(support.getName() + " recibio " + fireDamage + " de daño fuego");
		support.healthDown(fireDamage);
		
		if(support.getHealthPoints() == 0) {
			System.out.println(support.getName() + " ha muerto");
			return new Death();
		} else {
			if(duration > 0) {
				System.out.println(support.getName() + " seguira incendiado por " + duration + " turnos");
				return this;
			} else {
				System.out.println(support.getName() + " dejo de estar incendiado");
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
	
	public StateCharacter cleanState(Character character) {
		System.out.println(character.getName() + " dejo de estar incendiado");
		return new Idle();
	}
	
	@Override
	public StateCharacter stun(Character character, int duration) {
		System.out.println(character.getName() + "fue stuneado por " + duration + " turnos");
		return new Stuneed(duration);
	}
	
	@Override
	public StateCharacter invulnerability(Character character, int duration) {
		System.out.println(character.getName() + " dejo de estar incendiado");
		return new Idle();
	}
	
	@Override
	public StateCharacter wounded(Character character, int bleendingDamage, int duration) {
		System.out.println(character.getName() + " recibio 100 de daño de tecnico");
		
		character.healthDown(100);
		
		if(character.getHealthPoints() == 0) {
			System.out.println(character.getName() + " ha muerto");
			return new Death();
		} else {
			System.out.println(character.getName() + " dejo de estar incendiado");
			return new Idle();
		}
	}
	
	@Override
	public StateCharacter electrocute(Character character, int electricDamage, int duration) {
		System.out.println(character.getName() + " recibio 400 de daño de tecnico");
		
		character.healthDown(400);
		
		if(character.getHealthPoints() == 0) {
			System.out.println(character.getName() + " ha muerto");
			return new Death();
		} else {
			System.out.println(character.getName() + " dejo de estar incendiado");
			return new Idle();
		}
	}
	
	@Override
	public StateCharacter healing(Character character, int health) {
		System.out.println(character.getName() + " no puede curarse ya que esta incendiado");
		
		duration --;
		
		if(duration > 0) {
			System.out.println(character.getName() + " seguira incendiado por " + duration + " turnos");
			return this;
		} else {
			System.out.println(character.getName() + " dejo de estar incendiado");
			return new Idle();
		}
	}
	
	@Override
	public StateCharacter confuse(Character character, int duration) {
		System.out.println(character.getName() + " esta confundido por " + duration + " turnos");
		return new Confused(duration);
	}
}
