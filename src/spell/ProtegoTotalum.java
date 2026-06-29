package spell;

import character.Character;

public class ProtegoTotalum  extends Spell {
	public ProtegoTotalum() {
		super("Protego Totalum", SpellType.SUPPORT, SpellCategory.COUNTERSPELL, 1);
	}

	@Override
	void use(Character caster, Character target) {
		
		System.out.println(caster.getName() + " le lanzó " + this.getName() + " a " + target.getName());	
		
		if (caster.getAffinity(getCategory()) < 25) {
			// Invulnerable por 1 turno
		} else {
			// Invulnerable por 3 turnos
		}
	}
}
