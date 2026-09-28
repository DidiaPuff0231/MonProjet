import java.util.*;
public class Main {
    public static void main(String[] args) {
        boolean actif = true;
        while (actif) {

            System.out.println(" ");
            System.out.println("--------POTIONARIUM------------");
            System.out.println(" ");
            System.out.println("TAPEZ 1 POUR AJOUTER UN ELEMENT");
            System.out.println("TAPEZ 2 POUR AFFICHER LES ELEMENTS");
            System.out.println("TAPEZ 3 POUR MODIFIER LES ELEMENTS");
            System.out.println("TAPEZ 4 POUR SUPPRIMER LES ELEMENTS");
            System.out.println("TAPEZ 5 POUR EN SAVOIR PLUS");
            System.out.println("TAPEZ 0 POUR QUITTER");


            int choix = 0;
            Boolean valide = false;

            //gerer les erreurs
            while (!valide) {
                try {
                    Scanner sc = new Scanner(System.in);
                    choix = sc.nextInt();
                    while (choix > 5 || choix < 0) {
                        System.out.println("QUEL EST VOTRE CHOIX?");
                        try {
                            Scanner sch = new Scanner(System.in);
                            choix = sch.nextInt();
                        } catch (InputMismatchException e) {
                            System.out.println("VEUILLEZ ENTRER UN CHOIX VALIDE !");
                        }
                    }
                    valide = true;
                } catch (InputMismatchException e) {
                    System.out.println("VEUILLEZ ENTRER UN CHOIX VALIDE !");
                }
            }



            //FONCTIONS
            switch (choix) {
                case 1:
                    System.out.println("AJOUT");
                    SousMenu objet1 = new SousMenu();
                    objet1.ajout();//ajouter
                    actif = objet1.Retour();
                    break;
                case 2:
                    System.out.println("AFFICHAGE");
                    SousMenu objet2 = new SousMenu();
                    objet2.affichage();
                    System.out.println("TAPEZ 4 POUR L'AFFICHAGE ");
                    Scanner sc = new Scanner(System.in);
                    int n = sc.nextInt();
                    if (n == 4){objet2.Archive();}
                    actif = objet2.Retour();
                    break;
                case 3:
                    System.out.println("MODIFICATION");
                    SousMenu objet3 = new SousMenu();
                    objet3.modification();
                    actif = objet3.Retour();
                    break;
                case 4:
                    System.out.println("SUPPRESSION");
                    SousMenu objet4 = new SousMenu();
                    objet4.Suppression();
                    actif = objet4.Retour();
                    break;
                case 5:
                    System.out.println("SAVOIR PLUS");
                    System.out.println(" ");
                    Inventeur decrire = new Inventeur();
                    decrire.A_propos();//description
                    actif = decrire.Retour();
                    break;
                case 0:
                    System.out.println("FIN DU PROGRAMME");
                    actif = false;
                    break;

            }
        }




    }
}