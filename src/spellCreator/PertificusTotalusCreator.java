package spellCreator;

import spell.Spell;
import spell.PertificusTotalus;

public class PertificusTotalusCreator extends SpellCreator {

	@Override
	public Spell createSpell() {
		return new PertificusTotalus();
	}

}
