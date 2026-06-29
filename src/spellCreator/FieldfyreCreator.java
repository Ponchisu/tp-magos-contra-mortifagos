package spellCreator;

import spell.Spell;
import spell.Fieldfyre;

public class FieldfyreCreator extends SpellCreator {

	@Override
	public Spell createSpell() {
		return new Fieldfyre();
	}

}
