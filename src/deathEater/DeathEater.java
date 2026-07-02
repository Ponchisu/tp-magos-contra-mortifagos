package deathEater;
// Representa a un mortífago dentro del combate.

import character.Character;
import character.CharacterType;
import spell.Spell;
import spell.SpellType;

public abstract class DeathEater extends Character {
	public DeathEater(String name, int magicLevel, int healthPoints, int defense, double accuracy) {
		super(name, magicLevel, healthPoints, defense, accuracy, CharacterType.DEATHEATER);
	}
	
	void attack(Character target) {
		
	}
	
	void specialSpell(Character target) {
		
		state = state.castSpell(this, target, spell);
	}
}
