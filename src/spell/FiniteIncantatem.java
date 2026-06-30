package spell;

import character.Character;

public class FiniteIncantatem extends Spell {

	public FiniteIncantatem() {
		super("Finite Incantatem", SpellType.SUPPORT, SpellCategory.COUNTERSPELL, 1);
	}

	@Override
	public void use(Character caster, Character target) {
		
		System.out.println(caster.getName() + " le lanzó " + this.getName() + " a " + target.getName());
		
		
		if(caster.getAffinity(getCategory()) < 10) {
			if(Math.random() < 0.5) {
				/// CleanState
			}
		} else {
			// CleanState			
		}
	}
}
