import arc.*;
import java.awt.Color;

public class Main {
	public static void main(String[] args){
		Console con = new Console("Connect 4", 1280, 720); // https://staugustinechs.ca/arc/arc/Console.html
		
		mainMenu(con);
		
	}
	
	public static void mainMenu(Console con) {
		// Connect 4 Logo TO BE CREATED
		
		con.println("Welcome to Connect 4!\n");
		
		con.println("-------------------------------------------");
		int intSelection;
		con.println("MAIN MENU");
		con.println(" [1] - Play Game ");
		con.println(" [2] - View Leaderboard ");
		con.println(" [3] - Load Theme ");
		con.println(" [4] - Create Theme ");
		intSelection = getValidInput(con,4);
		con.println("-------------------------------------------");
		
		// Option 1 Activated - Play Game
		if(intSelection == 1){
			System.out.println("TEST: Main Menu Option 1 Selected"); // TEST
			System.out.println();
			playGame(con);
			
		}
		// Option 2 Activated - View Leaderboard
		else if(intSelection == 2){
			System.out.println("TEST: Main Menu Option 2 Selected"); // TEST
			System.out.println();
			
		}
		// Option 3 Activated - Load Theme
		else if(intSelection == 3){
			System.out.println("TEST: Main Menu Option 3 Selected"); // TEST
			System.out.println();
			
		}
		// Option 4 Activated - Create Theme
		else if(intSelection == 4){
			System.out.println("TEST: Main Menu Option 4 Selected"); // TEST
			System.out.println();
		}
	}
	
	public static void playGame(Console con) {
		String p1Name = "";
		String p2Name = "";
		int p1Wins = 0;
		int p2Wins = 0;
		
		if(p1Name.isEmpty()){
			con.print("Player 1, enter your name: ");
			p1Name = con.readLine();
			while(p1Name.trim().isEmpty()){
				con.println("Name cannot be empty");
				con.println();
				
				con.print("Player 1, enter your name: ");
				p1Name = con.readLine().trim();
			}
		}
		
		if(p2Name.isEmpty()){
			con.print("Player 2, enter your name: ");
			p2Name = con.readLine();
			while(p2Name.trim().isEmpty()){
				con.println("Name cannot be empty");
				con.println();
				
				con.print("Player 2, enter your name: ");
				p2Name = con.readLine().trim();
			}
		}
	}
	
	
	public static int getValidInput(Console con, int intMax) {
		int intInput;
		while(true) {
			con.print("  -> Your Selection: ");
			intInput = con.readInt();
			
			if(intInput >= 1 && intInput <= intMax){
				break;
			}else{
				con.println("     INVALID. Enter a number #1-" + intMax + ".\n");
			}
		}
		
		return intInput;
	}
	
	public static void newScreen(Console con) {
		con.setBackgroundColor(Color.BLACK);
		con.clear();
	}
}
