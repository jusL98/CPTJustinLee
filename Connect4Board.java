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
		
		/* BELOW VALIDATION NO LONGER NEEDED AS METHOD PURPOSE CHANGED TO HELPER METHOD
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

	public static void main(String[] args){
		Console con = new Console("Test", 1280, 720);
		
		Color clrP1Color = Color.RED; 
		Color clrP2Color = Color.YELLOW;
		
		intBoard[0][0] = 1;
		drawBoard(con, new Color(255,204,24), clrP1Color, clrP2Color);
		
		
		intBoard[0][2] = 2;
		drawBoard(con, new Color(255,204,24), clrP1Color, clrP2Color);
		
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
	}
}
