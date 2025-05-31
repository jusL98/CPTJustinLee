import arc.*;

public class DataManager {
	/*
	 * getTheme method - Returns theme data in an array from "themes.txt" based on the provided theme name.
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
			strP1Color = "INVALID";
			strP2Color = "INVALID";
			strBoardColor = "INVALID";
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
		
		return strTheme;
	}
	
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
		
		// TBD - only return top 10 scores
		
		return strEntries;
	}
	
	public static String getLastTheme() {
		// Get last theme from lasttheme data file
		TextInputFile lastThemeFile = new TextInputFile("lasttheme.txt");
		String strLastTheme = "";
		strLastTheme = lastThemeFile.readLine(); // only 1 line should exist in the file at any given time
		lastThemeFile.close();
			
		return strLastTheme;
	}

	public static void main(String[] args) {
		String test[] = getTheme("christamas");
		for(String tes : test){
			System.out.println(tes);
		}
		
		String strLBEntries[][] = getLeaderboard();
		
		for(int intCount = 0; intCount < strLBEntries.length; intCount++){
			System.out.println(strLBEntries[intCount][0] + ", " + strLBEntries[intCount][1]);
		}
		
		System.out.println(getLastTheme());
	}
}
