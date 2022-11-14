package jfx;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TetrisCompound {

    private final int center;
    private final int rotationType;

    private final List<TetrisBlock> blockParts;

    public TetrisCompound(TetrisBlock[] blocks, int c, int type) {
        blockParts = new ArrayList<>();
        blockParts.addAll(Arrays.asList(blocks));
        center = c;
        rotationType = type;
    }

    public List<TetrisBlock> getBlockParts() {
        return blockParts;
    }

    public void turnClockwise() {
        switch(rotationType) {
            case 0:
                blockParts.replaceAll(this::rotateClock);
                break;
            case 1:
                //o rotation
                break;
            case 2:
                //i rotation
            default:
        }
    }

    public TetrisBlock rotateCounterClock(TetrisBlock block) {
        int x = block.getOffsetToCenterY();
        int y = -1 * block.getOffsetToCenterX();
        block.setX(x + blockParts.get(center).getX());
        block.setY(y + blockParts.get(center).getY());
        block.setOffsetToCenterX(x);
        block.setOffsetToCenterY(y);
        return block;
    }

    public int getCounterClockRotationX(TetrisBlock block) {
        return block.getOffsetToCenterY() + blockParts.get(center).getX();
    }

    public int getCounterClockRotationY(TetrisBlock block) {
        return -1 * block.getOffsetToCenterX() + blockParts.get(center).getY();
    }

    public void turnCounterClockwise() {
        switch(rotationType) {
            case 0:
                blockParts.replaceAll(this::rotateCounterClock);
                break;
            case 1:
                //o rotation
                break;
            case 2:
                //i rotation
                break;
            default:
        }
    }

    public int getClockRotationX(TetrisBlock block) {
        return -1 * block.getOffsetToCenterY() + blockParts.get(center).getX();
    }

    public int getClockRotationY(TetrisBlock block) {
        return block.getOffsetToCenterX() + blockParts.get(center).getY();
    }

    public TetrisBlock rotateClock(TetrisBlock block) {
        int x = -1 * block.getOffsetToCenterY();
        int y = block.getOffsetToCenterX();
        block.setX(x + blockParts.get(center).getX());
        block.setY(y + blockParts.get(center).getY());
        block.setOffsetToCenterX(x);
        block.setOffsetToCenterY(y);
        return block;
    }
}
