package wizard;

import java.util.Random;

import character.Character;
import spell.ExpectoPatronum;
import spell.Expelliarmus;
import spell.Protego;

public class WizardTeacher extends Character implements Wizard {
	public WizardTeacher() {
		super();
		Random random = new Random();
		name = "WizardTeacher";
		magicLevel = random.nextInt(15) + 16;
		healthPoints = 100 + (30 * magicLevel);
		
		spells.add(new Expelliarmus(23 + (4 * magicLevel)));
		spells.add(new ExpectoPatronum(18 + (6 *  magicLevel)));
		spells.add(new Protego(15 + (3 * magicLevel)));
	}

	@Override
	public void attack(Character character) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void support() {
		// TODO Auto-generated method stub
		
	}
}
