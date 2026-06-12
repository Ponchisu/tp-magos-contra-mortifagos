package wizardCreator;

import wizard.Wizard;
import wizard.WizardAuror;

public class WizardAurorCreator extends WizardCreator {
	@Override
	public Wizard createWizard() {
		return new WizardAuror();
	}
}
