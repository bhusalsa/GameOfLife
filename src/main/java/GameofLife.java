package main.java;

import java.util.Random;

public class GameofLife {

    int[][] random_state;
    public static void main(String[] args) {

        int width = 160;
        int height = 20;
        int[][] board_state;

//        board_state = dead_state(width,height);
//        render(board_state);
//        print_board(board_state,width,height);
//        System.out.println();

        board_state = random_state(width,height);
        //print_board(board_state,width,height);
        render(board_state);

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

                if (randomNum >= 0.5)
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

    static void render(int[][] board_state)
    {
        System.out.println("-".repeat(board_state[0].length + 4));
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
                    stringBuilder.append("#");
                }else {
                    //System.out.print(" ");
                    stringBuilder.append(" ");
                }
            }
            stringBuilder.append(" |");
            System.out.println(stringBuilder.toString());
            //System.out.println();
        }
        System.out.println("-".repeat(board_state[0].length + 4));
    }
}
