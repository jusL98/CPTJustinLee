import arc.*;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.Font;

public class Main{
	public static void main(String[] args){
		Console con = new Console("Connect 4", 1280, 720); // https://staugustinechs.ca/arc/arc/Console.html
		
		// TODO: create start screen with play button and animation??
		// TODO: add in secret number (9898) into validInput method and put hints throughout the game of the secret number
		
		// TODO: create a quit button
		// TODO: change Connect4Logo to not include name actually
		BufferedImage imgLogo = con.loadImage("assets/Connect4Logo.png"); // 500 x 150
		con.drawImage(imgLogo, 1280/2 - 250, 0);
		
		displayBanners(con, "MainMenuBanner.jpg");

		// Main menu display
		String strMainMenuDisplay = 
									"\n\n\n\n\n\n\n" +
									"                                ===========================================\n" + 
									"                                            Welcome to Connect 4!          \n" +
									"                                ===========================================\n" +
									"\n" + 
 
									"                                                  MAIN MENU                \n" +
									"                                -------------------------------------------\n" +
									"                                        [1] - Play Game                    \n" +
									"                                        [2] - View Leaderboard             \n" +
									"                                        [3] - Load Theme                   \n" +
									"                                        [4] - Create Theme                 ";
		
		int intSelection;														
		intSelection = getValidMenuInput(con, 4, strMainMenuDisplay);
		con.println("                                Loading...");
		con.sleep(1000);
		
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
	public static void playGameScreen(Console con){
		newScreen(con);
		
		// PLAY GAME SCREEN SETUP -------------------------------------------------------------
		// Loads theme from lasttheme data file
		String strLastTheme = DataManager.getLastTheme();
		String strThemeData[] = DataManager.getTheme(strLastTheme);
		String strThemeName = strThemeData[0];
		Color clrP1Color = InputValidation.stringToColor(strThemeData[1]);
		Color clrP2Color = InputValidation.stringToColor(strThemeData[2]);
		Color clrBoardColor = InputValidation.stringToColor(strThemeData[3]);
		String strBoardTitle = strThemeData[4];
		System.out.println("LOADED THEME \"" + strThemeName + "\""); // CONFIRMATION
		System.out.println();
		
		// Draws initial on screen information with board title
		con.setDrawColor(Color.WHITE); // white bar
		con.fillRect(20,20,1240,60);
		Font boardTitleFont = con.loadFont("assets/Roboto-Black.ttf", 25); // board title
		con.setDrawFont(boardTitleFont);
		con.setDrawColor(Color.BLACK);
		int strBoardTitleWidth = con.getTextFontMetrics().stringWidth(strBoardTitle);
		con.drawString(strBoardTitle, 20 + (1240 - strBoardTitleWidth) / 2, 20 + 10);
		con.repaint();
		
		// Draws initial empty Connect 4 board
		Connect4Board.drawBoard(con, clrBoardColor, clrP1Color, clrP2Color);
		
		String strP1Name;
		String strP2Name;
		int intP1Wins = 0;
		int intP2Wins = 0;
		
		// Gets player names and draws on screen information with player names
		strP1Name = getValidPlayerName(con, 1); // player 1
		con.println("  Welcome " + strP1Name + "!");
		con.sleep(1000);
		con.clear();
		Font player1TextFont = con.loadFont("assets/Roboto-Medium.ttf", 20);
		con.setDrawFont(player1TextFont);
		con.setDrawColor(Color.BLACK);
		String strP1Text = strP1Name + ": " +intP1Wins;
		int strP1TextWidth = con.getTextFontMetrics().stringWidth(strP1Text);
		con.drawString(strP1Text, 20 + 20, 20 + 15);
		con.repaint();
		strP2Name = getValidPlayerName(con, 2); // player 2
		con.println("  Welcome " + strP2Name + "!");
		con.sleep(1000);
		con.clear();
		Font player2TextFont = con.loadFont("assets/Roboto-Medium.ttf", 20);
		con.setDrawFont(player2TextFont);
		con.setDrawColor(Color.BLACK);
		String strP2Text = strP2Name + ": " +intP2Wins;
		int strP2TextWidth = con.getTextFontMetrics().stringWidth(strP2Text);
		con.drawString(strP2Text, 1280-20-strP2TextWidth-20, 20 + 15);
		con.repaint();
		
		
		// CONNECT 4 GAME LOOP ----------------------------------------------------------------
		Connect4Board.initBoard();
		
		int intCurrentPlayer = 1; // player 1 starts
		int intPreviousPlayer = 2;
		
		while(true){
			Connect4Board.drawBoard(con, clrBoardColor, clrP1Color, clrP2Color);
			
			// Displays player turn
			if(intCurrentPlayer != intPreviousPlayer){
				con.println("\n\n\n");  // TODO: maybe display this as a rectangular box (color) with player name in it
				if(intCurrentPlayer == 1){
					con.println("  PLAYER " + intCurrentPlayer + " (" + strP1Name + ") TURN");
				}else if(intCurrentPlayer == 2){
					con.println("  PLAYER " + intCurrentPlayer + " (" + strP2Name + ") TURN");
				}
				con.println("      CLICK A COLUMN TO PLACE!");
				con.println();
			}
			
			// Gets column from mouse click
			int intCol = Connect4Board.getColumnClick(con);
			
			// Runs win checks and switches players
			if(Connect4Board.isValidColumn(intCol)){ // checks if column is valid
				if(Connect4Board.dropDisc(con, intCol, intCurrentPlayer) == true){ // checks if drop was successful
					// Checks for win
					if(Connect4Board.checkWin(intCurrentPlayer)){
						Connect4Board.drawBoard(con, clrBoardColor, clrP1Color, clrP2Color);
						con.println("PLAYER " + intCurrentPlayer + " WINS!");
						System.out.println("GAME OVER: PLAYER " + intCurrentPlayer + " WINS!");
						break; // ends game
					}
					// Checks for tie
					else if(Connect4Board.checkTie()){
						Connect4Board.drawBoard(con, clrBoardColor, clrP1Color, clrP2Color);
						con.println("IT'S A TIE!");
						System.out.println("GAME OVER: IT'S A TIE!");
						break; // ends game
					}
					// Continues game and switches player if no win or tie
					else{
						if(intCurrentPlayer == 1){
							intCurrentPlayer = 2;
							intPreviousPlayer = 1;
						}else if(intCurrentPlayer == 2){
							intCurrentPlayer = 1;
							intPreviousPlayer = 2;
						}
					}
					
					System.out.println(Connect4Board.getBoardState());
					
					con.sleep(100);
					con.clear();
				}
			}else{
				con.println("      COLUMN " + intCol + " IS FULL.");
				con.println("      SELECT ANOTHER COLUMN.");
				con.println();
				
				System.out.println("ERROR: COLUMN " + intCol + " IS FULL."); // ERROR
				System.out.println();
				
				intPreviousPlayer = intCurrentPlayer; // keeps same player
				con.sleep(100);

			}
		}
		
		
	}
	
	
	/*
	 * viewLeaderboardScreen method:
	 * Activated when "[2] - View Leaderboard" is selected in the main menu.
	 * Displays the top 3 players with most wins within a session.
	 * Displays the next 7 players of the top 10.
	 */
	public static void viewLeaderboardScreen(Console con){
		newScreen(con);
		displayBanners(con, "LeaderboardBanner.jpg");
		
		String strLeaderboard[][] = DataManager.getLeaderboard();
		int intNumEntries = strLeaderboard.length;
		
		System.out.println("DISPLAYING " + intNumEntries + " LEADERBOARD ENTRIES"); // CONFIRMATION
		
		// Leaderboard menu display
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
			
			for(int intCount = 1; intCount <= intNumEntries; intCount++){
				if(intCount == 1){ // print for #1
					con.println("                                " + "   GOLD:   " + strLeaderboard[intCount-1][0] + " | " + strLeaderboard[intCount-1][1] + " wins");
				}else if(intCount == 2){ // print for #2
					con.println("                                " + "   SILVER: " + strLeaderboard[intCount-1][0] + " | " + strLeaderboard[intCount-1][1] + " wins");
				}else if(intCount == 3){ // print for #3
					con.println("                                " + "   BRONZE: " + strLeaderboard[intCount-1][0] + " | " + strLeaderboard[intCount-1][1] + " wins");
					con.println();
				}else if(intCount == 10){ // print for #10 (formats 2 digits rather than 1 so alignment is correct)
					con.println("                                " + "       " + (intCount) + ". " + strLeaderboard[intCount-1][0] + " | " + strLeaderboard[intCount-1][1] + " wins");
				}
				else{ // prints for #4-9
					con.println("                                " + "        " + (intCount) + ". " + strLeaderboard[intCount-1][0] + " | " + strLeaderboard[intCount-1][1] + " wins");
				}
			}
			
			if(intNumEntries >= 10){
				con.println("\n                                Players not in top 10 aren't shown.");
				con.println("                                Can you earn a spot?? Good luck!");
			}
		}
	}
	
	
	/*
	 * loadThemeScreen method:
	 * Activated when "[3] - Load Theme" is selected in the main menu.
	 * Allows the user to choose a theme to load and apply to change the game color scheme.
	 */
	public static void loadThemeScreen(Console con){
		newScreen(con);
		displayBanners(con, "LoadThemeBanner.jpg");
		
		String strThemeNames[] = DataManager.getAllThemeNames();
		
		// Load Theme menu display
		String strLoadThemeMenu = 
									"\n" + 
									"                                                 LOAD THEME                \n" +
									"                                -------------------------------------------\n" +
									"                                Pick a theme to load to customize the game!" +
									"\n";
				
		int intNumThemes = strThemeNames.length;											
	
		if(intNumThemes == 0){
			con.println(strLoadThemeMenu);
			con.println("                                No themes currently exist. Create one!");
			return;
		}
		
		int intCount = 1;
		while(intCount <= intNumThemes){
			if(intCount <= 9){ // prints menu items #1-9 (1 digit)
				strLoadThemeMenu += ("\n                                " + "    " + intCount + ". " + strThemeNames[intCount-1]);
			}else{ // prints menu items #10-15 (formats 2 digits rather than 1 so alignment is correct)
				strLoadThemeMenu += ("\n                                " + "   " + intCount + ". " + strThemeNames[intCount-1]);
			}
			intCount++;
		}
				
		int intSelection;														
		intSelection = getValidMenuInput(con, intNumThemes, strLoadThemeMenu);
		
		// Sets last theme to theme just loaded
		DataManager.setLastTheme(strThemeNames[intSelection-1]);
		
		con.println("                                Loading...");
		con.sleep(1000);
		con.println();
		con.println("                                THEME \"" + strThemeNames[intSelection-1] + "\" SUCCESSFULLY LOADED");
		System.out.println("THEME \"" + strThemeNames[intSelection-1] + "\" SUCCESSFULLY LOADED"); // CONFIRMATION
		System.out.println();
	}
	
	
	/*
	 * createThemeScreen method:
	 * Activated when "[4] - Create Theme" is selected in the main menu.
	 * Allows the user to create a custom theme to add to the load theme options.
	 */
	public static void createThemeScreen(Console con){
		newScreen(con);
		displayBanners(con, "CreateThemeBanner.jpg");
		
		String strCreateThemeMenu;
		
		int intNumThemes = DataManager.getNumThemes();
		String strThemeNames[];
		
		// Checks if 15 themes and handle deletion if needed
		while(intNumThemes >= 15){
			strThemeNames = DataManager.getAllThemeNames();
			
			// Create Theme menu display (DELETION VERSION)
			strCreateThemeMenu = 
									"\n" + 
									"                                                CREATE THEME               \n" +
									"                                -------------------------------------------\n" +
									"                                   Create and save a custom colour theme!" +
									"\n";
			strCreateThemeMenu += ("\n                                Max (15) themes exist. Select one to delete.");
			strCreateThemeMenu += ("\n");
			
			int intCount = 1;
			while(intCount <= intNumThemes && intCount <= 15){
				if(intCount <= 9){ // prints menu items #1-9 (1 digit)
					strCreateThemeMenu += ("\n                                " + "    " + intCount + ". " + strThemeNames[intCount-1]);
				}else{ // prints menu items #10-15 (formats 2 digits rather than 1 so alignment is correct)
					strCreateThemeMenu += ("\n                                " + "   " + intCount + ". " + strThemeNames[intCount-1]);
				}
				intCount++;
			}
					
			int intSelection;														
			intSelection = getValidMenuInput(con, 15, strCreateThemeMenu);
			
			DataManager.deleteTheme(intSelection);
			
			intNumThemes -= 1;
			
			con.println("                                Deleting Theme...");
			con.sleep(1000);
			con.println();
			con.println("                                THEME \"" + strThemeNames[intSelection-1] + "\" SUCCESSFULLY DELETED");
			System.out.println("THEME \"" + strThemeNames[intSelection-1] + "\" SUCCESSFULLY DELETED"); // CONFIRMATION
			System.out.println();
			
			con.sleep(500);
			con.clear();
		}
		
		
		// Creates new theme now that there are 14 or less themes existing
		String strThemeName = "";
		String strP1Color = "";
		String strP2Color = "";
		String strBoardColor = "";
		String strBoardTitle = "";
		
		strCreateThemeMenu = 
									"\n" + 
									"                                                CREATE THEME               \n" +
									"                                -------------------------------------------\n" +
									"                                   Create and save a custom colour theme!" +
									"\n";
		
		// Gets desired theme name
		strThemeNames = DataManager.getAllThemeNames();
		String strDesiredThemeName = "";
		boolean boolIsDuplicate = false;
		while(strDesiredThemeName.trim().isEmpty() || boolIsDuplicate == true){
			con.println(strCreateThemeMenu);
			con.println("                                THEME NAME");
            con.print("                                Enter a theme name: ");
            strDesiredThemeName = con.readLine().trim();
            
            if(strDesiredThemeName.isEmpty()){
                con.println("                                   [INVALID] Theme name cannot be empty.");
                con.sleep(500);
                con.clear();
            }
            
			boolIsDuplicate = false;
			int intCount;
			for(intCount = 0; intCount < strThemeNames.length; intCount++){
				if(strThemeNames[intCount] != null && strThemeNames[intCount].equals(strDesiredThemeName)){
					boolIsDuplicate = true;
					break;
				}
			}
            if(boolIsDuplicate == true){
                con.println("                                   [INVALID] Theme name already exists.");
                con.sleep(500);
                con.clear();
			}
        }
        strThemeName = strDesiredThemeName;
        con.println();
        con.println("                                RECORDED THEME NAME");
        System.out.println("DESIRED THEME NAME \"" + strThemeName + "\" VERIFIED"); // CONFIRMATION
        System.out.println();
        con.sleep(1000);
        con.clear();
		
		// Gets desired player 1 color
		String strDesiredP1Color = getValidRGB(con, "Enter Player 1 RGB Color: ", "PLAYER 1 COLOR", strCreateThemeMenu);
		strP1Color = strDesiredP1Color;
		
		// Gets desired player 2 color
		String strDesiredP2Color = getValidRGB(con, "Enter Player 2 RGB Color: ", "PLAYER 2 COLOR", strCreateThemeMenu);
		strP2Color = strDesiredP2Color;
		
		// Gets desired board color
		String strDesiredBoardColor = getValidRGB(con, "Enter Board RGB Color: ", "BOARD COLOR", strCreateThemeMenu);
		strBoardColor = strDesiredBoardColor;
		
		// Gets desired board title
		String strDesiredBoardTitle = "";
		while(strDesiredBoardTitle.trim().isEmpty()){
			con.println(strCreateThemeMenu);
			con.println("                                BOARD TITLE");
            con.print("                                Enter a board title: ");
            strDesiredBoardTitle = con.readLine().trim();
            
            if(strDesiredBoardTitle.isEmpty()){
                con.println("                                   [INVALID] Board title cannot be empty.");
                con.sleep(500);
                con.clear();
            }
        }
        strBoardTitle = strDesiredBoardTitle;
        con.println();
        con.println("                                RECORDED BOARD TITLE");
        System.out.println("DESIRED BOARD TITLE \"" + strBoardTitle + "\" VERIFIED"); // CONFIRMATION
        System.out.println();
        con.sleep(1000);
        con.clear();
        
        
        // Appends new theme to themes data file
		DataManager.createNewTheme(strThemeName, strP1Color, strP2Color, strBoardColor, strBoardTitle);
		DataManager.setLastTheme(strThemeName);
		
		con.println(strCreateThemeMenu);
        con.println("                                THEME \"" + strThemeName + "\" SUCCESSFULLY CREATED");
        con.println("                                THEME \"" + strThemeName + "\" LOADED");
        
        System.out.println("THEME \"" + strThemeName + "\" SUCCESSFULLY CREATED AND LOADED"); // CONFIRMATION
        System.out.println();
	}
	
	
	
