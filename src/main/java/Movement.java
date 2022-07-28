import java.util.ArrayList;
import java.util.Scanner;
import java.util.Locale;

public class Movement {
    private int switchPlayer = 1;

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
        ArrayList<int[]> validMoves;
        int row = coordinates[0];
        int column = coordinates[1];
        validMoves = emptyFields(row, column, board);

        return validMoves;
    }

    public boolean isFieldOnBoard(int row, int column, Board board) {
        return row >= 0 && column >= 0 && row < board.board.length - 1 && column <= board.board[row].length - 1;
    }

    public int getSwitchPlayer() {
        return switchPlayer;
    }

    public ArrayList<int[]> emptyFields(int row, int column, Board board) {
        int color = board.board[row][column].getColor();
        int direction;
        ArrayList<int[]> possibleMovesDuringHit = new ArrayList<>();
        ArrayList<int[]> possibleMovesWithoutHit = new ArrayList<>();

        if (color == 1) {
            direction = 1;
        } else {
            direction = -1;
        }

        for (int i = -1; i <= 1; i += 2) {
            if (isFieldOnBoard(row + i * 2, column + i * 2, board) &&
                    board.board[row + i * 2][column + i * 2].getColor() == 0 &&
                    board.board[row + i][column + i].isActive() && board.board[row + i][column + i].getColor() != color) {
                possibleMovesDuringHit.add(putRowAndColumnIntoTable(row + i * 2, column + i * 2, row + i, column + i));

            } else if ((isFieldOnBoard(row + i * 2, column -i * 2, board) &&
                    board.board[row + i * 2][column - i * 2].getColor() == 0 &&
                    board.board[row + i][column - i].isActive() && board.board[row + i][column - i].getColor() != color)) {
                possibleMovesDuringHit.add(putRowAndColumnIntoTable(row + i * 2, column - i * 2, row + i, column - i));
            } else if (isFieldOnBoard(row + direction, column + i, board) && !board.board[row + direction][column + i].isActive()) {

                possibleMovesWithoutHit.add(putRowAndColumnIntoTable(row + direction, column + i));

            }

        }

        if (possibleMovesDuringHit.size() != 0) {
            return possibleMovesDuringHit;
        } else if (possibleMovesWithoutHit.size() != 0) {
            return possibleMovesWithoutHit;
        } else {
            return null;
        }
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

    public int[] putRowAndColumnIntoTable(int moveRow, int moveColumn, int rowToDelete, int columnToDelete) {
        int[] coordinates = new int[4];
        coordinates[0] = moveRow;
        coordinates[1] = moveColumn;
        coordinates[2] = rowToDelete;
        coordinates[3] = columnToDelete;
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
                    if (isHitPossible(board, i, j, oppositeColor)) {
                        validMoves.add(putRowAndColumnIntoTable(i, j));
                    }
                }

            }
        }
        return validMoves;
    }

    private boolean isHitPossible(Board board, int i, int j, int oppositeColor) {
        return (((isFieldOnBoard(i + 2, j + 2, board) && board.board[i + 2][j + 2].getColor() == 0) && checkNeighbour(board, oppositeColor, i + 1, j + 1)) ||
                ((isFieldOnBoard(i - 2, j - 2, board) && board.board[i - 2][j - 2].getColor() == 0) && checkNeighbour(board, oppositeColor, i - 1, j - 1)) ||
                ((isFieldOnBoard(i - 2, j + 2, board) && board.board[i - 2][j + 2].getColor() == 0) && checkNeighbour(board, oppositeColor, i - 1, j + 1)) ||
                ((isFieldOnBoard(i + 2, j - 2, board) && board.board[i + 2][j - 2].getColor() == 0) && checkNeighbour(board, oppositeColor, i + 1, j - 1)));
    }

    private boolean checkNeighbour(Board board, int oppositeColor, int i, int j) {
        return (isFieldOnBoard(i, j, board) && board.board[i][j].getColor() == oppositeColor);

    }


    public void movementPhase(Board board) {
        ArrayList<int[]> inDangerFields = checkForBattle(board, switchPlayer);
        if (inDangerFields.size() != 0) {
            movementHit(board, inDangerFields);
        } else {
            makeMove(board);
        }

    }

    private void makeMove(Board board) {
        int x,y;
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

    private void movementHit(Board board, ArrayList<int[]> inDangerFields) {
        System.out.println("You must move with these Fields first: ");
        for (int i = 0; i < inDangerFields.size(); i++) {
            convertCoordinates(inDangerFields.get(i));
        }
        boolean isValid = false;
        while (!isValid) {
            int[] coordinates = playersMove(board, switchPlayer);
            Pawn pawn = board.board[coordinates[0]][coordinates[1]];
            for (int i = 0; i < inDangerFields.size(); i++) {
                if (inDangerFields.get(i)[0] == coordinates[0] && inDangerFields.get(i)[1] == coordinates[1]) {
                    ArrayList<int[]> possibleMoves = possibleMoves(coordinates, board);
                    displayPossibleMoves(possibleMoves);
                    int[] getCoordinates = getCoordinates(possibleMoves);
                    pawn.setX(getCoordinates[0]);
                    pawn.setY(getCoordinates[1]);
                    board.board[coordinates[0]][coordinates[1]] = board.board[getCoordinates[0]][getCoordinates[1]];
                    board.board[getCoordinates[0]][getCoordinates[1]] = pawn;
                    board.board[getCoordinates[2]][getCoordinates[3]].setColor(0);
                    board.board[getCoordinates[2]][getCoordinates[3]].setActive(false);
                    if (checkForBattle(board, switchPlayer).size() != 0) {
                        movementPhase(board);
                    }
                    isValid = true;
                }
            }
        }
    }

    public boolean hasWon(Board board){
        for(int i=0; i<board.board.length; i++){
            for(int j=0; j<board.board.length; j++){
                if(board.board[i][j].getColor() == switchPlayer)
                    return false;
            }
        }
        System.out.println("Player "+switchPlayer+" has Lost");
        return true;
    }
    public void switchPlayerFunc() {
        if (switchPlayer == 1) {
            switchPlayer = 2;
        } else {
            switchPlayer = 1;
        }
    }

}
