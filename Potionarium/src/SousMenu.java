import java.util.InputMismatchException;
import java.util.Scanner;

public class SousMenu { //sous_menu
    private int choix_utilisateur;//choix_utilisateur=0choice
    private int retour;//retour=0revenir
    private int archive;//archive=0choixArchive
    /*Option de Modification */
    public void modification(){ // modification=modifier
        switch (choix_utilisateur){
            case 1:
                Ingredient modifier_ingredient = new Ingredient();//0modifIngredient
                modifier_ingredient.modifierIngredient();
                break;
            case 2:
                Potion modifier_potion = new Potion();//0modifPotion=modifier_potion
                modifier_potion.modification_potion();//modifierPotion=modification_potion
                break;
            case 3:
                Inventeur modifier_inventeur = new Inventeur();//0modifInventeur=
                modifier_inventeur.Modification_inventeur();//modifierInventeur
                break;
        }
    }


    /* Gestion des archives */
    public void Archive(){ //archive
            info_archives();
            switch (archive) {
                case 1:
                    Ingredient archiver_ingredient = new Ingredient();//0archIngredient=
                    archiver_ingredient.archive_des_ingredients();//archiveIngredient=archive_des_ingredients
                    break;
                case 2:
                    Potion archiver_potion = new Potion();//0archPotion=
                    archiver_potion.archive_des_potions();//0archivePotion=archive_des_potions
                    break;
                case 3:
                    Inventeur archiver_inventeur = new Inventeur();//0archInventeur=archiver_inventeur
                    archiver_inventeur.archive_des_inventeurs();//archiveInventeur=archive_des_inventeurs
                    break;
            }

    }
    /*Demander le choix de l'archive*/
    public void info_archives() { //info_archives=0demanderChoixArchive
        System.out.println("ARCHIVE");
        System.out.println("1 : INGERDIENTS ");//Pour l'archive des ingrédients tapez « 1 »
        System.out.println("2 : POTIONS ");//
        System.out.println("3 : INVENTEURS");//INVENTEURS


        Boolean verif = false;//verif

        /* gestion d'ereurs */
        while (!verif) {
            try {
                Scanner scan = new Scanner(System.in);
                this.archive = scan.nextInt();
                while (this.archive > 3 || this.choix_utilisateur < 1) {
                    System.out.println("VEUILLEZ SAISIR LE CHIFFRE CORRESPONDANT A VOTRE CHOIX : ");
                    try {
                        Scanner scann = new Scanner(System.in);
                        this.archive = scann.nextInt();
                    } catch (InputMismatchException except) {
                        System.out.println("VEUILLEZ ENTRER UN CHOIX VALIDE : ");
                    }
                }
                verif = true;
            } catch (InputMismatchException except) {
                System.out.println("VEUILLEZ ENTRER UN CHOIX VALIDE : ");
            }
        }



    }



    /* Gestion des suppresiions*/
    public void Suppression() { //Suppression=0supprimer
        switch (choix_utilisateur) {
            case 1:
                Ingredient supprimer_ingredient = new Ingredient();//suppIngredient0=
                supprimer_ingredient.supprimer_des_ingredients();//supprimer_des_ingredients=0supprimerIngredient
                break;
            case 2:
                Potion supprimer_potion = new Potion();//suppPotion0=
                supprimer_potion.suppression_des_potions();//supprimerPotion
                break;
            case 3:
                Inventeur supprimer_inventeur = new Inventeur();//suppInventeur0=supprimer_inventeur
                supprimer_inventeur.suppression_inventeur();//suppression_inventeur=0supprimerInventeur
                break;
        }
    }



    /*Gestion du choix ajouter du sous menu*/
    public void affichage() { //affichage=0afficher
        switch (this.choix_utilisateur) {
            case 1:
                Ingredient ajouter_ingredient = new Ingredient();//ig0=ajouter_ingredient
                ajouter_ingredient.afficher_des_ingredients();//afficher_des_ingredients=0afficherIngredient
                break;
            case 2:
                Potion ajouter_potion = new Potion();//0pt=ajouter_potion
                ajouter_potion.Affichage_potion();//Affichage_potion=0afficherPotion
                break;
            case 3:
                Inventeur ajout_inventeur = new Inventeur();//0iv=ajouter_inventeru
                ajout_inventeur.afficher_les_inventeurs();//afficher_les_inventeurs=0afficherInventeur
                break;
        }

    }
    /*Gestion du choix ajouter */
    public void ajout(){ //ajout=0ajouter
        //String nom;

        switch (this.choix_utilisateur){
            case 1:
                Ingredient objet1 = new Ingredient();//objet1=in1
                objet1.Info_Ingredient();//Info_Ingredient=demanderIngredient
                objet1.ajouter_des_ingredients();//ajouter_des_ingredients=ajouterIngredient
                break;
            case 2:
                Potion objet2 = new Potion();
                objet2.Info_potions();//Info_potions=demanderPotion
                objet2.Ajout_potion();//Ajout_potion=ajouterPotion
                break;
            case 3:
                Inventeur objet3 = new Inventeur();
                objet3.info_inventor();//info_inventor=demanderInventeur
                objet3.ajout_inventeur();//ajout_inventeur=ajouterInventeur
                break;
        }
    }
    /*Gestion du retour au menu precedent*/
    public boolean Retour() { //Retour=menuPrecedent
        System.out.println("RETOUR : 0");
        Boolean verif = false;

        /* gestion d'ereurs */
        while (!verif) {
            try {
                Scanner scan = new Scanner(System.in);
                this.retour = scan.nextInt();

                verif = true;
            } catch (InputMismatchException except) {
                System.out.println("VEUILLEZ ENTRER UN CHOIX VALIDE :");
            }
        }
        if (retour == 0) {
            return true;
        }
        else {
            return false;
        }
    }
    /*Gestion des choix a faire dans le sous menu*/
    public SousMenu() {//Sous_menu=
        System.out.println("VEUILLEZ ENTRER LE CHIFFRE CORRESPONDANT AU CHOIX SOUHAITE : ");
        System.out.println("INGREDIENT : 1");
        System.out.println("POTION : 2");
        System.out.println("INVENTEUR : 3");


        Boolean verif = false;

        /* gestion d'ereurs */
        while (!verif) {
            try {
                Scanner scan = new Scanner(System.in);
                this.choix_utilisateur = scan.nextInt();
                while (this.choix_utilisateur > 3 || this.choix_utilisateur < 1) {
                    System.out.println("VEUILLEZ ENTRER UN CHOIX VALIDE ENTRE 1 ET 3 : ");
                    try {
                        Scanner scann = new Scanner(System.in);
                        this.choix_utilisateur = scann.nextInt();
                    } catch (InputMismatchException except) {
                        System.out.println("VEUILLEZ ENTRER UN CHOIX VALIDE :");
                    }
                }
                verif = true;
            } catch (InputMismatchException except) {
                System.out.println("VEUILLEZ ENTRER UN CHOIX VALIDE :");
            }
        }



    }


}
