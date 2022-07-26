import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        Board board = new Board(size);
        board.printBoard();
        board.movementPhase(3,0);
        board.printBoard();
    }
}
