package wizard;

import java.util.Random;

import character.Character;
import character.CharacterType;
import spell.SpellCategory;
import spellCreator.ConfundoCreator;
import spellCreator.ExpelliarmusCreator;
import spellCreator.FerulaCreator;
import spellCreator.FieldfyreCreator;
import spellCreator.FiniteIncantatemCreator;
import spellCreator.PetrificusTotalusCreator;
import spellCreator.ProtegoTotalumCreator;
import spellCreator.SectumsempraCreator;
import spellCreator.SpellCreator;
import spellCreator.TempestJinxCreator;
import spellCreator.TitillandoCreator;

public class WizardAuror extends Character implements Wizard {
	public WizardAuror() {
		Random random = new Random();
		SpellCreator spellCreator;
		int magicLevel;
		int healthPoints;
		magicLevel = random.nextInt(29) + 30;
		healthPoints = 1200 + (300 * magicLevel);
		super("WizardAuror", magicLevel, healthPoints, 175, 0.95, CharacterType.WIZARD);
		
		
		spellCreator = new ExpelliarmusCreator();
		addSpell(spellCreator.createSpell());
		
		spellCreator = new  FerulaCreator();
		addSpell(spellCreator.createSpell());
		
		spellCreator = new ProtegoTotalumCreator();
		addSpell(spellCreator.createSpell());
		
		spellCreator = new TempestJinxCreator();
		addSpell(spellCreator.createSpell());
		
		spellCreator = new FieldfyreCreator();
		addSpell(spellCreator.createSpell());
		
		spellCreator = new PetrificusTotalusCreator();
		addSpell(spellCreator.createSpell());
		
		spellCreator = new TitillandoCreator();
		addSpell(spellCreator.createSpell());
		
		spellCreator = new FiniteIncantatemCreator();
		addSpell(spellCreator.createSpell());

		spellCreator = new SectumsempraCreator();
		addSpell(spellCreator.createSpell());
		
		spellCreator = new ConfundoCreator();
		addSpell(spellCreator.createSpell());
	}
	
	@Override
	public int getAffinity(SpellCategory category) {
		if(category == SpellCategory.DARK) {
			return 3;
		} else if(category == SpellCategory.LIGHT) {
			return 60;
		} else if(category == SpellCategory.HEAL) {
			return 65;
		} else if(category == SpellCategory.DEFENSE) {
			return 45;
		} else if(category == SpellCategory.COUNTERSPELL) {
			return 50;
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
