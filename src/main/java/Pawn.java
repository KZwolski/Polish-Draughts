public class Pawn {
    private int color;
    private int x;
    private int y;
    private boolean isCrowned;
    private boolean isActive;
    private int squareColor;

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public int getSquareColor() {
        return squareColor;
    }

    public void setSquareColor(int squareColor) {
        this.squareColor = squareColor;
    }

    public Pawn(int color, int x, int y, boolean isCrowned, boolean isActive, int squareColor) {
        this.color = color;
        this.x = x;
        this.y = y;
        this.isCrowned = isCrowned;
        this.isActive = isActive;
        this.squareColor = squareColor;
    }

    public Pawn() {
    }

    @Override
    public String toString() {
        return "color=" + color;
    }

    public String toStringCords() {
        return "X= " + x + "Y= " + y;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
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
