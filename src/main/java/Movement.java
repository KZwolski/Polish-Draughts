import java.util.ArrayList;
import java.util.Scanner;
import java.util.Locale;

public class Movement {
    public String askForInput() {
        Scanner cords = new Scanner(System.in);
        System.out.println("Please enter coordinates: ");
        String move = cords.nextLine().toUpperCase(Locale.ROOT);
        return move;
    }

    public boolean checkForValidInput(String input, int size) {
        try {
            int column = convertInputIntoColumn(input);
            int row = convertInputIntoRow(input);
            return ((column <= size && column >=0) && (row <= size && row >=0));
        } catch (Exception e) {
            return false;
        }
    }

    public int convertInputIntoColumn(String input) {
        int row = input.charAt(0) - 'A';
        return row;
    }

    public int convertInputIntoRow(String input) {
        int column = Integer.parseInt(input.substring(1)) - 1;
        return column;
    }

    public int[] playersMove(Board board, int player) {
        while (true) {
            String input = askForInput();
            if (checkForValidInput(input, board.board.length)) {
                int column = convertInputIntoColumn(input);
                int row = convertInputIntoRow(input);
                if (board.board[row][column].isActive() && board.board[row][column].getColor() == player) {
                    int coordinates[] = new int[2];
                    coordinates[0] = row;
                    coordinates[1] = column;
                    return coordinates;
                }
            }
            System.out.println("Invalid coordinates");
        }
    }

    public int askForBoardSize() {
        while (true) {
            System.out.println("Please enter board size (must be an even number between 10-20): ");
            Scanner scanner = new Scanner(System.in);
            try {
                int size = scanner.nextInt();
                if ((size >= 10 && size <= 20) && size % 2 == 0) {
                    return size;
                }
            } catch (Exception e) {
                continue;
            }
            System.out.println("Invalid Board size");
        }

    }

    public ArrayList<int[]> possibleMoves(int[] coordinates, Board board) {
        ArrayList<int[]> validMoves = new ArrayList<>();
        int row = coordinates[0];
        int column = coordinates[1];

        int[] leftMove = emptyFields(row, column, board, -1);
        int[] rightMove = emptyFields(row, column, board, 1);
        validMoves.add(leftMove);
        validMoves.add(rightMove);
//        System.out.println(leftMove[0] + " " + leftMove[1]);
        System.out.println(leftMove);
        System.out.println(rightMove[0] + " " + rightMove[1]);
        return validMoves;
    }

    public boolean isFieldOnBoard(int row, int column, Board board) {
        return row >= 0 && column >= 0 && row < board.board.length && column <= board.board[row].length;
    }

    public int[] emptyFields(int row, int column, Board board, int diagonal) {
        int[] coords = new int[2];
        if (isFieldOnBoard(row + 1, column + diagonal, board)) {
            if (!board.board[row + 1][column + diagonal].isActive()) {
                coords[0] = row + 1;
                coords[1] = column + diagonal;
                return coords;
            }
        }
        return null;
    }

    public void movementPhase(int[] askForInput, Board board) {
        int x = askForInput[0];
        int y = askForInput[1];
        Pawn pawn = board.board[x][y];
        pawn.setX(x + 1);
        pawn.setY(y + 1);
        board.board[x][y] = board.board[x + 1][y + 1];
        board.board[x + 1][y + 1] = pawn;

    }

}
