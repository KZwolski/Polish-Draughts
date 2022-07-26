import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
        Movement movement = new Movement();
        System.out.println("Enter size: ");
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        Board board = new Board(size);
        board.printBoard();

        board.movementPhase(movement.askForInput());
        board.printBoard();


    }

}
