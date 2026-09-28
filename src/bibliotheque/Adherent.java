package bibliotheque;

import java.util.ArrayList;
import java.util.List;

public class Adherent implements Membre {

    private String nom;
    private List<Livre> livresEmpruntes = new ArrayList<>();

    public Adherent(String nom) {
        this.nom = nom;
    }

    @Override
    public String getNom() {
        return nom;
    }

    @Override
    public void emprunterLivre(Livre livre) {
        livresEmpruntes.add(livre);
    }

    @Override
    public void retournerLivre(Livre livre) {
        livresEmpruntes.remove(livre);
    }

    @Override
    public List<Livre> getLivresEmpruntes() {
        return livresEmpruntes;
    }
}