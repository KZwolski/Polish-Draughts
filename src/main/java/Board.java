public class Board {
    private int whiteSquare = 4;
    private int blackSquare = 8;
    public Pawn[][] board;

    public Board(int size) {
        board = new Pawn[size][size];
        initEmptyBoard(size);
        initPawns(0, 4, size, 1);
        initPawns(size - 4, size, size, 2);

    }

    private void initPawns(int b, int x, int size, int color) {
        for (int i = b; i < x; i++) {
            for (int j = 0; j < size; j++) {
                if (i % 2 == 0) {
                    if (j % 2 != 0) {
                        Pawn pawn = new Pawn(color, i, j, false, true, board[i][j].getSquareColor());
                        board[i][j] = pawn;
                    }
                } else {
                    if (j % 2 == 0) {
                        Pawn pawn = new Pawn(color, i, j, false, true, board[i][j].getSquareColor());
                        board[i][j] = pawn;
                    }
                }
            }
        }
    }

    private void initEmptyBoard(int size) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i % 2 == 0) {
                    if (j % 2 != 0) {
                        board[i][j] = new Pawn(whiteSquare);
                    } else {
                        board[i][j] = new Pawn(blackSquare);
                    }
                } else {
                    if (j % 2 == 0) {
                        board[i][j] = new Pawn(whiteSquare);
                    } else {
                        board[i][j] = new Pawn(blackSquare);
                    }
                }
            }
        }
    }

    public void printBoard() {

        for (int i = 1; i <= this.board.length; i++) {
            if (i < 10) {
                System.out.print(i + "  ");
            } else {
                System.out.print(i + " ");
            }

            for (int j = 0; j < this.board[i - 1].length; j++) {
                if (board[i - 1][j].getColor() == 1) {
                    System.out.print(" ¤ ");
                } else if (board[i - 1][j].getColor() == 2) {
                    System.out.print(" ■ ");
                } else if (board[i - 1][j].getSquareColor() == 8) {
                    System.out.print("███");
                } else if (board[i - 1][j].getSquareColor() == 4) {
                    System.out.print("   ");
                }

            }
            System.out.println();
        }
        System.out.print("   ");
        for (int i = 1; i <= board.length; i++) {
            System.out.print(" " + (char) (64 + i)+ " ");
        }
        System.out.println();

    }

    public int[][] fillBoard(int size) {
        int[][] pawns = new int[size][size];
        return pawns;
    }



}