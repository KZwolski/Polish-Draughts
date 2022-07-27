import java.util.ArrayList;

public class Game {
    public static void main(String[] args) {
        Movement movement = new Movement();
        int boardSize = movement.askForBoardSize();
        Board board = new Board(boardSize);
        board.printBoard();
        int [] coordinates = movement.playersMove(board,1);
        ArrayList<int[]> possibleMoves = movement.possibleMoves(coordinates, board);
        movement.displayPossibleMoves(possibleMoves);


    }

}
