package wizard;

import java.util.Random;

import character.Character;
import spell.ExpectoPatronum;
import spell.Expelliarmus;

public class WizardStudent extends Character implements Wizard {
	public WizardStudent() {
		super();
		Random random = new Random();
		name = "WizardStudent";
		magicLevel = random.nextInt(10) + 1;
		healthPoints = 100 + (15 * magicLevel);

		spells.add(new Expelliarmus(15 + (2 * magicLevel)));
		spells.add(new ExpectoPatronum(10 + (4 *  magicLevel)));
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
