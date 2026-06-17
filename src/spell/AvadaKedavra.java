package spell;

import character.Character;

public class AvadaKedavra implements Spell {
	public AvadaKedavra() {
	}

	@Override
	public void use(Character character) {
		if (Math.random() < 0.15) {
			character.receiveDamage(10000);
		}
	}

}
