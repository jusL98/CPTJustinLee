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
			playGame(con);
			
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
	
	
	public static void playGame(Console con){
		String p1Name = "";
		String p2Name = "";
		int p1Wins = 0;
		int p2Wins = 0;
		
		if(p1Name.isEmpty()){
			con.print("Player 1, enter your name: ");
			p1Name = con.readLine();
			while(p1Name.isEmpty()){
				con.println("Name cannot be empty");
				con.println();
				
				con.print("Player 1, enter your name: ");
				p1Name = con.readLine();
			}
		}
		
		if(p2Name.isEmpty()){
			con.print("Player 2, enter your name: ");
			p2Name = con.readLine();
			while(p1Name.isEmpty()){
				con.println("Name cannot be empty");
				con.println();
				
				con.print("Player 2, enter your name: ");
				p2Name = con.readLine();
			}
		}
	}
	
	public static void viewLeaderboard(){
		
	}
	
	public static void loadTheme(){
		
	}
	
	public static void createTheme(){
		
	}
}
