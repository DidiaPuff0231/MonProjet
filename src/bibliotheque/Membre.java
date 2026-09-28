package bibliotheque;

import java.util.List;

public interface Membre {
    String getNom();
    void emprunterLivre(Livre livre);
    void retournerLivre(Livre livre);
    List<Livre> getLivresEmpruntes();
}