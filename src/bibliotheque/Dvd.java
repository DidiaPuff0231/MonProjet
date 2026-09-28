package bibliotheque;

public class Dvd implements Livre {

    private String titre;
    private int dureeMinutes;
    private boolean disponible;
    private String cheminPdf;
    private int id;

    public Dvd(String titre, int dureeMinutes) {
        this.titre = titre + " [" + dureeMinutes + " min]";
        this.disponible = true;
    }

    @Override
    public String getTitre() {
        return titre;
    }

    @Override
    public boolean estDisponible() {
        return disponible;
    }

    @Override
    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
    @Override
    public String getCheminPdf() {
    	return cheminPdf;
    }
    @Override
    public void setCheminPdf(String cheminPdf) {
    	this.cheminPdf = cheminPdf;
    }
 
    @Override
    public int getId() {
    	return this.id;
    }
    
    public void setId(int id) {
    	this.id = id;
    }
}