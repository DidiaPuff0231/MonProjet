package bibliotheque;

import java.util.List;

public interface GenerateurRapport {
    void generer(List<Livre> livres, List<Membre> membres);
}