package stateCharacter;
// Representa el estado de un personaje electrificado.

import character.Character;
import spell.Spell;

public class Electrocuted extends StateCharacter {
	private int electricDamage;
	private int duration;
	
	public Electrocuted(int electricDamage, int duration) {
		this.electricDamage = electricDamage;
		this.duration = duration;
	}

	@Override
	public StateCharacter castSpell(Character caster, Character target, Spell spell) {
		spell.use(caster, target);
		
		duration --;
		System.out.println(caster.getName() + " recibio daño electrico");
		caster.healthDown(electricDamage);
		
		if(caster.getHealthPoints() == 0) {
			System.out.println(caster.getName() + " ha muerto");
			return new Death();
		} else {
			if(duration > 0) {
				System.out.println(caster.getName() + " seguira electrificado por " + duration + " turnos");
				return this;
			} else {
				System.out.println(caster.getName() + " dejo de estar electrocutado");
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
		System.out.println(character.getName() + " dejo de estar electrocutado");
		return new Idle();
	}
	
	@Override
	public StateCharacter stun(Character character, int duration) {
		System.out.println(character.getName() + "fue stuneado por " + duration + " turnos");
		return new Stuneed(duration);
	}
	
	@Override
	public StateCharacter invulnerability(Character character, int duration) {
		System.out.println(character.getName() + " dejo de estan electrocutado");
		return new Idle();
	}
	
	@Override
	public StateCharacter burnt(Character character, int fireDamage, int duration) {
		System.out.println(character.getName() + " recibio daño tecnico");
		
		character.healthDown(400);
		
		if(character.getHealthPoints() == 0) {
			System.out.println(character.getName() + " ha muerto");
			return new Death();
		} else {
			System.out.println(character.getName() + " dejo de electrocutarse");
			return new Idle();
		}
	}
	
	@Override
	public StateCharacter wounded(Character character, int bleendingDamage, int duration) {
		System.out.println(character.getName() + " recibio daño tecnico");
		
		character.healthDown(100);
		
		if(character.getHealthPoints() == 0) {
			System.out.println(character.getName() + " ha muerto");
			return new Death();
		} else {
			System.out.println(character.getName() + " dejo de electrocutarse");
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
