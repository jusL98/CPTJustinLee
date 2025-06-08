import arc.*;

public class DataManager {
	/*
	 * getLeaderboard method:
	 * Returns a sorted leaderboard 2D array of {player, name} based on most to least wins.
	 * Only returns top 10 scores.
	 * If no entries in leaderboard exist, array will be empty.
	 */
	public static String[][] getLeaderboard() {
		// Count number of entries in leaderboard data file
		TextInputFile leaderboardFile = new TextInputFile("leaderboard.txt");
		String strName;
		int intWins;
		int intNumEntries = 0;
		while(leaderboardFile.eof() != true){
			strName = leaderboardFile.readLine();
			intWins = leaderboardFile.readInt();
			
			intNumEntries++;
		}
		leaderboardFile.close();
		
		System.out.println("TEST: Num LB Entries -> " + intNumEntries); // TEST
		System.out.println();
		
		// Create and fill leaderboard array with values from data file
		leaderboardFile = new TextInputFile("leaderboard.txt");
		String strEntries[][] = new String[intNumEntries][2];
		int intCount;
		for(intCount = 0; intCount < intNumEntries; intCount++){
			strEntries[intCount][0] = leaderboardFile.readLine();
			strEntries[intCount][1] = leaderboardFile.readLine();
		}
		leaderboardFile.close();
		
		// Sort leaderboard array in descending order (highest to least wins)
		int intCount2;
		String strNameTemp;
		String strWinsTemp;
		for(intCount = 0; intCount < strEntries.length - 1; intCount++){
			for(intCount2 = 0; intCount2 < strEntries.length - 1; intCount2++){
				if(Integer.parseInt(strEntries[intCount2][1]) < Integer.parseInt(strEntries[intCount2+1][1])){
					strNameTemp = strEntries[intCount2][0];
					strEntries[intCount2][0] = strEntries[intCount2+1][0];
					strEntries[intCount2+1][0] = strNameTemp;
					
					strWinsTemp = strEntries[intCount2][1];
					strEntries[intCount2][1] = strEntries[intCount2+1][1];
					strEntries[intCount2+1][1] = strWinsTemp;
				}
			}
		}
		
		// Return only the top 10 scores of the leaderboard by creating a new array
		String strLB[][];
		if(strEntries.length > 10){
			strLB = new String[10][2];
			for(intCount = 0; intCount < 10; intCount++){
				strLB[intCount][0] = strEntries[intCount][0];
				strLB[intCount][1] = strEntries[intCount][1];
			}
		}else{
			strLB = strEntries;
		}
		
		return strLB;
	}
	
	
	/*
	 * getTheme method:
	 * Returns theme data in an array from "themes.txt" based on the provided theme name.
	 * If theme name not located, returns "INVALID" string values in the array.
	 */
	public static String[] getTheme(String strSelectedTheme) {
		// Locate theme that matches strSelectedTheme parameter and fill all values
		TextInputFile themesFile = new TextInputFile("themes.txt");
		String strThemeName = "";
		String strP1Color = "";
		String strP2Color = "";
		String strBoardColor = "";
		String strBoardTitle = "";
		boolean boolKeepSearching = true;
		while(themesFile.eof() != true && boolKeepSearching == true){
			strThemeName = themesFile.readLine();
			if(strThemeName.equalsIgnoreCase(strSelectedTheme)){
				boolKeepSearching = false;
			}
			strP1Color = themesFile.readLine();
			strP2Color = themesFile.readLine();
			strBoardColor = themesFile.readLine();
			strBoardTitle = themesFile.readLine();
		}
		themesFile.close();
		
		// If strSelectedTheme does not exist, set all values to "INVALID"
		if(boolKeepSearching == true){
			strThemeName = "INVALID";
			strP1Color = "0, 0, 0";
			strP2Color = "0, 0, 0";
			strBoardColor = "0, 0, 0";
			strBoardTitle = "INVALID";
		}
		
		// Create and fill array with values of selected theme
		String strTheme[] = new String[5];
		strTheme[0] = strThemeName;
		strTheme[1] = strP1Color;
		strTheme[2] = strP2Color;
		strTheme[3] = strBoardColor;
		strTheme[4] = strBoardTitle;
		
		System.out.println("TEST: Theme Properties -> " + strThemeName + "  " + strP1Color + "  " +  strP2Color + "  " +  strBoardColor + "  " + strBoardTitle); // TEST
		System.out.println();
		
		return strTheme;
	}
	
	
	/*
	 * getNumThemes method:
	 * Returns the number of themes that exist in "themes.txt".
	 */
	public static int getNumThemes() {
		TextInputFile themesFile = new TextInputFile("themes.txt");
		String strThemeName = "";
		String strP1Color = "";
		String strP2Color = "";
		String strBoardColor = "";
		String strBoardTitle = "";
		int intCount = 0;
		
		while(themesFile.eof() != true){
			strThemeName = themesFile.readLine();
			strP1Color = themesFile.readLine();
			strP2Color = themesFile.readLine();
			strBoardColor = themesFile.readLine();
			strBoardTitle = themesFile.readLine();
			intCount++;
		}
		themesFile.close();
		
		System.out.println("TEST: Num Themes -> " + intCount); // TEST
		System.out.println();
		
		return intCount;
	}
	
	
	/*
	 * getAllThemeNames method:
	 * Returns an array of all existing theme names from the themes.txt file.
	 * Used for checking duplicate theme names when creating new themes.
	 */
	public static String[] getAllThemeNames() {
		TextInputFile themesFile = new TextInputFile("themes.txt");
		String strThemeName = "";
		String strP1Color = "";
		String strP2Color = "";
		String strBoardColor = "";
		String strBoardTitle = "";
		String strThemeNames[];
		
		int intNumThemes = getNumThemes();
		if(intNumThemes < 15){
			strThemeNames = new String[intNumThemes];
		}else{
			strThemeNames = new String[15]; // Max 15 themes
		}

		int intCount = 0;
		while(themesFile.eof() != true && intCount < strThemeNames.length) {
			strThemeName = themesFile.readLine();
			strThemeNames[intCount] = strThemeName;
			strP1Color = themesFile.readLine();
			strP2Color = themesFile.readLine();
			strBoardColor = themesFile.readLine();
			strBoardTitle = themesFile.readLine();
			
			intCount++;
		}
		themesFile.close();
		
		System.out.print("TEST: Themes Array -> ");
		for(intCount = 0; intCount < strThemeNames.length; intCount++){
			System.out.print(strThemeNames[intCount] + "  "); // TEST
		}
		System.out.println();
		System.out.println();
		
		return strThemeNames;
	}
	
	
	/*
	 * getLastTheme method:
	 * Returns the theme name of the last theme loaded in the last round/session from "lasttheme.txt". 
	 * Then, should use load theme to load the data of the last theme following this method call.
	 */
	public static String getLastTheme() {
		// Get last theme from lasttheme data file
		TextInputFile lastThemeFile = new TextInputFile("lasttheme.txt");
		String strLastTheme = "";
		strLastTheme = lastThemeFile.readLine(); // only 1 line should exist in the file at any given time
		lastThemeFile.close();
		
		System.out.println("TEST: Last Theme -> " + strLastTheme); // TEST
		System.out.println();
			
		return strLastTheme;
	}
	
	
	/*
	 * setLastTheme method:
	 * Writes the name of the last theme loaded to "lasttheme.txt".
	 */
	public static void setLastTheme(String strThemeName) {
		// Writes the last theme set into lasttheme data file
		TextOutputFile themesFile = new TextOutputFile("lasttheme.txt");
		themesFile.println(strThemeName);
		themesFile.close();
		
		System.out.println("SET LAST THEME IN LASTTHEME.TXT"); // CONFIRMATION
		System.out.println("TEST: Last Theme -> " + strThemeName); // TEST
		System.out.println();
	}

		
	/*
	 * createNewTheme method:
	 * Appends the new theme data entered in the parameters to "themes.txt".
	 * Removes leading and trailing spaces from any of the parameters.
	 */
	public static void createNewTheme(String strThemeName, String p1_colour, String p2_colour, String board_colour, String board_title) {
		// Adds new theme data into themes data file
		TextOutputFile themesFile = new TextOutputFile("themes.txt", true);
		themesFile.println(strThemeName.strip());
		themesFile.println(p1_colour.strip());
		themesFile.println(p2_colour.strip());
		themesFile.println(board_colour.strip());
		themesFile.println(board_title.strip());
		themesFile.close();
		
		System.out.println("APPENDED NEW THEME TO THEMES.TXT"); // CONFIRMATION
		System.out.println("TEST: New Theme Data -> " + strThemeName.strip() + "  " + p1_colour.strip() + "  " + p2_colour.strip() + "  " + board_colour.strip() + "  " + board_title.strip()); // TEST
		System.out.println();
	}
	
