import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
        Movement movement = new Movement();
        int boardSize = movement.askForBoardSize();
        Board board = new Board(boardSize);
        board.printBoard();
        int [] coordinates = movement.playersMove(board,1);
        board.movementPhase(coordinates);
        board.printBoard();


    }

}
