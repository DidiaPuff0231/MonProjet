package bibliotheque;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ServiceEmpruntImplTest {

    private ServiceEmprunt serviceEmprunt;
    private Membre membre;
    private Livre livre;

    @BeforeEach
    void setUp() {
        serviceEmprunt = new ServiceEmpruntImpl();
        membre = new Adherent("Alice");
        livre = new LivreImpl("Le Petit Prince");
    }

    @Test
    void testEmprunterLivreSucces() throws LivreIndisponibleException {
        serviceEmprunt.emprunter(membre, livre);

        assertFalse(livre.estDisponible());
        assertEquals(1, membre.getLivresEmpruntes().size());
        assertTrue(membre.getLivresEmpruntes().contains(livre));
    }

    @Test
    void testEmprunterLivreIndisponibleLeveException() throws LivreIndisponibleException {
        // Premier emprunt
        serviceEmprunt.emprunter(membre, livre);

        // Tentative de deuxième emprunt sur le même livre -> doit lever l'exception
        assertThrows(LivreIndisponibleException.class, () -> {
            serviceEmprunt.emprunter(membre, livre);
        });
    }

    @Test
    void testRetournerLivre() throws LivreIndisponibleException {
        serviceEmprunt.emprunter(membre, livre);
        serviceEmprunt.retourner(membre, livre);

        assertTrue(livre.estDisponible());
        assertEquals(0, membre.getLivresEmpruntes().size());
    }
}