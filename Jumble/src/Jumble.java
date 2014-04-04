import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

/**********************************************************************
 * The following class takes a string and prints out a list of words 
 * contain exactly the same letters. Multiple strings can be entered.
 * @author Paul Hood
 * @version 3/14/2014
 *********************************************************************/
public class Jumble {

	/** path of the text file */
	private static final String PATH = "dict.txt";

	/** dictionary text file */
	private static final File FILE = new File(PATH);

	/** hashmap that stores keys and arraylist of strings */
	private HashMap<String, ArrayList<String>> hashMap;

	/******************************************************************
	 * Constructor searchs for each string in the parameter array after
	 * filling the hashmap.  
	 * @param searchWords String array of words to be searched for.
	 *****************************************************************/
	public Jumble(String[] searchWords) {
		
		long start = System.currentTimeMillis();

		// fill new hashmap
		hashMap = new HashMap<String, ArrayList<String>>(fillMap());
		
		boolean firstSearch = false;

		// loop through each string in array
		for (String s : searchWords) {

			// create key to search for
			String key = sortWord(s.toLowerCase());

			// prints matching words if hashmap contains key
			if (hashMap.containsKey(key)) {
				printList(s, hashMap.get(key));
			}

			// no words found
			else {
				System.out.println(s + ": N/A");
			}
			
			if (!firstSearch) {
				System.out.println("Run Time: " + (System
						.currentTimeMillis() - start));
				firstSearch = true;
			}
		}
	}

	/******************************************************************
	 * Method runs Jumble class, multiple parameters can be entered.
	 * @param args Words to be searched for
	 *****************************************************************/
	public static void main(String[] args) {

		// string array to search for words
		String[] words = new String[args.length];

		// fills array & sets all words to lowercase
		for (int i = 0; i < words.length; i++) {
			words[i] = args[i].trim();
		}

		// search for words
		new Jumble(words);
	}

	/******************************************************************
	 * Creates a hashmap by sorting each word in the text file and
	 * adding it to the hashmap. If the key has already been created,
	 * the word is added to the arraylist for that key.
	 * @return HashMap of possible matches for the word
	 *****************************************************************/
	private HashMap<String, ArrayList<String>> fillMap() {

		// hashmap to return
		HashMap<String, ArrayList<String>> wordMap = 
				new HashMap<String, ArrayList<String>>();

		try {
			// scanner to read text file
			Scanner scan = new Scanner(FILE);

			// read text file until it has no more lines
			while (scan.hasNextLine()) {

				// get the next word to in the text file
				String nextWord = scan.nextLine().trim();

				// turn the string into a key & set to lowercase
				String key = sortWord(nextWord.toLowerCase());

				// check if the hashmap contains the key
				if (wordMap.containsKey(key)) {

					// add the word to the keys arraylist
					wordMap.get(key).add(nextWord);
				}

				// create new key and add new arraylist
				else {
					ArrayList<String> values = new ArrayList<String>();
					values.add(nextWord);

					// add to hashmap
					wordMap.put(key, values);
				}
			}

			// closes the scanner
			scan.close();
		}

		// error occurd while reading text file
		catch (Exception e) {
			System.out.println("Error: " + e);
		}

		return wordMap;
	}

	/******************************************************************
	 * Rearranges a word into alphabetical order.
	 * @param word String to be sorted.
	 * @return Sorted string
	 *****************************************************************/
	private String sortWord(String word) {

		// breaks word into character array
		char[] charWord = word.toCharArray();

		// sorts the character array
		Arrays.sort(charWord);

		// returns sorted string
		return new String(charWord);
	}

	/******************************************************************
	 * Prints the matches and the search word.
	 * @param searchVal Word 
	 * @param words Arraylist of the matching words
	 *****************************************************************/
	private void printList(String searchVal, ArrayList<String> words) {
		String display = "";
		for (int i = 0; i < words.size(); i++) {
			display += words.get(i) + " ";
		}

		// print the search string and all its matches
		System.out.println(searchVal + ": " + display);
	}

}
