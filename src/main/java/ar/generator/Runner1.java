package ar.generator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Runner1 {
	
    public static void main(String[] args) {

		String fileNameSources = getArg(args,1);
		String fileNameOutput = getArg(args,2);
		
		//String fileNameSources = "files//exampleFile.txt";
		//String fileNameOutput = "files//exampleFile2.txt";

		List<String> wordList = new ArrayList<String>();
		
		try {
			wordList = UtilHelper.readFileInList(fileNameSources);

			//String[] wordList = WordMatrix.getWordList();
			char[][] puzzle = WordMatrix.generateWordSearch(WordMatrix.computeGridSize(wordList), wordList);
			
			WordMatrix.SaveGrid(puzzle, wordList, fileNameOutput);

		} catch (IOException e) {
			System.out.println();
			System.err.println(e.getMessage());
		}
		
		System.out.println();
	}
	
	public static String getArg(String[] args, int index){
		
		if (args.length > 1 && index < args.length )
		{
			return args[index];
		}
		else
		{
			return null;
		}
	}
	
}
