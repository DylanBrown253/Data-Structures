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
            genWordList();
        }
        int numGuesses = wrongGuesses();

        HangMan(wordList, letterCount, numGuesses);

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
    public static void HangMan(HashMap<Integer, List<String>> wordList, int letterCount, int numGuesses){
        List<String> bestFam = new ArrayList<>();

        boolean user_Won = false;
        System.out.println("========================================Your Hangman Board========================================");
        for (int i = 0; i < letterCount; i++) {
            System.out.print('_');
        }
        int timesGuessed = 0;
        Set<Character> guessed = new HashSet<>();
        HashMap<Integer, List<String>> wordFamilies = new HashMap<>();
        int timesRun = 0;

        int user_gueses = 0;
        while(user_gueses < numGuesses && (user_Won == false) ) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("\nMake a guess: ");
            String StringGuess = scanner.next();
            for (int i = 0; i < StringGuess.length(); i++) {
                guessed.add(StringGuess.charAt(i));
            }
            user_gueses++;
            if(timesRun == 0) {
                wordFamilies.putAll(familyGenerator(wordList.get(letterCount), guessed));
            }
            else{
                wordFamilies.putAll(familyGenerator(bestFam, guessed));
            }

            bestFam.addAll(getBestFamily(wordFamilies));
            if(bestFam.size() == 1){
                user_Won = true;
            }

            currentBoard(guessed, bestFam);
            System.out.println("Your current guesses: " + guessed);
            System.out.println(bestFam);
            if(user_gueses == numGuesses && user_Won == false){
                Random rand = new Random();
                int random = rand.nextInt(bestFam.size());
                System.out.println("GAME OVER");
                System.out.println("COMPUTER WINS");
                System.out.println("The word I was thinking of is: " + bestFam.get(random));
            }
            else if(user_Won){
                System.out.println("You actually won wow!");
                System.out.println("Well played!");
            }
        }

    }

    private static void currentBoard(Set<Character> guessed, List<String> bestFam) {
        for (int i = 0; i <bestFam.get(0).length(); i++) {
            if(guessed.contains(bestFam.get(0).charAt(i))){
                System.out.print(bestFam.get(0).charAt(i));
            }
            else{
                System.out.print('_');
            }
        }
        System.out.println('\n');
    }


    //either in this function or generate best family function you need to keep in mind the letters already guessed and even the current positions of them?
    public static Map<Integer, List<String>> familyGenerator(List<String> wordList, Set<Character> guessed){
        HashMap<Integer, List<String>> family = new HashMap<>();
        List<String> noMatches = new LinkedList<>();


        for(int i = 0; i < wordList.size() ; i++) {
            int sum = 0;
            int addedIndex = 1;
            boolean flag = false;
            for (int j = 0; j < wordList.get(i).length(); j++) {
                if(guessed.contains(wordList.get(i).charAt(j))){
                    addedIndex += j;
                    sum++;
                    flag = true;
                }
            }
            if(!flag){
                noMatches.add(wordList.get(i));
                family.put(0, noMatches);
            }
            else if(family.containsKey(addedIndex+sum)) {
                family.get(addedIndex+sum).add(wordList.get(i));
            }
            else{
                List<String> list = new LinkedList<>();
                list.add(wordList.get(i));
                family.put(addedIndex + sum, list);
            }
        }
        return family;
    }
    public static String findWordFamily(String word, Set<Character> guessed){
        return null;
    }

    //currently not working when called for 8-letter word and 8 guess limit, something about null error.
    public static List<String> getBestFamily(Map<Integer, List<String>> wordFamilies){
        List<String> bestFamily = new ArrayList<>();
        for (int i = 0; i < wordFamilies.size(); i++) {
            if(wordFamilies.get(i) == null){
                i++;
            }
            else if(wordFamilies.get(i).size() > bestFamily.size()){
                bestFamily = wordFamilies.get(i);
            }
        }
        return bestFamily;
    }
    public static int wrongGuesses(){
        Scanner scanner  = new Scanner(System.in);
        System.out.println("\tHow many wrong guesses should we give you");
        int wrongGuesses = scanner.nextInt();
        return wrongGuesses;
    }

}
