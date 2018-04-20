package TicTacToeGUI;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;


public class TTT100 extends JFrame{ //lets TTT100 use JFrame elements
  /**
  This program is a TicTacToe game between the computer and one player
  */

  //GUI variables
  private static final int WIDTH = 500; //modify width/height
  private static final int HEIGHT = 600;

  public static JLabel dialog; //displays game status
  public static JButton[][] boardB = new JButton[3][3]; //a 3x3 2d button array
  private static ChangeBoardHandler bBHandler; //listens to actions performed on buttons boardB

  public static char[][] board = new char[3][3]; //declare an array of a 3x3 board
  public static int currentR,currentC; //currentR is the row of the cell in the current move, currentC is the column of the cell in current move
  public Container pane = getContentPane(); //new window
  /**
    TTT100 is the graphic part of the program. It creates a new window that displays the TicTacToe board.
  */
  public TTT100(){
    setTitle("Tic Tac Toe"); //sets window title to Tic Tac Toe
    pane.setLayout(new GridLayout(4,3)); //makes 4x3 layout
    dialog = new JLabel("Game in Progress");
    bBHandler = new ChangeBoardHandler();
    initializeButtons(boardB);
    pane.add(dialog); //tells player current status of Game
    setSize(WIDTH, HEIGHT); //sets size of window in pixels
    setVisible(true); //makes window visible
    setDefaultCloseOperation(EXIT_ON_CLOSE); //exits program when window is closed
    Board.initialize(); //starts game
  }

  /**
    initializeButtons takes a JButton array and creates default values for it.
    @param boardB is the 3x3 2D JButton array that corresponds to the internal board[][].
  */
  public void initializeButtons(JButton[][] boardB){
    for(int i = 0; i < 3; i++){
      for(int j = 0; j < 3; j++){
        boardB[i][j] = new JButton(" ");
        boardB[i][j].addActionListener(bBHandler);
        pane.add(boardB[i][j]);
        Color blue = new Color(202,228,224);
        boardB[i][j].setBackground(blue);
        boardB[i][j].setOpaque(true);
      }
    }
  }

  /**
    Class that specifies how a button on the board responds when pressed.
    @param e action performed (ie pressing a button) that the listener responds to
    The action listener detects when a button is pressed and responds by disabling it
    from being pressed again. It also sets it to display the player's letter, X,
    and changes the entry in board[][] to be X as well. Then it allows the computer to take a turn.
  */
  private class ChangeBoardHandler implements ActionListener{
    public void actionPerformed(ActionEvent e){
      JButton button = (JButton) e.getSource(); //button corresponding to one entry in boardB array
      button.setText("X"); //sets to player's letter
      button.setEnabled(false); //cannot be modified
      			boolean found = false;
      			for (int row = 0; row < 3 && !found; row++) {
      				for (int column = 0; column < 3 && !found; column++) {
      					if (button == boardB[row][column]) { //if button corresponds with entry in board button array
      						board[row][column] = 'X'; //sets board array value
      						currentR = row; //makes new currentR and currentC
      						currentC = column;
      						found = true;
      					}
      				}
      			}
			Winner.checkWinner(currentR, currentC); //checks if there is winner
			computer(); //computer makes a move
    }
  }

  /**
    main method creates TTT100 which creates the new window for the game.
    @param args an array of Strings which we ignore
  */
  public static void main(String[] args) {
    new TTT100();
  }

  /**
   an ordered sequence of moves that the computer would take if user go first
   First, computer should check if the center cell is empty; if so, it should take the center cell
   Second, computer should check if there are two '0's on the same row, column or diagonal; if so,
   it should occupy the cell that would make three 'O's
   Third, computer should check if there are two 'X's on the same row, column or diagonal; if so,
   it should thake the remaining empty cell on that row, column or diagonal so that the user would not win
   Fourth, computer should check if there are empty cells on the edge, i.e. cell [0][1],[1][0],[1][2],[2][1];
   if so, it should take the first empty edge cell it finds
   Finally, it should check if there still are empty cells on the board; if so, it should take the first
   empty cell it finds.
  */
  public static void computer(){
    if (!Check.noughtsOrCross('O')) {
			if (!Check.noughtsOrCross('X')) {
				if (Check.corner() == false)
					Check.checkEmpty();
			}
		}
    if(boardB[currentR][currentC].isEnabled()){
      boardB[currentR][currentC].setText("O");
      boardB[currentR][currentC].setEnabled(false); // updates buttons
      Winner.checkWinner(currentR,currentC);  //check if there is a winner or a tie or still in process
    }
  }

}
