package StateCharacter;

import character.Character;

public abstract class StateCharacter {
	public abstract StateCharacter attack(Character attacker, Character target, String spellName);
	
	public abstract StateCharacter support(Character support, Character target, String spellName);
	
	public boolean isLive() {
		return true;
	}
	
	public StateCharacter receiveDamage(Character character, int damage) {
		return this;
	}
	
	public StateCharacter cleanState(Character character) {
		return this;
	}
	
	public StateCharacter stun(Character character, int duration) {
		return this;
	}
	
	public StateCharacter invulnerability(Character character, int duration) {
		return this;
	}
	
	public StateCharacter burnt(Character character, int fireDamage, int duration) {
		return this;
	}
	
	public StateCharacter wounded(Character character, int bleendingDamage, int duration) {
		return this;
	}
	
	public StateCharacter electrocute(Character character, int electricDamage, int duration) {
		return this;
	}
	
	public StateCharacter healing(Character character, int health) {
		return this;
	}
	
	public StateCharacter confuse(Character character, int duration) {
		return this;
	}
}
