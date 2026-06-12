package wizardCreator;

import wizard.Wizard;
import wizard.WizardStudent;

public class WizardStudentCreator extends WizardCreator {
	@Override
	public Wizard createWizard() {
		return new WizardStudent();
	}
}
