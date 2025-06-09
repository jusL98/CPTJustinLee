import arc.*;

public class DataManager{
	/*
	 * getLeaderboard method:
	 * Returns a sorted leaderboard 2D array of {name, wins} based on most to least wins.
	 * Only returns top 10 scores.
	 * If no entries in leaderboard exist, array will be empty.
	 */
	public static String[][] getLeaderboard(){
		TextInputFile leaderboardFile = new TextInputFile("data/leaderboard.txt");
		
		// Counts number of entries in leaderboard data file
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
		
		// Creates and fills leaderboard array with values from data file
		leaderboardFile = new TextInputFile("data/leaderboard.txt");
		String strEntries[][] = new String[intNumEntries][2];
		int intCount;
		for(intCount = 0; intCount < intNumEntries; intCount++){
			strEntries[intCount][0] = leaderboardFile.readLine();
			strEntries[intCount][1] = leaderboardFile.readLine();
		}
		leaderboardFile.close();
		
		// Sorts leaderboard array in descending order (highest to least wins)
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
		
		// Returns only the top 10 scores of the leaderboard by creating a new array
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
	public static String[] getTheme(String strSelectedTheme){
		TextInputFile themesFile = new TextInputFile("data/themes.txt");
		String strThemeName = "";
		String strP1Color = "";
		String strP2Color = "";
		String strBoardColor = "";
		String strBoardTitle = "";
		boolean boolKeepSearching = true;
		
		// Locates theme that matches strSelectedTheme parameter and fills all values
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
		
		// If strSelectedTheme does not exist, sets all values to defaults
		if(boolKeepSearching == true){
			strThemeName = "INVALID";
			strP1Color = "0, 0, 0";
			strP2Color = "0, 0, 0";
			strBoardColor = "0, 0, 0";
			strBoardTitle = "INVALID";
		}
		
		// Creates and fills array with values of selected theme
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
	public static int getNumThemes(){
		TextInputFile themesFile = new TextInputFile("data/themes.txt");
		String strThemeName = "";
		String strP1Color = "";
		String strP2Color = "";
		String strBoardColor = "";
		String strBoardTitle = "";
		int intCount = 0;
		
		// Increments count by 1 after reading a theme
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
	 * Returns an array of all existing theme names from "themes.txt".
	 * Used for checking duplicate theme names when creating new themes.
	 */
	public static String[] getAllThemeNames(){
		TextInputFile themesFile = new TextInputFile("data/themes.txt");
		String strThemeName = "";
		String strP1Color = "";
		String strP2Color = "";
		String strBoardColor = "";
		String strBoardTitle = "";
		String strThemeNames[];
		
		// Creates an array with a max size of 15 (first 15 themes) or less (if there are less than 15 themes)
		int intNumThemes = getNumThemes();
		if(intNumThemes < 15){
			strThemeNames = new String[intNumThemes];
		}else{
			strThemeNames = new String[15]; // Max 15 themes
		}

		// Fills the array with only theme names
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
	public static String getLastTheme(){
		TextInputFile lastThemeFile = new TextInputFile("data/lasttheme.txt");
		String strLastTheme = "";
		
		// Gets last theme from lasttheme data file
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
	public static void setLastTheme(String strThemeName){
		TextOutputFile themesFile = new TextOutputFile("data/lasttheme.txt");
		
		// Writes the last theme set into lasttheme data file
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
	public static void createNewTheme(String strThemeName, String strP1Color, String strP2Color, String strBoardColor, String strBoardTitle){
		TextOutputFile themesFile = new TextOutputFile("data/themes.txt", true);
		
		// Adds new theme data into themes data file
		themesFile.println(strThemeName.strip());
		themesFile.println(strP1Color.strip());
		themesFile.println(strP2Color.strip());
		themesFile.println(strBoardColor.strip());
		themesFile.println(strBoardTitle.strip());
		themesFile.close();
		
		System.out.println("APPENDED NEW THEME TO THEMES.TXT"); // CONFIRMATION
		System.out.println("TEST: New Theme Data -> " + strThemeName.strip() + "  " + strP1Color.strip() + "  " + strP2Color.strip() + "  " + strBoardColor.strip() + "  " + strBoardTitle.strip()); // TEST
		System.out.println();
	}
	
	/*
	 * deleteTheme method:
	 * Deletes a theme from "themes.txt" based on the theme number (NOT INDEX... THEME # starts at 1).
	 */
	public static void deleteTheme(int intThemeNum){
		TextInputFile themesFile = new TextInputFile("data/themes.txt");
	    TextOutputFile tempThemesFile = new TextOutputFile("data/tempthemes.txt");
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
	    TextInputFile tempThemesFile2 = new TextInputFile("data/tempthemes.txt");
		TextOutputFile themesFile2 = new TextOutputFile("data/themes.txt");
		
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
	public static void main(String[] args){
		String strTestGetTheme[] = getTheme("candy");
		
		String strTestLeaderboard[][] = getLeaderboard();
		
		setLastTheme("Classic");
		
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
