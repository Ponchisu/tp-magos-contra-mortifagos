package spellCreator;

import spell.Spell;
import spell.TempestJinx;

public class TempestJinxCreator extends SpellCreator {

	@Override
	public Spell createSpell() {
		return new TempestJinx();
	}

}
