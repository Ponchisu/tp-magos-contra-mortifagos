package stateCharacter;
// Representa el estado de confusión temporal del personaje.

import character.Character;
import spell.Spell;

public class Confused extends StateCharacter {
	private int duration;
	
	public Confused(int duration) {
		this.duration = duration;
	}
	
	@Override
	public StateCharacter castSpell(Character caster, Character target, Spell spell) {
		duration --;
		System.out.println(caster.getName() + " esta confundido");
		spell.use(caster, caster);
		
		if(duration > 0) {
			System.out.println(caster.getName() + " seguira confundido por " + duration + " turnos");
			return this;
		} else {
			System.out.println(caster.getName() + " dejo de estar confundido");
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
		System.out.println(character.getName() + " recibio daño de fuego");
		
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
		System.out.println(character.getName() + " recibio daño de sangrado");
		
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
		System.out.println(character.getName() + " recibio de daño electrico");
		
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
	
	@Override
	public String toString() {
		return "Confundido";
	}
}
