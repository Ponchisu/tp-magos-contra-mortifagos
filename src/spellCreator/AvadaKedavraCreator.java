package spellCreator;

import spell.Spell;
import spell.AvadaKedavra;

public class AvadaKedavraCreator extends SpellCreator {

	@Override
	public Spell createSpell() {
		return new AvadaKedavra();
	}

}
