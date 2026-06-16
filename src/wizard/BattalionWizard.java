package wizard;

import java.util.ArrayList;
import java.util.List;

public class BattalionWizard implements Wizard {
	private List<Wizard> wizards;
	
	public BattalionWizard() {
		wizards = new ArrayList<Wizard>();
	}
	
	public boolean add(Wizard wizard) {
		return wizards.add(wizard);
	}

	@Override
	public void attack() {
		for (Wizard w : wizards) {
			w.attack();			
		}
	}

	@Override
	public void support() {
		for (Wizard w : wizards) {
			w.attack();			
		}
	}

}
