package spell;

import character.Character;

public class Confundo extends Spell {

	public Confundo() {
		super("Confundo", SpellType.OFFENSIVE, SpellCategory.LIGHT, 0.65);
	}

	@Override
	void use(Character caster, Character target) {
		if(Math.random() < this.getAccuracy()) {
			System.out.println(caster.getName() + " le lanzó " + this.getName() + " a " + target.getName());
			
			if (caster.getAffinity(getCategory()) < 30) {
				// Invulnerable por 1 turno
			} else {
				// Invulnerable por 3 turnos
			}
			
		} else {
			System.out.println(caster.getName() + " no le acerto " + this.getName() + " a " + target.getName());
		}
	}
}
