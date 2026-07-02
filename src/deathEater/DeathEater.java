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
	
	void specialSpell(Character target, String spellName) {
		Spell spell = validCastSpell(target, spellName, SpellType.SUPPORT);
		
		state = state.castSpell(this, target, spell);
	}
}
