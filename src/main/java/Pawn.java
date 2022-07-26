public class Pawn {
    private String color;
    private int X;
    private int Y;
    private boolean isCrowned;

    public Pawn(String color, int x, int y, boolean isCrowned) {
        this.color = color;
        X = x;
        Y = y;
        this.isCrowned = isCrowned;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
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
}
