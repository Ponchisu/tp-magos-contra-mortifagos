package spell;
// Implementa el hechizo Avada Kedavra como ataque letal.

import character.Character;

public class AvadaKedavra extends Spell {
	private int damage;
	
	public AvadaKedavra() {
		super("AvadaKedavra", SpellType.OFFENSIVE, SpellCategory.DARK, 0.15);
		damage = 400;
	}

	@Override
	public void use(Character caster, Character target) {
		if (Math.random() < getAccuracy()) {
			System.out.println(caster.getName() + " le lanzó " + this.getName() + " a " + target.getName());
			
			if(caster.getAffinity(getCategory()) > 10) {
				target.decreaseDefense(0, 1);
				target.increaseDefense(0, 1);
				target.decreaseDefense(target.getDefense(), 1);
				target.receiveDamage(target.getHealthPoints());
			} else {
				target.receiveDamage(damage);
			}
			
		} else {
			System.out.println(caster.getName() + " no le acerto " + this.getName() + " a " + target.getName());
		}
	}
}
