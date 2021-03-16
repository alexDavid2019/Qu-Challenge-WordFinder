package ar.generator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class WordMatrix {


	/**
	 * Generates a word search of gridSize x gridSize, with the words from 
	 * wordList hidden in various directions. For each word in wordList, pick a 
	 * random coordinate and attempt to place the word at that coordinate. This 
	 * is attempted by calling the placeWord() method, which returns a boolean 
	 * value - true if the word was able to be placed, and false otherwise.
	 * 
	 * (Note: placeWord() checks whether the word can be placed, and also 
	 * actually places the word in the grid if possible.) If the word is unable 
	 * to be placed, a new random coordinate is chosen and placeWord() is called 
	 * again. This process is repeated UtilHelper.SEARCH_CAP times, or until 
	 * placeWord() returns true. If placeWord() never returns true after 
	 * UtilHelper.SEARCH_CAP times, the following error message is printed: 
	 * 
	 * "Unable to add <word> to the puzzle."
	 * 
	 * After the error message, move on to the next word in the list. Once all 
	 * words in the list have been attempted, the empty spots in the grid are 
	 * filled by a call to fillPuzzle(), and the completed grid is returned. 
	 * 
	 * @param gridSize The length and width of the square 2D array, as computed 
	 * by computeGridSize()
	 * @param wordList The list of words to be hidden in the puzzle
	 * @return A char[][] of size gridSize x gridSize filled with a word search 
	 * puzzle
	 */
	public static char[][] generateWordSearch(int gridSize, List<String> wordList){
		
		//Create a new char[][] to hold the puzzle
		char[][] puzzle = new char[gridSize][gridSize];
		//An arraylist to store words that successfully 
		//added to the puzzle.
		ArrayList<String> addedWords = new ArrayList<String>();
		//Loop through wordList, attempting to place each word in the puzzle
		//for Util.SEARCH_CAP times. If succeed, add the word to arraylist 
		//addWords. If fail, print out the warning message.
		for (int index = 0; index < wordList.size(); index++)
		{
			for (int times = 0; times < UtilHelper.SEARCH_CAP; times++)
			{
				int row = UtilHelper.RNG.nextInt(gridSize);
				int col = UtilHelper.RNG.nextInt(gridSize);
				
				if (placeWord(puzzle, row, col, wordList.get(index)) == false)
				{
					if (times == UtilHelper.SEARCH_CAP - 1)
					{
						//Error message is printed when a word is unable to be 
						//added
						System.out.println("Warning! Unable to add "
											+ wordList.get(index) + 
											" to the puzzle.");
					}
				}
				else
				{
					//Add the word to the arraylist addedWords and
					//move on to the next word.
					addedWords.add(wordList.get(index));
					break;
				}
			}
		}
		
		//Print out words that were successfully added.
		System.out.println("Word List:");
		for (int i = 0; i < addedWords.size(); i++)
		{
			String addedWord = addedWords.get(i);
			System.out.println(addedWord);
			
		}
		
		//Fill empty spots in the grid with random characters
		fillPuzzle(puzzle);
		
		return puzzle;
	}

	/**
	 * Fills any empty spaces in the grid with random characters, to conceal the 
	 * locations of the hidden words. The random characters must be letters 
	 * between 'a' and 'z'. 
	 * @param grid The 2D array of characters making up the word search puzzle.
	 */
	private static void fillPuzzle(char[][] grid){
		//loop through the grid, searching for empty spots 
		//(denoted by the null character)
		for (int i = 0; i < grid.length; i++)
		{
			// Access each sub array in grid.
			for (int j = 0; j < grid[0].length; j++)
			{
				//if a spot is empty, fill it with a random character
				if (grid[i][j] == '\0')
				{
					grid[i][j] = (char) (UtilHelper.RNG.nextInt(26) + 'a');
				}
			}
		}
	}

	/**
	 * Attempts to place word at location grid[row][col]. First, calls 
	 * findDirection() to determine the direction the word will go in. 
	 * findDirection() returns one of the direction constants listed in Util if 
	 * one of those directions is a good location for word. Otherwise, 
	 * findDirection returns 0. As long as the direction returned is not 0, word 
	 * is actually placed on the grid. If however, the direction returned is 0, 
	 * placeWord() returns false, because word could not be placed at this 
	 * location.
	 * 
	 * For example, if the direction is NORTH, and the word is "smoke", and the 
	 * starting location is [5, 0], "smoke" will be placed in the grid as such:
	 * 
	 *    0 1 2 3 4 5 6
	 *  
	 * 0  q e v b s s a
	 * 1  e r l c w l k 
	 * 2  k q k z r h f
	 * 3  o h m x r g c
	 * 4  m l n f t d x
 	 * 5  s i u p o a z 
	 * 
	 * @param grid The 2D array of characters making up the word search puzzle. 
	 * @param row The row value of the location we are attempting to place word.
	 * @param col The column value of the location we are attempting to place 
	 * word.
	 * @param word The word we are attempting to place at grid[row][col].
	 * @return True if word is placed at grid[row][col] and false otherwise. 
	 */
	private static boolean placeWord(char[][] grid, int row, int col, String word){
		
		int direction = findDirection(grid, row, col, word);
		
		//if direction != 0, place word in that direction in the grid, and 
		//return true
		switch(direction)
		{
			case UtilHelper.NORTH: //NORTH
				for (int i = 0; i < word.length(); i++)
				{
					grid[row - i][col] = word.charAt(i);
				}
				return true;
				
			case UtilHelper.NORTH_EAST: //NORTH EAST
				for (int i = 0; i < word.length(); i++)
				{
					grid[row - i][col + i ] = word.charAt(i);
				}
				return true;
			case UtilHelper.EAST: //EAST
				for (int i = 0; i < word.length(); i++)
				{
					grid[row][col + i ] = word.charAt(i);
				}
				return true;
			case UtilHelper.SOUTH_EAST: //SOUTH_EAST
				for (int i = 0; i < word.length(); i++)
				{
					grid[row + i][col + i ] = word.charAt(i);
				}
				return true;
			case UtilHelper.SOUTH: //SOUTH
				for (int i = 0; i < word.length(); i++)
				{
					grid[row + i][col] = word.charAt(i);
				}
				return true;
			case UtilHelper.SOUTH_WEST: //SOUTH_WEST
				for (int i = 0; i < word.length(); i++)
				{
					grid[row + i][col - i ] = word.charAt(i);
				}
				return true;
			case UtilHelper.WEST: //WEST
				for (int i = 0; i < word.length(); i++)
				{
					grid[row][col - i ] = word.charAt(i);
				}
				return true;
			case UtilHelper.NORTH_WEST: //NORTH WEST
				for (int i = 0; i < word.length(); i++)
				{
					grid[row - i][col - i ] = word.charAt(i);
				}
				return true;
			default: 
				return false;
		}
	}

	/**
	 * First, picks a random direction to try - this will be a valid index of 
	 * UtilHelper.DIRECTIONS. If that direction at that index goes out of bounds or 
	 * intersects with another word currently on the board (it's ok to intersect 
	 * with a matching letter), then direction index is increased by 1. Be sure 
	 * to check if the current index is past the bounds of the DIRECTIONS array 
	 * - if so, instead of incrementing the index by 1, wrap around to 0. If all 
	 * directions are tried and none work, findDirection returns 0. When a 
	 * direction is found that works for this word, return the integer 
	 * corresponding to that direction. DO NOT return the index of the 
	 * direction. For example, if the selected direction is north, Util.NORTH 
	 * would be the integer returned, not whatever index UtilHelper.DIRECTIONS has 
	 * UtilHelper.NORTH stored in.
	 * 
	 * @param row The row value of the location we are attempting to place word.
	 * @param col The column value of the location we are attempting to place 
	 * word.
	 * @param word The word we are attempting to place at grid[row][col].
	 * @return An integer corresponding to a direction in which word can be 
	 * placed at grid[row][col]
	 */
	private static int findDirection(char[][] grid, int row, int col, 
									String word)
		{
		//The list of directions that have been tried on the word.
		int[] oldSelectedDir = new int[UtilHelper.DIRECTIONS.length];
		//count the index of oldSelectedDir, each time an element
		//is added to the list, count increments.
		int count = 0;
		//An appropriate direction for the word.
		int niceDir = 0;
		while (sameElem(oldSelectedDir, UtilHelper.DIRECTIONS) == false)
		{
			//Pick a random direction index (number between 0 and 
			//Util.DIRECTIONS.length)
			int dir = UtilHelper.DIRECTIONS[UtilHelper.RNG.nextInt(UtilHelper.DIRECTIONS.length)];
			//Call checkPlacement() to see if the direction chosen will work
			//If checkPlacement() returns true, return the chosen direction 
			if (checkPlacement(grid, row, col, word, dir) == true)
			{
				niceDir = dir;
			}
			else
			{
				//If the direction has not been tried, add it to the
				//oldSelectedDir so that the program will avoid to 
				//try that direction again. This will guard off 
				//infinite loop.
				if (findElementInArr(oldSelectedDir, dir) == false)
				{
					oldSelectedDir[count] = dir;
					count++;
				}
			}
			//If niceDir != 0, which means that the program has found
			//a good direction, break from the loop.
			if (niceDir != 0)
			{
				break;
			}
		}
		//Repeat this process until a direction is found.
		return niceDir;
	}

	/**
	 * Checks the placement of a word at a specific location in a specific 
	 * direction. If the word can be placed here, true is returned. The word is 
	 * NOT actually placed in the grid at this time. If the word cannot be 
	 * placed here, false is returned. 
	 * @param grid
	 * @param row
	 * @param col
	 * @param word The word we are attempting to place at grid[row][col].
	 * @param dir
	 * @return true if word can be placed here, false otherwise
	 */
	private static boolean checkPlacement(char[][] grid, int row, int col, String word, int dir)
	{
		//Check which direction constant corresponds to dir
		boolean appropriate = false;
		/* If a tried case resultant in a return appropriate of true
		 * the word can be placed in that direction. For each corresponding
		 * direction, check each of the box that will be placed a letter 
		 * from the word if it is possible or not. If not, return false.
		 * Check for the two possible conflicts in placing a word
		 *	1. Not enough room in the grid to go in this direction
		 *	2. Conflicts with an already placed word
		 *	
		 * If either of these conflicts occurs, return false
		 */
		switch(dir)
		{
			case UtilHelper.NORTH: //NORTH
				for (int i = 0; i < word.length(); i++)
				{
					if ((row - i) >= 0) 
					{
						appropriate = true;
						if (grid[row - i][col] != '\0' && 
							grid[row - i][col] != word.charAt(i))
						{
							appropriate = false;
							break;
						}
					}
					else
					{
						appropriate = false;
					}
				}
				return appropriate;
						
			case UtilHelper.NORTH_EAST: //NORTH EAST
				for (int i = 0; i < word.length(); i++)
				{
					if ((row - i >= 0) && (col + i < grid[0].length)) 
					{
						appropriate = true;
						if (grid[row - i][col + i] != '\0' && 
							grid[row - i][col + i] != word.charAt(i))
						{
							appropriate = false;
							break;
						}
					}
					else
					{
						appropriate = false;
					}
				}
				return appropriate;
						
			case UtilHelper.EAST: //EAST
				for (int i = 0; i < word.length(); i++)
				{
					if ((col + i) < grid.length)
					{
						appropriate = true;
						if(grid[row][col + i] != '\0' && 
						   grid[row][col + i] != word.charAt(i))
						{
							appropriate = false;
							break;
						}
					}
					else
					{
						appropriate = false;
					}
				}
				return appropriate;
					
			case UtilHelper.SOUTH_EAST: //SOUTH EAST
				for (int i = 0; i < word.length(); i++)
				{
					if ((row + i < grid.length) && (col + i < grid[0].length))
					{
						appropriate = true;
						if ((grid[row + i][col + i] != '\0' && 
							grid[row + i][col + i] != word.charAt(i)))
						{
							appropriate = false;
							break;
						}
					}
					else
					{
						appropriate = false;
					}
				}
				return appropriate;
					
			case UtilHelper.SOUTH: //SOUTH
				for (int i = 0; i < word.length(); i++)
				{
					if ((row + i) < grid.length)
					{		
						appropriate = true;
						if (grid[row + i][col] != '\0' && 
							grid[row + i][col] != word.charAt(i)) 
						{
							appropriate = false;
							break;
						}
					}
					else
					{
						appropriate = false;
					}
				}
				return appropriate;
					
			case UtilHelper.SOUTH_WEST: //SOUTH_WEST
				for (int i = 0; i < word.length(); i++)
				{
					if ((row + i < grid.length) && (col - i >= 0))
					{
						appropriate = true;
						if (grid[row + i][col - i] != '\0' && 
							grid[row + i][col - i] != word.charAt(i))
						{
							appropriate = false;
							break;
						}
					}
					else
					{
						appropriate = false;
					}
				}
				return appropriate;
						
			case UtilHelper.WEST: //WEST
				for (int i = 0; i < word.length(); i++)
				{
					if ((col - i) >= 0)
					{
						appropriate = true;
						if (grid[row][col - i] != '\0' && 
							grid[row][col - i] != word.charAt(i))
						{
							appropriate = false;
							break;
						}
					}
					else
					{
						appropriate = false;
					}
				}
				return appropriate;
					
			case UtilHelper.NORTH_WEST: //NORTH WEST
				for (int i = 0; i < word.length(); i++)
				{
					if ( (row - i >= 0) && (col - i >= 0))
					{
						appropriate = true;
						if (grid[row - i][col - i] != '\0' && 
							grid[row - i][col - i] != word.charAt(i))
						{
							appropriate = false;
							break;
						}
					}
					else
					{
						appropriate = false;
					}
				}
				return appropriate;
					
			default:
				return false;
		}
	}
		

	/**
	 * Computes the gridSize. Takes the total number of characters in the list,
	 * finds the square root. The grid size is then the ceiling value of this 
	 * square root, plus 3. 
	 * @param wordList
	 * @return An integer representing the length and width of the square grid.
	 */
	public static int computeGridSize(List<String> wordList){
		//Find number of characters in list
		//add up all of the characters in the word list
		String words = "";
		for (int i = 0; i < wordList.size(); i ++)
		{
			words = words + wordList.get(i);
		}
		
		int len = words.length();
		//Find the square root of this number
		double squareRoot = Math.ceil(Math.sqrt(len));
		
		//int gridSize = (int) (squareRoot + 3);
		int gridSize = (int) ( (squareRoot + 3) + (64 - (squareRoot + 3)) );
		
		//Find the ceiling of the square root, and add 3. This number is the grid size.
		return gridSize;
	}

	/**
	 * Prints and save the grid. Each character of each line should be 
	 * separated by a space. 
	 * @param grid
	 * @throws IOException 
	 */
	public static void SaveGrid(char[][] grid, List<String> wordList, String filename) throws IOException{

		java.net.URL url = WordMatrix.class.getResource("/files/");
		
		FileWriter fileWriter = null;

		File outFile = new File(filename);
		    
		fileWriter = new FileWriter(outFile.getPath().toString());
		
		PrintWriter printWriter = new PrintWriter(fileWriter);
	    
		// Access each element in two dimensional array named grid.
		for (int i = 0; i < grid.length; i++)
		{
			String _merge = "";
			// Access each sub array in grid.
			for (int j = 0; j < grid[0].length; j++)
			{
				System.out.print(grid[i][j] + " ");
				_merge += grid[i][j];
			}
			
			printWriter.println(_merge);
			
			//System.out.println();
		}
		
		printWriter.println();
		
		for(String line : wordList) 
		{
			printWriter.println(line);
		}
		
		printWriter.close();
	
	}

	/**
	 *
	 * Places a word in the grid in a "bent" shape rather than a straight line. 
	 * This is accomplished by dividing word into two separate Strings - each 
	 * half the length of the original word. If the word length is odd, the 
	 * second half will be the longer half. 
	 * 
	 * For example, "computer" is divided up into "comp" and "uter", while 
	 * "house is divided up into "ho" and "use".
	 * 
	 * After dividing up the word, we can use previously completed methods to 
	 * achieve the necessary results. Some complexities are involved here, as 
	 * we don't want words to change direction and overwrite characters that 
	 * have already been written. 
	 * 
	 * @param grid
	 * @param row
	 * @param col
	 * @param word
	 * @return true if the word was placed in the grid, false otherwise
	 */

	private static boolean sameElem(int[] arr1, int[] arr2)
	{
		if (arr1.length != arr2.length)
		{
			return false;
		}
		//count the elements that both arr1 and arr2 have in common
		int count = 0;
		for (int i = 0; i < arr2.length; i++)
		{
			
			if (findElementInArr(arr1, arr2[i]) == true)
			{
				count++;
			}
		}
		if (count == arr2.length)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	/* This function try to find an integer in a specified array.
	 * 
	 * @param array 
	 * @param number
	 * @return true if found and false otherwise
	*/
	private static boolean findElementInArr(int[] array, int number)
	{
		//count the number of occurrence of an element in an array.
		int count = 0;
		for (int i = 0; i < array.length; i++)
		{
			if (number == array[i])
			{
				count++;
			}
		}
		if (count > 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
}
