import arc.*;

public class DataManager {
	
	public static void loadTheme(String strSelectedTheme) {
		TextInputFile themes = new TextInputFile("themes.txt");
		String strThemeName = "";
		String strP1Color = "";
		String strP2Color = "";
		String strBoardColor = "";
		String strBoardTitle = "";
		boolean boolKeepSearching = true;
		
		// Locate theme that matches strSelectedTheme parameter and fill all values
		while(themes.eof() != true && boolKeepSearching == true){
			strThemeName = themes.readLine();
			if(strThemeName.equalsIgnoreCase(strSelectedTheme)){
				boolKeepSearching = false;
			}
			strP1Color = themes.readLine();
			strP2Color = themes.readLine();
			strBoardColor = themes.readLine();
			strBoardTitle = themes.readLine();
		}
		
		System.out.println("TEST: Theme Properties -> " + strThemeName + "  " + strP1Color + "  " +  strP2Color + "  " +  strBoardColor + "  " + strBoardTitle); // TEST
		
		themes.close();
	}
	
	public static String[][] viewLeaderboard() {
		// Count number of entries in leaderboard data file
		TextInputFile leaderboard = new TextInputFile("leaderboard.txt");
		String strName;
		int intWins;
		int intNumEntries = 0;
		while(leaderboard.eof() != true){
			strName = leaderboard.readLine();
			intWins = leaderboard.readInt();
			
			intNumEntries++;
		}
		leaderboard.close();
		
		System.out.println("TEST: Num LB Entries -> " + intNumEntries); // TEST
		
		// Create and fill leaderboard array with values from data file
		leaderboard = new TextInputFile("leaderboard.txt");
		String strEntries[][] = new String[intNumEntries][2];
		int intCount;
		for(intCount = 0; intCount < intNumEntries; intCount++){
			strEntries[intCount][0] = leaderboard.readLine();
			strEntries[intCount][1] = leaderboard.readLine();
		}
		leaderboard.close();
		
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
	
	public static void main(String[] args) {
		loadTheme("christmas");
		String strLBEntries[][] =  viewLeaderboard();
		
		for(int intCount = 0; intCount < strLBEntries.length; intCount++){
			System.out.println(strLBEntries[intCount][0] + ", " + strLBEntries[intCount][1]);
		}
	}
}