	// ************************************************************************
	// HELPER/UTILITY METHODS - reusable components for common tasks
	// ************************************************************************
		
	/* 
	 * newScreen method: 
	 * Used to clear the text and images of the screen after a 100mm pause.
	 */
	public static void newScreen(Console con){
		con.sleep(100);
		con.setBackgroundColor(Color.BLACK); // clears image
		con.clear(); // clears text
	}
	
	
	/* 
	 * displayBanner method: 
	 * Used to display the decorative screen banner images.
	 */
	public static void displayBanners(Console con, String strImgFile){
		BufferedImage imgBanner = con.loadImage("assets/" + strImgFile); // 300 x 680
		con.drawImage(imgBanner, 20, 20); // left banner
		con.drawImage(imgBanner, 1280-300-20, 20); // right banner
		con.repaint();
	}
	
	
	/* 
	 * getValidMenuInput method: 
	 * Returns a valid numeric input from the user.
	 * Filters out and handles numbers over or under a range of 1-intMax.
	 * If input is invalid, prompts for input until valid input given.
	 */
	public static int getValidMenuInput(Console con, int intMax, String strMenu){
		String strInput;
		int intInput;
		while(true){
			con.println(strMenu);
			con.println();
			
			con.print("                                Your Selection: ");
			strInput = con.readLine();
			
			if(!InputValidation.isValidInteger(strInput)){ // checks if input is an integer
				con.println("                                   [INVALID] Please enter a valid number.");
				con.sleep(500);
				con.clear();
			}else{
				intInput = InputValidation.stringToInteger(strInput);
				
				if(InputValidation.isInRange(intInput, 1, intMax)){ // checks if integer is within range
					break;
				}else{
					con.println("                                   [INVALID] Enter a number #1-" + intMax + ".\n");
					con.sleep(500);
					con.clear();
				}
			}
		}
		
		return intInput;
	}
	
	
	/*
	 * getValidPlayerName method:
	 * Returns a valid String input from the user for a valid name.
	 * Used for gathering player name at the start of each Connect 4 session.
	 * Filters out empty inputs and only whitespace inputs.
	 * If input is invalid, prompts for input until valid input given.
	 * Trims whitespace before and after the valid name.
	 */
	public static String getValidPlayerName(Console con, int intPlayerNum){
		String strName = "";
		while(strName.trim().isEmpty()){
			con.println("\n\n\n");
			con.println("  PLAYER " + intPlayerNum + ":");
			con.println("  -----------------------------------\n");
            con.print("  Player " + intPlayerNum + ", enter your name: ");
            strName = con.readLine().trim();
            if(strName.isEmpty()){
                con.println("    [INVALID] Name cannot be empty.");
                con.sleep(500);
                con.clear();
            }
        }
        
        return strName;
	}
	
	
	/*
	 * getRGB method:
	 * Used to get valid String input from the user for a valid RGB color.
	 * Validates that input is in format "R,G,B" where R,G,B are integers 0-255.
	 */
	public static String getValidRGB(Console con, String strPrompt, String strHeading, String strMenu){
		String strInput = "";
		boolean boolIsValidRGB = false;
		
		while(!boolIsValidRGB){
			con.println(strMenu);
			con.println("                                "+ strHeading);
			con.print("                                " + strPrompt);
			strInput = con.readLine().trim();
			
			// Checks for 2 commas
			int intNumCommas = 0;
			int intCount;
			for(intCount = 0; intCount < strInput.length(); intCount++){
				if(strInput.charAt(intCount) == ','){
					intNumCommas++;
				}
			}
			if(intNumCommas != 2){
				con.println("                                   [INVALID] RGB format is: (ex. 255,0,128)");
				con.sleep(500);
				con.clear();
				continue;
			}
			
			// Seperates and checks for 3 RGB values
			String strRGBParts[] = strInput.split(",");
			if(strRGBParts.length != 3){
				con.println("                                   [INVALID] RGB format is: (ex. 255,0,128)");
				con.sleep(500);
				con.clear();
				continue;
			}
			
			// Validates each RGB value
			boolean boolAllIsValid = true;
			for(intCount = 0; intCount < 3; intCount++){
				if(!InputValidation.isValidInteger(strRGBParts[intCount])){ // checks if value is an integer
					boolAllIsValid = false;
					break;
				}    
				int intValue = InputValidation.stringToInteger(strRGBParts[intCount]);
				if(!InputValidation.isInRange(intValue, 0, 255)){ // checks if integer is within range
					boolAllIsValid = false;
					break;
				}
			}
			if(!boolAllIsValid){
				con.println("                                   [INVALID] RGB values must be between 0-255");
				con.sleep(500);
				con.clear();
			}else{
				boolIsValidRGB = true;
			}
		}
		
		con.println();
		con.println("                                RECORDED " + strHeading);
        System.out.println("DESIRED COLOR \"" + strInput + "\" VERIFIED"); // CONFIRMATION
        System.out.println();
        con.sleep(1000);
        con.clear();
		
		return strInput;
	}
}
