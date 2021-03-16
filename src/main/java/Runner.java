import ar.generator.Runner1;
import ar.wordfinder.Runner2;

public class Runner {

	public static void main(String[] args) {

		System.out.println("======== start ========\n");
		
		String action = getFirstArg(args);
		if (action != null && action.equals("gen")) {
			Runner1.main(args);
		}
		else if (action != null && action.equals("read")) {
			Runner2.main(args);
		}
		else {
			System.out.println("invalid comands");
		}
		
		System.out.println("======== end ========\n");

	}
		
	private static String getFirstArg(String[] args){
		
		if (args.length > 0){
			return args[0];
		}
		else
		{
			System.out.print("You need a single file name.");
			System.exit(0);
			return null;
		}
	}

}
