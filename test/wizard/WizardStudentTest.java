package wizard;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import character.Character;
import character.ToRecruit;

class WizardStudentTest {

	@Test
	void test() {
		Character w = ToRecruit.GetInstance().createWizard();
		Character d = ToRecruit.GetInstance().createDeathEater();
		
		d.attack(w, "Sectumsempra");
		w.attack(d, "Sectumsempra");
		
		System.out.println(w.getName() + " lvl:" + w.getMagicLevel());
		System.out.println(d.getName() + " lvl:" + d.getMagicLevel());

	}

}
