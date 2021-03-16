package ar.generator;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * A collection of utility methods that must be used by your WordSearch program
 * to facilitate our ability to accurate test your results.
 * 
 * Do not edit, except to add word lists.  Leave existing word lists in place.
 */
public class UtilHelper {

	/** 
	 * Max number of times your WordSearch will attempt to randomly
	 * place a word in the word search field.  This is intended to 
	 * avoid the potential for an infinite loop should a word not 
	 * be able to placed without conflict in the current version of 
	 * the puzzle.
	 */
	public static final int SEARCH_CAP = 3;

	/** 
	 * A seed value is used to create a deterministic random number generator.
	 * A different seed value will mean a different order of random numbers
	 * are generated. But, using the same seed value in multiple runs,
	 * should mean that the same numbers are "randomly" generated each time.
	 * 
	 * This is useful for testing different parts of your program.
	 */
	public static final int SEED = 10;

	/** 
	 * Use this Random Number Generator for all random numbers
	 * that must be generated.
	 */
	public static final Random RNG = new Random(SEED);

	public static final Scanner input = new Scanner(System.in);


	/** Indicates that word will be placed going up (North) in the puzzle. */
	public static final int NORTH = 1;

	/** Indicates that word will be placed going up and right (Northeast) in the puzzle. */
	public static final int NORTH_EAST = 2;

	/** Indicates that word will be placed going right (East) in the puzzle. */
	public static final int EAST = 3;

	/** Indicates that word will be placed going down and right (Southeast) in the puzzle. */
	public static final int SOUTH_EAST = 4;

	/** Indicates that word will be placed going down (South) in the puzzle. */
	public static final int SOUTH = 5;

	/** Indicates that word will be placed going down and left (Southwest) in the puzzle. */
	public static final int SOUTH_WEST = 6;

	/** Indicates that word will be placed going left (West) in the puzzle. */
	public static final int WEST = 7;

	/** Indicates that word will be placed going up and left (Northwest) in the puzzle. */
	public static final int NORTH_WEST = 8;


	/** 
	 *  List of all possible directions. Use this array when searching for a direction
	 *  to place a word.
	 */
	public static final int[] DIRECTIONS = {NORTH, NORTH_EAST, EAST, SOUTH_EAST,
		SOUTH, SOUTH_WEST, WEST, NORTH_WEST};

	/** Indicates number of possible directions that words can be placed. */
	public static final int NUM_DIRECTIONS = DIRECTIONS.length;


	/** 
	 * Converts all characters to lowercase and removes any non alphabetic 
	 * characters.  Must be called to ensure no invalid words are added to the
	 * word search.
	 * 
	 * The Util.sanitize() method works on a line at a time. If the user enters
	 * words with spaces or non-alphabetical characters, 
	 * they will simply be removed. 
	 * 
	 * For example, if the argument is the string "Word Search", 
	 * then the String "wordsearch" is what is returned.
	 * 
	 * @param word The word or phrase to sanitize
	 * @return A word with only the letters a-z and without invalid characters.
	 */
	public static String sanitize(String word){
		word = word.toLowerCase(); // only lowercase letters are valid
		String sanitizedWord = "";
		for( int i = 0; i < word.length(); i++ ){
			char c = word.charAt(i);
			if( c >= 'a' && c <= 'z' )
				sanitizedWord += c; // append next valid character
		}
		return sanitizedWord;
	}

	public static List<String> readFileInList(String fileName) throws IOException 
	{ 
	    List<String> lines = Collections.emptyList(); 
	    lines = Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8); 
	    return lines; 
	} 
}
