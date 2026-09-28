package bibliotheque;

public class ServiceEmpruntImpl implements ServiceEmprunt {

    @Override
    public void emprunter(Membre membre, Livre livre) throws LivreIndisponibleException {
        if (!livre.estDisponible()) {
            throw new LivreIndisponibleException("Le livre '" + livre.getTitre() + "' n'est pas disponible actuellement.");
        }
        livre.setDisponible(false);
        membre.emprunterLivre(livre);
        System.out.println(membre.getNom() + " a emprunté " + livre.getTitre());
    }

    @Override
    public void retourner(Membre membre, Livre livre) {
        livre.setDisponible(true);
        membre.retournerLivre(livre);
        System.out.println(membre.getNom() + " a retourné " + livre.getTitre());
    }
}