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
		
		
			
		System.out.println(strThemeName + strP1Color + strP2Color + strBoardColor + strBoardTitle);
		
		themes.close();
	}
	
	public static void main(String[] args){
		loadTheme("christmas");
	}
}
