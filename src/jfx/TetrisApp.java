package jfx;

import javafx.stage.Stage;
import javafx.application.Application;

public class TetrisApp extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        int rows = 20;
        int columns = 10;
        TetrisModel model = new TetrisModel(rows,columns);
        TetrisView view = new TetrisView(primaryStage,rows,columns);
        new TetrisController(model,view);
        primaryStage.setTitle("Tetris");
        primaryStage.show();
    }
}
