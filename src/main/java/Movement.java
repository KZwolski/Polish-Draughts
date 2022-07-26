import java.util.Scanner;

public class Movement {
    public int[] askForInput () {

        Scanner scanner = new Scanner(System.in);
        int inputX = scanner.nextInt();
        int inputY = scanner.nextInt();
        return new int[]{inputX, inputY};
    }


}
