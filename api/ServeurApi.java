package api;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URI;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ServeurApi {

    private static final String BDD_URL = "jdbc:sqlite:bibliotheque.db"; // Indiquez le chemin vers votre fichier .db

    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        // Endpoint PDF dynamique avec paramètre ID
        server.createContext("/api/pdf", new PdfHandler());

        server.setExecutor(null);
        System.out.println("🚀 Serveur API dynamique démarré sur http://localhost:8080");
        server.start();
    }

    static class PdfHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            URI requestUri = exchange.getRequestURI();
            String query = requestUri.getQuery(); // Récupère le paramètre "?id=..."
            
            int livreId = -1;
            if (query != null && query.startsWith("id=")) {
                try {
                    livreId = Integer.parseInt(query.substring(3));
                } catch (NumberFormatException e) {
                    // ID invalide
                }
            }

            if (livreId == -1) {
                envoyerErreur(exchange, 400, "ID du livre invalide ou manquant");
                return;
            }

            // Chercher le chemin du PDF dans SQLite
            String cheminPdf = obtenirCheminPdfDepuisBdd(livreId);

            if (cheminPdf == null || cheminPdf.isEmpty()) {
                envoyerErreur(exchange, 404, "Aucun PDF associé à ce livre");
                return;
            }

            File pdfFile = new File(cheminPdf);
            if (!pdfFile.exists()) {
                envoyerErreur(exchange, 404, "Fichier PDF introuvable sur le disque du serveur");
                return;
            }

            // Envoi du fichier PDF
            exchange.getResponseHeaders().set("Content-Type", "application/pdf");
            exchange.sendResponseHeaders(200, pdfFile.length());

            try (OutputStream os = exchange.getResponseBody();
                 FileInputStream fis = new FileInputStream(pdfFile)) {
                fis.transferTo(os);
            }
        }

        private String obtenirCheminPdfDepuisBdd(int id) {
            String sql = "SELECT cheminPdf FROM livre WHERE id = ?";
            try (Connection conn = DriverManager.getConnection(BDD_URL);
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                
                pstmt.setInt(1, id);
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    return rs.getString("cheminPdf");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        private void envoyerErreur(HttpExchange exchange, int code, String message) throws IOException {
            exchange.sendResponseHeaders(code, message.getBytes().length);
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(message.getBytes());
            }
        }
    }
    
}