package spell;

import character.Character;

public class Sectumsempra extends Spell {
	private int damage;
	private int bleedingDamage;

	public Sectumsempra() {
		super("Sectumsempra", SpellType.OFFENSIVE, SpellCategory.DARK, 0.6);
		damage = 10;
		bleedingDamage = 2;
	}

	@Override
	public void use(Character caster, Character target) {
		if(Math.random() < (this.getAccuracy() + caster.getAccuracy()) / 2) {
			System.out.println(caster.getName() + " le lanzó " + this.getName() + " a " + target.getName());
			
			target.receiveDamage(damage + caster.getMagicLevel() + caster.getAffinity(getCategory()) * 4);
			
			// Wounded -- bleedingDamage + caster.getMagicLevel() / 2 -- 1 + caster.getAffinity(getCategory()) / 10
			
		} else {
			System.out.println(caster.getName() + " no le acerto " + this.getName() + " a " + target.getName());
		}		
	}
}
