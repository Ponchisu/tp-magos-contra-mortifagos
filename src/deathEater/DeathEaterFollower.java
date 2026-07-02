package deathEater;
// Representa a un seguidor de los mortífagos.

import java.util.Random;

import spell.SpellCategory;
import spellCreator.ConfundoCreator;
import spellCreator.CrucioCreator;
import spellCreator.FieldfyreCreator;
import spellCreator.PetrificusTotalusCreator;
import spellCreator.SectumsempraCreator;
import spellCreator.SpellCreator;
import spellCreator.TempestJinxCreator;
import spellCreator.TitillandoCreator;

public class DeathEaterFollower extends DeathEater {
	public DeathEaterFollower() {
		Random random = new Random();
		SpellCreator spellCreator;
		int magicLevel;
		int healthPoints;
		magicLevel = random.nextInt(10) + 1;
		healthPoints = 80 + (32 * magicLevel);
		super("DeathEaterFollower", magicLevel, healthPoints, 23, 0.85);
		
		
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
	}
	
	@Override
	public int getAffinity(SpellCategory category) {
		if(category == SpellCategory.DARK) {
			return 20;
		} else if(category == SpellCategory.LIGHT) {
			return 10;
		} else if(category == SpellCategory.HEAL) {
			return 1;
		} else if(category == SpellCategory.DEFENSE) {
			return 38;
		} else if(category == SpellCategory.COUNTERSPELL) {
			return 10;
		} else if(category == SpellCategory.AILMENTS) {
			return 10;
		} else {
			throw new IllegalArgumentException("No existe esa categoria de hechizo");
		}
	}
}
