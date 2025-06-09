import arc.*;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.Font;

public class Connect4Board{
	static int intBoard[][] = new int[6][7];
	
	public static void initBoard(){
		int intR;
		int intC;
        for (intR = 0; intR < 6; intR++){
            for (intC = 0; intC < 7; intC++){
                intBoard[intR][intC] = 0; // set all values to 0
            }
        }
	}
	
	public static void drawBoard(Console con, Color clrBoardColor, Color clrP1Color, Color clrP2Color){
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
        int intR;
        int intC;
        for(intR = 0; intR < 6; intR++){
            for(intC = 0; intC < 7; intC++){
                int intX = intBoardX + intPadding + (intC * (intSlotSize + intPadding));
                int intY = intBoardY + intPadding + (intR * (intSlotSize + intPadding));
                
                // Draws empty slot if slot is empty  ([0])
				con.setDrawColor(Color.WHITE);
                con.fillOval(intX, intY, intSlotSize, intSlotSize);
                
                // Draws visual disc if slot is filled  ([1] or [2])
                if(intBoard[intR][intC] == 1){ // player 1  ([1])
					drawDisc(con, intR+1, intC+1, clrP1Color);
                }else if(intBoard[intR][intC] == 2){ // player 2  ([2])
					drawDisc(con, intR+1, intC+1, clrP2Color);
                }
            }
        }

        con.repaint();
    }
    
    public static boolean dropDisc(Console con, int intCol, int intPlayer){
		// Adjusts col input to account for logic (makes index start at 0)
		intCol = intCol - 1;
		
		/* VALIDATION BELOW NO LONGER NEEDED AS THEY ARE CHECKED ELSWHERE ALREADY
		// Validates input
		if(intCol < 0 || intCol > 6){ // column check
			System.out.println("ERROR: COLUMN (" + (intCol+1) + ") INVALID (1-7 only)"); // ERROR
			System.out.println();
			return false;
		}
		if(intPlayer != 1 && intPlayer != 2){ // player check
			System.out.println("ERROR: PLAYER " + intPlayer + " INVALID (1 or 2 only)"); // ERROR
			System.out.println();
			return false;
		}
		*/
		
		// Finds lowest empty slot (row) in the column
		int intRow;
		for(intRow = 5; intRow >= 0; intRow--){
			if(intBoard[intRow][intCol] == 0){
				intBoard[intRow][intCol] = intPlayer; // places if empty
				System.out.println("DISC (" + (intRow+1) + "," + (intCol+1) + ") SUCCESSFULLY PLACED BY PLAYER " + intPlayer); // CONFIRMATION
				System.out.println();
				return true;
			}
		}
		
		// Column full
		System.out.println("ERROR: COLUMN (" + (intCol+1) + ") FULL"); // ERROR
		System.out.println();
		return false;
	}


	// ************************************************************************
	// BOARD HELPER/UTILITY METHODS - to support other methods in Connect 4 board
	// ************************************************************************

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
		
		con.fillOval(intDiscX, intDiscY, intDiscSize, intDiscSize);
		
		/* VALIDATION BELOW NO LONGER NEEDED AS METHOD PURPOSE CHANGED TO HELPER METHOD
		 * if((intRow < 0 || intRow > 5) || (intCol < 0 || intCol > 6)){ // handles disc trying to be placed outside board
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
		*/
		
