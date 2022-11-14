package jfx;

import java.util.Observable;

public class TetrisModel extends Observable {

    private TetrisField[][] board; // [r][c]
    private TetrisBlockFactory blockFactory;
    private int rows;
    private int columns;

    private TetrisCompound playerActiveBlocks;
    private boolean nextPlayerBlockNeeded;

    TetrisModel(int r, int c) {
        setBoard(r, c);
    }

    private void setBoard(int r, int c) {
        board = new TetrisField[r][c];
        for (int i = 0; i < c; i++) {
            for (int j = 0; j < r; j++) {
                board[j][i] = new TetrisField();
            }
        }
        rows = r;
        columns = c;
        blockFactory = new TetrisBlockFactory();
    }

    TetrisModel restartGame() {
        return new TetrisModel(rows, columns);
    }

    TetrisCompound getNextBlock() {
        playerActiveBlocks = blockFactory.getNextBlock();
        for(TetrisBlock block : playerActiveBlocks.getBlockParts()) {
            if(board[block.getY()][block.getX()].getOccupied()) {
                //Game has ended, notify!!!
                return null;
            }
        }
        nextPlayerBlockNeeded = false;
        return playerActiveBlocks;
    }

    public TetrisCompound getPlayerActiveBlocks() {
        return playerActiveBlocks;
    }

    public void moveDown() {
        boolean doesMoveCollide = false;
        for(TetrisBlock block : playerActiveBlocks.getBlockParts()) {
            if(block.getY() + 1 == rows) {
                for(TetrisBlock blocks : playerActiveBlocks.getBlockParts()) {
                    board[blocks.getY()][blocks.getX()].switchOccupied(blocks);
                }
                doesMoveCollide = true;
                break;
            }else if (board[block.getY() + 1][block.getX()].getOccupied()) {
                for(TetrisBlock blocks : playerActiveBlocks.getBlockParts()) {
                    board[blocks.getY()][blocks.getX()].switchOccupied(blocks);
                }
                doesMoveCollide = true;
                break;
            }
        }
        if(!doesMoveCollide) {
            for (TetrisBlock block : playerActiveBlocks.getBlockParts()) {
                block.moveY(1);
            }
        }else {
            nextPlayerBlockNeeded = true;
        }
    }

    public void moveLeft() {
        boolean data = true;
        for(TetrisBlock block : playerActiveBlocks.getBlockParts()) {
            if (block.getX() == 0 || board[block.getY()][block.getX() - 1].getOccupied()) {
                data = false;
                break;
            }
        }
        if(data) {
            for(TetrisBlock block : playerActiveBlocks.getBlockParts()) {
                block.moveX(-1);
            }
        }
    }

    public void moveRight() {
        boolean data = true;
        for(TetrisBlock block : playerActiveBlocks.getBlockParts()) {
            if (block.getX() == columns - 1 || board[block.getY()][block.getX() + 1].getOccupied()) {
                data = false;
                break;
            }
        }
        if(data) {
            for(TetrisBlock block : playerActiveBlocks.getBlockParts()) {
                block.moveX(1);
            }
        }
    }

    public void turnClock() {
        if(isTurnValid(0)) {
            playerActiveBlocks.turnClockwise();
        }
    }

    public void turnCounterClock() {
        if(isTurnValid(1)) {
            playerActiveBlocks.turnCounterClockwise();
        }
    }

    boolean isTurnValid(int direction) {
        if(direction == 0) {
            for(TetrisBlock block : playerActiveBlocks.getBlockParts()) {
                int rotationPosX = playerActiveBlocks.getClockRotationX(block);
                int rotationPosY = playerActiveBlocks.getClockRotationY(block);
                if(((rotationPosX > columns - 1 || rotationPosX < 0)|| (rotationPosY > rows - 1 || rotationPosY < 0)) || board[rotationPosY][rotationPosX].getOccupied()) {
                    return false;
                }
            }
            return true;
        }else {
            for(TetrisBlock block : playerActiveBlocks.getBlockParts()) {
                int rotationPosX = playerActiveBlocks.getCounterClockRotationX(block);
                int rotationPosY = playerActiveBlocks.getCounterClockRotationY(block);
                if(((rotationPosX > columns - 1 || rotationPosX < 0)|| (rotationPosY > rows - 1 || rotationPosY < 0)) || board[rotationPosY][rotationPosX].getOccupied()) {
                    return false;
                }
            }
            return true;
        }
    }

    public boolean isNextPlayerBlockNeeded() {
        return nextPlayerBlockNeeded;
    }

    public void scanForTetrisLine() {
        boolean isTetris = false;
        for(int i = rows - 1; i >= 0; i--) {
            for(int j = 0; j < columns; j++) {
                if(board[i][j].getOccupied()) {
                    isTetris = true;
                }else {
                    isTetris = false;
                    break;
                }
            }
            if(isTetris) {
                collapseLine(i);
                isTetris = false;
                i++;
            }
        }
    }

    public void collapseLine(int row) {
        for(int i = 0; i < columns; i++) {
            board[row][i].switchOccupied(null);
        }
        cascadeMove(row);
    }

    public void cascadeMove(int row) {
        for(int i = row; i >= 1; i--) {
            for(int j = 0; j < columns; j++) {
                board[i-1][j].moveBlock();
                board[i][j].switchOccupied(board[i-1][j].getBlock());
                board[i-1][j].switchOccupied(null);
            }
        }
    }

    public TetrisField[][] getBoard() {
        return board;
    }

    public TetrisCompound getPreviewBlock() {
        return blockFactory.getPreviewPiece();
    }

}