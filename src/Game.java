/**
 * Created by Yannick Meiller in February 2016.
 *
 * Modified by Esma TALHI
 * This class is the main class of a simple,text-based, adventure game.
 * This game is inspired by the Zorg game, developped in March 2000 by Michael Kolling - Monash University.
 *
 * The universe of this game is made of interconnected rooms.
 * The player can move through these rooms.
 * In this basic version the goal is to reach a particular room.
 * This game can be extended  - and it should be to be more interesting !
 *
 * This Game class creates and initialises all the others: it creates all
 * rooms, creates the parser and starts the game.  It also evaluates the
 * commands that the parser identified.
 */
import java.util.Scanner;
import java.util.*;
import java.util.ArrayList;
public class Game
{
    //Attributes

    private Parser parser;
    private Room currentRoom;
    private Room currentRoom1;
    private Room currentRoom2;
    private Room goalRoom;
    private DirectionLexicon dirVocabulary;
    private CommandLexicon commandVocabulary;
    private boolean wantsToQuit;
    private int taillePartie;
    private int tailleChoisie;
    private int difficultéChoisie;
    private int nbreJouer1;
    private int nbreJouer2;
    private String name;
    private String name1;
    private String name2;
    private int nrjLevel1;
    private int nrjLevel2;
    private int[] tailleEtDifficulté;
    private Joueur theJoueur;
    private boolean finPartie1;
    private boolean finPartie2;
    private int nbrePlayers;
    private int compteur;
    private String[] namesOfPlayer;


    //Methods

    //Public methods

    /**
     * This is a constructor.
     * It creates the game and initialise its internal map.
     */
    public Game() {
        dirVocabulary = new DirectionLexicon();
        commandVocabulary = new CommandLexicon();

        parser = new Parser(2);  //the parameter 2 here in the call to the constructor of Parser means that in this game
        // sentences typed in by the player will contain at most 2 words.
    }


    /**
     * Main play routine.  Loops until end of play.
     */

    /*



     */


    public void play() {
        System.out.println("How many players are you? 1 or 2 ");
        Scanner input=new Scanner(System.in);
        nbrePlayers=input.nextInt();
        Joueur theJoueur = new Joueur();
        Joueur theJoueur1 = new Joueur();
        Joueur theJoueur2 = new Joueur();
        tailleEtDifficulté=theJoueur.renvoiDeValeurJoueur(nbrePlayers);
        namesOfPlayer =theJoueur.names(nbrePlayers);
        if(nbrePlayers == 2) {
            name1 = namesOfPlayer[0];
            name2 = namesOfPlayer[1];
        }
        else {
            name1 = namesOfPlayer[0];
        }
        this.taillePartie=tailleEtDifficulté[0];
        createRooms(nbrePlayers);
        printWelcome();
        Scanner choice = new Scanner(System.in);
        String answer = new String();
        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.

        wantsToQuit = false;
        if(nbrePlayers==2){
            finPartie1=theJoueur1.niveauEnergie(nbreJouer1, tailleEtDifficulté[1],nrjLevel1,1,name1);
            finPartie2=theJoueur2.niveauEnergie(nbreJouer2, tailleEtDifficulté[1],nrjLevel2,2,name2);
        }
        else{
            finPartie1=theJoueur1.niveauEnergie(nbreJouer1, tailleEtDifficulté[1],nrjLevel1,1,name1);
        }

        while (!currentRoom.isSameRoomAs(goalRoom) && !wantsToQuit && finPartie1==false && finPartie2==false) {
            if(nbrePlayers == 2) {
                compteur = (compteur % 2) + 1;
            }
            else {
                compteur = 1;
            }
            System.out.println();
            if(compteur==1){
                System.out.println(name1 + ", à vous de jouer :");
            }
            else {
                System.out.println(name2 + ", à vous de jouer :");
            }
            parser.getAndAnalyzeSentence();
            if (parser.lastSentenceLength() > 0) {// Here we know the player has typed in at list 1 word
                if (commandVocabulary.isLexiconWord(parser.word(1))) { // here we know the firs word typed in corresponds to a known command


                    if(compteur==1){
                        name = name1;
                        nbreJouer1=compteurNrj(nbreJouer1);
                        theJoueur = theJoueur1;
                        executeCommand(currentRoom1);
                        currentRoom1=currentRoom;


                    }
                    else{
                        name = name2;
                        nbreJouer2=compteurNrj(nbreJouer2);
                        theJoueur=theJoueur2;
                        executeCommand(currentRoom2);
                        currentRoom2=currentRoom;
                    }

                }
                else {
                    System.out.println("Sorry... but I do not understand what you mean.");
                }
            }
            if(nbrePlayers==1){
                finPartie1=theJoueur1.niveauEnergie(nbreJouer1, tailleEtDifficulté[1],nrjLevel1, compteur, name);
            }
            else{
                if (compteur == 1) {
                    finPartie1=theJoueur1.niveauEnergie(nbreJouer1, tailleEtDifficulté[1],nrjLevel1, compteur, name1);
                }
                else {
                    finPartie2=theJoueur2.niveauEnergie(nbreJouer2, tailleEtDifficulté[1],nrjLevel2,compteur, name2);
                }
            }
            System.out.println(currentRoom.getLongDescription());
        }
        if (!wantsToQuit && finPartie1==false && finPartie2==false && currentRoom.isSameRoomAs(goalRoom)) { // this means we are here because the player has won
            System.out.println("Bravo "+name+" !");
            System.out.println("What an extraordinary player you are !");
        }

        else {
            System.out.println("Thank you for playing.  Good bye.");
        }
    }
    // Private methods



