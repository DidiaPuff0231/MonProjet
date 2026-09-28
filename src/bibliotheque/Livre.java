package bibliotheque;

public interface Livre {
	int getId(); //<--- AJOUTER CETTE LIGNE
    String getTitre();
    boolean estDisponible();
    void setDisponible(boolean disponible);
    
    // + NOUVELLES METHODES POUR LE PDF
    String getCheminPdf();
    void setCheminPdf(String CheminPdf);
   
    // Retourne un émoji / icone visuelle selonle type de document 
    default String getIcone() {
    	if (this instanceof Magazine) return "📰";
        if (this instanceof Dvd) return "💿";
        return "📖";
    }
    

}
