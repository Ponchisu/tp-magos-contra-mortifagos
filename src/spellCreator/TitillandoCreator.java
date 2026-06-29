package spellCreator;

import spell.Spell;
import spell.Titillando;

public class TitillandoCreator extends SpellCreator {

	@Override
	public Spell createSpell() {
		return new Titillando();
	}

}
