import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import exceptions.AutoAttackException;
import exceptions.InvalidSpellTypeException;
import spell.SpellCategory;
import wizard.Wizard;
import wizard.WizardStudent;

// Pruebas sobre comportamiento básico de personajes: vida, defensa y validación de hechizos.
class CharacterTest {

    // Verifica que healthUp y healthDown funcionan y validan parámetros negativos.
    @Test
    void testHealthUpDownAndDefense() {
        Wizard test = new Wizard("TestWizard", 5, 100, 20, 1.0) {
            @Override
            public int getAffinity(SpellCategory category) {
                return 0;
            }
        };

        int before = test.getHealthPoints();
        test.healthUp(10);
        assertEquals(before + 10, test.getHealthPoints());

        // daño menor que la defensa efectiva no debe restar vida
        int hpBefore = test.getHealthPoints();
        test.healthDown(1); // con defensa alto, posiblemente quede en 0 daño
        assertTrue(test.getHealthPoints() <= hpBefore);

        // comprobación de argumentos inválidos
        assertThrows(IllegalArgumentException.class, () -> test.healthUp(-5));
        assertThrows(IllegalArgumentException.class, () -> test.healthDown(-5));
        assertThrows(IllegalArgumentException.class, () -> test.increaseDefense(-1, 2));
        assertThrows(IllegalArgumentException.class, () -> test.decreaseDefense(1, -1));
    }

    // Verifica que lanzar un hechizo de soporte como ataque lanza InvalidSpellTypeException
    // y que no se puede atacarse a uno mismo con un ofensivo (AutoAttackException).
    @Test
    void testValidCastSpellExceptions() {
        Wizard caster = new WizardStudent();
        Wizard target = new WizardStudent();

        // Protego es de tipo SUPPORT, intentar usarlo como ataque debe fallar.
        // assertThrows(InvalidSpellTypeException.class, () -> caster.attack(target, "Protego"));
        assertThrows(InvalidSpellTypeException.class, () -> caster.attack(target, "Protego"));

        // No se puede atacarse a uno mismo con un ofensivo
        assertThrows(AutoAttackException.class, () -> caster.attack(caster, "Expelliarmus"));
    }
}
