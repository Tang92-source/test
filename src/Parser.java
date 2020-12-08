
/**
 * Created by Yannick Meiller in February 2016.
 * Modified by Esma TALHI
 * This class manages the inputs tuped in by a player of the game.
 * It can get a line entered thanks to the keyboard.
 * It can analyze this line to separate individual words.
 * It can give access to information about this line, about these words and access to each word.
 *
 */
import java.util.Scanner; //Scanner is already defined in Java.
// It is localized in the set of classes util, which is localized in the set java.

public class Parser
{
    // Attributes

    private Scanner input;   // will be used to read a sentence
    private String[] words;  // stores the words of the last sentence
    private int maxNbWords; //maximum number of words in a sentence
    private int numberOfWords; //number of words in the last analyzed sentence


    // Methods

    // Public Methods

    /**
     * This is a constructor ofthis class.
     * @param sentenceMaxLength - an integer defining the maximum number of words we can expect in one sentence.
     */
    public Parser(int sentenceMaxLength) {

        input = new Scanner(System.in);
        // the attribute input is an object Scanner "plugged" on the keyboard
        maxNbWords = sentenceMaxLength;
        words = new String[maxNbWords+1];// +1 because we are not going to use words[0]
        numberOfWords = 0;
    }

    /**
     *
     * @return a String (text) : the sentence typed in by a player.
     */
    public String getSentence() {
        System.out.print("> ");     // print prompt
        return input.nextLine();    // the method nextLine of the class Scanner returns a String
        // This String contains all what has been typed in, until the next return
    }


    /**
     *
     * @param sentence is a String, which what has been typed in by a player.
     *   This method identifies the different words in this sentence.
     *   This method stores in the attribute words[] each word at its real position
     *                Example : words[1] stores the 1st word of the sentence
     *                          words[2] stores ths second one...
     */
    public void analyzeSentence(String sentence) {
        Scanner analyzer; // we create another object of class Scanner
        int i;

        analyzer = new Scanner(sentence); // the source of this object of class Scanner is not the keyboard
        // its source is only on the String named sentence

        i = 1;
        while (analyzer.hasNext() && (i <= maxNbWords)) {   //&& is the logcal operator AND
            // hasNext() is a method of class Scanner returning true if there is still
            // something to extract from the source
            words[i] = analyzer.next();  // next() is a method of the class Scanner
            // it returns all the characters until the next space (excluded): it returns the next word
            i = i + 1;
        }
        numberOfWords = i - 1;


    }

    /**
     * This is a combo : it gets a sentence fromthe keyboard and stores each of its words in words[].
     */
    public void getAndAnalyzeSentence(){
        String sentence;

        sentence = getSentence();
        analyzeSentence(sentence);
    }


    /**
     * @param position (position of the word in the sentence. This starts at 1.
     * position is not checked. It should be between 1 and maxNbWords.
     *
     * @return the corresponding word in the last analyzed sentence.
     */
    public String word(int position) {

        return words[position];
    }

    /**
     *
     * @return the number of words of thelast sentence which has been analyzed with analyzeSentence(String s)
     */
    public int lastSentenceLength() {
        return numberOfWords;
    }

    // There is no protected methods
    // There is no private methods
}
