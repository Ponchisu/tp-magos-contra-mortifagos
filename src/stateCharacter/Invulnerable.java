package stateCharacter;
// Representa el estado de invulnerabilidad del personaje.

import character.Character;
import spell.Spell;

public class Invulnerable extends StateCharacter {
	private int duration;

	public Invulnerable(int duration) {
		this.duration = duration;
	}

	@Override
	public StateCharacter castSpell(Character caster, Character target, Spell spell) {
		spell.use(caster, target);
		
		duration --;
		
		if(duration > 0) {
			System.out.println(caster.getName() + " seguira siendo invulnerable por " + duration + " turnos");
			return this;
		} else {
			System.out.println(caster.getName() + " dejo de ser invulnerable");
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
	
	@Override
	public String toString() {
		return "invulnerable";
	}
}
