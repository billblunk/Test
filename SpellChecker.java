/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.*;
import java.util.Scanner;
/**
 *
 * @author Bill Blunk C202 IUS 
 */
public class SpellChecker
{
    private BinarySearchTree [] dictionary = new BinarySearchTree [26];
    private long notFoundWords;
    private long wordsFoundComparison;
    private long foundWords;
    private long wordsNotFoundComparison;
    private final int [] stringComparisons = new int[1];
    
    /**
     * Default constructor of SpellChecker objects.  Putting all counters at 0
     */
    public SpellChecker()
    {
        notFoundWords = 0;
        wordsFoundComparison = 0;
        foundWords = 0;
        wordsNotFoundComparison = 0;
        for (int i =0; i < dictionary.length; i++)
        {
            dictionary[i] = new BinarySearchTree<String>();
        }
    }// end of spellChecker
    
    /* pre : empty args
     * post: read dictionary file into array / linkedlist
    */ 
    public void readDictionaryFile()
    {
        File df = new File("random_dictionary.txt");
        try{
            Scanner input = new Scanner(df);
            while (input.hasNext ()){
                String wordstr = (input.nextLine().toLowerCase());
                dictionary [(int)wordstr.charAt(0) - 97].insert(wordstr);
            }
            input.close();
        }
        catch (Exception e)
        { // nothing to add to dictionary
            e.printStackTrace();
        }
    }// end of readDictionaryFile method
    
/* pre : empty args
 * post: compare input text file to dictionary
 */ 
    public void spellCheck()
    {
        try
        {
            FileInputStream inf = new FileInputStream(new File("oliver.txt"));
            char let;
            String wordstr=""; //word to be processed            
            int n = 0;
            while ((n = inf.read()) != -1)
            {
                let = (char)n;
                if (Character.isLetter(let))
                {
                    wordstr += Character.toLowerCase(let);                    
                }
                if ((Character.isWhitespace(let) || let =='-') && !wordstr.isEmpty())
                {
                    //if(dictionary[(int)wordstr.charAt(0)-97].search(wordstr))
                    if(dictionary[(int)wordstr.charAt(0)-97].search(wordstr, stringComparisons))    
                    {
                        foundWords++;
                        wordsFoundComparison += stringComparisons[0];
                    }
                    else
                    {
                        notFoundWords++;
                        wordsNotFoundComparison += stringComparisons[0];
                    }
                    wordstr="";
                }
            }
            inf.close(); 
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }// end of spellCheck
    /**
     * Tests the methods created above.  
     * @param args
     */
    public static void main(String[] args)
    {
        SpellChecker newChecker = new SpellChecker();
        newChecker.readDictionaryFile();
        newChecker.spellCheck();        
        System.out.println("Number of misspelled words: " + newChecker.notFoundWords);
        System.out.println("Number of correct spelled words: " + newChecker.foundWords);
        System.out.println("Number of misspelled words comparisons: " + newChecker.wordsNotFoundComparison);
        System.out.println("Number of correct spelled words comparisons: " + newChecker.wordsFoundComparison);
        System.out.println("The average number of comparisons for words "
                + "found: " + (double)newChecker.wordsFoundComparison/newChecker.foundWords);
        System.out.println("The average number of comparisons for words not"
                + "found: " + (double)newChecker.wordsNotFoundComparison/newChecker.notFoundWords);
    }
}

