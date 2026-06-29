package spellCreator;

import spell.Spell;
import spell.ExpectoPatronum;

public class ExpectoPatronumCreator extends SpellCreator {

	@Override
	public Spell createSpell() {
		return new ExpectoPatronum();
	}

}
