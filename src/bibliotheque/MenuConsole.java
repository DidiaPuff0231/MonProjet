package bibliotheque;

import java.util.Scanner;

public class MenuConsole {

    private final Bibliotheque biblio;
    private final Scanner scanner;

    public MenuConsole(Bibliotheque biblio) {
        this.biblio = biblio;
        this.scanner = new Scanner(System.in);
    }

    public void demarrer() {
        boolean continuer = true;

        while (continuer) {
            afficherMenu();
            System.out.print("Choisissez une option : ");
            String choix = scanner.nextLine().trim();

            switch (choix) {
                case "1":
                    ajouterLivre();
                    break;
                case "2":
                    ajouterMembre();
                    break;
                case "3":
                    emprunterLivre();
                    break;
                case "4":
                    retournerLivre();
                    break;
                case "5":
                    biblio.afficherRapport();
                    break;
                case "0":
                    continuer = false;
                    System.out.println("Au revoir !");
                    break;
                default:
                    System.out.println("Option invalide, veuillez réessayer.");
            }
            System.out.println();
        }
    }

    private void afficherMenu() {
        System.out.println("=== GESTION DE LA BIBLIOTHÈQUE ===");
        System.out.println("1. Ajouter un document (Livre, Magazine, DVD)");
        System.out.println("2. Inscrire un membre");
        System.out.println("3. Emprunter un document");
        System.out.println("4. Retourner un document");
        System.out.println("5. Afficher le rapport complet");
        System.out.println("0. Quitter");
        System.out.println("==================================");
    }

    private void ajouterLivre() {
        System.out.println("\nType de document :");
        System.out.println("1. Livre standard");
        System.out.println("2. Magazine");
        System.out.println("3. DVD");
        System.out.print("Choix : ");
        String type = scanner.nextLine().trim();

        System.out.print("Titre : ");
        String titre = scanner.nextLine().trim();

        if (titre.isEmpty()) {
            System.out.println("Le titre ne peut pas être vide.");
            return;
        }

        switch (type) {
            case "1":
                biblio.ajouterLivre(new LivreImpl(titre));
                System.out.println("Livre '" + titre + "' ajouté avec succès.");
                break;
            case "2":
                System.out.print("Numéro d'édition : ");
                int num = Integer.parseInt(scanner.nextLine().trim());
                biblio.ajouterLivre(new Magazine(titre, num));
                System.out.println("Magazine '" + titre + "' ajouté avec succès.");
                break;
            case "3":
                System.out.print("Durée (en minutes) : ");
                int duree = Integer.parseInt(scanner.nextLine().trim());
                biblio.ajouterLivre(new Dvd(titre, duree));
                System.out.println("DVD '" + titre + "' ajouté avec succès.");
                break;
            default:
                System.out.println("Type invalide.");
        }
    }

    private void ajouterMembre() {
        System.out.print("Nom du membre : ");
        String nom = scanner.nextLine().trim();
        if (!nom.isEmpty()) {
            biblio.ajouterMembre(new Adherent(nom));
            System.out.println("Membre '" + nom + "' inscrit avec succès.");
        } else {
            System.out.println("Le nom ne peut pas être vide.");
        }
    }

    private void emprunterLivre() {
        try {
            Membre membre = rechercherMembre();
            Livre livre = rechercherLivre();
            biblio.emprunterLivre(membre, livre);
        } catch (LivreNonTrouveException | LivreIndisponibleException e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }

    private void retournerLivre() {
        try {
            Membre membre = rechercherMembre();
            Livre livre = rechercherLivre();
            biblio.retournerLivre(membre, livre);
        } catch (LivreNonTrouveException e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }

    private Membre rechercherMembre() throws LivreNonTrouveException {
        System.out.print("Nom du membre : ");
        String nom = scanner.nextLine().trim();
        for (Membre m : biblio.getMembres()) {
            if (m.getNom().equalsIgnoreCase(nom)) {
                return m;
            }
        }
        throw new LivreNonTrouveException("Membre '" + nom + "' introuvable.");
    }

    private Livre rechercherLivre() throws LivreNonTrouveException {
        System.out.print("Titre du document : ");
        String titre = scanner.nextLine().trim();
        for (Livre l : biblio.getLivres()) {
            if (l.getTitre().equalsIgnoreCase(titre)) {
                return l;
            }
        }
        throw new LivreNonTrouveException("Document '" + titre + "' introuvable.");
    }
}