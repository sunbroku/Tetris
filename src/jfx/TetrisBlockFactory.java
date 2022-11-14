package jfx;

import java.util.Random;

public class TetrisBlockFactory {

    TetrisCompound nextPiece;
    TetrisCompound previewPiece;

    TetrisBlockFactory() {
        Random rnd = new Random();
        nextPiece = build(rnd.nextInt(7));
        previewPiece = build(rnd.nextInt(7));
    }

    public TetrisCompound build(int i) {
        switch (i) {
            case 0:
                return buildI();
            case 1:
                return buildJ();
            case 2:
                return buildL();
            case 3:
                return buildO();
            case 4:
                return buildS();
            case 5:
                return buildT();
            case 6:
                return buildZ();
            default:
                return buildI();
        }
    }

    /*public TetrisCompound buildI() {
        //Length 4 i-block, doesn't work with the way rotation is handled
        TetrisBlock [] iBlock = new TetrisBlock[4];
        int[] xCoord = {3,4,5,6};
        int[] yCoord = {0,0,0,0};
        for(int i = 0; i < 4; i++) {
            iBlock[i] = new TetrisBlock(javafx.scene.paint.Color.CYAN, xCoord[i], yCoord[i]);
        }
        return new TetrisCompound(iBlock);
    }*/

    public TetrisCompound buildI() {
        TetrisBlock [] iBlock = new TetrisBlock[3];
        int[] xCoord = {3,4,5};
        int[] yCoord = {0,0,0};
        int[] offsetToCenterX = {-1,0,1};
        int[] offsetToCenterY = {0,0,0};
        for(int i = 0; i < 3; i++) {
            iBlock[i] = new TetrisBlock(javafx.scene.paint.Color.CYAN, xCoord[i], yCoord[i], offsetToCenterX[i], offsetToCenterY[i]);
        }
        return new TetrisCompound(iBlock,1,0);
    }

    public TetrisCompound buildJ() {
        TetrisBlock [] jBlock = new TetrisBlock[4];
        int[] xCoord = {4,4,5,6};
        int[] yCoord = {0,1,1,1};
        int[] offsetToCenterX = {-1,-1,0,1};
        int[] offsetToCenterY = {-1,0,0,0};
        for(int i = 0; i < 4; i++) {
            jBlock[i] = new TetrisBlock(javafx.scene.paint.Color.BROWN, xCoord[i], yCoord[i], offsetToCenterX[i], offsetToCenterY[i]);
        }
        return new TetrisCompound(jBlock,2,0);
    }

    public TetrisCompound buildL() {
        TetrisBlock [] lBlock = new TetrisBlock[4];
        int[] xCoord = {3,4,5,5};
        int[] yCoord = {1,1,1,0};
        int[] offsetToCenterX = {-1,0,1,1};
        int[] offsetToCenterY = {0,0,0,-1};
        for(int i = 0; i < 4; i++) {
            lBlock[i] = new TetrisBlock(javafx.scene.paint.Color.ORANGE, xCoord[i], yCoord[i], offsetToCenterX[i], offsetToCenterY[i]);
        }
        return new TetrisCompound(lBlock,1,0);
    }

    public TetrisCompound buildO() {
        TetrisBlock [] oBlock = new TetrisBlock[4];
        int[] xCoord = {4,5,4,5};
        int[] yCoord = {0,0,1,1};
        int[] offsetToCenterX = {0,0,0,0};
        int[] offsetToCenterY = {0,0,0,0};
        for(int i = 0; i < 4; i++) {
            oBlock[i] = new TetrisBlock(javafx.scene.paint.Color.YELLOW, xCoord[i], yCoord[i], offsetToCenterX[i], offsetToCenterY[i]);
        }
        return new TetrisCompound(oBlock,0,1);
    }

    public TetrisCompound buildS() {
        TetrisBlock [] sBlock = new TetrisBlock[4];
        int[] xCoord = {4,5,5,6};
        int[] yCoord = {1,1,0,0};
        int[] offsetToCenterX = {-1,0,0,1};
        int[] offsetToCenterY = {0,0,-1,-1};
        for(int i = 0; i < 4; i++) {
            sBlock[i] = new TetrisBlock(javafx.scene.paint.Color.GREEN, xCoord[i], yCoord[i], offsetToCenterX[i], offsetToCenterY[i]);
        }
        return new TetrisCompound(sBlock,1,0);
    }

    public TetrisCompound buildT() {
        TetrisBlock [] tBlock = new TetrisBlock[4];
        int[] xCoord = {4,5,5,6};
        int[] yCoord = {1,1,0,1};
        int[] offsetToCenterX = {-1,0,0,1};
        int[] offsetToCenterY = {0,0,-1,0};
        for(int i = 0; i < 4; i++) {
            tBlock[i] = new TetrisBlock(javafx.scene.paint.Color.PURPLE, xCoord[i], yCoord[i], offsetToCenterX[i], offsetToCenterY[i]);
        }
        return new TetrisCompound(tBlock,1,0);
    }

    public TetrisCompound buildZ() {
        TetrisBlock [] zBlock = new TetrisBlock[4];
        int[] xCoord = {3,4,4,5};
        int[] yCoord = {0,0,1,1};
        int[] offsetToCenterX = {-1,0,0,1};
        int[] offsetToCenterY = {-1,-1,0,0};
        for(int i = 0; i < 4; i++) {
            zBlock[i] = new TetrisBlock(javafx.scene.paint.Color.RED, xCoord[i], yCoord[i], offsetToCenterX[i], offsetToCenterY[i]);
        }
        return new TetrisCompound(zBlock,2,0);
    }

    public TetrisCompound getPreviewPiece() {
        return previewPiece;
    }

    public TetrisCompound getNextBlock() {
        Random rnd = new Random();
        TetrisCompound next = nextPiece;
        nextPiece = previewPiece;
        previewPiece = build(rnd.nextInt(7));
        return next;
    }
}
