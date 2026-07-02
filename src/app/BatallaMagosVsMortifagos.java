package app;
import java.util.Random;

import character.ToRecruit;
import wizard.BattalionWizard;
import deathEater.BattalionDeathEater;

// Main de interfaz que crea dos batallones y los hace combatir automáticamente.
public class BatallaMagosVsMortifagos {

    public static void main(String[] args) {
        BattalionWizard batallonMagos = new BattalionWizard();
        BattalionDeathEater batallonMortifagos = new BattalionDeathEater();
        ToRecruit reclutador = ToRecruit.GetInstance();

        // Crear algunos integrantes en cada batallón mediante la fábrica ToRecruit.
        for (int i = 0; i < 3; i++) {
            batallonMagos.addWizard(reclutador.createWizard());
            batallonMortifagos.addDeathEater(reclutador.createDeathEater());
        }

        Random startRandom = new Random();
        Random magosRandom = new Random();
        Random mortifagosRandom = new Random();

        // Ejecutar algunas rondas demostrativas.
        int round = 0;
        while (batallonMagos.isLive() && batallonMortifagos.isLive()) {
            System.out.println("--- Ronda " + (++round) + " ---");
            if (startRandom.nextBoolean()) {
                System.out.println("Turno de los magos");
                if (magosRandom.nextBoolean()) {
                    System.out.println("Los magos usan un ataque normal");
                    batallonMagos.attack(batallonMortifagos);
                } else {
                    System.out.println("Los magos usan soporte entre aliados");
                    batallonMagos.support(batallonMagos);
                }

                if (batallonMortifagos.isLive()) {
                    System.out.println("Turno de los mortífagos");
                    if (mortifagosRandom.nextBoolean()) {
                        System.out.println("Los mortífagos usan un ataque normal");
                        batallonMortifagos.attack(batallonMagos);
                    } else {
                        System.out.println("Los mortífagos usan un hechizo especial");
                        batallonMortifagos.specialSpell(batallonMagos);
                    }
                }
            } else {
                System.out.println("Turno de los mortífagos");
                if (mortifagosRandom.nextBoolean()) {
                    System.out.println("Los mortífagos usan un ataque normal");
                    batallonMortifagos.attack(batallonMagos);
                } else {
                    System.out.println("Los mortífagos usan un hechizo especial");
                    batallonMortifagos.specialSpell(batallonMagos);
                }

                if (batallonMagos.isLive()) {
                    System.out.println("Turno de los magos");
                    if (magosRandom.nextBoolean()) {
                        System.out.println("Los magos usan un ataque normal");
                        batallonMagos.attack(batallonMortifagos);
                    } else {
                        System.out.println("Los magos usan soporte entre aliados");
                        batallonMagos.support(batallonMagos);
                    }
                }
            }
            System.out.println();
        }

        if (batallonMagos.isLive() && !batallonMortifagos.isLive()) {
            System.out.println("¡Los magos han ganado la batalla!");
        } else if (!batallonMagos.isLive() && batallonMortifagos.isLive()) {
            System.out.println("¡Los mortífagos han ganado la batalla!");
        } else {
            System.out.println("La batalla terminó en empate técnico.");
        }
    }
}
