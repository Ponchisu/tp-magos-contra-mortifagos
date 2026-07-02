package spell;
// Implementa un hechizo de disipación o contrainte.

import character.Character;

public class FiniteIncantatem extends Spell {

	public FiniteIncantatem() {
		super("Finite Incantatem", SpellType.SUPPORT, SpellCategory.COUNTERSPELL, 1);
	}

	@Override
	public void use(Character caster, Character target) {
		
		System.out.println(caster.getName() + " le lanzó " + this.getName() + " a " + target.getName());
		
		
		if(caster.getAffinity(getCategory()) < 30) {
			if(Math.random() < 0.5) {
				target.cleanState();
			}
		} else {
			target.cleanState();
		}
	}
}
