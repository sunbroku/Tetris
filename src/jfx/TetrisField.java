package jfx;

public class TetrisField {

    //Field is Occupied when a non player controlled block is settled on the spot

    private boolean isOccupied = false;

    private TetrisBlock block;

    public boolean getOccupied() {
        return isOccupied;
    }

    public void moveBlock() {
        if(block != null) {
            block.moveY(1);
        }
    }

    public TetrisBlock getBlock() {
        return block;
    }

    public void switchOccupied(TetrisBlock b) {
        block = b;
        if(block == null) {
            isOccupied = false;
        }else {
            isOccupied = true;
        }
    }
}
