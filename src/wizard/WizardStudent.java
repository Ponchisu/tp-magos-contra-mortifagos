package wizard;
// Representa a un estudiante de magia del proyecto.

import java.util.Random;

import spell.SpellCategory;
import spellCreator.ExpelliarmusCreator;
import spellCreator.FerulaCreator;
import spellCreator.ProtegoCreator;
import spellCreator.ProtegoTotalumCreator;
import spellCreator.SpellCreator;

public class WizardStudent extends Wizard {
	public WizardStudent() {
		Random random = new Random();
		SpellCreator spellCreator;
		int magicLevel;
		int healthPoints;
		magicLevel = random.nextInt(15) + 16;
		healthPoints = 100 + (15 * magicLevel);
		super("WizardStudent", magicLevel, healthPoints, 10, 0.7);
		
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
			return 20;
		} else if(category == SpellCategory.HEAL) {
			return 30;
		} else if(category == SpellCategory.DEFENSE) {
			return 25;
		} else if(category == SpellCategory.COUNTERSPELL) {
			return 5;
		} else if(category == SpellCategory.AILMENTS) {
			return 1;
		} else {
			throw new IllegalArgumentException("No existe esa categoria de hechizo");
		}
	}
}
