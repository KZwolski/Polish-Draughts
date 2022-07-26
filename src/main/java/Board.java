
public class Board {
    public String[][] board;

    public Board(int size) {
        board = new String[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i % 2 == 0) {
                    if (j % 2 == 0) {
                        board[i][j] = "[ ]";
                    } else {
                        board[i][j] = "[" + String.valueOf((char) (35))+ "]";
                    }
                } else {
                    if (j % 2 == 0) {

                        board[i][j] = "[" + String.valueOf((char) (35))+ "]";
                    } else {
                        board[i][j] = "[ ]";
                    }

                }
            }
        }

    }

    public void printBoard() {

        for (int i = 1; i <= this.board.length; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < this.board[i - 1].length; j++) {
                System.out.print(this.board[i - 1][j]);
            }
            System.out.println();
        }
        System.out.print("  ");
        for (int i = 1; i <= board.length; i++) {
            System.out.print(" " + (char) (64 + i) + " ");
        }

    }

}
