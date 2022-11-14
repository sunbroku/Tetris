package jfx;

public class TetrisBlock {
    private final javafx.scene.paint.Color blockColor;
    private int locationX;
    private int offsetToCenterX;
    private int offsetToCenterY;
    private int locationY;

    TetrisBlock(javafx.scene.paint.Color c, int x, int y, int offx, int offy) {
        this.blockColor = c;
        locationY = y;
        locationX = x;
        offsetToCenterX = offx;
        offsetToCenterY = offy;
    }

    public void moveX(int i) {
        locationX += i;
    }

    public void moveY(int i) {
        locationY += i;
    }

    public void setX(int x) {
        locationX = x;
    }

    public void setOffsetToCenterX(int x) {
        offsetToCenterX = x;
    }

    public void setY(int y) {
        locationY = y;
    }

    public void setOffsetToCenterY(int y) {
        offsetToCenterY = y;
    }

    public int getX() {
        return locationX;
    }

    public int getOffsetToCenterX() {
        return offsetToCenterX;
    }

    public int getY() {
        return locationY;
    }

    public int getOffsetToCenterY() {
        return offsetToCenterY;
    }

    public javafx.scene.paint.Color getBlockColor() {
        return blockColor;
    }
}
