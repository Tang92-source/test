import java.util.Scanner;
public class Difficulté{
    private int tailleChoisie;

    public int choixDeLaTaille(){
        System.out.println("Choose a game size between 8, 10 and 12 rooms");
        Scanner input=new Scanner(System.in);
        tailleChoisie=input.nextInt();
        while(tailleChoisie!=8 && tailleChoisie!=10 && tailleChoisie!=12 ){
            System.out.println("choose an available size ");
            input=new Scanner(System.in);
            tailleChoisie=input.nextInt();
        }
        return tailleChoisie;
    }

    public int choixDeLaDifficulté(){
        System.out.println("Choose a game difficulty between 1 (Difficile), 2 (Moyen) and 3 (Facile)");
        int difficultéChoisie;
        Scanner input=new Scanner(System.in);
        difficultéChoisie=input.nextInt();

        while(difficultéChoisie!=1 && difficultéChoisie!=2 && difficultéChoisie!=3 ){
            System.out.println("choose an available  difficulty");
            input=new Scanner(System.in);
            difficultéChoisie=input.nextInt();
        }
        return difficultéChoisie;
    }
}