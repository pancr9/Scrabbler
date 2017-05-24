// Author: Rekhansh Panchal (rpanchal@uncc.edu)


import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.SortedSet;
import java.util.TreeSet;

public class Main {

    public static void main(String[] args) throws IOException {

        String fileName = "words.txt";                                              //File Name to extract words from.
        Trie trie = new Trie();                                                     //Trie for efficient retrieval using prefix.
        ArrayList<String> scrabblerArray = new ArrayList<>();                       //ArrayList to store all words.
        ArrayList<String> uniqueWords = new ArrayList<>();                          //ArrayList to store words with unique characters.

        // Getting the words
        try {
            FileInputStream inputStream = new FileInputStream(fileName);
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            String word;
            while ((word = br.readLine()) != null) {
                word = word.replaceAll(" ", "");                     //Remove all white spaces using regex.
                if (!word.equals("")) {
                    word = word.toLowerCase();
                    trie.insert(word);                                               //Adding word to trie.
                    scrabblerArray.add(word);                                        //Adding word to the arraylist.
                    if (areCharUnique(word))
                        uniqueWords.add(word);                                       //Adding word to the uniqueword list.
                }
            }
            br.close();                                                              //Input process complete.

            char ch;
            do {
                br = new BufferedReader(new InputStreamReader(System.in));
                System.out.println("Welcome to Scrabbler: \n Input 'a' for Anagram \n Input 'b' for Prefix \n Input 'c' for Suffix");
                ch = (char) br.read();
                ch = Character.toLowerCase(ch);
                String input;
                switch (ch) {
                    case 'a':                                                       //To find anagrams.
                        System.out.println("Enter letters to be searched for Anagrams: ");
                        input = getInput();
                        System.out.println(" ");
                        findAnagrams(uniqueWords, input);
                        break;
                    case 'b':                                                       //To find words with given prefix.
                        System.out.println("Enter prefix to be searched: ");
                        input = getInput();
                        System.out.println(" ");
                        if (!input.equals("") && input.matches("[a-zA-Z]*")) {
                            TrieNode trieNode = trie.returnNode(input);
                            trie.printPrefixWords(trieNode, input);
                        } else System.out.println("Invalid input.");
                        break;
                    case 'c':                                                       //To find words with given suffix.
                        System.out.println("Enter suffix to be searched: ");
                        input = getInput();
                        System.out.println(" ");
                        printSuffixes(scrabblerArray, input);
                        break;
                    default:                                                        //Condition if input is invalid.
                        System.out.println("Invalid input.");
                        break;
                }
                System.out.println("-------------------------------------------");
                System.out.println("Press Y to continue. Any other key to exit.");
                br = new BufferedReader(new InputStreamReader(System.in));          //Input to continue/exit.
                ch = (char) br.read();
            } while (ch == 'y' || ch == 'Y');

        } catch (FileNotFoundException e) {
            System.out.println("File Missing.");
        }
    }

    private static String getInput() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));   //To get input
        String input = null;
        try {
            input = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        input = input.toLowerCase();
        input = input.replaceAll(" ", "");
        return input;
    }

    //Function to print all words with given suffix.
    public static void printSuffixes(ArrayList<String> arrayList, String string) {
        string = string.replaceAll(" ", "");
        boolean suffixFound = false;
        if (!string.equals("")) {
            SortedSet<String> keys = new TreeSet<>(arrayList);                        //Convert ArrayList to TreeSet for sorted keys.
            for (String key : keys) {
                if (key.endsWith(string)) {                                           //Check if word ends with particular string.
                    System.out.println(key);
                    suffixFound = true;
                }
            }
            if (!suffixFound) System.out.println("No words found with suffix: " + string);
        } else System.out.println("No words found.");
    }


    //Function to find all possible anagrams.
    public static void findAnagrams(ArrayList<String> arrayList, String string) {
        ArrayList<String> stringArrayList = new ArrayList<>();
        string = string.replaceAll(" ", "");
        if (!string.equals("")) {
            int strLen = string.length();
            for (String s : arrayList) {
                boolean isAnagram = true;
                HashMap<Character, Boolean> map = new HashMap<>();                     //Creating a HashMap to store characters.
                for (int i = 0; i < strLen; i++) {
                    char c = string.charAt(i);
                    map.put(c, true);
                }
                for (int i = 0; i < s.length(); i++) {
                    char c = s.charAt(i);
                    if (!map.containsKey(c)) {                                         //Not an anagram, if character mismatch from input letters.
                        isAnagram = false;
                        break;
                    }
                }
                if (isAnagram) stringArrayList.add(s);
            }
            printAnagrams(stringArrayList);
        } else
            System.out.println("Please enter something.");
    }


    //Function to print all possible anagrams.
    private static void printAnagrams(ArrayList<String> anagramList) {
        if (anagramList.size() != 0) {
            SortedSet<String> keys = new TreeSet<>(anagramList);
            for (String key : keys) System.out.println(key);
        } else System.out.println("No words found.");
    }

    //Function to check if string contains all unique characters.
    public static boolean areCharUnique(String string) {
        int size = string.length();
        if (size > 256) return false;
        int valid = 0;
        for (int i = 0; i < size; i++) {
            int val = string.charAt(i) - 'a';                                          //Checks for distance from a
            if ((valid & (1 << val)) > 0) return false;
            valid |= (1 << val);
        }
        return true;
    }
}
