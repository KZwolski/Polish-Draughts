import java.util.Scanner;
import java.util.Locale;
public class Movement {
    public String askForInput () {
        Scanner cords = new Scanner(System.in);
        System.out.println("Please enter coordinates: ");
        String move = cords.nextLine().toUpperCase(Locale.ROOT);
        return move;
    }
    public boolean checkForValidInput(String input, int size) {
        try{
            int column = convertInputIntoColumn(input);
            int row = convertInputIntoRow(input);
            return ((column <= size)&&(row <= size));
        }
        catch (Exception e){
            return false;
        }
    }

    public int convertInputIntoColumn(String input){
        int row = input.charAt(0) - 'A';
        return row;
    }
    public int convertInputIntoRow(String input){
        int column = Integer.parseInt(input.substring(1))- 1;
        return column;
    }
    public int[] playersMove(Board board, int player){
        while(true){
            String input = askForInput();
            if(checkForValidInput(input, board.board.length)){
               int column = convertInputIntoColumn(input);
               int row = convertInputIntoRow(input);
               if(board.board[row][column].isActive() && board.board[row][column].getColor() == player){
                   int coordinates[] = new int[2];
                   coordinates[0]=row;
                   coordinates[1]=column;
                   return coordinates;
               }
           }
        }
    }
    public int askForBoardSize (){
        while(true) {
            System.out.println("Please enter board size (must be an even number between 10-20): ");
            Scanner scanner = new Scanner(System.in);
            int size = scanner.nextInt();
            if ((size >= 10 && size <= 20) && size % 2 == 0) {
                return size;
            }
        }

    }


}
