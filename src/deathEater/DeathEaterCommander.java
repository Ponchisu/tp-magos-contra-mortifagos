package deathEater;

import java.util.Random;

import character.Character;
import character.CharacterType;
import spell.SpellCategory;
import spellCreator.AvadaKedavraCreator;
import spellCreator.ConfundoCreator;
import spellCreator.CrucioCreator;
import spellCreator.FerulaCreator;
import spellCreator.FieldfyreCreator;
import spellCreator.PetrificusTotalusCreator;
import spellCreator.ProtegoCreator;
import spellCreator.ProtegoTotalumCreator;
import spellCreator.SectumsempraCreator;
import spellCreator.SpellCreator;
import spellCreator.TempestJinxCreator;
import spellCreator.TitillandoCreator;

public class DeathEaterCommander extends Character implements DeathEater {
	public DeathEaterCommander() {
		Random random = new Random();
		SpellCreator spellCreator;
		int magicLevel;
		int healthPoints;
		magicLevel = random.nextInt(29) + 30;
		healthPoints = 1400 + (360 * magicLevel);
		super("DeathEaterCommander", magicLevel, healthPoints, 340, 0.97, CharacterType.DEATHEATER);
		
		
		spellCreator = new  FerulaCreator();
		addSpell(spellCreator.createSpell());
		
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
		
		spellCreator = new ProtegoCreator();
		addSpell(spellCreator.createSpell());
		
		spellCreator = new AvadaKedavraCreator();
		addSpell(spellCreator.createSpell());
		
		spellCreator = new ProtegoTotalumCreator();
		addSpell(spellCreator.createSpell());
	}
	
	@Override
	public int getAffinity(SpellCategory category) {
		if(category == SpellCategory.DARK) {
			return 65;
		} else if(category == SpellCategory.LIGHT) {
			return 50;
		} else if(category == SpellCategory.HEAL) {
			return 15;
		} else if(category == SpellCategory.DEFENSE) {
			return 41;
		} else if(category == SpellCategory.COUNTERSPELL) {
			return 40;
		} else {
			throw new IllegalArgumentException("No existe esa categoria de hechizo");
		}
	}

	@Override
	public void darkAttack(Character target, String spellName) {
		state = state.attack(this, target, spellName);
		
	}

	@Override
	public void specialSpell(Character target, String spellName) {
		state = state.attack(this, target, spellName);	
	}
}
