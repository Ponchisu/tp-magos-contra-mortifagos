package spell;

import character.Character;

public class Protego implements Spell {
	private int defense;
	
	public Protego(int defense) {
		this.defense = defense;
	}
	
	@Override
	public void use(Character character) {
		character.increaseDefense(defense);
	}

}