    public int compteurNrj(int nbreJouer){
        nbreJouer=nbreJouer+1;
        return nbreJouer;
    }
    /**
     * Create all the rooms and link their exits together.
     */
    public void createRooms(int nbrePlayers) {
        Room cafet, amphiA, amphiC, outside, scol, class001, class002, hall1, Accueil, salleInfo, Automatismes, salle154;
        // create the rooms

        cafet = new Room("La cafet' - the place for snacking", dirVocabulary);
        amphiA = new Room(" Amphi A - Lecture class ", dirVocabulary);
        amphiC = new Room(" Amphi C - Lecture class ", dirVocabulary);
        outside = new Room( " Outside EIGSI - To get some fresh air", dirVocabulary);
        scol = new Room(" La Scol' - To welcome the students", dirVocabulary);

        hall1 = new Room("Hall between La cafet, Amphi A and outside", dirVocabulary);

        class001 = new Room("Courses class n 1", dirVocabulary);
        class002 = new Room("Courses class n 2", dirVocabulary);

        Accueil = new Room("Accueil", dirVocabulary);
        salleInfo = new Room("salleInfo", dirVocabulary);
        Automatismes = new Room("Automatismes", dirVocabulary);
        salle154 = new Room("salle154", dirVocabulary);

        currentRoom=taillePetitePartie(cafet, amphiA, amphiC, outside, scol, class001,class002, hall1, Accueil, salleInfo, Automatismes, salle154, currentRoom, taillePartie);
        if(nbrePlayers==2){
            currentRoom1=currentRoom;
            currentRoom2=currentRoom;
        }
        else{currentRoom1=currentRoom;}
    }

