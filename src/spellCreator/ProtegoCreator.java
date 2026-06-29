package spellCreator;

import spell.Spell;
import spell.Protego;

public class ProtegoCreator extends SpellCreator {

	@Override
	public Spell createSpell() {
		return new Protego();
	}

}
