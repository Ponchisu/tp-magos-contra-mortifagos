package deathEater;
// Representa al comandante de los mortífagos.

import java.util.Random;

import spell.SpellCategory;
import spellCreator.AvadaKedavraCreator;
import spellCreator.ConfundoCreator;
import spellCreator.CrucioCreator;
import spellCreator.ExpelliarmusCreator;
import spellCreator.FieldfyreCreator;
import spellCreator.PetrificusTotalusCreator;
import spellCreator.SectumsempraCreator;
import spellCreator.SpellCreator;
import spellCreator.TempestJinxCreator;
import spellCreator.TitillandoCreator;

public class DeathEaterCommander extends DeathEater {
	public DeathEaterCommander() {
		Random random = new Random();
		SpellCreator spellCreator;
		int magicLevel;
		int healthPoints;
		magicLevel = random.nextInt(29) + 30;
		healthPoints = 140 + (36 * magicLevel);
		super("DeathEaterCommander", magicLevel, healthPoints, 34, 0.97);
		
		
		spellCreator = new TempestJinxCreator();
		addSpell(spellCreator.createSpell());
		
		spellCreator = new CrucioCreator();
		addSpell(spellCreator.createSpell());
		
		spellCreator = new FieldfyreCreator();
		addSpell(spellCreator.createSpell());
		
		spellCreator = new SectumsempraCreator();
		addSpell(spellCreator.createSpell());
		
		spellCreator = new PetrificusTotalusCreator();
		addSpell(spellCreator.createSpell());
		
		spellCreator = new TitillandoCreator();
		addSpell(spellCreator.createSpell());
		
		spellCreator = new ConfundoCreator();
		addSpell(spellCreator.createSpell());

		spellCreator = new AvadaKedavraCreator();
		addSpell(spellCreator.createSpell());
		
		spellCreator = new ExpelliarmusCreator();
		addSpell(spellCreator.createSpell());
	}
	
	@Override
	public int getAffinity(SpellCategory category) {
		if(category == SpellCategory.DARK) {
			return 65;
		} else if(category == SpellCategory.LIGHT) {
			return 50;
		} else if(category == SpellCategory.HEAL) {
			return 1;
		} else if(category == SpellCategory.DEFENSE) {
			return 41;
		} else if(category == SpellCategory.COUNTERSPELL) {
			return 40;
		} else if(category == SpellCategory.AILMENTS) {
			return 30;
		} else {
			throw new IllegalArgumentException("No existe esa categoria de hechizo");
		}
	}
}
