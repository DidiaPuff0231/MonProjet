package bibliotheque;

public interface ServiceSauvegarde {
    void sauvegarder(Bibliotheque biblio, String chemin) throws Exception;
    void charger(Bibliotheque biblio, String chemin) throws Exception;
}