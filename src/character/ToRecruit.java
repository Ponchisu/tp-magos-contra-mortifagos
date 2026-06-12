package character;

import java.util.Random;

import deathEater.DeathEater;
import deathEaterCreator.DeathEaterCommanderCreator;
import deathEaterCreator.DeathEaterCreator;
import deathEaterCreator.DeathEaterFollowerCreator;
import wizard.Wizard;
import wizardCreator.WizardAurorCreator;
import wizardCreator.WizardCreator;
import wizardCreator.WizardStudentCreator;
import wizardCreator.WizardTeacherCreator;

public class ToRecruit implements CharacterCreator {
	private static ToRecruit INSTANCE =  new ToRecruit();
	
	public static ToRecruit GetInstance() {
		return INSTANCE;
	}
	
	@Override
	public Wizard createWizard() {
		Random rand = new Random();
		int selector = rand.nextInt(3);
		WizardCreator creator;
		
		switch (selector) {
		case 0: 
			creator = new WizardAurorCreator();
			return creator.createWizard();
		case 1:
			creator = new WizardStudentCreator();
			return creator.createWizard();
		default:
			creator = new WizardTeacherCreator();
			return creator.createWizard();
		}
	}

	@Override
	public DeathEater createDeathEater() {
		Random rand = new Random();
		int selector = rand.nextInt(3);
		DeathEaterCreator creator;
		
		switch (selector) {
		case 0:
			creator = new DeathEaterFollowerCreator();
			return creator.createDeathEater();
		default:
			creator = new DeathEaterCommanderCreator();
			return creator.createDeathEater();
		}
	}
}
