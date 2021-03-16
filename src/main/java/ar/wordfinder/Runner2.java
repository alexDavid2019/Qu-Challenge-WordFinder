package ar.wordfinder;

import java.util.*;

import ar.model.Words;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Runner2 {
		
	public static void main(String[] args) {

		String fileName = getArg(args,1);
		//String fileName = "files//exampleFile2.txt";
		
		Words _w = getWordsFromFile(fileName);
		
		if (_w != null && _w.IsCompleted()) 
		{
			WordFinder _finder = new WordFinder(_w.getMatrix());
			List<String> _result = _finder.Find(_w.getWordsToFind());
			
			if (_result != null && !_result.isEmpty()) 
			{
				for(String word : _result) 
				{
					System.out.print("--> Word found: " + word);
					System.out.println();
				}
			}
			else {
				System.out.print("--> Words not found");
				System.out.println();
			}
		}
		else
		{
			System.out.print("The file is not valid. ");
			System.exit(0);
		}
		
	}
		
	public static String getArg(String[] args, int index){
		
		if (args.length > 1){
			return args[index];
		}
		else
		{
			return null;
		}
	}

	/*
	 * Required file format:
	 * Example:
	 * yaqewyifowkdxiwaixthjzlkvomuwnllibtfjxyztyiztsbiddayzvvkeqxepeyc
	 * ......
	 * ......
	 * (new line)
	 * mother
	 * program
	 * .....
	 * ....
	 */
	public static Words getWordsFromFile(String fileName) {
		
		Words _arg = new Words();

		Path _path = Paths.get(fileName);
		
        File file = _path.toFile();
        
		try {
			
			Scanner scanner = new Scanner(file);
			
			List<String> matrix = new ArrayList<String>();
			
			// Determine puzzle dimensions and populate first line of array.
			String firstLine = scanner.nextLine();
			matrix.add(firstLine);
			int size = firstLine.length();
			int row = 1; // Don't read beyond the bounds of the puzzle.
			while (scanner.hasNextLine()  && row < size) {
	            String line = scanner.nextLine();
	            if (line.length() > 0) {
	            	matrix.add(line);
	            }
	            row++;
	        }
			
			_arg.setMatrix(matrix);
			
			
	        Vector<String> toFind = new Vector<String>();
	        while (scanner.hasNextLine()) { // Remainder of the file is words to search for.
	        	String word = scanner.nextLine();
	        	if (word.length() > 0) {
	        		toFind.add(word);
	        	}
	        }
	        
	        _arg.setWordsToFind(toFind);
			
			scanner.close();
			
		}
		catch (FileNotFoundException e) {
			System.out.println();
			System.err.println(e.getMessage());
		}
		return _arg;        
	}
}
