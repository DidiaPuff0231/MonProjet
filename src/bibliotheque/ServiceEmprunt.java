package bibliotheque;

public interface ServiceEmprunt {
    void emprunter(Membre membre, Livre livre) throws LivreIndisponibleException;
    void retourner(Membre membre, Livre livre);
}