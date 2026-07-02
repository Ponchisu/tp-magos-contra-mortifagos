package wizard;
// Representa a un mago con sus habilidades y atributos.

import character.Character;
import character.CharacterType;
import spell.Spell;
import spell.SpellType;

public abstract class Wizard extends Character {
	public Wizard(String name, int magicLevel, int healthPoints, int defense, double accuracy) {
		super(name, magicLevel, healthPoints, defense, accuracy, CharacterType.WIZARD);
	}
	
	void attack(Character target) {
		
	}

	void support(Character target) {
		
		state = state.castSpell(this, target, spell);
	}
}
