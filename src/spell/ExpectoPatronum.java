package spell;

import character.Character;

public class ExpectoPatronum implements Spell {
	private double healing;
	
	public ExpectoPatronum(double healing) {
		this.healing = healing;
	}

	@Override
	public void use(Character character) {
		character.healthUp(healing);			
	}
}
