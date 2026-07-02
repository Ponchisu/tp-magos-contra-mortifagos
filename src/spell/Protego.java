package spell;
// Implementa un hechizo defensivo de protección básica.

import character.Character;

public class Protego extends Spell {
	private int defense;
	
	public Protego() {
		super("Protego", SpellType.SUPPORT, SpellCategory.DEFENSE, 1);
		this.defense = 1;
	}
	
	@Override
	public void use(Character caster, Character target) {
		
		System.out.println(caster.getName() + " le lanzó " + this.getName() + " a " + target.getName());
		
		target.increaseDefense(defense + (caster.getAffinity(getCategory()) / 5), 3);
	}
}
