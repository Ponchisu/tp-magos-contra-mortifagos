package spell;

import character.Character;

public abstract class Spell {
	private String name;
	private SpellType type;
	private SpellCategory category;
	private double accuracy;
	
	public Spell(String name, SpellType type, SpellCategory category, double accuracy) {
		this.name = name;
		this.type = type;
		this.category = category;
		this.accuracy = accuracy;
	}
	
	public String getName() {
		return name;
	}
	
	public SpellType getType() {
		return type;
	}
	
	public SpellCategory getCategory() {
		return category;
	}
	
	public double getAccuracy() {
		return accuracy;
	}
	
	abstract void use(Character caster, Character target);
}
