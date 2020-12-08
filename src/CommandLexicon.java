
/**
 * Created by Yannick Meiller in February 2016.
 * Modified by Esma TALHI
 * This class is a derived class from class Lexicon.
 * It defines the lexicon of the words defining commands which can be used in the game.
 */
public class CommandLexicon  extends Lexicon
{
    // Attributes

    //( there is no attribute specific to this class)
    //(remember, this class inherit of all protected attributes of its mother class.


    // Methods
    // (remember this class inherits of all public and protected methods of its mother class)
    // (hereafter are only defined methods which are specific to this CommandLexicon class

    /**
     * This method is a constructor.
     * Here, it allows to initialize all the attributes of this lexicon with the specificities
     * of our set of command words.
     */

    public CommandLexicon(){
        numberOfWords = 3;
        wordList = new String[numberOfWords];  //creates the array of String with the right size
        wordList[0] = "Go";
        wordList[1] = "Quit";
        wordList[2] = "Help";

    }
}
