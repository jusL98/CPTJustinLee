import arc.*;

public class Connect4Main {
	public static void main(String[] args){
		Console con = new Console("Connect 4", 1280, 720); // https://staugustinechs.ca/arc/arc/Console.html
		
		// Connect 4 Logo TO BE CREATED
		
		con.println("Welcome to Connect 4!\n");
		
		int intSelection;
		con.println("MAIN MENU");
		con.println(" [1] - Play Game ");
		con.println(" [2] - View Leaderboard ");
		con.println(" [3] - Load Theme ");
		con.println(" [4] - Create Theme ");
		con.print("  -> Your Selection: ");
		intSelection = con.readInt();
		while(!(intSelection >= 1 && intSelection <= 4)){ // keep asking until valid option selected
			con.println("  Invalid Entry. Select an option #1-4.");
			con.println();
			con.print("  -> Your Selection: ");
			intSelection = con.readInt();
		}
		
		// Option 1 Activated - Play Game
		if(intSelection == 1){
			System.out.println("TEST: Option 1 Selected");
			
		}
		// Option 2 Activated - View Leaderboard
		else if(intSelection == 2){
			System.out.println("TEST: Option 2 Selected");
			
		}
		// Option 3 Activated - Load Theme
		else if(intSelection == 3){
			System.out.println("TEST: Option 3 Selected");
			
		}
		// Option 4 Activated - Create Theme
		else if(intSelection == 4){
			System.out.println("TEST: Option 4 Selected");
			
		}
	}
	
	public static void playGame(){
		
	}
	
	public static void viewLeaderboard(){
		
	}
	
	public static void loadTheme(){
		
	}
	
	public static void createTheme(){
		
	}
}
