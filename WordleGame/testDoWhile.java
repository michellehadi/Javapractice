//package untitled.src;
//
//import java.util.*;
//
///**
// * This class contains static methods that play Wordle using a simple artificial intelligence.
// *
// * @author StudentNumber1 AND StudentNumber2
// */
//public class WordleAI
//{
//    private WordleAI()
//    {
//        // Constructor is private because all methods are static
//        // and instances of WordleAI should not be created
//    }
//
//
//    /**
//     * Returns true if guess contains the letter c and false otherwise.
//     */
//    public static boolean guessContains(String guess, char c)
//    {
//        // TODO 1
//        // looping through the String guess to check if char c is contained
//        // return true when found, false otherwise
//        for (int i=0; i<guess.length();i++) {
//            if (guess.charAt(i) == c) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    /**
//     * Returns true if newGuess is consistent with a previousGuess and its result
//     * and false otherwise.
//     *
//     * The parameter previousGuess is a previous Wordle guess made by the AI.
//     * The parameter result is the result of the guessWord method in WordleGame.
//     * The parameter newGuess is a potential new word to guess.
//     *
//     * A newGuess is consistent with the previousGuess and result if they can be
//     * explained by newGuess being the secret word. That is, newGuess should not
//     * contradict results from previous guesses.
//     *
//     * For example, suppose we have previousGuess="dxaxx" and the result="*_.__",
//     * then newGuess="dairy" or newGuess="dzzza" would return true, but
//     * newGuess="testa" or newGuess="dxiry" would be false.
//     * This is because only newGuess="dairy" or newGuess="dzzza" could have been the secret word
//     * for previousGuess="dxaxx" to get result="*_.__"
//     *
//     * HINT: Can you use a new WordleGame(...) somehow?
//     */
//    public static boolean isConsistent(String previousGuess, String result, String newGuess)
//    {
//        // TODO 2
//        String newResult = "";
//        for (int i = 0; i < newGuess.length(); i++) {
//            if (newGuess.charAt(i) == previousGuess.charAt(i))
//                newResult += "*";
//            else if (guessContains(previousGuess, newGuess.charAt(i)))
//                newResult += ".";
//            else
//                newResult += "_";
//        }
//        // looping to check if the position of '*' remains the same. returns false if it moves
//        for (int j=0; j<newResult.length(); j++) {
//            if (result.charAt(j) == '*' && newResult.charAt(j) != '*') {
//                return false;
//            }
//            // check if the '.' character position is still consistent
//            if (result.charAt(j) == '.' && !guessContains(newGuess,previousGuess.charAt(j))) {
//                return false;
//            }
//            if (result.charAt(j) == '_' && guessContains(newGuess,previousGuess.charAt(j))) {
//                return false;
//            }
//        }
//        return true;
//    }
//
//    /**
//     * Returns true if result contains only '*' characters
//     * and false if it contains a non-'*' character.
//     */
//    public static boolean isAllStars(String result)
//    {
//        // TODO 3
//        // looping through String result to check if all characters are '*'
//        // return true when all is '*', false otherwise
//        for (int i=0; i<result.length();i++) {
//            if (result.charAt(i) != '*') {
//                return false;
//            }
//        }
//        return true;
//    }
//
//    /**
//     * This method runs the AI algorithm.
//     * Given a dictionary and a game, makes a series of calls to game.guessWord(word)
//     * to find the secret word in game.
//     * Returns an ArrayList containing the words in the order they were guessed.
//     * If the secret word could not be found, returns null.
//     *
//     * The AI algorithm is very specific!
//     * It uses a simple strategy similar to one you may have used when playing Wordle.
//     *
//     * The AI starts by guessing the the lexicographicaly smallest word.
//     *
//     * Then, for every guess after that, the AI guesses a word that does not contradict any previous
//     * results it has seen. That is, it makes guesses that are consistent (see isConsistent)
//     * with all guesses made so far.
//     *
//     * If there are multiple possible guesses that are consistent, then the AI
//     * will pick the lexicographically smallest option.
//     *
//     * WordleGame has been modified to only allow 6 guesses. After this, it will return an
//     * empty string "".
//     *
//     * If the game ends because the AI has run out of guesses, then findWord returns null.
//     * Otherwise, findWord returns once it has made a correct guess. This can
//     * be checked using the isAllStars method above. In this case, a list of the AI's
//     * guesses in the order they were made is returned.
//     *
//     * HINT 1: You will almost certainly need to read the unit test and use the debugger for this method.
//     * HINT 2: See Collections.sort in the Java class libraries for lexicographical ordering.
//     */
//    public static ArrayList<String> findWord(WordleDictionary dictionary, WordleGame game) {
//        // TODO 4
//        // create an arraylist of words in the dictionary with the secret word's length
//        // and creat an arraylist of consistent words to be chosen lexicographically smallest
//        // and create arraylist of words guessed
//        ArrayList<String> words = dictionary.getWordsWithLength(game.getWordLength());
//        ArrayList<String> temp = dictionary.getWordsWithLength(game.getWordLength());
//        ArrayList<String> wordsGuessed = new ArrayList<>();
//        // variable to store lexicographically smallest word
//        String smallestWord = "";
//        // variable to store the result of guessWord method
//        String result = "";
//
////        System.out.println(temp.get(0));
//        int counter = 0;
//
//        do {
//
//            counter +=1;
//            System.out.println("Loop no. : " + counter);
//            System.out.println(temp.get(0));
//
//            // sort the dictionary
//            Collections.sort(temp);
//            System.out.println(temp.get(0));
//            // check if there is any consistent word, if none, break the while loop
//            if (temp.size() == 0) {
//                break;
//            }
//            // store the lexicographically smallest word in a variable
//            smallestWord = temp.get(0);
//            // AI guesses the smallest word and store the result in result variable
//            result = game.guessWord(smallestWord);
//            System.out.println(result);
//            // store the smallest word in wordGuessed ArrayList
//            wordsGuessed.add(smallestWord);
//            // clear the temp ArrayList to store the future consistent words
//            temp.clear();
//            // looping through the words to find the consistent words and store in temp arrayList
//            for (int i = 0; i < wordsGuessed.size(); i++) {
//                for (int k = 0; k < words.size(); k++) {
//                    if (wordsGuessed.get(i) != words.get(k)) {
//                        if (isConsistent(wordsGuessed.get(i), result, words.get(k))) {
//                            temp.add(words.get(k));
//                        }
//                    }
//                }
//            }
//            // remove words arrayList content, and replace with the only consistent words left
//            words.clear();
//            for (int m = 0; m < temp.size(); m++) {
//                words.add(temp.get(m));
//            }
//            if (result == "") {
//                return null;
//            }
//            // check if there is any consistent word, if none, break the while loop
//            if (temp.size() == 0) {
//                // check if the first guess is the right word
//                if (isAllStars(result) == true) {
//                    return wordsGuessed;
//                }
//            }
//        }
//        while (result != "");
//
//        if (result == "") {
//            return null;
//        }
//        // check if the first guess is the right word
//        if (isAllStars(result) == true) {
//            return wordsGuessed;
//        }
//        return null;
//    }
//}
