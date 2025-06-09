import arc.*;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.Font;

public class Connect4Board{
	public static void drawOnScreenInformation(Console con, String strBoardTitle){
		// Draws rectangular bar
		con.setDrawColor(Color.WHITE);
		con.fillRect(20,20,1240,60);
		
		// Draws board title
		Font boardTitleFont = con.loadFont("assets/Roboto-Black.ttf", 25);
		con.setDrawFont(boardTitleFont);
		con.setDrawColor(Color.BLACK);
		int strBoardTitleWidth = con.getTextFontMetrics().stringWidth(strBoardTitle);
		con.drawString(strBoardTitle, 20 + (1240 - strBoardTitleWidth) / 2, 20 + 10);
		
		con.repaint();
	}
	
	public static void drawOnScreenInformation(Console con, String strBoardTitle, String strP1Name, int intP1Wins){
		// Draws rectangular bar
		con.setDrawColor(Color.WHITE);
		con.fillRect(20,20,1240,60);
		
		// Draws board title
		Font boardTitleFont = con.loadFont("assets/Roboto-Black.ttf", 25);
		con.setDrawFont(boardTitleFont);
		con.setDrawColor(Color.BLACK);
		int strBoardTitleWidth = con.getTextFontMetrics().stringWidth(strBoardTitle);
		con.drawString(strBoardTitle, 20 + (1240 - strBoardTitleWidth) / 2, 20 + 10);
		
		// Draws player 1 name and wins
		Font player1TextFont = con.loadFont("assets/Roboto-Medium.ttf", 20);
		con.setDrawFont(player1TextFont);
		con.setDrawColor(Color.BLACK);
		String strP1Text = strP1Name + ": " +intP1Wins;
		int strP1TextWidth = con.getTextFontMetrics().stringWidth(strP1Text);
		con.drawString(strP1Text, 20 + 20, 20 + 15);
		
		con.repaint();
	}
	
	public static void drawOnScreenInformation(Console con, String strBoardTitle, String strP1Name, int intP1Wins, String strP2Name, int intP2Wins){
		// Draws rectangular bar
		con.setDrawColor(Color.WHITE);
		con.fillRect(20,20,1240,60);
		
		// Draws board title
		Font boardTitleFont = con.loadFont("assets/Roboto-Black.ttf", 25);
		con.setDrawFont(boardTitleFont);
		con.setDrawColor(Color.BLACK);
		int strBoardTitleWidth = con.getTextFontMetrics().stringWidth(strBoardTitle);
		con.drawString(strBoardTitle, 20 + (1240 - strBoardTitleWidth) / 2, 20 + 10);
		
		// Draws player 1 name and wins
		Font player1TextFont = con.loadFont("assets/Roboto-Medium.ttf", 20);
		con.setDrawFont(player1TextFont);
		con.setDrawColor(Color.BLACK);
		String strP1Text = strP1Name + ": " +intP1Wins;
		int strP1TextWidth = con.getTextFontMetrics().stringWidth(strP1Text);
		con.drawString(strP1Text, 20 + 20, 20 + 15);
		
		// Draws player 2 name and wins
		Font player2TextFont = con.loadFont("assets/Roboto-Medium.ttf", 20);
		con.setDrawFont(player2TextFont);
		con.setDrawColor(Color.BLACK);
		String strP2Text = strP2Name + ": " +intP2Wins;
		int strP2TextWidth = con.getTextFontMetrics().stringWidth(strP2Text);
		con.drawString(strP2Text, 1280-20-strP2TextWidth-20, 20 + 15);
		
		con.repaint();
	}
	
	public static void drawBoard(Console con, Color clrBoardColor){
		// Draws board background
		int intBoardWidth = 694;
        int intBoardHeight = 596;
        int intBoardX = 1280 - 20 - intBoardWidth;
        int intBoardY = 100;
        con.setDrawColor(clrBoardColor);
        con.fillRect(intBoardX, intBoardY, intBoardWidth, intBoardHeight);
        
        // Draws board circles
        int intCircleDiameter = 90;
        int intPadding = 8;
        int intVerticalPadding = 8;
        con.setDrawColor(Color.WHITE);
        for (int intR = 0; intR < 6; intR++) {
            for (int intC = 0; intC < 7; intC++) {
                int intX = intBoardX + intPadding + (intC * (intCircleDiameter + intPadding));
                int intY = intBoardY + intVerticalPadding + (intR * (intCircleDiameter + intVerticalPadding));

                con.fillOval(intX, intY, intCircleDiameter, intCircleDiameter);
            }
        }

        con.repaint();
    }

	public static void main(String[] args){
		Console con = new Console("Test", 1280, 720);
		
		drawOnScreenInformation(con, "Test 4");
		con.sleep(500);
		drawOnScreenInformation(con, "Test 4", "Justin", 5);
		con.sleep(500);
		drawOnScreenInformation(con, "Test 4", "Justin", 5, "Joey", 25);
		con.sleep(500);
		
		drawBoard(con, new Color(255,244,244));
	}
}
