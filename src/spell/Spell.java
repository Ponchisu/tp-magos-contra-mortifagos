package spell;
// Define la estructura base de todos los hechizos del proyecto.

import java.util.Objects;

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
	
	public abstract void use(Character caster, Character target);

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Spell other = (Spell) obj;
		return Objects.equals(name, other.name);
	}
	
	@Override
	public String toString() {
		return getName();
	}
}
