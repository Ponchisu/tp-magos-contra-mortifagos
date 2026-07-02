package spell;
// Implementa un hechizo que causa un efecto ligero y peculiar.

import character.Character;

public class Titillando extends Spell {
	private int defense;
	
	public Titillando() {
		super("Protego", SpellType.SPECIAL, SpellCategory.DEFENSE, 1);
		this.defense = 40;
	}
	
	@Override
	public void use(Character caster, Character target) {
		
		System.out.println(caster.getName() + " le lanzó " + this.getName() + " a " + target.getName());
		
		target.increaseDefense(defense + (caster.getAffinity(getCategory()) / 5) * caster.getMagicLevel(), 3);
	}
}