		con.repaint();
	}

	public static int getColumnClick(Console con){
		int intBoardWidth = 694;
		int intBoardHeight = 596;
		int intBoardX = 1280 - 20 - intBoardWidth;
		int intBoardY = 100;
		int intSlotSize = 90;
		int intPadding = 8;
		int intHalfPadding = intPadding / 2; // 4 pixels
		
		int intCol = -1;

		boolean blnWasPressed = false;
				
		// Gets valid column from mouse click
		while(true){
			boolean blnIsPressed = con.currentMouseButton() == 1;
			
			// Logic runs when left click press (not hold) occurs by validating click wasn't held from previous iteration and was just released
			if(blnWasPressed && blnIsPressed != true){ // passes if blnWasPressed = true (from last iteration when mouse held) and blnIsPressed != false (mouse just released)
				intCol = -1;
				
				int intMouseX = con.currentMouseX();
				int intMouseY = con.currentMouseY();
				
				// Gets mouse input within board dimensions
				if(intMouseX >= intBoardX && intMouseX <= intBoardX + intBoardWidth && intMouseY >= intBoardY && intMouseY <= intBoardY + intBoardHeight){
					int intMouseXFromBoard = intMouseX - intBoardX - intPadding;
					
					if(intMouseXFromBoard < 0){
						intCol = 0; // left edge belongs to column 1
					}else if(intMouseXFromBoard >= 6 * (intSlotSize + intPadding)){
						intCol = 6; // Right edge belongs to column 7
					}else{
						intMouseXFromBoard = intMouseXFromBoard + intHalfPadding; // columns split by half the padding
						intCol = intMouseXFromBoard / (intSlotSize + intPadding); // calculates the column based on click
					}
				}
				
				// Validates column
				if(intCol >= 0 && intCol < 7){
					int intColValidated = intCol + 1;
					System.out.println("COLUMN " + intColValidated + " CLICKED"); // CONFIRMATION
					System.out.println();
					
					return intColValidated;
				}else{
					System.out.println("CLICK REGISTERED OUTSIDE OF BOARD"); // CONFIRMATION
					System.out.println();
				}
			}
			
			blnWasPressed = blnIsPressed; // blnWasPressed turns true when mouse clicked (mouse held down)
			con.sleep(50);
		}
	}

	public static void main(String[] args){
		Console con = new Console("Test", 1280, 720);
		
		Color clrP1Color = Color.RED; 
		Color clrP2Color = Color.YELLOW;
		Color clrBoardColor = Color.BLUE;
		
		initBoard();
		int intCurrentPlayer = 1;
		int intPreviousPlayer = 2;
		
		while(true){
			drawBoard(con, clrBoardColor, clrP1Color, clrP2Color);
			
			if(intCurrentPlayer != intPreviousPlayer){
				con.println("PLAYER " + intCurrentPlayer + " TURN");
			}
			int intCol = getColumnClick(con);
			
			if(dropDisc(con, intCol, intCurrentPlayer) == true){
				// switch players if disc drop was successful
				if(intCurrentPlayer == 1){
					intCurrentPlayer = 2;
					intPreviousPlayer = 1;
				}else if(intCurrentPlayer == 2){
					intCurrentPlayer = 1;
					intPreviousPlayer = 2;
				}
			}else{
				con.println("COLUMN (" + (intCol+1) + ") FULL. SELECT ANOTHER COLUMN.");
				intPreviousPlayer = intCurrentPlayer;
			}
			
			con.sleep(100);
		}
		
		// OLD TESTING BELOW
		/*
		drawDisc(con, 1, 1, clrP1Color); // valid
		drawDisc(con, 2, 3, clrP2Color); // valid
		drawDisc(con, 6, 7, clrP1Color); // valid
		drawDisc(con, 7, 7, clrP1Color); // row invalid
		drawDisc(con, 6, 8, clrP1Color); // col invalid
		drawDisc(con, 7, 8, clrP1Color); // both invalid
		drawDisc(con, 0, 1, clrP1Color); // row invalid
		drawDisc(con, 1, 0, clrP1Color); // col invalid
		drawDisc(con, 0, 0, clrP1Color); // both invalid
		*/
		
		/*
		drawBoard(con, new Color(255,204,24), clrP1Color, clrP2Color);
		con.sleep(500);
		
		dropDisc(con, 9, 1);
		drawBoard(con, new Color(255,204,24), clrP1Color, clrP2Color);
		con.sleep(500);
		
		dropDisc(con, 4, 3);
		drawBoard(con, new Color(255,204,24), clrP1Color, clrP2Color);
		con.sleep(500);
		
		dropDisc(con, 1, 1);
		drawBoard(con, new Color(255,204,24), clrP1Color, clrP2Color);
		con.sleep(500);
		
		dropDisc(con, 1, 2);
		drawBoard(con, new Color(255,204,24), clrP1Color, clrP2Color);
		con.sleep(500);
		
		dropDisc(con, 1, 1);
		drawBoard(con, new Color(255,204,24), clrP1Color, clrP2Color);
		con.sleep(500);
		
		dropDisc(con, 1, 2);
		drawBoard(con, new Color(255,204,24), clrP1Color, clrP2Color);
		con.sleep(500);
		
		dropDisc(con, 1, 1);
		drawBoard(con, new Color(255,204,24), clrP1Color, clrP2Color);
		con.sleep(500);
		
		dropDisc(con, 1, 2);
		drawBoard(con, new Color(255,204,24), clrP1Color, clrP2Color);
		con.sleep(500);
		
		dropDisc(con, 1, 1);
		drawBoard(con, new Color(255,204,24), clrP1Color, clrP2Color);
		con.sleep(500);
		
		getColumnClick(con);
		*/
	}
}
