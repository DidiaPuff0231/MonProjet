import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Ingredient {

    private String Nom_ingredient;
    private int Quantite;
    private int Prix_Unitaire;
    private String Type_de_l_ingredient;//Type
    private int choix_type;



    /* Les getters et les setters */
    public String getNom_Ingredient(){
        return this.Nom_ingredient;
    }
    public void setNom_Ingredient(String nom_Ingredient){
        this.Nom_ingredient = nom_Ingredient;
    }

    public int getQuantite(){
        return this.Quantite;
    }
    public void setQuantite(int quantite) {
        this.Quantite = quantite;
    }

    public int getPrix_Unitaire(){
        return this.Prix_Unitaire;
    }
    public void setPrix_Unitaire(int prix_Unitaire){
        this.Prix_Unitaire = prix_Unitaire;
    }

    public String getType(){
        return this.Type_de_l_ingredient;
    }
    public void setType(String type_de_l_ingredient){
        this.Type_de_l_ingredient = type_de_l_ingredient;
    }

    // methodes
    String fichier_des_ingredients = "ingredient.txt";//nomFichier
    // modifier les ingretients




    // Archives des ingredients
    public void archive_des_ingredients(){//archiveIngredient
        // Lecture du fichier
        try (BufferedReader BR = new BufferedReader(new FileReader("ArchiveIngredient.txt"))) {//ArchiveIngredient.txt
            String i; // Variable pour stocker chaque ligne lue
            // Lire chaque ligne tant qu'il y a des lignes à lire
            while ((i = BR.readLine()) != null) {
                // Affiche la ligne lue
                System.out.println(i);
            }
        } catch (IOException except) {//e
            // Gère les exceptions d'entrée/sortie
            System.err.println("ERROR(read file) : " + except.getMessage());
        }

    }

    public void supprimer_des_ingredients() {
        System.out.println("VEUILLEZ SAISIR LE NOM DE L'ELEMENT A SUPPRIMER");
        Scanner scanner = new Scanner(System.in);
        String element_a_supprimer = scanner.nextLine();//motAchercher
        String fichier_archive = "Archive_des_Ingredient.txt";//nomFichierDestination


        List<String> elements_archives = new ArrayList<>();//lignesConservees

        // Lire le fichier source et traiter les lignes
        try (BufferedReader BR = new BufferedReader(new FileReader(fichier_des_ingredients))) {//nomFichier
            String i;//ligne=i
            while ((i = BR.readLine()) != null) {
                if (i.contains(element_a_supprimer)) {
                    // Écrire la ligne contenant le mot dans le fichier de destination
                    try (BufferedWriter BW = new BufferedWriter(new FileWriter(fichier_archive, true))) {
                        BW.write(i);
                        BW.newLine(); // Écrire une nouvelle ligne
                    } catch (IOException except) {//e
                        System.err.println("ERROR(write):écriture dans le fichier : " + except.getMessage());
                    }
                } else {
                    // Conserver les lignes qui ne contiennent pas le mot
                	elements_archives.add(i);
                }
            }
        } catch (IOException except) {
            System.err.println("ERROR(read file)  : " + except.getMessage());
        }

        // Écrire les lignes conservées dans le fichier source
        try (BufferedWriter BW = new BufferedWriter(new FileWriter(fichier_des_ingredients))) {
            for (String i : elements_archives) {
                BW.write(i);
                BW.newLine(); // Écrire une nouvelle ligne
            }
        } catch (IOException except) {
            System.err.println("ERROR(write):écriture dans le fichier : " + except.getMessage());
        }
    }






    /*Methode pour ajouter un ingredient*/
    public void ajouter_des_ingredients(){
        // Écriture dans le fichier
        try (BufferedWriter BW = new BufferedWriter(new FileWriter(fichier_des_ingredients, true))) {
            // Écriture des lignes dans le fichier
            BW.newLine();
            BW.write(this.Nom_ingredient + "    " + this.Quantite+"    " + this.Prix_Unitaire+ "    " + this.Type_de_l_ingredient); // 
            BW.newLine(); // Ajoute un saut de ligne
        } catch (IOException except) {
            // Gère les exceptions d'entrée/sortie
            System.err.println("ERROR(write):écriture dans le fichier : : " + except.getMessage());
        }
    }


/* Methode pour afficher les informations des ingredients*/
    public void afficher_des_ingredients() {
        // Lecture du fichier
        try (BufferedReader BR = new BufferedReader(new FileReader(fichier_des_ingredients))) {
            String i; // Variable pour stocker chaque ligne lue
            // Lire chaque ligne tant qu'il y a des lignes à lire
            while ((i = BR.readLine()) != null) {
                // Affiche la ligne lue
                System.out.println(i);
            }
        } catch (IOException except) {
            // Gère les exceptions d'entrée/sortie
            System.err.println("ERROR(read file)  : " + except.getMessage());
        }
    }
    /*Methode pour demander les informations d'un ingredient*/
    public void Info_Ingredient(){//demanderIngredient
        System.out.println("VEUILLEZ PRECISER LE NOM DE L'INGREDIENT : ");
        Scanner sc = new Scanner(System.in);
        this.Nom_ingredient = sc.nextLine();
        System.out.println("VEUILLEZ PRECISER LA QUANTITE : ");
        
        Boolean verification_1 = false;//valide1
        //Boolean verification_2 = false;//valide2
        //Boolean verification_3 = false; // valide

        /* gestion d'ereurs */
        while (!verification_1) {
            try {
                Scanner scn = new Scanner(System.in);
                this.Quantite = scn.nextInt();
                verification_1 = true;
            } catch (InputMismatchException except) {
                System.out.println("VEUILLEZ ENTRER UN CHOIX VALIDE : ");
            }
        }
        System.out.println("VEUILLEZ ENTRER LE PRIX DE L'INGREDIENT : ");
        //Boolean valide2 = false;

        /* gestion d'ereurs */
        Boolean verification_2 = false;//valide2
        while (!verification_2) {
            try {
                Scanner scnn = new Scanner(System.in);
                this.Prix_Unitaire = scnn.nextInt();

                verification_2 = true;
            } catch (InputMismatchException except) {
                System.out.println("VEUILLEZ ENTRER UN CHOIX VALIDE : ");
            }
        }

        type_ingredient(); //demanderTypeIngredient
    }
    /* Demander le type de l'ingredient*/
    public void type_ingredient(){
        /* Creation des listes */
        String[] liste_des_types = {"Gemme", "Materiel magique", "Materiel non magique"};
        String[] numerotation_des_types = {"1 Gemme", "2 Materiel magique", "3 Materiel non magique"};
        Boolean verification_3 = false; // valide

        /*
        System.out.println("TYPES : ");
        for (int x = 0; x < 3; x++){
            System.out.println("VEUILLEZ ENTRER VOTRE CHOIX : "+(x+1)+" "+ liste_des_types[x] );
            
        }
        */
        System.out.println("VEUILLEZ SPECIFIER LE CHOIX : ");
        for (String type : numerotation_des_types) {
            System.out.println(type);
        }
        
        
//___________________________________________________________________________________________________________________

        //Boolean verification_3 = false; // valide

        // gestion d'ereurs 
        while (!verification_3) {//valide
            try {
                Scanner sc = new Scanner(System.in);
                this.choix_type = sc.nextInt();

                while (this.choix_type >  3| this.choix_type < 1) {
                    System.out.println("VEUILLEZ ENTRER UN CHOIX COMPRIS ENTRE : ");
                    for (String type_de_lingredient : numerotation_des_types) {
                        System.out.println(type_de_lingredient);
                    }
                    try {
                        Scanner sch = new Scanner(System.in);
                        this.choix_type = sch.nextInt();
                    } catch (InputMismatchException except) {
                        System.out.println("VEUILLEZ ENTRER UN CHOIX VALIDE : ");
                    }
                }
                verification_3 = true;
            } catch (InputMismatchException except) {
                System.out.println("VEUILLEZ ENTRER UN CHOIX VALIDE : ");
            }
        }
        this.Type_de_l_ingredient = liste_des_types[this.choix_type];//Type

    }

    public void modifierIngredient (){//Modification_Ingedient
        String mot_a_modifier ; // Mot clé à rechercher //motCle
        String modification_ingredient; // Nouvelle ligne à écrire //nouvelleLigne
        List<String> liste_des_ingredients_a_modifier = new ArrayList<>();//lignes
        System.out.println("VEUILLEZ ENTRER L'INGEDIENT A MODIFIER : ");//Entrez le nom exact de l'ingredient à modifier: 
        Scanner scnn = new Scanner(System.in);
        mot_a_modifier = scnn.nextLine();

        System.out.println("VEUILLEZ ENTRER LE NOUVEL INGREDIENT : ");//Entrez les modifications: 
        System.out.println("VEUILLEZ INDIQUER SON NOM,SA QUANTITE,SON PRIX ET SON TYPE : ");//Nom de l'ingredient, pincée, prix unitaire, type
        Scanner scn = new Scanner(System.in);
        modification_ingredient = scn.nextLine();


        // Étape 1 : Lire le fichier
        try (BufferedReader BR = new BufferedReader(new FileReader(fichier_des_ingredients))) {
            String ligne_a_modifier;//ligne
            while ((ligne_a_modifier = BR.readLine()) != null) {
                // Étape 2 : Trouver la ligne contenant le mot clé
                if (ligne_a_modifier.contains(mot_a_modifier)) {
                    // Modifier la ligne
                	liste_des_ingredients_a_modifier.add(modification_ingredient); // Remplacer par la nouvelle ligne
                } else {
                	liste_des_ingredients_a_modifier.add(ligne_a_modifier); // Conserver la ligne originale
                }
            }
        } catch (IOException except) {
        	except.printStackTrace();
        }

        // Étape 4 : Écrire les modifications dans le fichier
        try (BufferedWriter BW = new BufferedWriter(new FileWriter(fichier_des_ingredients))) {
            for (String modification : liste_des_ingredients_a_modifier) {//ligneModifiee=modification
            	BW.write(modification);
            	BW.newLine(); // Ajouter une nouvelle ligne
            }
        } catch (IOException except) {
            //System.err.println("ERROR" + except.getMessage());
        	except.printStackTrace();
        }
    }

}



