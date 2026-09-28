package bibliotheque;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Bibliotheque {
    private final List<Livre> livres = new ArrayList<>();
    private final List<Membre> membres = new ArrayList<>();
    
    private final ServiceEmprunt serviceEmprunt;
    private final GenerateurRapport generateurRapport;

    // Injection des dépendances via le constructeur
    public Bibliotheque(ServiceEmprunt serviceEmprunt, GenerateurRapport generateurRapport) {
        this.serviceEmprunt = serviceEmprunt;
        this.generateurRapport = generateurRapport;
    }

    public void ajouterLivre(Livre livre) {
        livres.add(livre);
    }

    public void ajouterMembre(Membre membre) {
        membres.add(membre);
    }

    public void emprunterLivre(Membre membre, Livre livre) throws LivreIndisponibleException {
        serviceEmprunt.emprunter(membre, livre);
    }

    public void retournerLivre(Membre membre, Livre livre) {
        serviceEmprunt.retourner(membre, livre);
    }

    public void afficherRapport() {
        generateurRapport.generer(livres, membres);
    }

    public List<Livre> getLivres() {
        return Collections.unmodifiableList(livres);
    }

    public List<Membre> getMembres() {
        return Collections.unmodifiableList(membres);
    }
}