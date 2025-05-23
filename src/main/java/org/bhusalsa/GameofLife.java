package org.bhusalsa;

import java.util.Random;

public class GameofLife {

    static int population = 0;
    static int generation = 0;
    static int width = 50;
    static int height = 35;
    static int[][] board_state;
    public static void main(String[] args) throws InterruptedException {

        //int width = 150;
        //int height = 25;
        //int[][] board_state;

        board_state = random_state(width,height);
        //render(board_state);
        int[][] next_state = board_state;
        while(true) {
            generation += 1;
            render(next_state);
            next_state = next_board_state(next_state);
            Thread.sleep(300);
            //render(board_state);
        }
    }

    static int[][] random_state(int width, int height){
        //Alive - 1
        //Dead - 0
        int[][] state = dead_state(width,height);
        int cell_state;

        Random random = new Random();

        for(int y = 0 ; y < width; y++)
        {
            for(int x = 0; x < height; x++)
            {
                double randomNum = random.nextDouble();

                if (randomNum >= 0.87)
                    cell_state = 1;
                else
                    cell_state = 0;

                state[x][y] = cell_state;
            }
        }

        return state;

    }

    static int[][] dead_state(int width, int height){

        int[][] board = new int[height][width];
        for(int x = 0; x < height; x++)
        {
            for(int y = 0 ; y < width; y++)
            {
                board[x][y] = 0;
            }
        }
        return board;
    }

    static int[][] next_board_state(int[][] boardState){
        int[][] editBoard = dead_state(boardState[0].length, boardState.length);

        /*
        boardState[x][y]
        boardState[x][y-1] left cell
        boardState[x][y+1] right cell
        boardState[x-1][y] top cell
        boardState[x+1][y] bottom cell
        boardState[x-1][y-1] top left cell
        boardState[x-1][y+1] top right cell
        boardState[x+1][y+1] bottom right cell
        boardState[x+1][y-1] bottom left cell

        Any live cell with 0 or 1 live neighbors becomes dead,
         because of underpopulation
         if sum of all < 1 then die (except our cell)

        Any live cell with 2 or 3 live neighbors stays alive,
         because its neighborhood is just right
         if 1<sum<3 then alive

        Any live cell with more than 3 live neighbors becomes dead,
        because of overpopulation
        if sum >= 3 then die

        Any dead cell with exactly 3 live
        neighbors becomes alive, by reproduction
        if sum = 3, then alive

         */

        for (int x = 0 ; x < editBoard.length; x++){
            int sum = 0;
            for(int y = 0; y<editBoard[0].length; y++){
                // check for alive cells in neighbouring cells

                if (y == 0 && x==0){
                    sum = (boardState[x][y + 1] + boardState[x + 1][y] + boardState[x + 1][y + 1]);

                } else if (y == (editBoard[0].length -1) && x == 0 ){
                    sum = (boardState[x][y - 1] + boardState[x + 1][y] + boardState[x + 1][y - 1]);

                } else if (x == (editBoard.length-1) && y== 0) {
                    sum = (boardState[x][y + 1] + boardState[x - 1][y] + boardState[x - 1][y + 1]);

                } else if (x == (editBoard.length -1) && y == (editBoard[0].length - 1) ) {
                    sum = (boardState[x][y - 1] + boardState[x - 1][y] + boardState[x - 1][y - 1]);

                } else if (x == 0 && ( y >= 1 && y <= (editBoard[0].length - 2) )) {
                    sum = boardState[x][y-1] + boardState[x][y+1] + boardState[x+1][y] +
                            boardState[x+1][y+1] + boardState[x+1][y-1];

                } else if (y == 0 && ( x >= 1 && x <= (editBoard.length - 2))) {
                    sum = boardState[x][y+1] + boardState[x-1][y] + boardState[x+1][y] +
                            boardState[x-1][y+1] + boardState[x+1][y+1];

                } else if ( x == (editBoard.length - 1) && ( y >= 1 && y <= (editBoard[0].length - 2) ) ) {
                    sum = boardState[x][y-1] + boardState[x][y+1] + boardState[x-1][y] +
                            boardState[x-1][y-1] + boardState[x-1][y+1];

                } else if ((y == editBoard[0].length -1) && (x >= 1 && x <= (editBoard.length -1))) {
                    sum = boardState[x][y-1] + boardState[x-1][y] + boardState[x+1][y] +
                            boardState[x-1][y-1] + boardState[x+1][y-1];

                } else if( (x >= 1 && y >= 1) || (x <= (editBoard.length-2) &&  y <= (editBoard[0].length-2))) {
                    sum = (boardState[x][y - 1] + boardState[x][y + 1] + boardState[x - 1][y] + boardState[x + 1][y] +
                            boardState[x - 1][y - 1] + boardState[x - 1][y + 1] + boardState[x + 1][y + 1] + boardState[x + 1][y - 1]);
                }

                if (boardState[x][y] == 1) {
                    if (sum < 1) {
                        editBoard[x][y] = 0;
                    } else if (sum == 2 || sum == 3) {
                        editBoard[x][y] = 1;
                    } else if (sum > 3) {
                        editBoard[x][y] = 0;
                    }
                } else {
                    if (sum == 3) {
                        editBoard[x][y] = 1;
                    }
                }
            }

        }

        //render(editBoard);
        return editBoard;

    }

    static void print_board(int[][] board, int width, int height){

        System.out.print("[");
        for(int x = 0 ; x < width; x++)
        {
            System.out.print("[");
            for(int y = 0; y < height; y++)
            {
                System.out.print(board[x][y]);
                System.out.print(",");
            }
            System.out.print("], ");
            System.out.println();
        }
        System.out.print("]");
    }

    static void render(int[][] board_state) {
        System.out.println("-".repeat(board_state[0].length + 4) + " Gen: " + generation );
        StringBuilder stringBuilder = new StringBuilder("");
        for(int x = 0; x < board_state.length; x++)
        {
            stringBuilder.setLength(0);
            stringBuilder.append("| ");
            for(int y = 0 ; y < (board_state[0].length); y++)
            {
                //System.out.print(board_state[x][y]);
                if (board_state[x][y] == 1) {
                    //System.out.print("#");
                    stringBuilder.append("\u25AE"); //"\u25AE"
                }else {
                    //System.out.print(" ");
                    stringBuilder.append(" ");  //"\u25A1"
                }
            }
            stringBuilder.append(" |");
            System.out.println(stringBuilder.toString());
            //System.out.println();
        }
        System.out.println("-".repeat(board_state[0].length + 4));
    }

    static int getWidth() {
        return width;
    }

    static int getHeight(){
        return height;
    }

    static int[][] getBoard_state()
    {
        return random_state(width,height);
    }
}
