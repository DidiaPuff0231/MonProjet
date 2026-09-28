import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Inventeur {


    private String Nom_Inventeur;//NomInventeur=Nom_Inventeur
    private String Lignee_Inventeur;//LigneInventeur=Lignee_Inventeur
    private String Type_Magie;//TypeMagie=Type_Magie
    private String Element;//ElementIventeur=Element
    private String Benediction;//
    //private  int choixLignee;//choix_de_la_lignee
    //private  int choixElement;//
    //private  int choixBenediction;//



    /* Les getters et les setters */
    public String getNom_Inventeur(){//getNomInventeur=getNom_Inventeur
        return this.Nom_Inventeur;
    }
    public void setNom_Inventeur(String nom_Inventeur){//setNomInventeur
        this.Nom_Inventeur = nom_Inventeur;
    }

    public String getLignee_Inventeur(){
        return this.Lignee_Inventeur;
    }
    public void setLignee_Inventeur(String lignee_Inventeur) {
        this.Lignee_Inventeur = lignee_Inventeur;
    }

    public String getType_Magie(){
        return this.Type_Magie;
    }
    public void setType_Magie(String type_magie){
        this.Type_Magie = type_magie;
    }

    public String getBenediction(){
        return this.Benediction;
    }
    public void setBenediction(String benediction){
        this.Benediction = benediction;
    }

    /* Les methodes */
    String fichier_des_inventeurs = "inventeur.txt";//fichier_des_inventeurs

    /* Archives des inventeurs */
    public void archive_des_inventeurs(){//archive_des_inventeurs=archiveInventeur
        // Lecture du fichier
        try (BufferedReader BR = new BufferedReader(new FileReader("Archive_des_inventeurs.txt"))) {//reader=BR //"ArchiveInventeur.txt"
            String i; // Variable pour stocker chaque ligne lue //ligne=i
            // Lire chaque ligne tant qu'il y a des lignes à lire
            while ((i = BR.readLine()) != null) {
                // Affiche la ligne lue
                System.out.println(i);
            }
        } catch (IOException except) {//e=except
            // Gère les exceptions d'entrée/sortie
            System.err.println("ERROR(read file) : " + except.getMessage());//Erreur lors de la lecture du fichier : 
        }

    }


    /* Methode pour supprimer les inventeurs*/
    public void suppression_inventeur() {//supprimerInventeur=suppression_inventeur
        System.out.println("VEUILLEZ SAISIR LE NOM DE L'INVENTEUR : ");
        Scanner scanner = new Scanner(System.in);
        String inventeur_a_supprimer = scanner.nextLine();//motAchercher=inventeur_a_supprimer
        String fichier_archive = "Archive_des_inventeurs.txt"; //nomFichierDestination=fichier_archive//ArchiveInventeur.txt=Archive_des_inventeurs.txt


        //List<String> lignesConservees = new ArrayList<>();
        List<String> elements_archives = new ArrayList<>();//lignesConservees

        // Lire le fichier source et traiter les lignes
        try (BufferedReader BR = new BufferedReader(new FileReader(fichier_des_inventeurs))) {
            String i;//ligne
            while ((i = BR.readLine()) != null) {
                if (i.contains(inventeur_a_supprimer)) {
                    // Écrire la ligne contenant le mot dans le fichier de destination
                    try (BufferedWriter BW = new BufferedWriter(new FileWriter(fichier_archive, true))) {//BW=bw
                    	BW.write(i);
                        BW.newLine(); // Écrire une nouvelle ligne
                    } catch (IOException except) {
                        System.err.println("ERROR(write):écriture dans le fichier : " + except.getMessage());
                    }
                } else {
                    // Conserver les lignes qui ne contiennent pas le mot
                	elements_archives.add(i);
                }
            }
        } catch (IOException except) {
            System.err.println("ERROR(read file) :  " + except.getMessage());
        }

        // Écrire les lignes conservées dans le fichier source
        try (BufferedWriter BW = new BufferedWriter(new FileWriter(fichier_des_inventeurs))) {
            for (String i : elements_archives) {
            	BW.write(i);
                BW.newLine(); // Écrire une nouvelle ligne
            }
        } catch (IOException except) {
            System.err.println("ERROR(write):écriture dans le fichier : " + except.getMessage());
        }
    }



        /* Methode pour ajouter un inventeur */
    public void ajout_inventeur(){//ajout_inventeur=
        // Écriture dans le fichier
        try (BufferedWriter BW = new BufferedWriter(new FileWriter(fichier_des_inventeurs, true))) {
            // Écriture des lignes dans le fichier
        	BW.newLine();
        	BW.write(this.Nom_Inventeur + " " + this.Lignee_Inventeur+ " " + this.Type_Magie + " " + this.Element+ " " +this.Benediction);
        	BW.newLine(); // Ajoute un saut de ligne
        } catch (IOException except) {
            // Gère les exceptions d'entrée/sortie
            System.err.println("ERROR(write):écriture dans le fichier : " + except.getMessage());
        }
    }



    /*Methode pour afficher les informations des inventeurs*/
    public void afficher_les_inventeurs(){//afficherInventeur
        // Lecture du fichier
        try (BufferedReader BR = new BufferedReader(new FileReader(fichier_des_inventeurs))) {
            String i; // Variable pour stocker chaque ligne lue
            // Lire chaque ligne tant qu'il y a des lignes à lire
            while ((i = BR.readLine()) != null) {
                // Affiche la ligne lue
                System.out.println(i);
            }
        } catch (IOException except) {
            // Gère les exceptions d'entrée/sortie
            System.err.println("ERROR(read file) : " + except.getMessage());
        }




        }

    /* Methodes spour demander les informations d'un inventeur*/
    public void info_inventor(){//demanderInventeur
        System.out.println("VEUILLEZ SAISIR LE NOM DE L'INVENTEUR : ");
        Scanner scanner = new Scanner(System.in);
        this.Nom_Inventeur = scanner.nextLine();

        info_ligne_inventeur();

        Type_Magie type_magie = new Type_Magie();
        this.Type_Magie = type_magie.info_type_de_magie();

        info_elements_des_inventeurs();

        info_benedictions_des_inventeurs();

    }
//______________________________________________________________________________________________________________________
    /* Demander la lignée de l'inventeur*/
    private  int choix_de_la_lignee;//choixLignee=choix_de_la_lignee
    public void info_ligne_inventeur(){//demanderLigneeInventeur=info_ligne_inventeur
    /* Creation des listes */
    String[] Liste_des_lignees = {"Black", "De Kelliwic’h", "Rogue", "De Kamelott", "Bl'ack", "Strange", "Jedusor"};//ListeLignee=Liste_des_lignees
    //private  int choixLignee;//


        System.out.println("LISTE DES LIGNEES : ");
        System.out.println("VEUILLEZ SAISIR LE NOMBRE INDIQUE POUR LA LIGNEE CORRESPONDANTE : ");
        for (int i = 0; i < 6; i++){
            System.out.println((i+1)+" - "+ Liste_des_lignees[i] );
        }


        Boolean Verif = false;//Verif
        //private  int choixLignee;//

        /* gestion d'ereurs */
        while (!Verif) {
            try {
                Scanner scanner = new Scanner(System.in);
                this.choix_de_la_lignee = scanner.nextInt();

                while (this.choix_de_la_lignee >  7| this.choix_de_la_lignee < 1) {
                    System.out.println("VEUILLEZ ENTRER UN CHOIX VALIDE ENTRE 1 ET 7 : ");
                    try {
                        Scanner scan = new Scanner(System.in);
                        this.choix_de_la_lignee = scan.nextInt();
                    } catch (InputMismatchException except) {
                        System.out.println("VEUILLEZ ENTRER UN CHOIX VALIDE : ");
                    }
                }
                Verif = true;
            } catch (InputMismatchException except) {
                System.out.println("VEUILLEZ ENTRER UN CHOIX VALIDE : ");
            }
        }
        this.Lignee_Inventeur = Liste_des_lignees[this.choix_de_la_lignee];

    }
    /* Demander la benediction de l'inventeur*/
    private  int choix_de_la_benediction;//choix_de_la_benediction 
    public void info_benedictions_des_inventeurs(){ //demanderBenedictionInventeur=info_benedictions_des_inventeurs
        
        String[] liste_des_benedictions = {"Resistance", "Charisme", "Furtivité", "Longue vie", "Sagesse", "Légèreté", "Force mentale"};//liste_des_benedictions
        //private  int choixBenediction;//

        System.out.println("LISTE DES LIGNEES BENEDICTIONS : ");
        System.out.println("VEUILLEZ SAISIR LE NOMBRE INDIQUE POUR LA LIGNEE CORRESPONDANTE : ");
        //System.out.println("BENEDICTIONS : ");
        for (int i = 0; i < 6; i++){
            System.out.println((i+1)+" - "+ liste_des_benedictions[i] );
        }


        Boolean Verif = false;//Verif

        /* gestion d'ereurs */
        while (!Verif) {
            try {
                Scanner scan = new Scanner(System.in);
                this.choix_de_la_benediction = scan.nextInt();

                while (this.choix_de_la_benediction >  7| this.choix_de_la_benediction < 1) {
                    System.out.println("VEUILLEZ ENTRER UN CHOIX VALIDE ENTRE 1 ET 7 : ");
                    try {
                        Scanner scann = new Scanner(System.in);
                        this.choix_de_la_benediction = scann.nextInt();
                    } catch (InputMismatchException except) {
                        System.out.println("VEUILLEZ ENTRER UN CHOIX VALIDE : ");
                    }
                }
                Verif = true;
            } catch (InputMismatchException except) {
                System.out.println("VEUILLEZ ENTRER UN CHOIX VALIDE : ");
            }
        }
        this.Benediction = liste_des_benedictions[this.choix_de_la_benediction];

    }
    /* Demander l'  élément de l'inventeur*/
    private  int choix_de_lelement;// choix_de_lelement=choixElement
    public void info_elements_des_inventeurs(){ //info_elements_des_inventeurs=demanderElementInventeur
        /* Creation des listes */
        String[] Liste_des_elements = {"FEU", "TERRE", "FEU,GLACE", "TERRE, FEU, GLACE, EAU", "FOUDRE", "FOUDRE, FEU", "GLACE, FEU, FOUDRE"};//ListeElement=Liste_des_elements

        System.out.println("LISTE DES LIGNEES ELEMENTS : ");
        System.out.println("VEUILLEZ SAISIR LE NOMBRE INDIQUE POUR LA LIGNEE CORRESPONDANTE : ");
        //System.out.println("Liste des éléments");
        for (int i = 0; i < 6; i++){
            System.out.println((i+1)+" - "+ Liste_des_elements[i] );
        }


        Boolean Verif = false;//Verif=valide

        /* gestion d'ereurs */
        while (!Verif) {
            try {
                Scanner scan = new Scanner(System.in);
                this.choix_de_lelement = scan.nextInt();

                while (this.choix_de_lelement >  7| this.choix_de_lelement < 1) {
                    System.out.println("VEUILLEZ ENTRER UN CHOIX VALIDE ENTRE 1 ET 7 : ");
                    try {
                        Scanner scann2 = new Scanner(System.in);
                        this.choix_de_lelement = scann2.nextInt();
                    } catch (InputMismatchException except) {
                        System.out.println("VEUILLEZ ENTRER UN CHOIX VALIDE : "); 
                    }
                }
                Verif = true;
            } catch (InputMismatchException except) {
                System.out.println("VEUILLEZ ENTRER UN CHOIX VALIDE : ");
            }
        }
        this.Element = Liste_des_elements[this.choix_de_lelement];

    }

    public void Modification_inventeur (){ // Modification_inventeur=modifierInventeur VEUILLEZ SAISIR LE NOM DE L'INVENTEUR : 
        String inventeur_a_modifier ; // Mot clé à rechercher // inventeur_a_modifier
        String nouvel_inventeur; // Nouvelle ligne à écrire //nouvel_inventeur=nouvelleLigne
        List<String> inventeurs = new ArrayList<>();//inventeurs=lignes 
        System.out.println("VEUILLEZ SAISIR LE NOM DE L'INVENTEUR A MODIFIER : ");
        Scanner scan = new Scanner(System.in);
        inventeur_a_modifier = scan.nextLine();

        //System.out.println("Entrez les modifications: ");
        System.out.println("VEUILLEZ SAISIR LE NOM DU NOUVEL INVENTEUR : ");//Nom de l'inventeur
        Scanner scann3 = new Scanner(System.in);
        String nom_du_nouvle_inventeur = scann3.nextLine();//nom_du_nouvle_inventeur=nomInventeurModifie

        info_ligne_inventeur();//info_ligne_inventeur

        Type_Magie sorcier = new Type_Magie();//mage
        this.Type_Magie = sorcier.info_type_de_magie();//demanderTypeMagie

        info_elements_des_inventeurs();

        info_benedictions_des_inventeurs();

        nouvel_inventeur = (nom_du_nouvle_inventeur +" : "+this.Lignee_Inventeur+" , "+this.Type_Magie+" , "+this.Element+" , "+this.Benediction);
        System.out.print(nouvel_inventeur);

        // Étape 1 : Lire le fichier
        try (BufferedReader BR = new BufferedReader(new FileReader(fichier_des_inventeurs))) {
            String i;
            while ((i = BR.readLine()) != null) {
                // Étape 2 : Trouver la ligne contenant le mot clé
                if (i.contains(inventeur_a_modifier)) {
                    // Modifier la ligne
                	inventeurs.add(nouvel_inventeur); // Remplacer par la nouvelle ligne
                } else {
                	inventeurs.add(i); // Conserver la ligne originale
                }
            }
        } catch (IOException except) {
        	except.printStackTrace();
        }

        // Étape 4 : Écrire les modifications dans le fichier
        try (BufferedWriter BF = new BufferedWriter(new FileWriter(fichier_des_inventeurs))) {
            for (String inventeur_modifie : inventeurs) {//ligneModifiee
                BF.write(inventeur_modifie);
                BF.newLine(); // Ajouter une nouvelle ligne
            }
        } catch (IOException except) {
        	except.printStackTrace();
        }
    }

    /* Description de l' application*/
    public void A_propos(){//description=A_propos
        // Lecture du fichier
        try (BufferedReader BR = new BufferedReader(new FileReader("description.txt"))) {//description.txt"
            String i; // Variable pour stocker chaque ligne lue//ligne=i
            // Lire chaque ligne tant qu'il y a des lignes à lire
            while ((i = BR.readLine()) != null) {
                // Affiche la ligne lue
                System.out.println(i);
            }
        } catch (IOException except) {
            // Gère les exceptions d'entrée/sortie
            System.err.println("ERROR(read file) : " + except.getMessage());
        }

    }
    public boolean Retour() {//menuPrecedent
        int retour = 0;//revenir
        System.out.println("RETOUR : 0");
        Boolean verif = false;//verif=valide

        /* gestion d'ereurs */
        while (!verif) {
            try {
                Scanner scan = new Scanner(System.in);
                retour = scan.nextInt();

                verif = true;
            } catch (InputMismatchException except) {
                System.out.println("VEUILLEZ ENTRER UN CHOIX VALIDE :");
            }
        }
        if(retour == 0) {
            return true;
        }
        else {
            return false;
        }
    }

}


