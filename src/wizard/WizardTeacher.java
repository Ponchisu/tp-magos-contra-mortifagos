package wizard;
// Representa a un profesor de magia del proyecto.

import java.util.Random;

import spell.SpellCategory;
import spellCreator.CrucioCreator;
import spellCreator.ExpelliarmusCreator;
import spellCreator.FerulaCreator;
import spellCreator.FiniteIncantatemCreator;
import spellCreator.ProtegoCreator;
import spellCreator.ProtegoTotalumCreator;
import spellCreator.SpellCreator;
import spellCreator.TempestJinxCreator;

public class WizardTeacher extends Wizard {
	public WizardTeacher() {
		Random random = new Random();
		SpellCreator spellCreator;
		int magicLevel;
		int healthPoints;
		magicLevel = random.nextInt(15) + 16;
		healthPoints = 100 + (30 * magicLevel);
		super("WizardTeacher", magicLevel, healthPoints, 15, 0.85);
		
		
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
		
		spellCreator = new FiniteIncantatemCreator();
		addSpell(spellCreator.createSpell());
		
		spellCreator = new CrucioCreator();
		addSpell(spellCreator.createSpell());
	}
	
	@Override
	public int getAffinity(SpellCategory category) {
		if(category == SpellCategory.DARK) {
			return 1;
		} else if(category == SpellCategory.LIGHT) {
			return 80;
		} else if(category == SpellCategory.HEAL) {
			return 60;
		} else if(category == SpellCategory.DEFENSE) {
			return 35;
		} else if(category == SpellCategory.COUNTERSPELL) {
			return 25;
		} else if(category == SpellCategory.AILMENTS) {
			return 1;
		} else {
			throw new IllegalArgumentException("No existe esa categoria de hechizo");
		}
	}
}
