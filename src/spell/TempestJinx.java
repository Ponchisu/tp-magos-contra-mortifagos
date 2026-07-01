package spell;

import character.Character;

public class TempestJinx extends Spell {
	private int damage;
	private int electricDamage;
	
	public TempestJinx() {
		super("Tempest Jinx", SpellType.OFFENSIVE, SpellCategory.LIGHT, 0.7);
		damage = 35;
	}

	@Override
	public void use(Character caster, Character target) {
		if(Math.random() < (this.getAccuracy() + caster.getAccuracy()) / 2) {
			System.out.println(caster.getName() + " le acerto " + this.getName() + " a " + target.getName());
			
			target.receiveDamage(damage + 2 * caster.getMagicLevel());
			
			if(Math.random() < (caster.getAffinity(getCategory()) / 10)) {
				target.electrocute(electricDamage + caster.getMagicLevel() / 2, 1 + caster.getAffinity(getCategory()) / 10);
			}
			
		} else {
			System.out.println(caster.getName() + " no le acerto " + this.getName() + " a " + target.getName());
		}
	}
}
