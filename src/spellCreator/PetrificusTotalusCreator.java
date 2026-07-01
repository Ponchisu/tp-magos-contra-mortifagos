package spellCreator;

import spell.Spell;
import spell.PetrificusTotalus;

public class PetrificusTotalusCreator extends SpellCreator {

	@Override
	public Spell createSpell() {
		return new PetrificusTotalus();
	}

}
