import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input;
        TicTacToe game = new TicTacToe();
        game.printInstructions();
        while(!game.isGameWon() || game.isBoardFull())
        {
          input = new Scanner(System.in);
          if (!game.markCoordinates(input.nextLine())) {
            System.out.println("Invalid move");
          }

          game.printBoard();
        }

        if (game.isGameWon()) {
          game.printWinner();
        }
        else {
          System.out.println("Tie");
        }
        

    }
    
}

class TicTacToe {
    final private char EMPTY_CELL = 'N';
    private char board[][] = {{EMPTY_CELL, EMPTY_CELL, EMPTY_CELL}, {EMPTY_CELL, EMPTY_CELL, EMPTY_CELL}, {EMPTY_CELL, EMPTY_CELL, EMPTY_CELL}};
    private char currentPlayer = 'X';
    private char previousPlayer = 'O';
    
    public void printInstructions() {
        System.out.println("Play the TicTacToe game");
        System.out.println("Mark the coords");
    }

    public void printWinner() {
      System.out.print("Congratulations! Player ");
      System.out.print(previousPlayer);
      System.out.println(" won!");
    }
    
    public void printBoard() {
      for (char[] row: board) {
        for (char cell: row) {
          System.out.print(cell);
          System.out.print(' ');
        }
        System.out.println();
      }
    }

    public boolean isBoardFull() {
      for (char[] row: board) {
        for (char cell: row) {
          if (cell == EMPTY_CELL)
            return false;
        }
      }
      return true;
    }

    public boolean markCoordinates(String line) {
      if (line.length() != 3)
        return false;
      
      if (line.charAt(1) != ' ')
        return false;

      int row_position = Character.getNumericValue(line.charAt(0));
      if (row_position > 2 || row_position < 0)
        return false;

      int col_position = Character.getNumericValue(line.charAt(2));
      if (col_position > 2 || col_position < 0)
        return false;

      if (board[row_position][col_position] == 'N') {
        board[row_position][col_position] = currentPlayer;
        char temp = currentPlayer;
        currentPlayer = previousPlayer;
        previousPlayer = temp;

        return true;
      }
      else {
        return false;
      }

      
    }

    public boolean isGameWon() {
        boolean diagWon = false;
        
        for (int i = 0; i < board.length; i++) {
          // check rows
          if((board[i][0] == board[i][1]) && (board[i][1] == board[i][2]) && (board[i][0] != EMPTY_CELL)) {
            return true;
          }
          // check cols 
          if((board[0][i] == board[1][i]) && (board[1][i] == board[2][i]) && (board[0][i] != EMPTY_CELL)) {
            return true;
          }
        }

        if((board[0][0] == board[1][1]) && (board[1][1] == board[2][2]) && (board[0][0] != EMPTY_CELL)) {
          diagWon = true;
        }
        if((board[0][2] == board[1][1]) && (board[1][1] == board[2][0]) && (board[0][2] != EMPTY_CELL)) {
          diagWon = true;
        }
        return diagWon;
    }

}

