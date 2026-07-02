package spell;
// Implementa un hechizo que inmoviliza al objetivo.

import character.Character;

public class PetrificusTotalus extends Spell {
	private int stunDuration;
	
	public PetrificusTotalus() {
		super("Pertificus Totalus", SpellType.SPECIAL, SpellCategory.AILMENTS, 0.95);
		stunDuration = 1;
	}

	@Override
	public void use(Character caster, Character target) {
		if(Math.random() < (this.getAccuracy() + caster.getAccuracy()) / 2) {
			System.out.println(caster.getName() + " le lanzó " + this.getName() + " a " + target.getName());
			
			target.stun(stunDuration + (target.getAffinity(getCategory()) / 10));
			
		} else {
			System.out.println(caster.getName() + " no le acerto " + this.getName() + " a " + target.getName());
		}
	}
}
