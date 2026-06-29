package spell;

import character.Character;

public class Expelliarmus extends Spell {
	private int damage;
	
	public Expelliarmus() {
		super("Expelliarmus", SpellType.OFFENSIVE, SpellCategory.LIGHT, 0.8);
		damage = 25;
	}

	@Override
	public void use(Character caster, Character target) {
		if(Math.random() < (this.getAccuracy() + caster.getAccuracy()) / 2) {
			System.out.println(caster.getName() + " le lanzó " + this.getName() + " a " + target.getName());
			
			target.receiveDamage(damage + (caster.getAffinity(getCategory()) / 20) * caster.getMagicLevel());
			
		} else {
			System.out.println(caster.getName() + " no le acerto " + this.getName() + " a " + target.getName());
		}
	}
}
