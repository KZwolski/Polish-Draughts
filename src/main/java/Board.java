import java.util.ArrayList;
import java.util.List;

public class Board {
    public Object[][] board;

    public Board(int size) {
        board = new Object[size][size];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < size; j++) {
                if(i%2==0){
                    if(j%2!=0) {
                        Pawn pawn = new Pawn(1,i,j,false);
                        board[i][j] = pawn.getColor();
                    }
                }else{
                    if(j%2==0){
                        Pawn pawn = new Pawn(1,i,j,false);
                        board[i][j] = pawn.getColor();
                    }
                }
            }
        }
        for (int i = size-4; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if(i%2==0){
                    if(j%2!=0) {
                        Pawn pawn = new Pawn(2,i,j,false);
                        board[i][j] = pawn.getColor();

                    }
                }else{
                    if(j%2==0){
                        Pawn pawn = new Pawn(2,i,j,false);
                        board[i][j] = pawn.getColor();
                    }
                }
            }
        }

    }

    public void printBoard() {

        for (int i = 1; i <= this.board.length; i++) {
            if(i<10){
                System.out.print(i + "  ");
            }else {
                System.out.print(i + " ");
            }

            for (int j = 0; j < this.board[i - 1].length; j++) {
                System.out.print(this.board[i - 1][j]);
            }
            System.out.println();
        }
        System.out.print("   ");
        for (int i = 1; i <= board.length; i++) {
            System.out.print(" " + (char) (64 + i) + " ");
        }

    }

    public int[][] fillBoard(int size) {
        int[][] pawns = new int[size][size];
        return pawns;
    }

