package wizard;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import character.Character;
import character.ToRecruit;

class WizardStudentTest {

	@Test
	void test() {
		Character wizard = ToRecruit.GetInstance().createWizard();
		
		System.out.println(wizard.getName());
	}

}
