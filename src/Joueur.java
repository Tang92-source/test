import java.util.Scanner;
import java.util.*;
public class Joueur{
    private int nrjLevel;
    private int difficultéChoisie;
    private int nbreJouer;
    private boolean finPartie;
    private int[] tailleEtDifficulté;
    private String[] tabNames;
    private String name1;
    private String name2;
    private int tailleChoisie;


    public Joueur(){
        this.nrjLevel=0;
        this.finPartie=false;
        this.name1=null;
        this.name2=null;
    }

    public int[] renvoiDeValeurJoueur(int input){
        this.tailleEtDifficulté=appelJoueur(input);
        this.difficultéChoisie=tailleEtDifficulté[1];
        this.tailleChoisie=tailleEtDifficulté[0];
        return this.tailleEtDifficulté;
    }

    public String[] names(int nbrePlayers) {
        tabNames = new String[2];
        System.out.println("Player 1, Choose a name : ");
        Scanner input1=new Scanner(System.in);
        this.name1=input1.next();
        tabNames[0]=name1;
        if(nbrePlayers==2){
            System.out.println("Player 2, Choose a name : ");
            Scanner input2=new Scanner(System.in);
            name2=input2.next();
            tabNames[1]=name2;
        }
        return tabNames;
    }

    public boolean niveauEnergie(int nbreJouer, int difficultéChoisie1, int nrjLevel,int compteur,String name) {
        nrjLevel = (difficultéChoisie1*5)-nbreJouer;
        if(nrjLevel<=0){
            finPartie = true;
        }
        else{
            if(compteur == 1) {
                System.out.println("Niveau d'énergie restant de "+name+" : "+ nrjLevel);
            }
            else if(compteur==2){
                System.out.println("Niveau d'énergie restant de "+name+" : "+ nrjLevel);
            }
        }
        return finPartie;
    }


    public int[] appelJoueur(int input) {
        int[] tab = new int[2];

        Difficulté difficulté = new Difficulté();
        tailleChoisie = difficulté.choixDeLaTaille();
        System.out.println("Taille de la partie : " + tailleChoisie);
        tab[0] = tailleChoisie;
        difficultéChoisie= difficulté.choixDeLaDifficulté();
        tab[1] = difficultéChoisie;
        return tab;
    }
}