package bibliotheque;

public class LivreImpl implements Livre {
    private  String titre;
    private boolean disponible = true;
    private String cheminPdf;

    public LivreImpl(String titre) {
        this.titre = titre;
    }

    @Override
    public String getTitre() { 
        return titre; 
    }

    @Override
    public boolean estDisponible() { 
        return disponible; 
    }
    
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
    
    //Dans la classe LivreImpl:
    private int id;
    
    @Override
    public int getId() {
    	return this.id;
    }
    
    //Optionnel : un setter si besoin
    public void setId(int id) {
    	this.id = id;
    }
  
}