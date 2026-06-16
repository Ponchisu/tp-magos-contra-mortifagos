package deathEater;

import java.util.ArrayList;
import java.util.List;

public class BattalionDeathEater implements DeathEater {
	private List<DeathEater> deathEaters;
	
	public BattalionDeathEater() {
		deathEaters = new ArrayList<DeathEater>();
	}

	@Override
	public void darkAttack() {
		for(DeathEater deathEater : deathEaters) {
			deathEater.darkAttack();
		}
	}

	@Override
	public void specialSpell() {
		for(DeathEater deathEater : deathEaters) {
			deathEater.specialSpell();
		}
	}

}
