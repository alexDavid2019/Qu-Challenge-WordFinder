package ar.wordfinder;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class WordFinder {
	
	char[][] puzzle; // The puzzle representation.
	
	Vector<String> toFind; // The words to find.
	
	List<String> mMessages = new ArrayList<String>();
	
	WordFinder(List<String> matrix) {
		mMessages.clear();
		populatePuzzle(matrix);
	}

	public List<String> getMessages(){
		return mMessages;
	}
	
	private void populatePuzzle(List<String> matrix) {

		String firstLine = matrix.get(0);
		int size = firstLine.length();
		// Determine puzzle dimensions and populate first line of array.
		this.puzzle = new char[size][size];
		
		// Populate remainder of array.
		int row = 0;
		for(String line : matrix) 
		{
        	for (int col = 0; col < size; col++) {
				this.puzzle[row][col] = line.charAt(col);
			}
			row++;
 		}
	}
	
	// Passes each word to find.
	public List<String> Find(Vector<String> wordstream)
	{
		List<String> lstWordFound = new ArrayList<String>();
		
		mMessages.clear();

		this.toFind = wordstream;

		for (String word: toFind) {
			boolean wordFound = findWord(word);
			if (wordFound && !lstWordFound.contains(word)) 
			{
				lstWordFound.add(word);
			}
		}
		return lstWordFound;
	}
	
	// Searches for first letter until match is found or array is exhausted.
	private Boolean findWord(String word) 
	{ 
		boolean wordFound = false;

		int size = puzzle[0].length; // Puzzle dimensions.
		for (int row = 0; row < size; row++) 
		{
			for (int column = 0; column < size; column++) 
			{
				if (wordFound == false && puzzle[row][column] == word.charAt(0)) { 
					// Assumes the puzzle only contains the match once.
					wordFound = confirmMatch(word, row, column);
				}
			}
		}
				
		if (wordFound == false)
			System.out.println(word + " not found");
	
		return wordFound;
	}
	
	// Searches all eight directions from a first letter match passed by findWord().
	// Algorithm is efficient, but lots of repeated code.
	private boolean confirmMatch(String word, int row, int column) { 

		boolean foundMatch = false;
		
		int len = word.length(); // We will only search in directions that have at least this much space. 
		
		if ((column - len) >= -1) { // The word can exist to the left.
			int wordPos = 0;
			for (int i = column; i >= (column - len) + 1; i--) {
				if (word.charAt(wordPos) != puzzle[row][i]) {
					break;
				}
				if (i == (column - len) + 1) { // A match was found
					foundMatch = printMatch(word, row, column, row, i);
				}
				wordPos++;
			}
		}
		
		if ((column + len) <= puzzle[0].length) { // The word can exist to the right
			int wordPos = 0;
			for (int i = column; i <= (column + len) - 1; i++) {
				if (word.charAt(wordPos) != puzzle[row][i]) {
					break;
				}
				if (i == (column + len) - 1) { // A match was found
					foundMatch = printMatch(word, row, column, row, i);
				}
				wordPos++;
			}
		}
		
		if ((row - len) >= -1) { // The word can exist above
			int wordPos = 0;
			for (int i = row; i >= (row - len) + 1; i--) {
				if (word.charAt(wordPos) != puzzle[i][column]) {
					break;
				}
				if (i == (row - len) + 1) { // A match was found
					foundMatch = printMatch(word, row, column, i, column);
				}
				wordPos++;
			}
		}

		if ((row + len) <= puzzle[0].length) { // The word can exist below
			int wordPos = 0;
			for (int i = row; i <= (row + len) - 1; i++) {
				if (word.charAt(wordPos) != puzzle[i][column]) {
					break;
				}
				if (i == (row + len) - 1) { // A match was found
					foundMatch = printMatch(word, row, column, i, column);
				}
				wordPos++;
			}
		}
		
		if ((row - len) >= -1 && (column - len) >= -1) { // The word can exist up and to the left
			int wordPos = 0;
			int j = column;
			for (int i = row; i >= (row - len) + 1; i--) 
			{
				if (word.charAt(wordPos) != puzzle[i][j]) {
					break;
				}
				if (i == (row - len) + 1) { // A match was found
					foundMatch = printMatch(word, row, column, i, j);
				}
				wordPos++;
				j--;
			}
		}
		
		if ((row - len) >= -1 && (column + len) <= puzzle[0].length) { // The word can exist up and to the right
			int wordPos = 0;
			int j = column;
			for (int i = row; i >= (row - len) + 1; i--) 
			{
				if (word.charAt(wordPos) != puzzle[i][j]) {
					break;
				}
				if (i == (row - len) + 1) { // A match was found
					foundMatch = printMatch(word, row, column, i, j);
				}
				wordPos++;
				j++;
			}
		}
		
		if ((row + len) <= puzzle[0].length && (column - len) >= -1) { // The word can exist down and to the left
			int wordPos = 0;
			int j = column;
			for (int i = row; i <= (row + len) - 1; i++) {
				if (word.charAt(wordPos) != puzzle[i][j]) {
					break;
				}
				if (i == (row + len) - 1) { // A match was found
					foundMatch = printMatch(word, row, column, i, j);
				}
				wordPos++;
				j--;
			}
		}
		
		if ((row + len) <= puzzle[0].length && (column + len) <= puzzle[0].length) { // The word can exist down and to the right
			int wordPos = 0;
			int j = column;
			for (int i = row; i <= (row + len) - 1; i++) {
				if (word.charAt(wordPos) != puzzle[i][j]) {
					break;
				}
				if (i == (row + len) - 1) { // A match was found
					foundMatch = printMatch(word, row, column, i, j);
				}
				wordPos++;
				j++;
			}
		}
		
		return foundMatch;
	}
	
	private Boolean printMatch(String word, int startRow, int startColumn, int endRow, int endColumn) {
		
		String message = word + " found at start: " + startRow + ", " + startColumn + " end: " + endRow + ", " + endColumn;
		
		if (!mMessages.contains(message)) 
		{
			mMessages.add(message);
			//System.out.println(message);
			return true;
		}
		return false;
	}
}
