import java.util.ArrayList;

public class Game {
    public static void main(String[] args) {
        Movement movement = new Movement();
        int boardSize = movement.askForBoardSize();
        Board board = new Board(boardSize);
        board.printBoard();
        while(true) {
            System.out.println("Player " + movement.getSwitchPlayer());
            movement.movementPhase(board);
            board.printBoard();
            movement.switchPlayerFunc();
        }
    }

}
