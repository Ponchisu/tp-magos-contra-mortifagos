package stateCharacter;
// Representa el estado de herida o sangrado del personaje.

import character.Character;
import spell.Spell;

public class Wounded extends StateCharacter {
	private int bleendingDamage;
	private int duration;
	
	public Wounded(int bleendingDamage, int duration) {
		this.bleendingDamage = bleendingDamage;
		this.duration = duration;
	}

	@Override
	public StateCharacter castSpell(Character attacker, Character target, Spell spell) {
		spell.use(attacker, target);
		
		duration --;
		System.out.println(attacker.getName() + " recibio daño sangrado");
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
		System.out.println(character.getName() + " recibio daño tecnico");
		
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
		System.out.println(character.getName() + " recibio daño tecnico");
		
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
