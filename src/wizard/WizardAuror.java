package wizard;

import java.util.Random;

import character.Character;
import spell.ExpectoPatronum;
import spell.Expelliarmus;
import spell.Protego;

public class WizardAuror extends Character implements Wizard {
	public WizardAuror() {
		super("WizardAuror");
		Random random = new Random();
		magicLevel = random.nextInt(29) + 30;
		healthPoints = 100 + (30 * magicLevel);
		
		spells.add(new Expelliarmus(40 + (4 * magicLevel)));
		spells.add(new ExpectoPatronum(29 + (6 *  magicLevel)));
		spells.add(new Protego(30 + (3 * magicLevel)));
	}
	
	@Override
	public void attack(Character character) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void support() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getAffinity() {
		// TODO Auto-generated method stub
		return 0;
	}
}
