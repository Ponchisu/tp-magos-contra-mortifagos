package spellCreator;

import spell.Spell;
import spell.FiniteIncantatem;

public class FiniteIncantatemCreator extends SpellCreator {

	@Override
	public Spell createSpell() {
		return new FiniteIncantatem();
	}

}
