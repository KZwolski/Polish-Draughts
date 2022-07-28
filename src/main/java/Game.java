import java.util.ArrayList;

public class Game {
    public static void main(String[] args) {
        Movement movement = new Movement();
        int boardSize = movement.askForBoardSize();
        Board board = new Board(boardSize);
        board.printBoard();
        boolean hasWon = movement.hasWon(board);
        while(!hasWon) {
            System.out.println("Player " + movement.getSwitchPlayer());
            movement.switchPlayerFunc();
            movement.movementPhase(board);
            board.printBoard();
            hasWon= movement.hasWon(board);
        }
    }

}
