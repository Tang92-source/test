
/**
 * Created by Yannick Meiller in February 2016.
 * Modified by Esma TALHI
 * This class manages a set of words (for example for interaction with the user).
 *
 */
public class Lexicon
{
    // Attributes ------------------------------------------------------------------

    protected int numberOfWords;
    protected String[] wordList;
    private final String errorWord = "Error"; //"final" means this variable is a constant
    // Here its value is "Error" and cannot be changed
    private final int errorCode = -1;       //"final" means this variable is a constant
    // Here its value is -1 and cannot be changed


    // Methods --------------------------------------------------------------------

    // Public methods (accessible from any class)

    /**
     * A constructor.
     * This method will be called when an object of this class will be created
     */
    public Lexicon() {

        numberOfWords = 0;
    }

    /**
     *
     * @param code (an integer)
     * @return the word ( as a String - ie text) corresponding to the code provided by the parameter (an integer).
     * If the code does not correspond to a word of this lexicon, the error word is returned
     */
    public String codeToWord(int code) {

        String result;

        if (code >= numberOfWords) {
            result = errorWord;
        } else {
            result = wordList[code];
        }
        return result;
    }

    /**
     *
     * @param word (a String - ie text)
     * @return the code (an integer) corresponding to the word provided by the parameter
     * if this word does not exist in this lexicon, the error code is returned
     */
    public int wordToCode(String word) {
        int i;

        i = 0;
        while ((i < numberOfWords) && !(wordList[i].equals(word))) { //&& is the logical AND
            // equals(String s) is a method of the class String used to compare with another String
            i = i + 1;
        }
        if (i == numberOfWords) {
            return errorCode; //we did not find the word
        } else {
            return i;
        }
    }

    /**
     * @param word (a String - ie text)
     * @return boolean (true or false) - returns true if the word provided by the parameter exists in this lexicon, false otherwise
     */
    public boolean isLexiconWord(String word) {
        int i;

        i = 0;
        while ((i < numberOfWords) && (!wordList[i].equals(word))) {
            i = i + 1;
        }
        return (i < numberOfWords);
        //if i<numberOfWords then the word has been found, otherwise is does not exist in this lexicon
    }

    /**
     * This method is a getter
     * @return the number of words in this lexicon
     * this is the only way to access this protected attribute from other classes than this one and its derivated classes
     */
    public int getNumberOfWords() {
        return numberOfWords;
    }

    /**
     *
     * @param code (an integer)
     * @return a boolean (true or false) - returns true if the code provided by the parameter is the error code
     * Thanks to this method, users of this class do not have to know what is the error code.
     * They will use this function to check whether the code at hand is an error code or not.
     */
    public boolean isErrorCode(int code) {
        return (code == errorCode);    //== is the "equal" operator (Watch out it does not work when comparing Strings)
    }

    /**
     *
     * @param word (a String - ie text)
     * @return a boolean (true or false) - returns true if the word provided by the parameter is the error word
     * Thanks to this method, users of this class do not have to know what is the error word.
     * They will use this function to check whether the word at hand is an error word or not.
     */
    public boolean isErrorWord(String word) {
        return word.equals(errorWord);
        //word is an object of class String. we call its method equalsawith the String errorWord as parameter
        // this method returns true if word is equal to errorWord, false otherwise
    }

    /**
     *
     * @return a String (ie text) made of all the words in this lexicon, separated by a coma and a space (", ")
     */
    public String completeWordList() {
        int i;
        String list;

        list="";
        for (i=0; i<numberOfWords; i=i+1){
            list=list+", "+wordList[i]; // + allows to concatenate pieces of text
        }
        return list;
    }

    // Protected Methods (accessible only from this class and its derived classes

    // Getters
    /**
     * This method is a getter
     * @return the errorCode (an integer)
     * this is the only way to access this private attribute
     */
    protected int getErrorCode() {
        return errorCode;
    }

    /**
     * This method is a getter
     * @return the errorWord (a String - ie text)
     * this is the only way to access this private attribute
     */
    protected String getErrorWord() {

        return errorWord;
    }

}
