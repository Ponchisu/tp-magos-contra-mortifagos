package spellCreator;

import spell.Spell;
import spell.Confundo;

public class ConfundoCreator extends SpellCreator {

	@Override
	public Spell createSpell() {
		return new Confundo();
	}

}
