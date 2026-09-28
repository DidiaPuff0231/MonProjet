package bibliotheque;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {

    private static final String URL = "jdbc:sqlite:bibliotheque.db";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            System.err.println("Driver SQLite non trouvé : " + e.getMessage());
        }
        return DriverManager.getConnection(URL);
    }

    public static void initialiserBase() {
        String sqlLivres = "CREATE TABLE IF NOT EXISTS livres ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "titre TEXT NOT NULL, "
                + "type TEXT NOT NULL, "
                + "disponible INTEGER NOT NULL, "
                + "chemin_pdf TEXT"
                + ");";

        String sqlMembres = "CREATE TABLE IF NOT EXISTS membres ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "nom TEXT NOT NULL"
                + ");";

        String sqlEmprunts = "CREATE TABLE IF NOT EXISTS emprunts ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "membre_id INTEGER, "
                + "livre_id INTEGER, "
                + "FOREIGN KEY(membre_id) REFERENCES membres(id), "
                + "FOREIGN KEY(livre_id) REFERENCES livres(id)"
                + ");";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {

            // Forcer la suppression de la vieille table si elle n'a pas les nouvelles colonnes
            stmt.execute("DROP TABLE IF EXISTS livres;");

            // Création des tables
            stmt.execute(sqlLivres);
            stmt.execute(sqlMembres);
            stmt.execute(sqlEmprunts);

            System.out.println("Base de données SQLite initialisée avec succès.");

        } catch (SQLException e) {
            System.err.println("Erreur d'initialisation DB : " + e.getMessage());
        }
    }
}