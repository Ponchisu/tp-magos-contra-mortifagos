package character;

import deathEater.DeathEater;
import wizard.Wizard;

public interface CharacterCreator {
	Wizard createWizard();
	DeathEater createDeathEater();
}
