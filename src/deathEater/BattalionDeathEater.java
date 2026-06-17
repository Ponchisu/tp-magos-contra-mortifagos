package deathEater;

import java.util.ArrayList;
import java.util.List;

import character.Character;

public class BattalionDeathEater implements DeathEater {
	private List<DeathEater> deathEaters;
	
	public BattalionDeathEater() {
		deathEaters = new ArrayList<DeathEater>();
	}

	@Override
	public void darkAttack(Character character) {
		for(DeathEater deathEater : deathEaters) {
			deathEater.darkAttack(character);
		}
	}

	@Override
	public void specialSpell(Character character) {
		for(DeathEater deathEater : deathEaters) {
			deathEater.specialSpell(character);
		}
	}

}
