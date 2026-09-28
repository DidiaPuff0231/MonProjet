package bibliotheque;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        // 1. Initialiser le fichier SQLite bibliotheque.db
        DatabaseManager.initialiserBase();

        ServiceEmprunt serviceEmprunt = new ServiceEmpruntImpl();
        GenerateurRapport generateurRapport = new GenerateurRapportConsole();
        Bibliotheque biblio = new Bibliotheque(serviceEmprunt, generateurRapport);

        // 2. Charger les données existantes de la base de données
        try {
            ServiceSauvegarde serviceBdd = new ServiceSauvegardeBdd();
            serviceBdd.charger(biblio, null);
            System.out.println("Données SQLite chargées avec succès au démarrage.");
        } catch (Exception e) {
            System.out.println("Première exécution ou base vide : " + e.getMessage());
        }

        // 3. Lancer l'interface graphique
        SwingUtilities.invokeLater(() -> {
            FenetreBibliotheque fenetre = new FenetreBibliotheque(biblio);
            fenetre.setVisible(true);
        });
    }
}