package bibliotheque;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;

import javax.swing.*;
import java.io.File;
import java.io.InputStream;
import java.io.IOException;

public class LecteurPdfDialog extends JDialog {

    private PDDocument document;

    // 1. Constructeur pour le fichier local actuel (String chemin, String titre)
    public LecteurPdfDialog(JFrame parent, String cheminPdf, String titre) throws IOException {
        super(parent, titre != null ? titre : "Lecteur PDF", true);
        this.document = Loader.loadPDF(new File(cheminPdf));
        initialiserComposants();
    }

    // 2. Constructeur pour la future API REST (InputStream stream, String titre)
    public LecteurPdfDialog(JFrame parent, InputStream streamPdf, String titre) throws IOException {
        super(parent, titre != null ? titre : "Lecteur PDF (API)", true);
        byte[] bytes = streamPdf.readAllBytes();
        this.document = Loader.loadPDF(bytes);
        initialiserComposants();
    }

    private void initialiserComposants() {
        // Gardez ici votre code d'interface graphique existant
        setSize(800, 600);
        setLocationRelativeTo(getParent());
    }

    @Override
    public void dispose() {
        if (document != null) {
            try {
                document.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        super.dispose();
    }
}