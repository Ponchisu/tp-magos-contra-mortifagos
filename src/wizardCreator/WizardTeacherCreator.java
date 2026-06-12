package wizardCreator;

import wizard.Wizard;
import wizard.WizardTeacher;

public class WizardTeacherCreator extends WizardCreator {
	@Override
	public Wizard createWizard() {
		return new WizardTeacher();
	}
}
