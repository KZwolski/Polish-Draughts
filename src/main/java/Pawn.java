public class Pawn {
    private int color;
    private int X;
    private int Y;
    private boolean isCrowned;

    public Pawn(int color, int x, int y, boolean isCrowned) {
        this.color = color;
        X = x;
        Y = y;
        this.isCrowned = isCrowned;
    }

    @Override
    public String toString() {
        return "color=" + color;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getX() {
        return X;
    }

    public void setX(int x) {
        X = x;
    }

    public int getY() {
        return Y;
    }

    public void setY(int y) {
        Y = y;
    }

    public boolean isCrowned() {
        return isCrowned;
    }

    public void setCrowned(boolean crowned) {
        isCrowned = crowned;
    }

    public boolean moveValidation (int X, int Y, String[][] board) {

        return false;
    }
}
