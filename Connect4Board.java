import arc.*;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.Font;

public class Connect4Board{
	// Represents the board's state through a 6x7 array.
	static int intBoard[][] = new int[6][7];
	
	
	/*
	 * initBoard method:
	 * Sets or resets the game to the beginning empty phase.
	 * Fills board with "0"s and/or clears board of existing discs.
	 */
	public static void initBoard(){
		int intR;
		int intC;
        for (intR = 0; intR < 6; intR++){
            for (intC = 0; intC < 7; intC++){
                intBoard[intR][intC] = 0; // set all values to 0
            }
        }
	}
	
	
	/*
	 * drawBoard method:
	 * Draws the main Connect 4 board using the theme colors.
	 * Each time the method is called, it reflects the board's state.
	 * At the start of the game, the board displays empty slots.
	 */
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
                
				// Draw column numbers in the top slot of each column using
                if(intR == 0){
					Font columnNumFont = con.loadFont("assets/Roboto-Black.ttf", 30);
					con.setDrawFont(columnNumFont);
					con.setDrawColor(clrBoardColor);
					String strColumnNum = Integer.toString(intC + 1);
					int strP1TextWidth = con.getTextFontMetrics().stringWidth(strColumnNum);
					int intX2 = intBoardX + intPadding + (intC * (intSlotSize + intPadding))+35;
					int intY2 = intBoardY + intPadding + (0 * (intSlotSize + intPadding))+20;
					con.drawString(strColumnNum, intX2, intY2);	
				}
                
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
    
    
	/*
	 * dropDisc method:
	 * Returns true if successfully and false otherwise.
	 * Inserts a player's disc at the lowest point in a specified column.
	 * If no discs exist in the column, it is placed at the bottom. If other discs exist, it is placed above the top disc.
	 * Returns false if the column was full.
	 * The dropped disc is stored in the intBoard array and will be visually displayed when drawBoard is called again.
	 */
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
		
		// Checks if column full before proceeding
		if(isColumnFull(intCol+1)){
			System.out.println("ERROR: COLUMN (" + (intCol+1) + ") FULL"); // ERROR
			System.out.println();
			return false;
		}
		
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
		
