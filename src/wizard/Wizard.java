package wizard;

import character.Character;

public interface Wizard {
	void attack(Character target, String spellName);
	void support(Character target, String spellName);
}
