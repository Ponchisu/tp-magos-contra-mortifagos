package wizard;
// Representa a un mago con sus habilidades y atributos.

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import character.Character;
import character.CharacterType;
import spell.Spell;
import spell.SpellType;

public abstract class Wizard extends Character {
	public Wizard(String name, int magicLevel, int healthPoints, int defense, double accuracy) {
		super(name, magicLevel, healthPoints, defense, accuracy, CharacterType.WIZARD);
	}

	public void support(Character target) {
		List<Spell> spells = new ArrayList<Spell>(this.getSpells());
		Random random = new Random();
		System.out.println("###########################################################################\n");		
		state = state.castSpell(this, target, spells.get(random.nextInt(spells.size())));
		System.out.println("");
	}
	
	public void support(Character target, String spellName) {
		Spell spell = validCastSpell(target, spellName, SpellType.SUPPORT);
		System.out.println("###########################################################################\n");		
		state = state.castSpell(this, target, spell);
		System.out.println("");
	}
}
