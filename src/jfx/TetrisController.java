package jfx;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;

import static java.lang.Thread.sleep;

public class TetrisController implements Runnable{
    TetrisModel model;
    TetrisView view;
    Scene activeScene;
    boolean progFlag;
    Thread activeThread;


    public TetrisController(TetrisModel model,TetrisView view) {
        this.model = model;
        this.view = view;
        activeScene = this.view.stage.getScene();
        setActions();
        nGame();
        activeThread = new Thread(this);
        activeThread.start();
    }

    public void setActions() {
        view.getMenuItems().get(0).setOnAction(ev -> restart());
        view.getMenuItems().get(1).setOnAction(ev -> exitGame());
        activeScene.setOnKeyReleased(event -> {
            if(event.getCode() == KeyCode.S) {
                playerMoveDown();
            }else if(event.getCode() == KeyCode.A) {
                view.resetOldField(model.getPlayerActiveBlocks());
                model.moveLeft();
                view.updatePlayerBlock(model.getPlayerActiveBlocks());
            }else if(event.getCode() == KeyCode.D) {
                view.resetOldField(model.getPlayerActiveBlocks());
                model.moveRight();
                view.updatePlayerBlock(model.getPlayerActiveBlocks());
            }else if(event.getCode() == KeyCode.Q) {
                view.resetOldField(model.getPlayerActiveBlocks());
                model.turnCounterClock();
                view.updatePlayerBlock(model.getPlayerActiveBlocks());
            }else if(event.getCode() == KeyCode.E) {
                view.resetOldField(model.getPlayerActiveBlocks());
                model.turnClock();
                view.updatePlayerBlock(model.getPlayerActiveBlocks());
            }
        });
    }

    public void nextPlayerBlock() {
        TetrisCompound playerBlock = model.getNextBlock();
        TetrisCompound previewBlock = model.getPreviewBlock();
        view.updatePreview(previewBlock);
        view.updatePlayerBlock(playerBlock);
    }

    void restart() {
        activeScene = this.view.stage.getScene();
        activeThread.interrupt();
        setActions();
        nGame();
        activeThread = new Thread(this);
        activeThread.start();
    }

    public void nGame() {
        this.model = model.restartGame();
        view.updateBoard(this.model.getBoard());
        progFlag = true;
        nextPlayerBlock();
    }

    public void exitGame() {
        progFlag = false;
        activeThread.interrupt();
        Platform.exit();
    }

    public void playerMoveDown() {
        view.resetOldField(model.getPlayerActiveBlocks());
        model.moveDown();
        view.updatePlayerBlock(model.getPlayerActiveBlocks());
        if (model.isNextPlayerBlockNeeded()) {
            TetrisCompound blocks = model.getNextBlock();
            if(blocks == null) {
                //game ended
                restart();
            }else {
                model.scanForTetrisLine();
                view.updateBoard(model.getBoard());
                view.updatePlayerBlock(model.getNextBlock());
                view.updatePreview(model.getPreviewBlock());
            }
        }
    }

    public boolean threadMoveDown() {
        view.resetOldField(model.getPlayerActiveBlocks());
        model.moveDown();
        view.updatePlayerBlock(model.getPlayerActiveBlocks());
        if (model.isNextPlayerBlockNeeded()) {
            TetrisCompound blocks = model.getNextBlock();
            if(blocks == null) {
                //game ended
                return false;
            }else {
                model.scanForTetrisLine();
                view.updateBoard(model.getBoard());
                view.updatePlayerBlock(model.getNextBlock());
                view.updatePreview(model.getPreviewBlock());
            }
        }
        return true;
    }

    public void run() {
        while(progFlag) {
            try {
                sleep(2000);
                progFlag = threadMoveDown();
            } catch (InterruptedException e) {
                return;
            }
        }
        restart();
    }
}
