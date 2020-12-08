
/**
 * Created by Yannick Meiller in February 2016 for ESCP Europe
 *
 * Modified by Esma TALHI 
 *
 * This class contains the main program.
 * Its unique purpose is to launch the game !
 */
import java.util.Scanner;
public class Main
{
    public static void main(String[] args) {
        Game theGame;
        theGame = new Game();
        theGame.play();
    }

}
