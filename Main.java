import arc.*;
import java.awt.Color;
import java.awt.image.BufferedImage;

public class Main {
	public static void main(String[] args){
		Console con = new Console("Connect 4", 1280, 720); // https://staugustinechs.ca/arc/arc/Console.html
		
		// TODO: create a quit button
		
		// TODO: logo to be replaced with custom one, set size properly and position properly
		//BufferedImage imgLogo = con.loadImage("C4Test.png");
		//con.drawImage(imgLogo, 0, 0);

		con.println(" =========================================== ");
		con.println("            Welcome to Connect 4!            ");
		con.println(" =========================================== ");
		
		con.println();
 
		int intSelection;
		con.println("                  MAIN MENU                  ");
		con.println(" ------------------------------------------- ");
		con.println("    [1] - Play Game ");
		con.println("    [2] - View Leaderboard ");
		con.println("    [3] - Load Theme ");
		con.println("    [4] - Create Theme ");
		con.println();
		intSelection = getValidInput(con,4);
		
		// Option 1 Activated - Play Game
		if(intSelection == 1){
			System.out.println("TEST: Main Menu Option 1 Selected"); // TEST
			System.out.println();
			
			playGameScreen(con);
		}
		// Option 2 Activated - View Leaderboard
		else if(intSelection == 2){
			System.out.println("TEST: Main Menu Option 2 Selected"); // TEST
			System.out.println();
			
			viewLeaderboardScreen(con);
		}
		// Option 3 Activated - Load Theme
		else if(intSelection == 3){
			System.out.println("TEST: Main Menu Option 3 Selected"); // TEST
			System.out.println();
			
			loadThemeScreen(con);
			
		}
		// Option 4 Activated - Create Theme
		else if(intSelection == 4){
			System.out.println("TEST: Main Menu Option 4 Selected"); // TEST
			System.out.println();
			
			createThemeScreen(con);
		}
		
	}
	
	
	
	// ************************************************************************
	// CONNECT 4 SCREEN METHODS - for each option of main menu
	// ************************************************************************
	
	/*
	 * playGameScreen method:
	 * Activated when "[1] - Play Game" is selected in the main menu.
	 * Contains the main Connect 4 game play and logic.
	 */
	public static void playGameScreen(Console con) {
		newScreen(con);

		String strP1Name;
		String strP2Name;
		
		strP1Name = getPlayerName(con, 1);
		con.println("\n ------------------------------------------- \n");
		strP2Name = getPlayerName(con, 2);  
	}
	
	
	/*
	 * playGameScreen method:
	 * Activated when "[2] - View Leaderboard" is selected in the main menu.
	 * Displays the top 3 players with most wins within a session.
	 * Displays the next 7 players of the top 10.
	 */
	public static void viewLeaderboardScreen(Console con){
		newScreen(con);
		String strLeaderboard[][] = DataManager.getLeaderboard();
		
		con.println("                 LEADERBOARD                 ");
		con.println(" ------------------------------------------- ");
		for(int intCount = 0; intCount < strLeaderboard.length; intCount++){
			con.println(strLeaderboard[intCount][0] + ", " + strLeaderboard[intCount][1]);
		}
		
		// TODO: display top 3 then the rest of the bottom 7 - need to handle if there are less than 3 leaderboard entries.
		// TODO: figure out if need to log players with 0 wins at end of session... first thought probably yes.
	}
	
	
	/*
	 * loadThemeScreen method:
	 * Activated when "[3] - Load Theme" is selected in the main menu.
	 * Allows the user to choose a theme to load and apply to change the game colour scheme.
	 */
	public static void loadThemeScreen(Console con){
		newScreen(con);
		
	}
	
	
	/*
	 * createThemeScreene method:
	 * Activated when "[4] - Create Theme" is selected in the main menu.
	 * Allows the user to create a custom theme to add to the load theme options.
	 */
	public static void createThemeScreen(Console con){
		newScreen(con);
		
	}
	
	
	
	// ************************************************************************
	// HELPER/UTILITY METHODS - reusable components for common tasks
	// ************************************************************************
	
	/* 
	 * getValidInput method: 
	 * Returns a valid numeric input from the user.
	 * Filters out and handles numbers over or under a range of 1-intMax.
	 * If input is invalid, prompts for input until valid input given.
	 */
	public static int getValidInput(Console con, int intMax) {
		int intInput;
		while(true) {
			con.print(" Your Selection: ");
			intInput = con.readInt();
			
			if(intInput >= 1 && intInput <= intMax){
				break;
			}else{
				con.println("    [INVALID] Enter a number #1-" + intMax + ".\n");
			}
		}
		
		return intInput;
	}
	
	
	/*
	 * getPlayerName method:
	 * Returns a valid String input from the user for a valid name.
	 * Used for gathering player name at the start of each Connect 4 session.
	 * Filters out empty inputs and only whitespace inputs.
	 * If input is invalid, prompts for input until valid input given.
	 * Trims whitespace before and after the valid name.
	 */
	public static String getPlayerName(Console con, int intPlayerNum) {
		String strName = "";
		while (strName.trim().isEmpty()) {
            con.print(" Player " + intPlayerNum + ", enter your name: ");
            strName = con.readLine().trim();
            if (strName.isEmpty()) {
                con.println("    [INVALID] Name cannot be empty.");
				con.println();
            }
        }
        
        return strName;
	}
	
	
	/* 
	 * newScreen method: 
	 * Used to clear the text and images of the screen after a 100mm pause.
	 */
	public static void newScreen(Console con) {
		con.sleep(100);
		con.setBackgroundColor(Color.BLACK); // clears image
		con.clear(); // clears text
	}
}
