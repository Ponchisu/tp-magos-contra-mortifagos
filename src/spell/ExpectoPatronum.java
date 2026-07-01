package spell;

import character.Character;

public class ExpectoPatronum extends Spell {
	private int stunDuration;
	
	public ExpectoPatronum() {
		super("ExpectoPatronum", SpellType.SUPPORT, SpellCategory.LIGHT, 0.95);
		stunDuration = 1;
	}

	@Override
	public void use(Character caster, Character target) {
		if(Math.random() < (this.getAccuracy() + caster.getAccuracy()) / 2) {
			System.out.println(caster.getName() + " le lanzó " + this.getName() + " a " + target.getName());
			
			target.stun(stunDuration);
			
		} else {
			System.out.println(caster.getName() + " no le acerto " + this.getName() + " a " + target.getName());
		}
	}
}
