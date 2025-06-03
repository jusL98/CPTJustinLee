import arc.*;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.Font;


public class Main {
	public static void main(String[] args){
		Console con = new Console("Connect 4", 1280, 720); // https://staugustinechs.ca/arc/arc/Console.html
		
		// TODO: create start screen with play button and animation??
		
		
		
		// TODO: create a quit button
		// TODO: change Connect4Logo to not include name actually
		BufferedImage imgLogo = con.loadImage("Connect4Logo.png"); // 500 x 150
		con.drawImage(imgLogo, 1280/2 - 250, 0);
		
		
		
		// TODO: mabe make the banner slightly smaller in width
		BufferedImage imgMainMenuBanner = con.loadImage("MainMenuBanner.jpg"); // 350 x 680
		con.drawImage(imgMainMenuBanner, 20, 20);
		con.drawImage(imgMainMenuBanner, 1280-350-20, 20);

		con.println("\n\n\n\n\n");

		con.println("                                ===========================================");
		con.println("                                            Welcome to Connect 4!          ");
		con.println("                                ===========================================");
		
		con.println();
 
		int intSelection;
		con.println("                                                  MAIN MENU                ");
		con.println("                                -------------------------------------------");
		con.println("                                        [1] - Play Game                    ");
		con.println("                                        [2] - View Leaderboard             ");
		con.println("                                        [3] - Load Theme                   ");
		con.println("                                        [4] - Create Theme                 ");
		con.println();
		intSelection = getValidInput(con,4, "                                ");
		
		// TODO: find a way to clear the above if invalid input given...
		
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
		
		// Draw the top white bar
		con.setDrawColor(Color.WHITE);
		con.fillRect(20,20,1240,60);
		
		// Set font and color for the text
		Font fntTest = con.loadFont("Hack-Regular.ttf", 20); // TODO: maybe change the font by uploading custom bold one
		con.setDrawFont(fntTest);
		
		con.setDrawColor(Color.BLACK);
		String strGameTitleText = "Connect 4";
		int strGameTitleTextWidth = con.getTextFontMetrics().stringWidth(strGameTitleText);
		con.drawString(strGameTitleText, 20 + (1280-20-20 - strGameTitleTextWidth) / 2, 20 + 15);		
		
		// Draw the blue Connect 4 board area
		con.setDrawColor(Color.BLUE);
		con.fillRect(1280-20-600,100,600,600);
		
		String strP1Name;
		String strP2Name;
		int intP1Wins = 0;
		int intP2Wins = 0;
		
		con.println("\n\n\n");
		
		strP1Name = getPlayerName(con, 1);
		con.println("\n ------------------------------------------- \n");
		strP2Name = getPlayerName(con, 2);  
		
		con.setDrawColor(Color.BLACK);
		String strP1Text = strP1Name + ": " +intP1Wins;
		int strP1TextWidth = con.getTextFontMetrics().stringWidth(strP1Text);
		con.drawString(strP1Text, 20 + 20, 20 + 15);
		
		con.setDrawColor(Color.BLACK);
		String strP2Text = strP2Name + ": " +intP2Wins;
		int strP2TextWidth = con.getTextFontMetrics().stringWidth(strP2Text);
		con.drawString(strP2Text, 1280-20-strP2TextWidth-20, 20 + 15);
		
		con.repaint();
	}
	
	
	/*
	 * viewLeaederboardScreen method:
	 * Activated when "[2] - View Leaderboard" is selected in the main menu.
	 * Displays the top 3 players with most wins within a session.
	 * Displays the next 7 players of the top 10.
	 */
	public static void viewLeaderboardScreen(Console con){
		newScreen(con);
		String strLeaderboard[][] = DataManager.getLeaderboard();
		int intNumEntries = strLeaderboard.length;
		
		con.println();
		con.println("                                                LEADERBOARD                ");
		con.println("                                -------------------------------------------");
		
		if(intNumEntries == 0){
			con.println("                                " + "   You're the first two players ever.");
			con.println("                                " + "   No top players yet... Good luck!");
		}else{
			con.print("                                ");
			if(intNumEntries >= 10){
				con.println("Try to beat these top 10 Connect 4 masters!");
			}else{
				con.println("Try to beat these Connect 4 masters!");
			}
			con.println();
			
			for(int intCount = 0; intCount < intNumEntries; intCount++){
				if(intCount == 0){
					con.println("                                " + "   GOLD:   " + strLeaderboard[intCount][0] + ", " + strLeaderboard[intCount][1]);
				}else if(intCount == 1){
					con.println("                                " + "   SILVER: " + strLeaderboard[intCount][0] + ", " + strLeaderboard[intCount][1]);
				}else if(intCount == 2){
					con.println("                                " + "   BRONZE: " + strLeaderboard[intCount][0] + ", " + strLeaderboard[intCount][1]);
					con.println();
				}else if(intCount == 9){
					con.println("                                " + "       " + (intCount+1) + ". " + strLeaderboard[intCount][0] + ", " + strLeaderboard[intCount][1]);
				}
				else{
					con.println("                                " + "        " + (intCount+1) + ". " + strLeaderboard[intCount][0] + ", " + strLeaderboard[intCount][1]);
				}
			}
			
			if(intNumEntries >= 10){
				con.println("\n                                Players not in top 10 aren't shown.");
				con.println("                                Can you earn a spot?? Good luck!");
			}
		}
	}
	
	// TODO: reformat wins display in leaderboard
	
	
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
	public static int getValidInput(Console con, int intMax, String strOffset) {
		int intInput;
		while(true) {
			con.print(strOffset + "Your Selection: ");
			intInput = con.readInt();
			
			if(intInput >= 1 && intInput <= intMax){
				break;
			}else{
				con.println(strOffset + "   [INVALID] Enter a number #1-" + intMax + ".\n");
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
