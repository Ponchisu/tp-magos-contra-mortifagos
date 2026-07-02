package stateCharacter;
// Representa el estado neutral del personaje.

import character.Character;
import spell.Spell;

public class Idle extends StateCharacter {
	@Override
	public StateCharacter castSpell(Character caster, Character target, Spell spell) {
		
		spell.use(caster, target);
		
		return this;
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
	public StateCharacter stun(Character character, int duration) {
		System.out.println(character.getName() + " fue stuneado por " + duration + " turnos");
		return new Stuneed(duration);
	}
	
	@Override
	public StateCharacter invulnerability(Character character, int duration) {
		System.out.println(character.getName() + " es invulnerable por " + duration + " turnos");
		return new Invulnerable(duration);
	}
	
	@Override
	public StateCharacter burnt(Character character, int fireDamage, int duration) {
		System.out.println(character.getName() + " esta incendiado por " + duration + " turnos");
		return new Burned(fireDamage, duration);
	}
	
	@Override
	public StateCharacter wounded(Character character, int bleendingDamage, int duration) {
		System.out.println(character.getName() + " esta sangrando por " + duration + " turnos");
		return new Wounded(bleendingDamage, duration);
	}
	
	@Override
	public StateCharacter electrocute(Character character, int electricDamage, int duration) {
		System.out.println(character.getName() + " esta electrocutado por " + duration + " turnos");
		return new Electrocuted(electricDamage, duration);
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
	
	@Override
	public String toString() {
		return "";
	}
}