	/*
	 * deleteTheme method:
	 * Deletes a theme from "themes.txt" based on the theme number (NOT INDEX... THEME # starts at 1).
	 */
	public static void deleteTheme(int intThemeNum) {
		TextInputFile themesFile = new TextInputFile("themes.txt");
	    TextOutputFile tempThemesFile = new TextOutputFile("tempthemes.txt");
		String strThemeName = "";
		String strP1Color = "";
		String strP2Color = "";
		String strBoardColor = "";
		String strBoardTitle = "";
		
		int intCount = 1;
		int intNumThemes = getNumThemes();
		
		// Stores everything except the theme to be deleted into a temporary file
		for(intCount = 1; intCount <= intNumThemes; intCount++){
			strThemeName = themesFile.readLine();
			strP1Color = themesFile.readLine();
			strP2Color = themesFile.readLine();
			strBoardColor = themesFile.readLine();
			strBoardTitle = themesFile.readLine();
			
			if(intCount != intThemeNum){
				tempThemesFile.println(strThemeName);
				tempThemesFile.println(strP1Color);
				tempThemesFile.println(strP2Color);
				tempThemesFile.println(strBoardColor);
				tempThemesFile.println(strBoardTitle);
			}
		}
		themesFile.close();
		tempThemesFile.close();
		
		// Copies temp data file back into themes data file
	    TextInputFile tempThemesFile2 = new TextInputFile("tempthemes.txt");
		TextOutputFile themesFile2 = new TextOutputFile("themes.txt");
		
		while(tempThemesFile2.eof() != true){
			themesFile2.println(tempThemesFile2.readLine());
		}
		tempThemesFile2.close();
		themesFile2.close();
		
		System.out.println("THEME #" + intThemeNum + " DELETED"); // CONFIRMATION, intCount-1 for message because loop increments intCount once more for first time condition is not met
		System.out.println();
	}



	/*
	 * main method:
	 * Used for temporary testing only within this file.
	 */
	public static void main(String[] args) {
		String strTestGetTheme[] = getTheme("candy");
		
		String strTestLeaderboard[][] = getLeaderboard();
		
		setLastTheme("red");
		
		String strTestGetLastTheme = getLastTheme();
		
		int intNumThemes = getNumThemes();
		
		// deleteTheme(2);
		
		if(intNumThemes < 15){
			createNewTheme("candy", "0,0,0", "255,255,20", "8,8,8", "Sweet 4");
		}else{
			System.out.println("MAX (15) THEMES EXIST. TEST THEME NOT CREATED."); // CONFIRMATION
			System.out.println(); 
		}
	}
}
