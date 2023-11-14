package jfx;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;


public class TetrisView {

    Stage stage;
    private final int rows;
    private final int columns;
    Rectangle [][] boardSquares;
    Rectangle [][] previewSquares;
    Text scoreText;
    Text scorePoints;
    private final MenuItem nGame = new MenuItem("New Game");
    private final MenuItem mExit = new MenuItem("Exit Game");

    TetrisView(Stage primaryStage, int r, int c) {

        rows = r;
        columns = c;
        stage = primaryStage;

        BorderPane bPane = new BorderPane();
        GridPane gPane = new GridPane();
        GridPane rightPane = new GridPane();
        GridPane previewPane = new GridPane();
        GridPane scorePane = new GridPane();
        HBox box = new HBox();

        Menu menu = new Menu("Menu");
        menu.getItems().addAll(nGame,mExit);
        MenuBar mBar = new MenuBar();
        mBar.getMenus().add(menu);
        box.getChildren().add(mBar);

        bPane.setStyle("-fx-background-color: Blue");
        gPane.setStyle("-fx-background-color: Blue");
        gPane.setHgap(1);
        gPane.setVgap(1);
        gPane.setPadding(new Insets(15,7.5,15,15));
        gPane.setAlignment(Pos.TOP_CENTER);
        previewPane.setStyle("-fx-background-color: Blue");
        previewPane.setHgap(1);
        previewPane.setVgap(1);
        previewPane.setPadding(new Insets(15,15,15,7.5));
        previewPane.setAlignment(Pos.TOP_CENTER);

        bPane.setTop(box);
        bPane.setCenter(gPane);

        rightPane.add(previewPane,0,0);
        rightPane.add(scorePane,0,1);
        bPane.setRight(rightPane);
        Scene scene = new Scene(bPane);

        boardSquares = new Rectangle[rows][columns];
        for(int i=0; i<rows; i++) {
            for(int j=0;j<columns;j++) {
                Rectangle slot = new Rectangle();
                slot.setHeight(30);
                slot.setWidth(30);

                slot.setFill(Color.DARKBLUE);
                boardSquares[(i)][j] = slot;
                gPane.add(slot,j,i);
                GridPane.setHalignment(slot, HPos.CENTER);
            }
        }
        previewSquares = new Rectangle[4][4];
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++) {
                Rectangle slot = new Rectangle();
                slot.setHeight(30);
                slot.setWidth(30);

                slot.setFill(Color.DARKBLUE);
                previewSquares[i][j] = slot;
                previewPane.add(slot,j,i);
                GridPane.setHalignment(slot, HPos.CENTER);
            }
        }

        scoreText = new Text();
        scoreText.setText("Score: ");
        scoreText.setFill(Color.WHITE);
        scoreText.setStyle("-fx-font: 24 arial;");

        scorePoints = new Text();
        scorePoints.setText("0");
        scorePoints.setFill(Color.WHITE);
        scorePoints.setStyle("-fx-font: 24 arial;");

        scorePane.setStyle("-fx-background-color: Blue");
        scorePane.add(scoreText,0,0);
        scorePane.add(scorePoints,1,0);

        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        primaryStage.setResizable(false);
    }

    List<MenuItem> getMenuItems() {
        List<MenuItem> menuItems = new ArrayList<>();
        menuItems.add(nGame);
        menuItems.add(mExit);
        return menuItems;
    }

    public void updatePlayerBlock(TetrisCompound tc) {
        //Gameover crash here because NullPointerException
        for(TetrisBlock block : tc.getBlockParts()) {
            boardSquares[block.getY()][block.getX()].setFill(block.getBlockColor());
        }
    }

    public void resetOldField(TetrisCompound tc) {
        for(TetrisBlock block : tc.getBlockParts()) {
            boardSquares[block.getY()][block.getX()].setFill(Color.DARKBLUE);
        }
    }

    public void updateBoard(TetrisField[][] board) {
        for(int i = rows - 1; i >= 0; i--) {
            for(int j = 0; j < columns; j++) {
                colorBlock(board[i][j].getBlock(),i,j);
            }
        }
    }

    public void colorBlock(TetrisBlock block, int y, int x) {
        if(block == null) {
            boardSquares[y][x].setFill(Color.DARKBLUE);
        }else {
            boardSquares[block.getY()][block.getX()].setFill(block.getBlockColor());
        }
    }

    public void setScore(int score) {
        scorePoints.setText(Integer.toString(score));
    }

    public void updatePreview(TetrisCompound tc) {
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++) {
                previewSquares[i][j].setFill(Color.DARKBLUE);
            }
        }
        for(TetrisBlock block : tc.getBlockParts()) {
            previewSquares[block.getY()+1][block.getX()-3].setFill(block.getBlockColor());
        }
    }
}
