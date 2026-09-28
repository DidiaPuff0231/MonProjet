package bibliotheque;

import java.sql.*;

public class ServiceSauvegardeBdd implements ServiceSauvegarde {

    @Override
    public void sauvegarder(Bibliotheque biblio, String chemin) throws Exception {
        try (Connection conn = DatabaseManager.getConnection()) {
            conn.setAutoCommit(false);

            try (Statement stmt = conn.createStatement()) {
                stmt.executeUpdate("DELETE FROM emprunts");
                stmt.executeUpdate("DELETE FROM livres");
                stmt.executeUpdate("DELETE FROM membres");
            }

            String sqlLivre = "INSERT INTO livres(titre, type, disponible, chemin_pdf) VALUES(?, ?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sqlLivre)) {
                for (Livre l : biblio.getLivres()) {
                    pstmt.setString(1, l.getTitre());
                    pstmt.setString(2, l.getClass().getSimpleName());
                    pstmt.setInt(3, l.estDisponible() ? 1 : 0);
                    pstmt.setString(4, l.getCheminPdf());
                    pstmt.executeUpdate();
                }
            }

            String sqlMembre = "INSERT INTO membres(nom) VALUES(?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sqlMembre)) {
                for (Membre m : biblio.getMembres()) {
                    pstmt.setString(1, m.getNom());
                    pstmt.executeUpdate();

                    for (Livre l : m.getLivresEmpruntes()) {
                        try (PreparedStatement pstmtEmp = conn.prepareStatement("INSERT INTO emprunts VALUES(?, ?)")) {
                            pstmtEmp.setString(1, m.getNom());
                            pstmtEmp.setString(2, l.getTitre());
                            pstmtEmp.executeUpdate();
                        }
                    }
                }
            }

            conn.commit();
        }
    }

    @Override
    public void charger(Bibliotheque biblio, String chemin) throws Exception {
        biblio.getLivres().clear();
        biblio.getMembres().clear();

        try (Connection conn = DatabaseManager.getConnection()) {
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery("SELECT * FROM livres")) {
                while (rs.next()) {
                    String titre = rs.getString("titre");
                    String type = rs.getString("type");
                    boolean dispo = rs.getInt("disponible") == 1;
                    String cheminPdf = rs.getString("chemin_pdf");

                    Livre livre;
                    if ("Magazine".equalsIgnoreCase(type)) {
                        livre = new Magazine(titre, 1);
                    } else if ("Dvd".equalsIgnoreCase(type)) {
                        livre = new Dvd(titre, 120);
                    } else {
                        livre = new LivreImpl(titre);
                    }
                    livre.setDisponible(dispo);
                    livre.setCheminPdf(cheminPdf);
                    biblio.ajouterLivre(livre);
                }
            }

            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery("SELECT * FROM membres")) {
                while (rs.next()) {
                    String nom = rs.getString("nom");
                    biblio.ajouterMembre(new Adherent(nom));
                }
            }
        }
    }
}