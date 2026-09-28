import java.util.InputMismatchException;
import java.util.Scanner;

public class Type_Magie {
    private String type_de_magie;//NomTypeMagie=Type_de_magie
    private String Competences_magiques;//CompetencesTypeMagie=Competences_magiques
    private  int choix_du_type;//choix_du_type=choix

    /* Creation des listes */
    String[] liste_des_types_de_magie = {"BLANCHE", "NOIRE", "BLEUE", "VERTE", "ROUGE", "POURPRE"};//liste_des_types_de_magie=typeMagie
    String[] liste_des_competences = {"Bienveillance;Soin", "Diabolisme;Sorcellerie", "Bienveillance;Sorcellerie", "Botanique;Soin", "Charisme;Diabolisme;Invocateur","Disparition"};//liste_des_competences

    public String info_type_de_magie(){//info_type_de_magie=demanderTypeMagie
        System.out.println("PARCHEMIN");
        for (int i = 0; i < 5; i++){
            System.out.println("MAGIE : "+ liste_des_types_de_magie[i] + ", COMPETENCES : " + liste_des_competences[i]);
        }
        System.out.println("VEUILLEZ ENTRER LE TYPE DE MAGIE : ");
        for (int i = 0; i < 5; i++){
        	System.out.println("VEUILLEZ ENTRER LE NOMBRE INDIQUE POUR LE TYPE DE MAGIE REHCERCHE : ");
            System.out.println( " - " + (i+1) + " » " + liste_des_types_de_magie[i]);
        }

        Boolean verification = false;//valide=verification

        /* gestion d'ereurs */
        while (!verification) {
            try {
                Scanner scanner = new Scanner(System.in);
                this.choix_du_type = scanner.nextInt();

                while (this.choix_du_type >  6|| this.choix_du_type < 1) {
                    System.out.println("VEUILLEZ ENTRER UN CHOIX VALIDE ENTRE 1 ET 6 : ");
                    try {
                        Scanner scanne = new Scanner(System.in);
                        this.choix_du_type = scanne.nextInt();
                    } catch (InputMismatchException except) {
                        System.out.println("VEUILLEZ ENTRER UN CHOIX VALIDE : ");
                    }
                }
                verification = true;
            } catch (InputMismatchException except) {
                System.out.println("VEUILLEZ ENTRER UN CHOIX VALIDE : ");
            }
        }
        this.type_de_magie = liste_des_types_de_magie[this.choix_du_type];
        return type_de_magie;
    }



}
