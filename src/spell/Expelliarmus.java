package spell;

import character.Character;

public class Expelliarmus implements Spell {
	private double damage;
	
	public Expelliarmus(double damage) {
		this.damage = damage;
	}

	@Override
	public void use(Character character) {
		character.receiveDamage(damage);
	}
}
