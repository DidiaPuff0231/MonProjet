package bibliotheque;

import java.util.List;

public class GenerateurRapportConsole implements GenerateurRapport {
    @Override
    public void generer(List<Livre> livres, List<Membre> membres) {
        System.out.println("\n--- Rapport de la bibliothèque ---");
        System.out.println("Livres disponibles :");
        for (Livre livre : livres) {
            if (livre.estDisponible()) {
                System.out.println("- " + livre.getTitre());
            }
        }
        System.out.println("\nMembres et leurs livres empruntés :");
        for (Membre membre : membres) {
            System.out.println(membre.getNom() + " a emprunté :");
            for (Livre livre : membre.getLivresEmpruntes()) {
                System.out.println("  * " + livre.getTitre());
            }
        }
    }
}