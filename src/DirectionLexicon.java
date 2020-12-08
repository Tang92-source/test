
/**
 * Created by Yannick Meiller in February 2016.
 * This class is a derived class from class Lexicon.
 * It defines the lexicon of the words defining directions which can be used in te game.
 */
public class DirectionLexicon extends Lexicon
{
    // Attributes

    //( there is no attribute specific to this class)
    //(remember, this class inherit of all protected attributes of its mother class.


    // Methods
    // (remember this class inherits of all public and protected methods of its mother class)
    // (hereafter are only defined methods which are specific to this DirectionLexicon class

    /**
     * This method is a constructor.
     * Here, it allows to initialize all the attributes of this lexicon with the specificities
     * of our set of direction words.
     */
    public DirectionLexicon(){
        numberOfWords=10;
        wordList = new String[numberOfWords]; //creates the array of String with the right size
        wordList[0]= "North";
        wordList[1]= "East";
        wordList[2]= "South";
        wordList[3]= "West";
        wordList[4]= "Top";
        wordList[5]= "Down";
        wordList[6]= "NorthWest";
        wordList[7]= "NorthEast";
        wordList[8]= "SouthEast";
        wordList[9]= "SouthWest";
    }

}
