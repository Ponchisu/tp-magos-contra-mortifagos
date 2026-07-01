package spell;

import character.Character;

public class Ferula extends Spell {
	private int healing;

	public Ferula() {
		super("Ferula", SpellType.SUPPORT, SpellCategory.HEAL, 1);
		healing = 20;
	}

	@Override
	public void use(Character caster, Character target) {

		System.out.println(caster.getName() + " le lanzó " + this.getName() + " a " + target.getName());
			
		target.healthUp((healing + (int)(caster.getMagicLevel() * 1.5) + caster.getAffinity(getCategory())));
	}
}
