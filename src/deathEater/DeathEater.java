package deathEater;
// Representa a un mortífago dentro del combate.

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import character.Character;
import character.CharacterType;
import spell.Spell;
import spell.SpellType;

public abstract class DeathEater extends Character {
	public DeathEater(String name, int magicLevel, int healthPoints, int defense, double accuracy) {
		super(name, magicLevel, healthPoints, defense, accuracy, CharacterType.DEATHEATER);
	}
	
	public void specialSpell(Character target) {
		List<Spell> spells = new ArrayList<Spell>(this.getSpells());
		Random random = new Random();
		System.out.println("###########################################################################");
		System.out.println(this.getName() + " Inicio de ataque especial\n\n");		
		state = state.castSpell(this, target, spells.get(random.nextInt(spells.size())));
		System.out.println("\n" + this.getName() + " Fin de ataque especial");	
		System.out.println("###########################################################################\n");
	}
	
	public void specialSpell(Character target, String spellName) {
		Spell spell = validCastSpell(target, spellName, SpellType.SPECIAL);
		System.out.println("###########################################################################");
		System.out.println(this.getName() + " Inicio de ataque especial\n\n");			
		state = state.castSpell(this, target, spell);
		System.out.println("\n" + this.getName() + " Fin de ataque especial");	
		System.out.println("###########################################################################\n");
	}
}
