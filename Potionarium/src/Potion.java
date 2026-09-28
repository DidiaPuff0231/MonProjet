import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Potion {


    private String Nom_de_la_potion;//Nom_de_la_potion
    private String Ingredient_1;// Ingredient_1
    private int Quantite_1;//Quantite_1
    private String Ingredient_2;// Ingredient_2
    private int Quantite_2;//Quantite_2
    private String Ingredient_3;// Ingredient_2
    private int Quantite_3;//Quantite_3




    /* Les getters et les setters */
    public String getNom_potion(){ //getNomPotion
        return this.Nom_de_la_potion;
    }
    public void setNom_de_la_potion(String nom_de_la_potion){
        this.Nom_de_la_potion = nom_de_la_potion;
    }

    public String getIngredient_1(){
        return this.Ingredient_1;
    }
    public void setIngredient_1(String ingredient_1){//setIngred1 ingred1
        this.Ingredient_1 = ingredient_1;
    }

    public String getIngredient_2(){
        return this.Ingredient_2;
    }
    public void setIngredient_2(String ingredient_2){
        this.Ingredient_2 = ingredient_2;
    }
    public String getIngredient_3(){
        return this.Ingredient_3;
    }
    public void setIngredient_3(String ingredient_3){
        this.Ingredient_3 = ingredient_3;
    }

    public int getQuantite_1(){
        return this.Quantite_1;
    }
    public void setQuantite_1(int q1) {
        this.Quantite_1 = q1;
    }

    public int getQuantite_2(){
        return this.Quantite_2;
    }
    public void setQuantite_2(int q2) {
        this.Quantite_2 = q2;
    }

    public int getQuantite_3(){
        return this.Quantite_3;
    }
    public void setQuantite_3(int q3) {
        this.Quantite_3 = q3;
    }



    /* Les methodes */
    String fichier_des_potions = "potion.txt";//nomFichier= fichier_des_potions

    /* Archives des Potion */
    public void archive_des_potions(){// archivePotion=archive_des_potions
        // Lecture du fichier
        try (BufferedReader BR = new BufferedReader(new FileReader("ArchivePotion.txt"))) {
            String i; // Variable pour stocker chaque i lue
            // Lire chaque i tant qu'il y a des lignes à lire
            while ((i = BR.readLine()) != null) {
                // Affiche la i lue
                System.out.println(i);
            }
        } catch (IOException except) {
            // Gère les exceptions d'entrée/sortie
            System.err.println("Erreur de lecture " + except.getMessage());
        }

    }

    /*Methode pour supprimer une potion*/
    public void suppression_des_potions() {//supprimerPotionn=suprresion_des_potions
        System.out.println("VEUILLEZ SAISIR LE NOM DE LA POTION : ");
        Scanner scanner = new Scanner(System.in);
        String Potion_a_supprimer = scanner.nextLine();//motAchercherR=
        String Archive_des_potions = "ArchivePotion.txt";//nomFichierDestinationN=Archive_des_potions


        List<String> Potions_archives = new ArrayList<>();//lignesConserveess=Potions_archives

        // Lire le fichier source et traiter les lignes
        try (BufferedReader BR = new BufferedReader(new FileReader(fichier_des_potions))) {
            String i;
            while ((i = BR.readLine()) != null) {
                if (i.contains(Potion_a_supprimer)) {
                    // Écrire la i contenant le mot dans le fichier de destination
                    try (BufferedWriter BW = new BufferedWriter(new FileWriter(Archive_des_potions, true))) {
                        BW.write(i);
                        BW.newLine(); // Écrire une nouvelle i
                    } catch (IOException except) {
                        System.err.println("ERROR(write):écriture dans le fichier : " + except.getMessage());
                    }
                } else {
                    // Conserver les lignes qui ne contiennent pas le mot
                    Potions_archives.add(i);
                }
            }
        } catch (IOException except) {
            System.err.println("Erreur de BR : " + except.getMessage());
        }

        // Écrire les lignes conservées dans le fichier source
        try (BufferedWriter BW = new BufferedWriter(new FileWriter(fichier_des_potions))) {
            for (String i : Potions_archives) {
                BW.write(i);
                BW.newLine(); // Écrire une nouvelle i
            }
        } catch (IOException except) {
            System.err.println("ERROR(write):écriture dans le fichier :" + except.getMessage());
        }
    }


        /* Methode pour ajouter une potion*/
    public void Ajout_potion(){//ajouterPotionn=Ajout_potion
        // Écriture dans le fichier
        try (BufferedWriter BW = new BufferedWriter(new FileWriter(fichier_des_potions, true))) {
            // Écriture des lignes dans le fichier
            BW.newLine();
            BW.write(this.Nom_de_la_potion + "    "+ this.Ingredient_1 +"    "+this.Quantite_1+ this.Ingredient_2 +"    "+this.Quantite_2+ this.Ingredient_3 +"    "+this.Quantite_3); // Écrit "jOHN" dans le fichier
            BW.newLine(); // Ajoute un saut de i
        } catch (IOException except) {
            // Gère les exceptions d'entrée/sortie
            System.err.println("ERROR(write):écriture dans le fichier :" + except.getMessage());
        }
    }




    /* Methode pour afficher les informations des potions*/
    public void Affichage_potion(){//Affichage_potion=afficherPotion
        // Lecture du fichier
        try (BufferedReader BR = new BufferedReader(new FileReader(fichier_des_potions))) {
            String i; // Variable pour stocker chaque i lue
            // Lire chaque i tant qu'il y a des lignes à lire
            while ((i = BR.readLine()) != null) {
                // Affiche la i lue
                System.out.println(i);
            }
        } catch (IOException except) {
            // Gère les exceptions d'entrée/sortie
            System.err.println("ERROR(write):écriture dans le fichier :" + except.getMessage());
        }

    }




    /*Methode pour demander les informations d'une potion*/
    public void Info_potions() {//demanderPotionn= Info_potions
        System.out.println("VEUILLEZ ENTRER LE NOM DE LA POTION SOUHAITEE : ");
        Scanner scanner = new Scanner(System.in);
        this.Nom_de_la_potion = scanner.nextLine();

        /*Ingredient 1*/
        System.out.println("VEUILLEZ ENTRER LE NOM DU PREMIER INGREDIENT : ");//"Entrez le nom de l'ingredient 1"
        Scanner scan = new Scanner(System.in);
        this.Ingredient_1 = scan.nextLine();

        System.out.println("VEUILLEZ ENTRER SA QUANTITE : ");//Entrez la quantité de l'ingredient 1
        Boolean verif = false;//verif=valide1
        /* gestion d'ereurs */
        while (!verif) {
            try {
                Scanner sc = new Scanner(System.in);
                this.Quantite_1 = sc.nextInt();

                verif = true;
            } catch (InputMismatchException except) {
                System.out.println("VEUILLEZ ENTRER UN CHOIX VALIDE : ");
            }
        }

        /*Ingredient 2*/
        System.out.println("VEUILLEZ ENTRER LE NOM DU SECOND INGREDIENT : ");
        Scanner scn = new Scanner(System.in);
        this.Ingredient_2 = scn.nextLine();

        System.out.println("VEUILLEZ ENTRER SA QUANTITE : ");
        Boolean veriff = false;
        /* gestion d'ereurs */
        while (!veriff) {
            try {
                Scanner sc = new Scanner(System.in);
                this.Quantite_2 = sc.nextInt();

                veriff = true;
            } catch (InputMismatchException except) {
                System.out.println("VEUILLEZ ENTRER UN CHOIX VALIDE : ");
            }
        }

        /*Ingredient 3*/
        System.out.println("VEUILLEZ ENTRER LE NOM DU TROISIEME INGREDIENT : ");
        Scanner s = new Scanner(System.in);
        this.Ingredient_3 = s.nextLine();

        System.out.println("VEUILLEZ ENTRER SA QUANTITE : ");
        Boolean verif_3 = false;
        /* gestion d'ereurs */
        while (!verif_3) {
            try {
                Scanner sc = new Scanner(System.in);
                this.Quantite_3 = sc.nextInt();

                verif_3 = true;
            } catch (InputMismatchException except) {
                System.out.println("VEUILLEZ ENTRER UN CHOIX VALIDE : ");
            }
        }


    }
    //___________________________________________________________________________________________________________________________
    public void modification_potion (){//modifierPotion=modification_potion
        String potion_a_modifier ; // Mot clé à rechercher//motClee
        String nouvelle_potion; // Nouvelle i à écrire//nouvelleLignen=nouvelle_potion
        List<String> lignes = new ArrayList<>();
        System.out.println("VEUILLEZ ENTRER LE NOM DE LA POTION A MODIFIER : ");
        Scanner scnn = new Scanner(System.in);
        potion_a_modifier = scnn.nextLine();

        //System.out.println("Entrez les modifications: ");

        System.out.println("VEUILLEZ ENTRER LE NOUVEAU NOM : ");
        Scanner scanner = new Scanner(System.in);
        this.Nom_de_la_potion = scanner.nextLine();

        /*Ingredient 1*/
        System.out.println("VEUILLEZ ENTRER LE PREMIER INGREDIENT : ");
        Scanner scan = new Scanner(System.in);
        this.Ingredient_1 = scan.nextLine();

        System.out.println("VEUILLEZ ENTRER SA QUANTITE : ");
        Boolean verif = false;
        /* gestion d'ereurs */
        while (!verif) {
            try {
                Scanner scnnr = new Scanner(System.in);
                this.Quantite_1 = scnnr.nextInt();

                verif = true;
            } catch (InputMismatchException except) {
                System.out.println("VEUILLEZ ENTRER UN CHOIX VALIDE : ");
            }
        }
        //
        /*Ingredient 2*/
        System.out.println("VEUILLEZ ENTRER LE SECOND INGREDIENT : ");
        Scanner scn = new Scanner(System.in);
        this.Ingredient_2 = scn.nextLine();

        System.out.println("VEUILLEZ ENTRER SA QUANTITE : ");
        Boolean veriff = false;
        /* gestion d'ereurs */
        while (!veriff) {
            try {
                Scanner scanm = new Scanner(System.in);
                this.Quantite_2 = scanm.nextInt();
                //
                veriff = true;
            } catch (InputMismatchException except) {
                System.out.println("VEUILLEZ ENTRER UN CHOIX VALIDE : ");
            }
        }

        /*Ingredient 3*/
        System.out.println("VEUILLEZ ENTRER LE TROISIEME INGREDIENT : ");
        Scanner s = new Scanner(System.in);
        this.Ingredient_3 = s.nextLine();

        System.out.println("VEUILLEZ ENTRER SA QUANTITE : ");
        Boolean verif_3 = false;
        /* gestion d'ereurs */
        while (!verif_3) {
            try {
                Scanner scann = new Scanner(System.in);
                this.Quantite_3 = scann.nextInt();

                verif_3 = true;
            } catch (InputMismatchException except) {
                System.out.println("VEUILLEZ ENTRER UN CHOIX VALIDE : ");
            }
        }

        nouvelle_potion = (this.Nom_de_la_potion +"    "+this.Ingredient_1+"   "+this.Quantite_1+"   "+this.Ingredient_2+"   "+this.Quantite_2+"  "+this.Ingredient_3+"   "+this.Quantite_3);

        // Étape 1 : Lire le fichier
        try (BufferedReader BR = new BufferedReader(new FileReader(fichier_des_potions))) {
            String i;
            while ((i = BR.readLine()) != null) {
                // Étape 2 : Trouver la i contenant le mot clé
                if (i.contains(potion_a_modifier)) {
                    // Modifier la i
                    lignes.add(nouvelle_potion); // Remplacer par la nouvelle i
                } else {
                    lignes.add(i); // Conserver la i originale
                }
            }
        } catch (IOException except) {
            except.printStackTrace();
        }

        // Étape 4 : Écrire les modifications dans le fichier
        try (BufferedWriter BW = new BufferedWriter(new FileWriter(fichier_des_potions))) {
            for (String ligne_modifiee : lignes) {
                BW.write(ligne_modifiee);
                BW.newLine(); // Ajouter une nouvelle i
            }
        } catch (IOException except) {
            except.printStackTrace();
        }
    }
}
