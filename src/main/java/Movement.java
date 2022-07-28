import java.util.ArrayList;
import java.util.Scanner;
import java.util.Locale;

public class Movement {
    private int switchPlayer = 2;

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
            return ((column <= size && column >= 0) && (row <= size && row >= 0));
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
        if (leftMove != null) {
            validMoves.add(leftMove);
        }
        if (rightMove != null) {
            validMoves.add(rightMove);
        }
        return validMoves;
    }

    public boolean isFieldOnBoard(int row, int column, Board board) {
        return row >= 0 && column >= 0 && row < board.board.length - 1 && column <= board.board[row].length - 1;
    }

    public int getSwitchPlayer() {
        return switchPlayer;
    }

    public int[] emptyFields(int row, int column, Board board, int diagonal) {
        int color = board.board[row][column].getColor();
        int direction;
        if (color == 1) {
            direction = 1;
        } else {
            direction = -1;
        }
        int[] coords = new int[2];
        if (isFieldOnBoard(row + direction * 2, column + diagonal * 2, board) &&
                board.board[row + direction * 2][column + diagonal * 2].getColor() == 0 &&
                board.board[row + direction][column + diagonal].isActive()) {
            coords[0] = row + direction * 2;
            coords[1] = column + diagonal * 2;
            return coords;

        } else if (isFieldOnBoard(row + direction, column + diagonal, board) && !board.board[row + direction][column + diagonal].isActive()) {
            coords[0] = row + direction;
            coords[1] = column + diagonal;
            return coords;
        }

        return null;
    }


    public void displayPossibleMoves(ArrayList<int[]> moves) {
        System.out.println("Possible moves for chosen Pawn: ");
        for (int i = 0; i < moves.size(); i++) {
            if (moves.get(i) != null) {
                System.out.print(i + 1 + ".");
                convertCoordinates(moves.get(i));
            }
        }

    }

    public void convertCoordinates(int[] list) {
        System.out.print((char) (list[1] + 'A'));
        System.out.print(list[0] + 1);
        System.out.println();
    }

    public int[] getCoordinates(ArrayList<int[]> moves) {
        System.out.println("Choose one of the above numbers: ");
        Scanner scanner = new Scanner(System.in);
        String chooseMove = scanner.next();
        if (moves.size() == 1) {
            return moves.get(0);
        } else if (moves.size() == 2) {
            switch (chooseMove) {
                case "1":
                    return moves.get(0);
                case "2":
                    return moves.get(1);
                default:
                    return moves.get(0);
            }
        }
        return null;
    }

    public int[] putRowAndColumnIntoTable(int row, int column) {
        int[] coordinates = new int[2];
        coordinates[0] = row;
        coordinates[1] = column;
        return coordinates;
    }

    public int getOppositeColor(int player) {
        int color;
        switch (player) {
            case 1:
                color = 2;
                break;
            case 2:
                color = 1;

                break;
            default:
                color = 0;
                break;

        }
        return color;
    }


    public ArrayList<int[]> checkForBattle(Board board, int player) {
        ArrayList<int[]> validMoves = new ArrayList<>();
        int oppositeColor = getOppositeColor(player);
        for (int i = 0; i < board.board.length - 1; i++) {
            for (int j = 0; j < board.board.length - 1; j++) {
                if (board.board[i][j].getColor() == player) {
                    if (checkNeighbour(board, oppositeColor, i, j) && isHitPossible(board, i, j)) {
                        validMoves.add(putRowAndColumnIntoTable(i, j));
                    }
                }

            }
        }
        return validMoves;
    }

    private boolean isHitPossible(Board board, int i, int j) {
        return ((isFieldOnBoard(i + 2, j + 2, board) && board.board[i + 2][j + 2].getColor() == 0) ||
                (isFieldOnBoard(i - 2, j - 2, board) && board.board[i - 2][j - 2].getColor() == 0) ||
                (isFieldOnBoard(i - 2, j + 2, board) && board.board[i - 2][j + 2].getColor() == 0) ||
                (isFieldOnBoard(i + 2, j - 2, board) && board.board[i + 2][j - 2].getColor() == 0));
    }

    private boolean checkNeighbour(Board board, int oppositeColor, int i, int j) {
        return ((isFieldOnBoard(i + 1, j + 1, board) && board.board[i + 1][j + 1].getColor() == oppositeColor) ||
                (isFieldOnBoard(i - 1, j - 1, board) && board.board[i - 1][j - 1].getColor() == oppositeColor) ||
                (isFieldOnBoard(i - 1, j + 1, board) && board.board[i - 1][j + 1].getColor() == oppositeColor) ||
                (isFieldOnBoard(i + 1, j - 1, board) && board.board[i + 1][j - 1].getColor() == oppositeColor));
    }

    public void movementPhase(Board board) {
        ArrayList<int[]> inDangerFields = checkForBattle(board, switchPlayer);
        int x, y;
        if (inDangerFields.size() != 0) {
            System.out.println("You must move with these Fields first: ");
            for (int i = 0; i < inDangerFields.size(); i++) {
                convertCoordinates(inDangerFields.get(i));
            }
            boolean isValid = false;
            while (!isValid) {
                int[] coordinates = playersMove(board, switchPlayer);
                for (int i = 0; i < inDangerFields.size(); i++) {
                    if (inDangerFields.get(i)[0] == coordinates[0] && inDangerFields.get(i)[1] == coordinates[1]) {
                        System.out.println("dupa");
                        ArrayList<int[]> possibleMoves = possibleMoves(coordinates, board);
                        displayPossibleMoves(possibleMoves);
                        isValid = true;
                    }
                }
            }
        } else {
            System.out.println("dupa2");
            int[] coordinates = playersMove(board, switchPlayer);
            ArrayList<int[]> possibleMoves = possibleMoves(coordinates, board);
            displayPossibleMoves(possibleMoves);
            x = coordinates[0];
            y = coordinates[1];
            Pawn pawn = board.board[x][y];
            int[] getCoordinates = getCoordinates(possibleMoves);
            pawn.setX(getCoordinates[0]);
            pawn.setY(getCoordinates[1]);
            board.board[x][y] = board.board[getCoordinates[0]][getCoordinates[1]];
            board.board[getCoordinates[0]][getCoordinates[1]] = pawn;
        }

    }

    public void switchPlayerFunc() {
        if (switchPlayer == 1) {
            switchPlayer = 2;
        } else {
            switchPlayer = 1;
        }
    }

}
