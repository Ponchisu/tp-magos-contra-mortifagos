package spellCreator;

import spell.Spell;
import spell.Sectumsempra;

public class SectumsempraCreator extends SpellCreator {

	@Override
	public Spell createSpell() {
		return new Sectumsempra();
	}

}
