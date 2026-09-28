package bibliotheque;

public class Magazine implements Livre {

    private String titre;
    private int numeroEdition;
    private boolean disponible;
    private String cheminPdf; 
    private int id;

    public Magazine(String titre, int numeroEdition) {
        this.titre = titre + " (N°" + numeroEdition + ")";
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