		// Column full (shouldn't be reached anyway)
		System.out.println("ERROR: COLUMN (" + (intCol+1) + ") FULL"); // ERROR
		System.out.println();
		return false;
	}



	// ************************************************************************
	// VALIDATION METHODS - to validate a win or tie in the Connect 4 board
	// ************************************************************************
	
	/*
	 * checkWin method:
	 * Returns true if a win was detected and false otherwise.
	 * Checks the horizontal, vertical and diagonal win methods by calling all of them.
	 */
	public static boolean checkWin(int intPlayer){
		boolean boolIsWin = checkHorizontalWin(intPlayer) || checkVerticalWin(intPlayer) || checkDiagonalWin(intPlayer);
		
		if(boolIsWin == true){
			System.out.println("PLAYER " + intPlayer + " WIN DETECTED");
			System.out.println();
		}else if(boolIsWin == false){
			System.out.println("NO WIN DETECTED, CONTINUING");
			System.out.println();
		}
		
		return boolIsWin;
	}
	
	
	/*
	 * checkTie method:
	 * Returns true if a tie was detected and false otherwise.
	 * Checks if any column's top row is empty and if so, board is not full and not a tie yet.
	 */
	public static boolean checkTie(){
		int intC;
		boolean boolIsTie = true;
		
		// Check every column's top row - if one is empty, board is not full and not a tie yet
		for(intC = 0; intC < 7; intC++){
			if(intBoard[0][intC] == 0){
				boolIsTie = false;
			}
		}
		
		if(boolIsTie == true){
			System.out.println("TIE DETECTED, CONTINUING"); // CONFIRMATION
			System.out.println();
		}else if(boolIsTie == false){
			System.out.println("NO TIE DETECTED, CONTINUING"); // CONFIRMATION
			System.out.println();
		}
			
		return boolIsTie;
	}
	
	
	/*
	 * checkHorizontalWin method:
	 * Returns true if a horizontal win was detected and false otherwise.
	 * Checks every horizontal combination for 4 of the same player's pieces in a row.
	 */
	public static boolean checkHorizontalWin(int intPlayer){
		int intR;
		int intC;
		boolean boolIsHorizontalWin = false;
		
		// Checks every combination for horizontal (-) win
		for(intR = 0; intR < 6; intR++){
			for(intC = 0; intC <= 3; intC++){ // only need to check up to column 3
				if(intBoard[intR][intC] == intPlayer && intBoard[intR][intC+1] == intPlayer && intBoard[intR][intC+2] == intPlayer && intBoard[intR][intC+3] == intPlayer){
					boolIsHorizontalWin = true;
				}
			}
		}
		
		return boolIsHorizontalWin;
	}
	
	
	/*
	 * checkVerticalWin method:
	 * Returns true if a vertical win was detected and false otherwise.
	 * Checks every vertical combination for 4 of the same player's pieces in a row.
	 */
	public static boolean checkVerticalWin(int intPlayer){
		int intR;
		int intC;
		boolean boolIsVerticalWin = false;
		
		// Checks every combination for vertical (|) win
		for(intC = 0; intC < 7; intC++){
			for(intR = 0; intR <= 2; intR++){ // only need to check up to row 2
				if(intBoard[intR][intC] == intPlayer && intBoard[intR+1][intC] == intPlayer && intBoard[intR+2][intC] == intPlayer && intBoard[intR+3][intC] == intPlayer){
					boolIsVerticalWin = true;
				}
			}
		}
		
		return boolIsVerticalWin;
	}
	
	
	/*
	 * checkDiagonalWin method:
	 * Returns true if a diagonal win was detected and false otherwise.
	 * Checks every diagonal combination (both ways) for 4 of the same player's pieces in a row.
	 */
	public static boolean checkDiagonalWin(int intPlayer){
		int intR;
		int intC;
		boolean boolIsDiagonalWin = false;
		
		// Check combinations bottom-left to top-right for diagonal (/) win
		for(intR = 3; intR < 6; intR++){
			for(intC = 0; intC <= 3; intC++){
				if(intBoard[intR][intC] == intPlayer && intBoard[intR-1][intC+1] == intPlayer && intBoard[intR-2][intC+2] == intPlayer && intBoard[intR-3][intC+3] == intPlayer){
					boolIsDiagonalWin = true;
				}
			}
		}
		
		// Check combinations top-left to bottom-right for diagonal (\) win
		for(intR = 0; intR <= 2; intR++){
			for(intC = 0; intC <= 3; intC++){
				if(intBoard[intR][intC] == intPlayer && intBoard[intR+1][intC+1] == intPlayer && intBoard[intR+2][intC+2] == intPlayer && intBoard[intR+3][intC+3] == intPlayer){
					boolIsDiagonalWin = true;
				}
			}
		}
		
		return boolIsDiagonalWin;
	}



	// ************************************************************************
	// DISPLAY METHODS - to display the on screen information and game outcome animation
	// ************************************************************************
	
	/*
	 * displayOutcomeAnimation method:
	 * Flashes pieces depending on win or tie to make them visible.
	 * If "WIN" specified in paramaters, flashes 4 winning pieces 8 times.
	 * If "TIE" specified in paramaters, flashes every piece 8 times.
	 */
	public static void displayOutcomeAnimation(Console con, int intPlayer, Color clrBoardColor, Color clrP1Color, Color clrP2Color, String strOutcome){
		// Gets winning pieces (FOR WIN OUTCOME ONLY)
		int[][] intWinningBoard = getWinningBoard(intPlayer);
    
		// Sets the correct winning player's color (FOR WIN OUTCOME ONLY)
		Color clrWinnerColor = Color.BLACK;
		if(intPlayer == 1){
			clrWinnerColor = clrP1Color;
		}else if(intPlayer == 2){
			clrWinnerColor = clrP2Color;
		}
		
		// Flashes the 4 winning pieces 8 times
		int intNumFlashes;
		for(intNumFlashes = 0; intNumFlashes < 8; intNumFlashes++){
			int intR;
			int intC;
			
			// Draws pieces in white (flash effect)
			for(intR = 0; intR < 6; intR++){
				for(intC = 0; intC < 7; intC++){
					if(intWinningBoard[intR][intC] == intPlayer && strOutcome.equals("WIN")){ // FOR WIN OUTCOME ONLY - flash only winning pieces
						drawDisc(con, intR + 1, intC + 1, Color.WHITE);
					}else if(strOutcome.equals("TIE")){ // FOR TIE OUTCOME ONLY - flash every piece
						drawDisc(con, intR + 1, intC + 1, Color.WHITE);
					}
				}
			}
			con.repaint();
			con.sleep(100);
			
			// Draws pieces back in original color
			for(intR = 0; intR < 6; intR++){
				for(intC = 0; intC < 7; intC++){
					if(intWinningBoard[intR][intC] == intPlayer && strOutcome.equals("WIN")){ // FOR WIN OUTCOME ONLY - flash only winning pieces
						drawDisc(con, intR + 1, intC + 1, clrWinnerColor);
					}else if(strOutcome.equals("TIE")){ // FOR TIE OUTCOME ONLY - flash every piece
						if(intBoard[intR][intC] == 1){
							drawDisc(con, intR + 1, intC + 1, clrP1Color);
						}else if(intBoard[intR][intC] == 2){ 
							drawDisc(con, intR + 1, intC + 1, clrP2Color);
						}
					}
				}
			}
			con.repaint();
			con.sleep(100);
		}	
	}
	
	
	/*
	 * displayOnScreenInformation method:
	 * Displays the on screen information. Used after a player has won to reupdate the display.
	 */
	public static void displayOnScreenInformation(Console con, String strBoardTitle, String strP1Name, int intP1Wins, String strP2Name, int intP2Wins){
		// Draws initial on screen information with board title
		con.setDrawColor(Color.WHITE); // white bar
		con.fillRect(20,20,1240,60);
		Font boardTitleFont = con.loadFont("assets/Roboto-Black.ttf", 25); // board title
		con.setDrawFont(boardTitleFont);
		con.setDrawColor(Color.BLACK);
		int intBoardTitleWidth = con.getTextFontMetrics().stringWidth(strBoardTitle);
		con.drawString(strBoardTitle, 20 + (1240 - intBoardTitleWidth) / 2, 20 + 10);
		con.repaint();
		
		Font player1TextFont = con.loadFont("assets/Roboto-Medium.ttf", 20);
		con.setDrawFont(player1TextFont);
		con.setDrawColor(Color.BLACK);
		String strP1Text = strP1Name + ": " +intP1Wins;
		int intP1TextWidth = con.getTextFontMetrics().stringWidth(strP1Text);
		con.drawString(strP1Text, 20 + 20, 20 + 15);
		
		Font player2TextFont = con.loadFont("assets/Roboto-Medium.ttf", 20);
		con.setDrawFont(player2TextFont);
		con.setDrawColor(Color.BLACK);
		String strP2Text = strP2Name + ": " +intP2Wins;
		int intP2TextWidth = con.getTextFontMetrics().stringWidth(strP2Text);
		con.drawString(strP2Text, 1280-20-intP2TextWidth-20, 20 + 15);
		con.repaint();
	}



	// ************************************************************************
	// BOARD HELPER/UTILITY METHODS - to support other methods in Connect 4 board
	// ************************************************************************

	/*
	 * drawDisc method:
	 * Draws a disc of a specified player color at a specified location (row,column).
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
		
		//con.repaint();
	}


	/*
	 * getColumnClick method:
	 * Returns the column number (starting from 1) that was left clicked within the board.
	 * Only continues if a left click occured within the board.
	 * Only continue if a click and release occured (not hold).
	 * Issues with click and hold, resulting in the wrong column return were resolved.
	 */
	public static int getColumnClick(Console con){
		int intBoardWidth = 694;
		int intBoardHeight = 596;
		int intBoardX = 1280 - 20 - intBoardWidth;
		int intBoardY = 100;
		int intSlotSize = 90;
		int intPadding = 8;
		int intHalfPadding = intPadding / 2; // 4 pixels
		
		int intCol = -1;

		boolean boolWasPressed = false;
				
		// Gets valid column from mouse click
		while(true){
			boolean boolIsPressed = con.currentMouseButton() == 1;
			
			// Logic runs when left click press (not hold) occurs by validating click wasn't held from previous iteration and was just released
			if(boolWasPressed && boolIsPressed != true){ // passes if boolWasPressed = true (from last iteration when mouse held) and boolIsPressed != false (mouse just released)
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
			
			boolWasPressed = boolIsPressed; // boolWasPressed turns true when mouse clicked (mouse held down)
			con.sleep(25);
		}
	}
	
	
	/*
	 * isColumnFull method:
	 * Returns true if a specified column is full or false otherwise.
	 * Specified column (parameter) starts at 1. 
	 */
	public static boolean isColumnFull(int intCol){
		boolean boolIsFull = intBoard[0][intCol-1] != 0;
		
		return boolIsFull;
	}
	
	
	/*
	 * isValidColumn method:
	 * Returns true if a specified column is within range of 1-7 AND not full or false if both conditions not met.
	 * Specified column (parameter) starts at 1. 
	 */
	public static boolean isValidColumn(int intCol){
		boolean boolIsValid = (intCol >= 1 && intCol <= 7) && !isColumnFull(intCol);
		
		return boolIsValid;
	}


	/*
	 * getBoardState method:
	 * Returns a string of intBoard (the board state).
	 * Used for informational purposes only in System print.
	 */
	public static String getBoardState(){
		String strBoardState = "";
		
		// Loop through each element to print
		int intR;
		int intC;
		for(intR = 0; intR < 6; intR++){
			for(intC = 0; intC < 7; intC++){
				strBoardState += (intBoard[intR][intC] + " ");
			}
			strBoardState += ("\n");
		}
		strBoardState += ("\n");
		
		return strBoardState;
	}

	/*
	 * getWinningBoard method:
	 * Returns a 2D array of only the 4 winning pieces in a 6x7 grid format.
	 * Used to help the displayOutcomeAnimation method to flash only the winning pieces.
	 */
	public static int[][] getWinningBoard(int intPlayer){
		int[][] intWinningBoard = new int[6][7];
		int intR;
		int intC;
		
		// Initializes winning board with zeros
		for(intR = 0; intR < 6; intR++){
			for(intC = 0; intC < 7; intC++){
				intWinningBoard[intR][intC] = 0;
			}
		}
		
		// Checks horizontal wins
		for(intR = 0; intR < 6; intR++){
			for(intC = 0; intC <= 3; intC++){ // only need to check up to column 3
				if(intBoard[intR][intC] == intPlayer && intBoard[intR][intC+1] == intPlayer && intBoard[intR][intC+2] == intPlayer && intBoard[intR][intC+3] == intPlayer){
					intWinningBoard[intR][intC] = intPlayer;
					intWinningBoard[intR][intC + 1] = intPlayer;
					intWinningBoard[intR][intC + 2] = intPlayer;
					intWinningBoard[intR][intC + 3] = intPlayer;
					
					return intWinningBoard;
				}
			}
		}
		
		// Checks vertical wins
		for(intC = 0; intC < 7; intC++){
			for(intR = 0; intR <= 2; intR++){ // only need to check up to row 2
				if(intBoard[intR][intC] == intPlayer && intBoard[intR+1][intC] == intPlayer && intBoard[intR+2][intC] == intPlayer && intBoard[intR+3][intC] == intPlayer){
					intWinningBoard[intR][intC] = intPlayer;
					intWinningBoard[intR + 1][intC] = intPlayer;
					intWinningBoard[intR + 2][intC] = intPlayer;
					intWinningBoard[intR + 3][intC] = intPlayer;
					
					return intWinningBoard;
				}
			}
		}
		
		// Check diagonal wins bottom-left to top-right
		for(intR = 3; intR < 6; intR++){
			for(intC = 0; intC <= 3; intC++){
				if(intBoard[intR][intC] == intPlayer && intBoard[intR-1][intC+1] == intPlayer && intBoard[intR-2][intC+2] == intPlayer && intBoard[intR-3][intC+3] == intPlayer){
					intWinningBoard[intR][intC] = intPlayer;
					intWinningBoard[intR - 1][intC + 1] = intPlayer;
					intWinningBoard[intR - 2][intC + 2] = intPlayer;
					intWinningBoard[intR - 3][intC + 3] = intPlayer;
					
					return intWinningBoard;
				}
			}
		}
		
		// Checks diagonal wins top-left to bottom-right
		for(intR = 0; intR <= 2; intR++){
			for(intC = 0; intC <= 3; intC++){
				if(intBoard[intR][intC] == intPlayer && intBoard[intR+1][intC+1] == intPlayer && intBoard[intR+2][intC+2] == intPlayer && intBoard[intR+3][intC+3] == intPlayer){
					intWinningBoard[intR][intC] = intPlayer;
					intWinningBoard[intR + 1][intC + 1] = intPlayer;
					intWinningBoard[intR + 2][intC + 2] = intPlayer;
					intWinningBoard[intR + 3][intC + 3] = intPlayer;
					
					return intWinningBoard;
				}
			}
		}
		return intWinningBoard;
	}
	


	/*
	 * main method:
	 * Used for temporary testing only within this file.
	 */
	public static void main(String[] args){
		Console con = new Console("Test", 1280, 720);
		
		Color clrP1Color = Color.RED; 
		Color clrP2Color = Color.YELLOW;
		Color clrBoardColor = Color.BLUE;
		
		initBoard();
		int intCurrentPlayer = 1; // player 1 starts
		int intPreviousPlayer = 2;
		
		while(true){
			drawBoard(con, clrBoardColor, clrP1Color, clrP2Color);
			
			// Displays player turn
			if(intCurrentPlayer != intPreviousPlayer){
				con.println("PLAYER " + intCurrentPlayer + " TURN");
			}
			
			// Gets column from mouse click
			int intCol = getColumnClick(con);
			
			// Runs win checks and switches players
			if(isValidColumn(intCol)){ // checks if column is valid
				if(dropDisc(con, intCol, intCurrentPlayer) == true){ // checks if drop was successful
					// Checks for win
					if(checkWin(intCurrentPlayer)){
						drawBoard(con, clrBoardColor, clrP1Color, clrP2Color);
						con.println("PLAYER " + intCurrentPlayer + " WINS!");
						System.out.println("GAME OVER: PLAYER " + intCurrentPlayer + " WINS!");
						displayOutcomeAnimation(con, intCurrentPlayer, clrBoardColor, clrP1Color, clrP2Color, "WIN");
						break; // ends game
					}
					// Checks for tie
					else if(checkTie()){
						drawBoard(con, clrBoardColor, clrP1Color, clrP2Color);
						con.println("IT'S A TIE!");
						System.out.println("GAME OVER: IT'S A TIE!");
						displayOutcomeAnimation(con, intCurrentPlayer, clrBoardColor, clrP1Color, clrP2Color, "TIE");
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
					
					System.out.println(getBoardState());
				}
			}else{
				con.println("COLUMN " + intCol + " IS FULL. SELECT ANOTHER COLUMN.");
				
				System.out.println("ERROR: COLUMN " + intCol + " IS FULL."); // ERROR
				System.out.println();
				
				intPreviousPlayer = intCurrentPlayer; // keeps same player
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
