package bibliotheque;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ServiceSauvegardeJson implements ServiceSauvegarde {

    @Override
    public void sauvegarder(Bibliotheque biblio, String fichier) {
        StringBuilder json = new StringBuilder();
        json.append("{\n");
        
        // Sauvegarde des livres
        json.append("  \"livres\": [\n");
        for (int i = 0; i < biblio.getLivres().size(); i++) {
            Livre livre = biblio.getLivres().get(i);
            json.append("    {\n");
            json.append("      \"titre\": \"").append(livre.getTitre()).append("\",\n");
            json.append("      \"disponible\": ").append(livre.estDisponible()).append("\n");
            json.append("    }").append(i < biblio.getLivres().size() - 1 ? "," : "").append("\n");
        }
        json.append("  ],\n");

        // Sauvegarde des membres
        json.append("  \"membres\": [\n");
        for (int i = 0; i < biblio.getMembres().size(); i++) {
            Membre membre = biblio.getMembres().get(i);
            json.append("    {\n");
            json.append("      \"nom\": \"").append(membre.getNom()).append("\"\n");
            json.append("    }").append(i < biblio.getMembres().size() - 1 ? "," : "").append("\n");
        }
        json.append("  ]\n");
        json.append("}");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fichier))) {
            writer.write(json.toString());
            System.out.println("\n--- Sauvegarde JSON ---");
            System.out.println("Données sauvegardées avec succès dans : " + fichier);
        } catch (IOException e) {
            System.err.println("Erreur lors de la sauvegarde : " + e.getMessage());
        }
    }

    @Override
    public void charger(Bibliotheque biblio, String fichier) {
        File file = new File(fichier);
        if (!file.exists()) {
            System.out.println("Aucun fichier de sauvegarde trouvé.");
            return;
        }

        try (Scanner scanner = new Scanner(file)) {
            String titre = null;
            String nomMembre = null;

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();

                // Extraction simple des livres
                if (line.startsWith("\"titre\":")) {
                    titre = line.split(":")[1].replace("\"", "").replace(",", "").trim();
                    if (titre != null) {
                        biblio.ajouterLivre(new LivreImpl(titre));
                    }
                }

                // Extraction simple des membres
                if (line.startsWith("\"nom\":")) {
                    nomMembre = line.split(":")[1].replace("\"", "").replace(",", "").trim();
                    if (nomMembre != null) {
                        biblio.ajouterMembre(new Adherent(nomMembre));
                    }
                }
            }
            System.out.println("\n--- Chargement JSON ---");
            System.out.println("Données chargées avec succès depuis : " + fichier);
        } catch (IOException e) {
            System.err.println("Erreur lors du chargement : " + e.getMessage());
        }
    }
}