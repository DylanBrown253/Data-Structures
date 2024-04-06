import java.io.FileNotFoundException;

import java.sql.SQLOutput;
import java.util.*;
import java.io.File;  // Import the File class

public class Main {
    public static void main(String[] args)throws FileNotFoundException {
        HashMap<Integer, List<String>> wordList = new HashMap<>();
        wordList.putAll(genWordList());
        Scanner scanner  = new Scanner(System.in);
        System.out.println("\tHow many letters do you want to play with? ");
        int letterCount = scanner.nextInt();
        while(!wordList.containsKey(letterCount)){
            System.out.println("\tThere are no words of this size");
            System.out.println("\tTry again how many letters do you want to play with? ");
            letterCount = scanner.nextInt();
        }
        int numGuesses = wrongGuesses();
        game(wordList.get(letterCount), numGuesses);
    }
    public static void game(List<String> wordList, int guesses){
        HashMap<String, List<String>> families = new ArrayList<>();
        System.out.println("========================================Your Hangman Board========================================");
        char guess = 'c';
        Set<Character> guessed = new HashSet<>();
        guessed.add(guess);
        families.putAll(createWordFamilies(wordList,guessed));
        for (int i = 0; i < wordList.get(0).length(); i++) {
            System.out.print("_");
        }
    }
    public static HashMap<Integer, List<String>>  genWordList() throws FileNotFoundException {
        HashMap<Integer, List<String>> wordsBySize = new HashMap<>();
        Scanner fileScanner = new Scanner(new File("/Users/dylanbrown/JavaStuff/CheatersHangman/words.txt"));
        while(fileScanner.hasNextLine()){
            String word = fileScanner.nextLine().toLowerCase();
            if(wordsBySize.containsKey(word.length())){
                wordsBySize.get(word.length()).add(word);
            }
            else{
                List<String> list = new ArrayList<>();
                list.add(word);
                wordsBySize.put(word.length(), list);
            }
        }
        return wordsBySize;
    }
    public static Map<String, List<String>> createWordFamilies(List<String> words, Set<Character> guessed){
        HashMap<String, List<String>> families = new HashMap<>();
        for (String word: words) {
            if(word.)

        }
        return null;
    }
    public static String findWordFamily(String word, Set<Character> guessed){
        return null;
    }
    public static String getBestFamily(Map<String, List<String>> wordFamilies){
        return null;
    }
    public static int wrongGuesses(){
        Scanner scanner  = new Scanner(System.in);
        System.out.println("\tHow many wrong guesses should we give you");
        int wrongGuesses = scanner.nextInt();
        return wrongGuesses;
    }

}