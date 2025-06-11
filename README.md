
# Connect 4 Game

This Connect 4 game is written in Java for my culminating Grade 11 Computer Science (ICS3U1) project. The game features the standard Connect 4 gameplay, a leaderboard with sorting, theme loading, theme creation, a help menu and an easter egg screen.

This Connect 4 game was created using my teacher, Mr. Cadawas's arc library, which provides a "Console" window for console output, user input, keyboard/mouse input and screen graphics in a dedicated window.

<p align="center">
   <img width="1000" alt="image" src="https://github.com/user-attachments/assets/e4c7d3ee-57d4-4f06-8813-0fa04c008dd8"/>
</p>


## Description

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
19. After the user selects a theme, a clickable button appears on the screen to return to the main menu.

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

**UNDERLYING FEATURES**

- Validation for int inputs (without exceptions)
- Validation for int input range
- Validation for String not being empty
- Validation for theme name not repeating when creating themes
- Validation for RGB value format
- Decorative image banners
- Screen clearing
- Click handling

## Built With

- [Java SE 23.0.2](https://www.java.com/en/): programming language
- [arc](https://github.com/MrCadawas/arc/): library that provides a Console window with simple input and output methods for learning.

## Quick Start

### Prerequisites

- OS
- Java SE 23.0.2 or higher installed

### Installation

To install the Connect 4 game, follow these steps:

1. **Clone the repository.**

   ```bash
   git clone https://github.com/jusL98/CPTJustinLee.git
   cd CPTJustinLee
   ```

</br>

2. **Download [arc.jar](https://github.com/MrCadawas/arc/releases).**

   Once downloaded, place the arc.jar file into the directory within the cloned project.

   ```
   mv /path/to/downloaded/arc.jar CPTJustinLee
   ```


### Setup

3. **Compile the program.**

   For Linux/macOS:

   ```
   javac -cp .:arc.jar Main.java
   ```

   For Windows:

   ```
   javac -cp .;arc.jar Main.java
   ```


### Run

4. **Run the program.**

   For Linux/macOS:

   ```
   java -cp .:arc.jar Main.java
   ```

   For Windows:

   ```
   java -cp .;arc.jar Main.java
   ```

## Usage

1. Run the program.
2. Navigate the main menu:

   a. [1] Play Connect 4.

   b. [2] View the leaderboard.

   c. [3] Load themes.

   d. [4] Create themes.

   e. [5] View how to play instructions.
   
   f. [9898] View the secret joke menu.


## Project Structure
```
├── CPTJustinLee/
│   ├── assets/
│   │   ├── Pixlr Files/               // contains pixlr.com files used to create banners
│   │   ├── Connect4Logo.jpg           // logo featured on main menu
│   │   ├── _____Banner.jpg            // decorative banners for each screen
│   │   └── Roboto-______.ttf          // fonts used
│   │
│   ├── data/
│   │   ├── lasttheme.txt              // stores the name of the last theme loaded
│   │   ├── leaderboard.txt            // stores players' names and wins
│   │   └── themes.txt                 // stores theme properties
│   │
│   ├── Connect4Board.java             // contains methods that controls the Connect 4 board
│   ├── DataManager.java               // contains methods to update the txt data files
│   ├── InputValidation.java           // contains methods to handle input like verifying integers, RGB formats and strings
│   └── Main.java                      // contains the main game logic to handle screens and gameplay loop
```


## Acknowledgements
This project was created for my culminating ICS3U1 project.

This project was created using my teacher's arc library. Thanks Mr. Cadawas!
- [arc](https://github.com/MrCadawas/arc)

<br>

---
<br>
Thank you!

<br>

<p align="left">
  <a href="mailto:justin.matthew.lee.18@gmail.com">
    <img src="https://img.shields.io/badge/Gmail-D14836?style=for-the-badge&logo=gmail&logoColor=white"/>
  </a>
  <a href="https://www.linkedin.com/in/justin-matthew-lee/">
    <img src="https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white"/>
  </a>
    <a href="https://github.com/jusl98">
    <img src="https://img.shields.io/badge/GitHub-100000?style=for-the-badge&logo=github&logoColor=white"/>
  </a>
</p>

<br>

<p align="left">
   <img width="500" alt="image" src="https://github.com/user-attachments/assets/866d3f40-a438-4d48-936e-365f07b41b49"/>
   <img width="500" alt="image" src="https://github.com/user-attachments/assets/74c205b7-4fee-47f3-8e24-3f6fb6da6ab1"/>
   <img width="500" alt="image" src="https://github.com/user-attachments/assets/beefe72d-5f47-430c-bff9-75da1a3ad1c0"/>
   <img width="500" alt="image" src="https://github.com/user-attachments/assets/535534b3-585e-4af8-aa84-4d48fed414ad"/>
   <img width="500" alt="image" src="https://github.com/user-attachments/assets/8a689b59-07d8-4e6e-bf52-50218ee5b224"/>
   <img width="500" alt="image" src="https://github.com/user-attachments/assets/1121c3e3-9d0f-43f6-a9f0-7fa0523a1d43"/>
</p>