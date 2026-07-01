package wizard;

import java.util.Random;

import character.Character;
import character.CharacterType;
import spell.SpellCategory;
import spellCreator.ExpectoPatronumCreator;
import spellCreator.ExpelliarmusCreator;
import spellCreator.FerulaCreator;
import spellCreator.ProtegoCreator;
import spellCreator.ProtegoTotalumCreator;
import spellCreator.SpellCreator;

public class WizardStudent extends Character implements Wizard {
	public WizardStudent() {
		Random random = new Random();
		SpellCreator spellCreator;
		int magicLevel;
		int healthPoints;
		magicLevel = random.nextInt(10) + 1;
		healthPoints = 1000 + (150 * magicLevel);
		super("WizardStudent", magicLevel, healthPoints, 100, 0.7, CharacterType.WIZARD);
		
		spellCreator = new ExpectoPatronumCreator();
		addSpell(spellCreator.createSpell());
		
		spellCreator = new ExpelliarmusCreator();
		addSpell(spellCreator.createSpell());
		
		spellCreator = new  FerulaCreator();
		addSpell(spellCreator.createSpell());
		
		spellCreator = new ProtegoCreator();
		addSpell(spellCreator.createSpell());
		
		spellCreator = new ProtegoTotalumCreator();
		addSpell(spellCreator.createSpell());
	}

	@Override
	public int getAffinity(SpellCategory category) {
		if(category == SpellCategory.DARK) {
			return 1;
		} else if(category == SpellCategory.LIGHT) {
			return 10;
		} else if(category == SpellCategory.HEAL) {
			return 30;
		} else if(category == SpellCategory.DEFENSE) {
			return 25;
		} else if(category == SpellCategory.COUNTERSPELL) {
			return 5;
		} else {
			throw new IllegalArgumentException("No existe esa categoria de hechizo");
		}
	}
	
	@Override
	public void attack(Character target, String spellName) {
		state = state.attack(this, target, spellName);	
	}

	@Override
	public void support(Character target, String spellName) {
		state = state.attack(this, target, spellName);
	}
}
