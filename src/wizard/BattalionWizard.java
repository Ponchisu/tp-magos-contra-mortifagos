package wizard;

import java.util.ArrayList;
import java.util.List;

import character.Character;

public class BattalionWizard implements Wizard {
	private List<Wizard> wizards;
	
	public BattalionWizard() {
		wizards = new ArrayList<Wizard>();
	}
	
	public boolean add(Wizard wizard) {
		return wizards.add(wizard);
	}

	@Override
	public void attack(Character target, String nameSpell) {
		for (Wizard w : wizards) {
			w.attack(target, nameSpell);			
		}
	}

	@Override
	public void support(Character targert, String nameSpell) {
		for (Wizard w : wizards) {
			w.support(targert, nameSpell);			
		}
	}

}
