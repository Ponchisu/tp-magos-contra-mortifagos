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
	public void darkAttack(Character target, String spellName) {
		for(DeathEater deathEater : deathEaters) {
			deathEater.darkAttack(target, spellName);
		}
	}

	@Override
	public void specialSpell(Character target, String spellName)  {
		for(DeathEater deathEater : deathEaters) {
			deathEater.darkAttack(target, spellName);
		}
	}
}
