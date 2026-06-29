package spell;

import character.Character;

public class Crucio extends Spell {
	private int damage;
	
	public Crucio() {
		super("Crucio", SpellType.OFFENSIVE, SpellCategory.DARK, 0.95);
		damage = 20;
	}

	@Override
	void use(Character caster, Character target) {
		if(Math.random() < (this.getAccuracy() + caster.getAccuracy()) / 2) {
			System.out.println(caster.getName() + " le lanzó " + this.getName() + " a " + target.getName());
			
			target.receiveDamage(damage + caster.getAffinity(getCategory()) * 1.7 + caster.getMagicLevel() / 3);
			
		} else {
			System.out.println(caster.getName() + " no le acerto " + this.getName() + " a " + target.getName());
		}		
	}
}