    private Room taillePetitePartie(Room cafet, Room amphiA, Room amphiC, Room outside, Room scol, Room class001, Room class002, Room hall1, Room Accueil, Room salleInfo, Room Automatismes, Room salle154, Room currentRoom, int taillePartie) {

        cafet.setExit("West", outside);
        cafet.setExit("South", amphiA);

        amphiA.setExit("West", hall1);
        amphiA.setExit("North", cafet);

        amphiC.setExit("North", hall1);
        amphiC.setExit("West", class002);

        class001.setExit("East", class002);

        class002.setExit("West", class001);
        class002.setExit("East", amphiC);

        scol.setExit("East", hall1);

        outside.setExit("South", hall1);
        outside.setExit("East",cafet);

        hall1.setExit("North", outside);
        hall1.setExit("East", amphiA);
        hall1.setExit("South", amphiC);
        hall1.setExit("West", scol);


        ArrayList<Room> salles = new ArrayList<Room>();

        salles.add(cafet);
        salles.add(amphiA);
        salles.add(amphiC);
        salles.add(outside);
        salles.add(scol);
        salles.add(hall1);
        salles.add(class001);
        salles.add(class002);



        if(taillePartie == 10) {
            scol.setExit("North",Automatismes);
            outside.setExit("West",Automatismes);
            Automatismes.setExit("East", outside);
            Automatismes.setExit("South", scol);
            Accueil.setExit("Down",hall1);
            hall1.setExit("Top", Accueil);

            salles.add(Automatismes);
            salles.add(Accueil);
        }
        if(taillePartie == 12) {
            scol.setExit("North",Automatismes);
            outside.setExit("West",Automatismes);
            Automatismes.setExit("East", outside);
            Automatismes.setExit("Top", salle154);
            Automatismes.setExit("South", scol);
            Accueil.setExit("East",salleInfo);
            Accueil.setExit("Down",hall1);
            Accueil.setExit("NorthWest",salle154);
            hall1.setExit("Top", Accueil);
            amphiA.setExit("Top", salleInfo);
            salle154.setExit("SouthEast", Accueil);
            salle154.setExit("Down",Automatismes);
            salleInfo.setExit("Down",amphiA);
            salleInfo.setExit("West",Accueil);

            salles.add(Automatismes);
            salles.add(Accueil);
            salles.add(salle154);
            salles.add(salleInfo);
        }

        Random random = new Random();
        int nb;
        nb = 0+random.nextInt((taillePartie-1)-0);

        currentRoom = salles.get(nb);

        Random random2 = new Random();
        int nb2;
        nb2 = 0+random2.nextInt((taillePartie-1)-0);

        goalRoom = salles.get(nb2);

        while (goalRoom == currentRoom) {
            Random random3 = new Random();
            int nb3;
            nb3 = 0+random3.nextInt((taillePartie-1)-0);
            goalRoom = salles.get(nb3);
        }
        return currentRoom;
    }
    /**
     * Print out the opening message for the player.
     */
    private void printWelcome() {
        System.out.println();
        System.out.println("Welcome!!");
        System.out.println("This adventure game is incredibly vintage!");
        System.out.println("Type 'Help' if you need help.");
        System.out.println();

        System.out.println(currentRoom.getLongDescription());
        System.out.println("you have to reach " + goalRoom.getShortDescription());
    }

    /**
     * initial state : a sentence typed in by the player has been obtained and analyzed
     * its first word is a known command
     * final state : the command has been processed.
     *              Either the following words made sense with the command, and an action in the game has been executed
     *              or the system has said to the player it did not understand
     */
    private void executeCommand(Room currentRoom3) {

        String secondWord;


        switch (parser.word(1)) {  //depending on the value of the first word of the player's sentence...
            case "Quit":
                wantsToQuit = true;
                break;
            case "Help":
                printHelp();
                break;
            case "Go":             // the command Go requires another word to indicate in which direction to go
                if (parser.lastSentenceLength() < 2) { // here the player'sentence does not contain a second word
                    System.out.println("Hum... you need to choose a direction if you want to move!");
                } else { //here the first word is Go and there is a second word
                    secondWord = parser.word(2);
                    if (dirVocabulary.isLexiconWord(secondWord)) { //here we know this second word is a known direction
                        if (currentRoom3.isExit(secondWord)) { //here we know in that direction there is indeed an exit
                            currentRoom = currentRoom3.nextRoom(secondWord);//the player moves in that direction and reach the next room


                        } else {
                            System.out.println("Sorry, there is no exit in this direction");
                        }
                    } else {//here we know the second word does not describe a known direction
                        System.out.println("Sorry, I do not understand which direction you chose...");
                    }
                }
                break;
        }

    }


    /**
     * Print out some help information.
     * Here we print a strange and sad message, as welle as the list of possible command words and the list of possible directions.
     * Is this really helpful ? ;-)
     */
    private void printHelp() {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at ESCP Europe, Paris campus.");
        System.out.println();
        System.out.println("Your command words are:");
        System.out.println(commandVocabulary.completeWordList());
        System.out.println("The possible directions are:");
        System.out.println(dirVocabulary.completeWordList());
    }
}
