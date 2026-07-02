package stateCharacter;
// Representa el estado de un personaje quemado por daño de fuego.

import character.Character;
import spell.Spell;

public class Burned extends StateCharacter {
	private int fireDamage;
	private int duration;
	
	public Burned(int fireDamage, int duration) {
		this.fireDamage = fireDamage;
		this.duration = duration;
	}

	@Override
	public StateCharacter castSpell(Character caster, Character target, Spell spell) {
		spell.use(caster, target);
		
		duration --;
		System.out.println(caster.getName() + " recibio de daño fuego");
		caster.healthDown(fireDamage);
		
		if(caster.getHealthPoints() == 0) {
			System.out.println(caster.getName() + " ha muerto");
			return new Death();
		} else {
			if(duration > 0) {
				System.out.println(caster.getName() + " seguira incendiado por " + duration + " turnos");
				return this;
			} else {
				System.out.println(caster.getName() + " dejo de estar incendiado");
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
		System.out.println(character.getName() + " recibio daño tecnico");
		
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
		System.out.println(character.getName() + " recibio daño tecnico");
		
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
