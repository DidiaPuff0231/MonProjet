package bibliotheque;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class FenetreBibliotheque extends JFrame {

    private Bibliotheque bibliotheque;
    private JTextField searchField;

    // Constructeur d'origine
    public FenetreBibliotheque(Bibliotheque bibliotheque) {
        super("Gestion de Bibliothèque");
        this.bibliotheque = bibliotheque;
        initialiserComposants();
        
        // CRUCIAL : Charger et afficher les cartes de livres au démarrage
        rafraichirCartes("");
        
        setVisible(true); //s'assurer que la fenetre s'affiche à la fin 
    }

    private void initialiserComposants() {
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    // Actions au clic sur un livre
    public void ouvrirFicheAction(Livre livre) {
        String[] options = {"Lire PDF (via API)", "Associer PDF local", "Annuler"};
        int choix = JOptionPane.showOptionDialog(
            this,
            "Que souhaitez-vous faire pour le livre : " + livre.getTitre() + " ?",
            "Option du livre",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            options,
            options[0]
        );

        if (choix == 0) {
            // Appelle l'API REST avec l'ID dynamique du livre
            telechargerEtOuvrirPdfDepuisApi(livre);
        } else if (choix == 1) {
            // Associer un nouveau fichier local
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Sélectionner le fichier PDF");
            fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Fichiers PDF", "pdf"));

            int result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                livre.setCheminPdf(selectedFile.getAbsolutePath());
                sauvegarderBdd();
                rafraichirCartes(searchField != null ? searchField.getText() : "");
                JOptionPane.showMessageDialog(this, "Fichier PDF associé avec succès !");
            }
        }
    }

    // Récupération dynamique du PDF depuis l'API par ID
    private void telechargerEtOuvrirPdfDepuisApi(Livre livre) {
        try {
            // Requête dynamique avec l'ID du livre sélectionné
            String urlApi = "http://localhost:8080/api/pdf?id=" + livre.getId(); 

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(urlApi))
                    .GET()
                    .build();

            HttpResponse<InputStream> response = client.send(request, HttpResponse.BodyHandlers.ofInputStream());

            if (response.statusCode() == 200) {
                InputStream streamPdf = response.body();
                // Ouvre le PDF reçu en flux dans PDFBox
                new LecteurPdfDialog(this, streamPdf, livre.getTitre()).setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, 
                    "Erreur Serveur (HTTP " + response.statusCode() + ")\n" +
                    "Vérifiez que le livre à l'ID " + livre.getId() + " possède un chemin PDF valide dans SQLite.", 
                    "Erreur API", 
                    JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Impossible de contacter le serveur API sur le port 8080 :\n" + e.getMessage(), 
                "Erreur Connexion", 
                JOptionPane.ERROR_MESSAGE);
        }
    }

    private void sauvegarderBdd() {
        // Logique de sauvegarde
    }

    private void rafraichirCartes(String filtre) {
        // Logique de rafraîchissement d'affichage
    	
    	
    }
    
}