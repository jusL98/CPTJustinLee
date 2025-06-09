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
        
        // Draws board slots
        int intSlotSize = 90;
        int intPadding = 8;
        int intVerticalPadding = 8;
        con.setDrawColor(Color.WHITE);
        for (int intR = 0; intR < 6; intR++) {
            for (int intC = 0; intC < 7; intC++) {
                int intX = intBoardX + intPadding + (intC * (intSlotSize + intPadding));
                int intY = intBoardY + intVerticalPadding + (intR * (intSlotSize + intVerticalPadding));

                con.fillOval(intX, intY, intSlotSize, intSlotSize);
            }
        }

        con.repaint();
    }

	/*
	 * intRow and intCol begin at 1 (index starts at 1). intRow's max is 6. intCol's max is 7. 
	*/
	public static void drawDisc(Console con, int intRow, int intCol, Color clrPlayerColor){
		int intOffset = 0;
		con.setDrawColor(clrPlayerColor);
		
		// Adjusts row and col input to account for logic (makes index start at 0)
		intRow = intRow - 1;
		intCol = intCol - 1;
		
		int intDiscX = (1280 - 20 - 694) + 8 + intCol * (90 + 8) + intOffset/2;
		int intDiscY = (100) + 8 + intRow * (90 + 8) + intOffset/2;
		int intDiscSize = 90 - intOffset;
		
		if((intRow < 0 || intRow > 5) || (intCol < 0 || intCol > 6)){ // handles disc trying to be placed outside board
			if((intRow < 0 || intRow > 5) && (intCol < 0 || intCol > 6)){ // handles row and col outside board
				System.out.println("ERROR: DISC (" + (intRow+1) + "," + (intCol+1) + ") PLACED OUTSIDE OF BOARD. BOTH ROW & COL INVALID"); // ERROR
				System.out.println();
			}else{
				if(intRow < 0 || intRow > 5){ // handles row outside board (valid is 1-6)
					System.out.println("ERROR: DISC (" + (intRow+1) + "," + (intCol+1) + ") PLACED OUTSIDE OF BOARD. ROW INVALID"); // ERROR
					System.out.println();
				}
				if(intCol < 0 || intCol > 6){ // handles col outside board (valid is 1-7)
					System.out.println("ERROR: DISC (" + (intRow+1) + "," + (intCol+1) + ") PLACED OUTSIDE OF BOARD. COL INVALID"); // ERROR
					System.out.println();
				}
			}	
		}else{ // continues to draw disc since its validated
			con.fillOval(intDiscX, intDiscY, intDiscSize, intDiscSize);
			
			System.out.println("DISC (" + (intRow+1) + "," + (intCol+1) + ") SUCCESSFULLY PLACED"); // CONFIRMATION
			System.out.println();
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
		
		drawBoard(con, new Color(255,204,24));
		
		Color clrP1Color = Color.RED;
		Color clrP2Color = Color.YELLOW;
		con.sleep(500);
		drawDisc(con, 1, 1, clrP1Color); // valid
		drawDisc(con, 2, 3, clrP2Color); // valid
		drawDisc(con, 6, 7, clrP1Color); // valid
		drawDisc(con, 7, 7, clrP1Color); // row invalid
		drawDisc(con, 6, 8, clrP1Color); // col invalid
		drawDisc(con, 7, 8, clrP1Color); // both invalid
		drawDisc(con, 0, 1, clrP1Color); // row invalid
		drawDisc(con, 1, 0, clrP1Color); // col invalid
		drawDisc(con, 0, 0, clrP1Color); // both invalid
	}
}
