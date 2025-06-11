<p align="center">
   <img width="600" alt="image" src="https://github.com/user-attachments/assets/bca85f09-f24d-4657-92fd-23e8d755b051"/>
</p>

# Connect 4 Game

This Connect 4 game is written in Java for my culminating Grade 11 Computer Science (ICS3U1) project. The game features the standard Connect 4 gameplay, a leaderboard with sorting, theme loading, theme creation, a help menu and an easter egg screen.

This Connect 4 game was created using my teacher, Mr. Cadawas's arc library, which provides a "Console" window for console output, user input, keyboard/mouse input and screen graphics in a dedicated window.

## Overview

**MAIN MENU**

1. A main menu is presented to the players when the program is run.

**[1] - Play Game**

2. Each player inputs their names.
3. Players' names and wins are always displayed on top in an on screen information bar.
4. A countdown begins until the game starts.
5. The Connect 4 gameplay has a visual Connect 4 board.
6. A text and color indicator is used to indicate which player's turn it next.
7. Mouse clicks are used for players to select a column to drop their colored disc.
8. Validation for a win (horizontal, vertical and diagonal 4 in a row) and a tie is checked after every piece is dropped.
9. When a player wins, the winning pieces are flashed or if a tie occurs, all the pieces on the board are flashed.
10. Players have the option to play again and continue their session, triggering another round.
11. If the players do not play again, their names and wins are logged to the leaderboard data file and the game return to the main menu.

**[2] - View Leaderboard**

12. A leaderboard is displayed that is ordered from most to least wins.
13. Only the top 10 at max is displayed.
14. The top 3 are displayed with titles GOLD, SILVER and BRONZE.
15. A clickable button appears on the screen to return to the main menu.

**[3] - Load Theme**

16. Color themes are stored in the themes data file.
17. Color themes include the following properties: theme name, player 1 color, player 2 color, board color and board title.
18. The theme is stored in the lasttheme data file to be used for the colors next time a new session begins.
18. After the user selects a theme, a clickable button appears on the screen to return to the main menu.

**[4] - Create Theme**

19. Players can create a custom theme to be added to the load theme list.
20. A maximum of 15 themes can exist.
21. If 15 themes exist, the players are prompted to select one theme to be deleted.
22. The players are asked for the the 5 properties of the new theme to be created.
23. After the user creates a theme, a clickable button appears on the screen to return to the main menu.

**[5] - Help**

24. The help menu displays instructions on how to play Connect 4.

**[6] - Secret Joke**

25. The secret joke menu is triggered when the user enters 9898 on the main menu, displaying fun jokes as an easter egg.

**Underlying Features**

- Validation for int inputs (without exceptions)
- Validation for int input range
- Validation for String not being empty
- Validation for theme name not repeating when creating themes
- Validation for RGB value format
- Decorative image banners
- Screen clearing
- Click handling


## Technologies Used

- Java SE 23.0.2
- <a href="https://github.com/MrCadawas/arc/">arc</a>

## Installation

To install the Connect 4 game, follow these steps:

1. Clone the repository:

   ```bash
   git clone https://github.com/jusL98/CPTJustinLee.git
   cd CPTJustinLee
   ```

2. Ensure that you have java running on your system.

3. Download <a href="https://github.com/MrCadawas/arc/">arc</a> and move it to the directory.

4. Run the program.

   ```bash
   python main.py
   ```

6. **Alternatively, download and run the filesorter.exe file.**

7. Navigate to the target directory and view the sorted files in date folders formatted by YYYY_MM_DD.

## Usage

1. Run the program or run the .exe file.
2. Configure the settings in the GUI.
3. Navigate to the target directory and view the sorted files in date folders formatted by YYYY_MM_DD.
4. Actions are logged in the log.txt file in the target directory.
5. If backup is enabled, the files (in the unsorted form) were also copied to the backup directory within the target directory.

---

Thank you!

<p align="center">
   <img width="1000" alt="image" src="https://github.com/user-attachments/assets/a766d4cc-24a8-4730-984e-54609e4e5973"/>
</p>
