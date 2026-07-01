package wizard;

import java.util.Random;

import character.Character;
import character.CharacterType;
import spell.SpellCategory;
import spellCreator.ConfundoCreator;
import spellCreator.ExpectoPatronumCreator;
import spellCreator.ExpelliarmusCreator;
import spellCreator.FerulaCreator;
import spellCreator.PetrificusTotalusCreator;
import spellCreator.ProtegoCreator;
import spellCreator.ProtegoTotalumCreator;
import spellCreator.SpellCreator;
import spellCreator.TempestJinxCreator;
import spellCreator.TitillandoCreator;

public class WizardTeacher extends Character implements Wizard {
	public WizardTeacher() {
		Random random = new Random();
		SpellCreator spellCreator;
		int magicLevel;
		int healthPoints;
		magicLevel = random.nextInt(15) + 16;
		healthPoints = 1000 + (300 * magicLevel);
		super("WizardTeacher", magicLevel, healthPoints, 150, 0.85, CharacterType.WIZARD);
		
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
		
		spellCreator = new TempestJinxCreator();
		addSpell(spellCreator.createSpell());
		
		spellCreator = new PetrificusTotalusCreator();
		addSpell(spellCreator.createSpell());
		
		spellCreator = new TitillandoCreator();
		addSpell(spellCreator.createSpell());
		
		spellCreator = new ConfundoCreator();
		addSpell(spellCreator.createSpell());
	}
	
	@Override
	public int getAffinity(SpellCategory category) {
		if(category == SpellCategory.DARK) {
			return 1;
		} else if(category == SpellCategory.LIGHT) {
			return 30;
		} else if(category == SpellCategory.HEAL) {
			return 60;
		} else if(category == SpellCategory.DEFENSE) {
			return 35;
		} else if(category == SpellCategory.COUNTERSPELL) {
			return 25;
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
