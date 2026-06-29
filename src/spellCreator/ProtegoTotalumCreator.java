package spellCreator;

import spell.Spell;
import spell.ProtegoTotalum;

public class ProtegoTotalumCreator extends SpellCreator {

	@Override
	public Spell createSpell() {
		return new ProtegoTotalum();
	}

}
