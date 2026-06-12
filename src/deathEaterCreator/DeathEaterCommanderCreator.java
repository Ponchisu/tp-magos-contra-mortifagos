package deathEaterCreator;

import deathEater.DeathEater;
import deathEater.DeathEaterCommander;

public class DeathEaterCommanderCreator extends DeathEaterCreator {
	@Override
	public DeathEater createDeathEater() {
		return new DeathEaterCommander();
	}
}
