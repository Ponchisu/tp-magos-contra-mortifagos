package wizard;
// Representa a un auror con rol de protección y combate.

import java.util.Random;

import spell.SpellCategory;
import spellCreator.AvadaKedavraCreator;
import spellCreator.CrucioCreator;
import spellCreator.ExpelliarmusCreator;
import spellCreator.FerulaCreator;
import spellCreator.FieldfyreCreator;
import spellCreator.FiniteIncantatemCreator;
import spellCreator.ProtegoCreator;
import spellCreator.ProtegoTotalumCreator;
import spellCreator.SectumsempraCreator;
import spellCreator.SpellCreator;
import spellCreator.TempestJinxCreator;

public class WizardAuror extends Wizard {
	public WizardAuror() {
		Random random = new Random();
		SpellCreator spellCreator;
		int magicLevel;
		int healthPoints;
		magicLevel = random.nextInt(29) + 30;
		healthPoints = 120 + (33 * magicLevel);
		super("WizardAuror", magicLevel, healthPoints, 17, 0.95);
		
		
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
		
		spellCreator = new FieldfyreCreator();
		addSpell(spellCreator.createSpell());
		
		spellCreator = new FiniteIncantatemCreator();
		addSpell(spellCreator.createSpell());

		spellCreator = new SectumsempraCreator();
		addSpell(spellCreator.createSpell());
		
		spellCreator = new CrucioCreator();
		addSpell(spellCreator.createSpell());
		
		spellCreator = new AvadaKedavraCreator();
		addSpell(spellCreator.createSpell());
	}
	
	@Override
	public int getAffinity(SpellCategory category) {
		if(category == SpellCategory.DARK) {
			return 3;
		} else if(category == SpellCategory.LIGHT) {
			return 95;
		} else if(category == SpellCategory.HEAL) {
			return 65;
		} else if(category == SpellCategory.DEFENSE) {
			return 45;
		} else if(category == SpellCategory.COUNTERSPELL) {
			return 50;
		} else if(category == SpellCategory.AILMENTS) {
			return 1;
		} else {
			throw new IllegalArgumentException("No existe esa categoria de hechizo");
		}
	}
}
