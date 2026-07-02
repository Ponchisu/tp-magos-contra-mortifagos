package stateCharacter;
// Representa el estado de muerte de un personaje.

import character.Character;
import spell.Spell;

public class Death extends StateCharacter {
	@Override
	public StateCharacter castSpell(Character caster, Character target, Spell spell) {
		return this;
	}
	
	@Override
	public boolean isLive() {
		return false;
	}

}
