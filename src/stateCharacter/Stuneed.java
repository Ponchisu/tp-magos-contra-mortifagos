package stateCharacter;
// Representa el estado de aturdimiento del personaje.

import character.Character;
import spell.Spell;

public class Stuneed extends StateCharacter {
	private int duration;
	
	public Stuneed(int duration) {
		this.duration = duration;
	}

	@Override
	public StateCharacter castSpell(Character caster, Character target, Spell spell) {
		System.out.println("No puedes atacar estando stuneado");
		
		duration --;
		
		if(duration > 0) {
			System.out.println(caster.getName() + " seguira stuneado por " + duration + " turnos");
			return this;
		} else {
			System.out.println(caster.getName() + " dejo de estar stuneado");
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
	
	public StateCharacter cleanState(Character character) {
		System.out.println(character.getName() + " dejo de estar stuneado");
		return new Idle();
	}
	
	@Override
	public StateCharacter invulnerability(Character character, int duration) {
		System.out.println(character.getName() + " dejo de estar stuneado");
		return new Idle();
	}
	
	@Override
	public StateCharacter burnt(Character character, int fireDamage, int duration) {
		System.out.println(character.getName() + " recibio daño de fuego");
		
		character.healthDown(fireDamage);
		
		if(character.getHealthPoints() == 0) {
			System.out.println(character.getName() + " ah muerto");
			return new Death();
		} else {
			return this;
		}
	}
	
	@Override
	public StateCharacter wounded(Character character, int bleendingDamage, int duration) {
		System.out.println(character.getName() + " recibio daño de sangrado");
		
		character.healthDown(bleendingDamage);
		
		if(character.getHealthPoints() == 0) {
			System.out.println(character.getName() + " ah muerto");
			return new Death();
		} else {
			return this;
		}
	}
	
	@Override
	public StateCharacter electrocute(Character character, int electricDamage, int duration) {
		System.out.println(character.getName() + " recibio daño electrico");
		
		character.healthDown(electricDamage);
		
		if(character.getHealthPoints() == 0) {
			System.out.println(character.getName() + " ah muerto");
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
	
	@Override
	public String toString() {
		return "Stuneado";
	}
}
