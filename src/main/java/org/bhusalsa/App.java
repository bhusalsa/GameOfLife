package org.bhusalsa;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;


/**
 * JavaFX App
 */
public class App extends Application {

    private int columns;// = 150;
    private int rows;// = 25;
    private int[][] colorState;
    final int cellSize = 20;
    Rectangle[][] cellArray;// = new Rectangle[rows][columns];

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Create a GridPane
        setRows();
        setColumns();
        initiateRectangle();
        initiateColor();
        GridPane grid = createGrid();
        final int cellSize = 10;

        //grid = updateGrid(grid);

        //nextState();

//        Rectangle[][] cellArray = new Rectangle[rows][columns];
//
//        for (int row = 0; row < rows; row++) {
//            for (int col = 0; col < columns; col++) {
//                Rectangle cell = new Rectangle(cellSize, cellSize);
//                cell.setFill(Color.WHITE);
//                cell.setStroke(Color.BLACK);
//
//                Rectangle blackCell = new Rectangle(cellSize,cellSize);
//               //blackCell.setStroke(Color.WHITE);
//                blackCell.setFill(Color.BLACK);
//
//                if(colorState[row][col] == 0) {
//                    grid.add(cell, col, row);
//                    cellArray[row][col] = cell; // Store reference
//                }else
//                {
//                    grid.add(blackCell, col, row);
//                    cellArray[row][col] = cell; // Store reference
//                }
//            }
//        }

        // To change cell at (13, 75) to black
//        Rectangle oldCell = cellArray[13][75];
//        grid.getChildren().remove(oldCell); // Remove old white cell
//
//        Rectangle blackCell = new Rectangle(cellSize, cellSize);
//        blackCell.setFill(Color.BLACK);
//        blackCell.setStroke(Color.WHITE);
//        grid.add(blackCell, 75, 13); // Add new cell
//        cellArray[13][75] = blackCell; // Update reference


        // Create a scene and add the grid to it
        Scene scene = new Scene(grid, columns * cellSize, rows * cellSize); // Adjust scene size to fit grid
        primaryStage.setTitle("Conway's Game of Life");
        primaryStage.setScene(scene);
        primaryStage.show();

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(700), event -> {
            updateGrid(grid);
        }));
        timeline.setCycleCount(Animation.INDEFINITE); // repeat forever
        timeline.play();
    }

    private GridPane updateGrid(GridPane grid){

        int[][] nextState = nextState();


        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {

                if(colorState[row][col] != nextState[row][col]){
                    Rectangle oldCell = cellArray[row][col];
                    grid.getChildren().remove(oldCell);

                    Rectangle cell = new Rectangle(cellSize, cellSize);
                    cell.setFill(Color.WHITE);
                    //cell.setStroke(Color.BLACK);

                    Rectangle blackCell = new Rectangle(cellSize,cellSize);
                    blackCell.setFill(Color.BLACK);

                    if(nextState[row][col] == 0) {
                        grid.add(cell, col, row);
                        cellArray[row][col] = cell; // Store reference
                    }else
                    {
                        grid.add(blackCell, col, row);
                        cellArray[row][col] = blackCell; // Store reference
                    }
                }
            }
        }

        colorState = nextState;
        return grid;
        //        Rectangle oldCell = cellArray[13][75];
//        grid.getChildren().remove(oldCell); // Remove old white cell
//
//        Rectangle blackCell = new Rectangle(cellSize, cellSize);
//        blackCell.setFill(Color.BLACK);
//        blackCell.setStroke(Color.WHITE);
//        grid.add(blackCell, 75, 13); // Add new cell
//        cellArray[13][75] = blackCell; // Update reference

    }

    private GridPane createGrid(){
        GridPane grid = new GridPane();
        final int cellSize = 10;

        //nextState();

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                Rectangle cell = new Rectangle(cellSize, cellSize);
                cell.setFill(Color.WHITE);
                //cell.setStroke(Color.BLACK);

                Rectangle blackCell = new Rectangle(cellSize,cellSize);
                //blackCell.setStroke(Color.WHITE);
                blackCell.setFill(Color.BLACK);

                if(colorState[row][col] == 0) {
                    grid.add(cell, col, row);
                    cellArray[row][col] = cell; // Store reference
                }else
                {
                    grid.add(blackCell, col, row);
                    cellArray[row][col] = blackCell; // Store reference
                }
            }
        }

        return grid;
    }

    private int[][] nextState(){

        return GameofLife.next_board_state(colorState);
    }

    private void initiateColor(){
        int width;
        int height;

        width = GameofLife.getWidth();
        height = GameofLife.getHeight();

        colorState = new int[width][height];
        colorState = GameofLife.getBoard_state();
    }

    private void setRows(){
        rows = GameofLife.getHeight();
    }

    private void setColumns(){
        columns = GameofLife.getWidth();
    }

    private void initiateRectangle(){
        cellArray = new Rectangle[rows][columns];
    }

}