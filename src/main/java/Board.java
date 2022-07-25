
public class Board {
    public String[][] board;

    public Board(int size) {
        board = new String[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = "0";
            }
        }

    }

    public void printBoard() {
        for (int i = 0; i < this.board.length; i++) {
            for (int j = 0; j < this.board[i].length; j++) {
                System.out.print(this.board[i][j]+ " ");
            }
            System.out.println();
        }

    }

}
