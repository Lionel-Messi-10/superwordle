import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Properties;
import java.util.Arrays;

public class WordBank {

    private HashSet<String> _validWordSet;
    private ArrayList<String> _potentialSolutionList;

    /* 
     * Constructor for WordBank
     *
     */
    public WordBank() throws IOException {
        this(false,0);
    }

    /* 
     * Constructor for WordBank
     * @param allowRepeatedCharacters: set to true to include words with repeated characters
     *
     */
    public WordBank(boolean allowRepeatedCharacters, int a) throws IOException {
        FileReader reader;
        if (a == 0){
            reader = new FileReader("words.txt");
        }else{
            reader = new FileReader("evil.txt");
        }
        
        Properties p = new Properties();
        p.load(reader);
        _potentialSolutionList = new ArrayList<String>(Arrays.asList(p.getProperty("potentialSolutions").split(",")));
        ArrayList<String> validWordList = new ArrayList<String>(Arrays.asList(p.getProperty("validWords").split(",")));
        if (!allowRepeatedCharacters) {
            _potentialSolutionList = dropWordsWithRepeatedCharacters(_potentialSolutionList);
            validWordList = dropWordsWithRepeatedCharacters(validWordList);
        }
        _validWordSet = new HashSet<String>(validWordList);
    }

    /* 
     * isValidWord 
     * returns true if @param word can be found in the Wordle dictionary, false otherwise
     * for example: "abcde" is not a valid word, but "extra" is a valid word
     *
     */
    public boolean isValidWord(String word) {
        return _validWordSet.contains(word.toLowerCase()) || _potentialSolutionList.contains(word.toLowerCase());
    }

    /* 
     * getRandomWord 
     * returns a word to be guessed
     *
     */
    public String getRandomWord() {
        int count = _potentialSolutionList.size();
        int index = (int)(Math.random()*count);
        return _potentialSolutionList.get(index);
    }

    private ArrayList<String> dropWordsWithRepeatedCharacters(ArrayList<String> words) {
        ArrayList<String> filtered = new ArrayList<>();
        for (String word : words) {
            if (!containsDuplicateCharacters(word)) {
                filtered.add(word);
            }
        }
        return filtered;
    }

    private boolean containsDuplicateCharacters(String word) {
        HashSet<String> characters = new HashSet<String>();
        for (int i = 0; i < word.length(); i++) {
            String c = word.substring(i, i+1);
            if (characters.contains(c)) {
                return true;
            } else {
                characters.add(c);
            }
        }
        return false;
    }
}